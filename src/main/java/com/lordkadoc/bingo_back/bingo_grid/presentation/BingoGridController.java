package com.lordkadoc.bingo_back.bingo_grid.presentation;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridService;
import com.lordkadoc.bingo_back.party.Party;
import com.lordkadoc.bingo_back.party.PartyRepository;
import com.lordkadoc.bingo_back.player.Player;
import com.lordkadoc.bingo_back.player.PlayerRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("parties/{partyId}")
public class BingoGridController implements BingoGridAPI {

    private final PlayerRepository playerRepository;

    private final PartyRepository partyRepository;

    private final BingoGridService bingoGridService;

    @Override
    public BingoGridDTO getBingoGrid(@PathVariable UUID partyId, Authentication auth) {
        Player player = playerRepository.findByName(auth.getName()).orElseThrow();
        Party party = partyRepository.findById(partyId).orElseThrow();
        return this.bingoGridService.getBingoGrid(party, player).orElseThrow();
    }

    @Override
    public BingoGridDTO createBingoGrid(@PathVariable UUID partyId, Authentication auth) {
        Player player = playerRepository.findByName(auth.getName()).orElseThrow();
        Party party = partyRepository.findById(partyId).orElseThrow();
        return this.bingoGridService.createBingoGridForPlayer(party, player);
    }

    @Override
    public void updateTaskCompletion(@PathVariable UUID partyId, Authentication auth, int taskIndex,
            TaskCompletionRequest request) {
        Player player = playerRepository.findByName(auth.getName()).orElseThrow();
        Party party = partyRepository.findById(partyId).orElseThrow();
        this.bingoGridService.updateTaskCompletion(party, player, taskIndex, request.completed());
    }
}
