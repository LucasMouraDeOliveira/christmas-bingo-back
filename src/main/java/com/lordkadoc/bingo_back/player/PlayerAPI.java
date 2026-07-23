package com.lordkadoc.bingo_back.player;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Players", description = "Player management endpoints")
public interface PlayerAPI {

    @GetMapping("players")
    @Operation(summary = "List players", description = "Retrieve all registered players.")
    List<PlayerDTO> listPlayers();

    @GetMapping("players/{playerId}")
    @Operation(summary = "Get player", description = "Retrieve a player by their unique identifier.")
    PlayerDTO getPlayer(
            @Parameter(description = "UUID of the player to retrieve", required = true)
            @PathVariable UUID playerId);

}
