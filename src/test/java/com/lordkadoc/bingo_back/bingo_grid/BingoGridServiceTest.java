package com.lordkadoc.bingo_back.bingo_grid;

import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridDTO;
import com.lordkadoc.bingo_back.bingo_grid.application.BingoGridService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BingoGridServiceTest {

    @Autowired
    private BingoGridService bingoGridService;

    @Test
    void createBingoGridCreatesNineTasksForPlayer() {
        BingoGridDTO grid = bingoGridService.createBingoGridForPlayer(UUID.randomUUID());

        assertThat(grid.tasks()).hasSize(9);
        assertThat(grid.tasks()).allSatisfy(task -> assertThat(task.title()).isNotBlank());
    }
}
