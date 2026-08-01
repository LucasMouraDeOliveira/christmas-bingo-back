package com.lordkadoc.bingo_back.bingo_grid.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lordkadoc.bingo_back.bingo_grid.domain.BingoGrid;
import com.lordkadoc.bingo_back.party.Party;
import com.lordkadoc.bingo_back.player.Player;

public interface BingoGridRepository extends JpaRepository<BingoGrid, UUID> {

    @Query("SELECT bg FROM BingoGrid bg WHERE bg.player = :player AND bg.party = :party ORDER BY bg.createdAt DESC LIMIT 1")
    Optional<BingoGrid> findLastByPartyAndPlayer(Party party, Player player);
}
