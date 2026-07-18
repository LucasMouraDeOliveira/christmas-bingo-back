package com.lordkadoc.bingo_back.bingo_grid.presentation;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BingoGridController implements BingoGridAPI {

    private final BingoGridService bingoGridService;

    @Override
    public BingoGridDTO getBingoGrid(UUID playerId) {
        return this.bingoGridService.getBingoGridForPlayer(playerId).orElseThrow();
    }

    @Override
    public BingoGridDTO createBingoGrid(UUID playerId) {
        return this.bingoGridService.createBingoGridForPlayer(playerId);
    }

    @Override
    public void updateTaskCompletion(UUID playerId, int taskIndex, TaskCompletionRequest request) {
        this.bingoGridService.updateTaskCompletion(playerId, taskIndex, request.completed());
    }
}
