package com.lordkadoc.bingo_back.leaderboard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridService;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoTaskDTO;
import com.lordkadoc.bingo_back.party.Party;
import com.lordkadoc.bingo_back.party.PartyRepository;
import com.lordkadoc.bingo_back.player.Player;
import com.lordkadoc.bingo_back.player.PlayerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final PlayerService playerService;

    private final PartyRepository partyRepository;

    private final BingoGridService bingoGridService;

    @Transactional
    public List<LeaderboardEntryDTO> getLeaderboard(UUID partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow();
        return party.getPlayers().stream()
                .map(player -> new LeaderboardEntryDTO(playerService.toDTO(player), this.getPlayerScore(party, player)))
                .sorted((entry1, entry2) -> Integer.compare(entry2.score(), entry1.score()))
                .toList();
    }

    private int getPlayerScore(Party party, Player player) {
        Optional<BingoGridDTO> bingoGridOpt = bingoGridService.getBingoGrid(party, player);
        return bingoGridOpt.map(bingoGrid -> bingoGrid.tasks().stream()
                .filter(BingoTaskDTO::completed)
                .mapToInt(BingoTaskDTO::points)
                .sum())
                .orElse(0);
    }

}
