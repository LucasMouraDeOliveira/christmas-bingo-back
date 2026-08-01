package com.lordkadoc.bingo_back.party;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lordkadoc.bingo_back.player.Player;

public interface PartyRepository extends JpaRepository<Party, UUID> {

    @Query("SELECT p from Party p JOIN p.players pl WHERE pl = :player OR p.organizer = :player")
    public List<Party> findAllPlayerParties(Player player);
    
}
