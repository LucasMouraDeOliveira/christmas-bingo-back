package com.lordkadoc.bingo_back.leaderboard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridService;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoTaskDTO;
import com.lordkadoc.bingo_back.player.PlayerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final PlayerService playerService;

    private final BingoGridService bingoGridService;

    public List<LeaderboardEntryDTO> getLeaderboard() {
        return playerService.listPlayers().stream()
                .map(player -> new LeaderboardEntryDTO(player, this.getPlayerScore(player.id())))
                .sorted((entry1, entry2) -> Integer.compare(entry2.score(), entry1.score()))
                .toList();
    }

    private int getPlayerScore(UUID playerId) {
        Optional<BingoGridDTO> bingoGridOpt = bingoGridService.getBingoGridForPlayer(playerId);
        return bingoGridOpt.map(bingoGrid -> bingoGrid.tasks().stream()
                .filter(BingoTaskDTO::completed)
                .mapToInt(BingoTaskDTO::points)
                .sum())
                .orElse(0);
    }

}
