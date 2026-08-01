package com.lordkadoc.bingo_back.party;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.lordkadoc.bingo_back.player.PlayerDTO;

public record PartyDTO(UUID id, String name, Instant createdAt, PlayerDTO organizer, List<PlayerDTO> players, int maxPlayers) {}
