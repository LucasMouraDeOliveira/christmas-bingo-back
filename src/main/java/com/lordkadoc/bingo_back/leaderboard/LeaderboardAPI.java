package com.lordkadoc.bingo_back.leaderboard;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Leaderboard", description = "Leaderboard management endpoints")
public interface LeaderboardAPI {

    @GetMapping("parties/{partyId}/leaderboard")
    @Operation(summary = "Get leaderboard", description = "Retrieve the current leaderboard with players and their scores.")
    @ApiResponse(responseCode = "200", description = "Leaderboard retrieved successfully")
    List<LeaderboardEntryDTO> getLeaderboard(@PathVariable UUID partyId);
    
}
