package com.lordkadoc.bingo_back.leaderboard;

import com.lordkadoc.bingo_back.player.PlayerDTO;

public record LeaderboardEntryDTO(PlayerDTO player, int score) {}
