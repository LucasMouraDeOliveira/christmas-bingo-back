package com.lordkadoc.bingo_back.bingo_grid.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lordkadoc.bingo_back.bingo_grid.domain.BingoGrid;

public interface BingoGridRepository extends JpaRepository<BingoGrid, UUID> {

    @Query("SELECT bg FROM BingoGrid bg WHERE bg.playerId = :playerId ORDER BY bg.createdAt DESC LIMIT 1")
    Optional<BingoGrid> findLastByPlayerId(UUID playerId);
}
