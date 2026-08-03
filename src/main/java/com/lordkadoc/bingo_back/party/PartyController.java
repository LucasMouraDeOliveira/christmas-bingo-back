package com.lordkadoc.bingo_back.party;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lordkadoc.bingo_back.player.Player;
import com.lordkadoc.bingo_back.player.PlayerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("parties")
@RequiredArgsConstructor
public class PartyController {

    private final PlayerRepository playerRepository;

    private final PartyService partyService;

    @PostMapping
    public PartyDTO createParty(Authentication auth, @RequestBody CreatePartyDTO createPartyDTO) {
        return partyService.createParty(auth.getName(), createPartyDTO);
    }

    @GetMapping
    public List<PartyDTO> getPlayerParties(Authentication auth) {
        Player player = playerRepository.findByName(auth.getName()).orElseThrow();
        return partyService.getPlayerParties(player);
    }

    @GetMapping("{partyId}")
    public PartyDTO getParty(@PathVariable UUID partyId) {
        return partyService.findParty(partyId).orElseThrow();
    }

    @PostMapping("{partyId}/players")
    public PartyDTO addPlayerToParty(@PathVariable UUID partyId, @RequestBody AddPlayerDTO addPlayerDTO) {
        // TODO check that the authenticated player is the owner of the party
        Player player = playerRepository.findById(addPlayerDTO.playerId()).orElseThrow();
        return partyService.addPlayerToParty(partyId, player);
    }

}
