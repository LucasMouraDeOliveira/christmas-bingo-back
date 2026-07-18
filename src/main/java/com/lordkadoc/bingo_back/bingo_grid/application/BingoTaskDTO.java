package com.lordkadoc.bingo_back.bingo_grid.application;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A single bingo task displayed in the bingo grid")
public record BingoTaskDTO(
        @Schema(description = "Task title") String title,
        @Schema(description = "Task description") String description,
        @Schema(description = "Points awarded for completing this task") int points,
        @Schema(description = "Indicates if the task is already completed") boolean completed) {}
