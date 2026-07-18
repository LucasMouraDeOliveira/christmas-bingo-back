package com.lordkadoc.bingo_back.bingo_grid.application;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Bingo grid payload containing the selected tasks for a player")
public record BingoGridDTO(@Schema(description = "List of bingo tasks in the grid") List<BingoTaskDTO> tasks) {}
