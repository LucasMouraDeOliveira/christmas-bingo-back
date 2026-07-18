package com.lordkadoc.bingo_back.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Test
    void createPlayerPersistsAndListsPlayers() {
        PlayerDTO created = playerService.createPlayer("Alice");

        assertThat(created.id()).isNotNull();
        assertThat(created.name()).isEqualTo("Alice");
        assertThat(playerService.listPlayers()).extracting(PlayerDTO::name).contains("Alice");
    }
}
