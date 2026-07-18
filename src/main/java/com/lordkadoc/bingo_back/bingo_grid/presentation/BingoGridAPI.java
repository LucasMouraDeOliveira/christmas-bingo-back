package com.lordkadoc.bingo_back.bingo_grid.presentation;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Bingo Grids", description = "Bingo grid creation and retrieval endpoints")
public interface BingoGridAPI {

        @GetMapping("players/{playerId}/bingo-grid")
        @Operation(summary = "Get bingo grid", description = "Retrieve the bingo grid belonging to a player.")
        BingoGridDTO getBingoGrid(
                        @Parameter(description = "UUID of the player", required = true) @PathVariable UUID playerId);

        @PostMapping("players/{playerId}/bingo-grid")
        @Operation(summary = "Create bingo grid", description = "Create a new random 3x3 bingo grid for the player.")
        @ApiResponse(responseCode = "200", description = "Bingo grid created successfully")
        BingoGridDTO createBingoGrid(
                        @Parameter(description = "UUID of the player", required = true) @PathVariable UUID playerId);

        @PostMapping("players/{playerId}/bingo-grid/tasks/{taskIndex}")
        @Operation(summary = "Update task completion", description = "Update the completion status of a task in the player's bingo grid.")
        @ApiResponse(responseCode = "200", description = "Task completion status updated successfully")
        void updateTaskCompletion(
                        @Parameter(description = "UUID of the player", required = true) @PathVariable UUID playerId,
                        @Parameter(description = "Index of the task in the bingo grid", required = true) @PathVariable int taskIndex,
                        @Parameter(description = "New completion status of the task", required = true) @RequestBody TaskCompletionRequest request);

}
