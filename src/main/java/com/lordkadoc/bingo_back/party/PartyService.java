package com.lordkadoc.bingo_back.party;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lordkadoc.bingo_back.player.Player;
import com.lordkadoc.bingo_back.player.PlayerRepository;
import com.lordkadoc.bingo_back.player.PlayerService;
import com.lordkadoc.bingo_back.tasks.TaskService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;

    private final PlayerService playerService;

    private final TaskService taskService;

    private final PlayerRepository playerRepository;

    @Transactional
    public Optional<PartyDTO> findParty(UUID partyId) {
        var party = partyRepository.findById(partyId);
        return party.map(this::toDTO);
    }

    @Transactional
    public List<PartyDTO> getPlayerParties(Player player) {
        return partyRepository.findAllPlayerParties(player).stream().map(this::toDTO).toList();
    }

    @Transactional
    public PartyDTO createParty(String organizerName, CreatePartyDTO createPartyDTO) {
        Player player = this.playerRepository.findByName(organizerName).orElseThrow();

        Party party = new Party();
        party.setOrganizer(player);
        party.setName(createPartyDTO.name());
        party.setCreatedAt(Instant.now());
        party.setMaxPlayers(createPartyDTO.maxPlayers());
        party.setGridSize(createPartyDTO.gridSize());
        party.setIncludeDefaultTasks(createPartyDTO.includeDefaultTasks());
        party.getPlayers().add(player);

        party = partyRepository.save(party);

        taskService.createTasks(createPartyDTO.tasks(), party);

        return toDTO(party);
    }

    @Transactional
    public PartyDTO addPlayerToParty(UUID partyId, Player player) {
        Party party = partyRepository.findById(partyId).orElseThrow();
        if (party.getPlayers().size() >= party.getMaxPlayers()) {
            throw new IllegalStateException("Party is full");
        } else if(party.getPlayers().contains(player) || party.getOrganizer().equals(player)) {
            throw new IllegalStateException("Player is already in the party");
        }

        party.getPlayers().add(player);
        partyRepository.save(party);

        return toDTO(party);
    }

    private PartyDTO toDTO(Party party) {
        return new PartyDTO(
            party.getId(),
            party.getName(),
            party.getCreatedAt(),
            playerService.toDTO(party.getOrganizer()), 
            party.getPlayers().stream().map(playerService::toDTO).toList(), 
            party.getMaxPlayers(),
            party.getGridSize()
        );
    }
    
}
