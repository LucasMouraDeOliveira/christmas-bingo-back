package com.lordkadoc.bingo_back.bingo_grid;

import com.lordkadoc.bingo_back.bingo_grid.domain.BingoGrid;
import com.lordkadoc.bingo_back.bingo_grid.domain.BingoTask;
import com.lordkadoc.bingo_back.bingo_grid.infrastructure.BingoGridRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BingoGridRepositoryTest {

    @Autowired
    private BingoGridRepository bingoGridRepository;

    @Test
    @Transactional
    void saveAndLoadBingoGridWithEmbeddedTasks() {
        BingoGrid bingoGrid = new BingoGrid();
        bingoGrid.setPlayerId(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        BingoTask task1 = new BingoTask();
        task1.setTitle("Buy gifts");
        task1.setDescription("Prepare presents");
        task1.setPoints(10);
        task1.setCompleted(false);
        bingoGrid.setTasks(List.of(task1));

        BingoGrid saved = bingoGridRepository.saveAndFlush(bingoGrid);

        BingoGrid loaded = bingoGridRepository.findById(saved.getId()).orElseThrow();

        assertThat(loaded.getPlayerId()).isEqualTo(bingoGrid.getPlayerId());
        assertThat(loaded.getTasks()).hasSize(1);
        assertThat(loaded.getTasks().get(0).getTitle()).isEqualTo("Buy gifts");
    }
}
