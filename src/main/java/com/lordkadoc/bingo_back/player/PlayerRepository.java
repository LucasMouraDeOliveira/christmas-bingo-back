package com.lordkadoc.bingo_back.player;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Optional<Player> findByName(String name);
}
