package com.lordkadoc.bingo_back.leaderboard;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LeaderboardController implements LeaderboardAPI {

    private final LeaderboardService leaderboardService;
    
    @Override
    public List<LeaderboardEntryDTO> getLeaderboard() {
        return leaderboardService.getLeaderboard();
    }
    
}
