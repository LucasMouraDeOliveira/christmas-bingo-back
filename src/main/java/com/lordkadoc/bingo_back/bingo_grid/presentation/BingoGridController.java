package com.lordkadoc.bingo_back.bingo_grid.presentation;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridService;
import com.lordkadoc.bingo_back.player.PlayerDTO;
import com.lordkadoc.bingo_back.player.PlayerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BingoGridController implements BingoGridAPI {

    private final PlayerService playerService;

    private final BingoGridService bingoGridService;

    @Override
    public BingoGridDTO getBingoGrid(Authentication auth) {
        PlayerDTO player = playerService.findPlayerByName(auth.getName());
        return this.bingoGridService.getBingoGridForPlayer(player.id()).orElseThrow();
    }

    @Override
    public BingoGridDTO createBingoGrid(Authentication auth) {
        PlayerDTO player = playerService.findPlayerByName(auth.getName());
        return this.bingoGridService.createBingoGridForPlayer(player.id());
    }

    @Override
    public void updateTaskCompletion(Authentication auth, int taskIndex, TaskCompletionRequest request) {
        PlayerDTO player = playerService.findPlayerByName(auth.getName());
        this.bingoGridService.updateTaskCompletion(player.id(), taskIndex, request.completed());
    }
}
