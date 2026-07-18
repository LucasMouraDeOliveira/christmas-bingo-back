package com.lordkadoc.bingo_back.player;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Players", description = "Player management endpoints")
@RequestMapping("players")
public interface PlayerAPI {

    @GetMapping
    @Operation(summary = "List players", description = "Retrieve all registered players.")
    List<PlayerDTO> listPlayers();

    @GetMapping("{playerId}")
    @Operation(summary = "Get player", description = "Retrieve a player by their unique identifier.")
    PlayerDTO getPlayer(
            @Parameter(description = "UUID of the player to retrieve", required = true)
            @PathVariable UUID playerId);

    @PostMapping
    @Operation(summary = "Create player", description = "Create a new player if the provided name is not already taken.")
    @ApiResponse(responseCode = "200", description = "Player created successfully")
    PlayerDTO createPlayer(@RequestBody PlayerDTO playerDto);
}
