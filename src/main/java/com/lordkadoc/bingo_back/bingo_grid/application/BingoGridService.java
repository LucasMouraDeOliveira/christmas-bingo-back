package com.lordkadoc.bingo_back.bingo_grid.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lordkadoc.bingo_back.bingo_grid.domain.BingoGrid;
import com.lordkadoc.bingo_back.bingo_grid.domain.BingoTask;
import com.lordkadoc.bingo_back.bingo_grid.infrastructure.BingoGridRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BingoGridService {

    private final BingoGridRepository bingoGridRepository;
    private final BingoTaskSelectionService bingoTaskSelectionService;

    @Transactional
    public Optional<BingoGridDTO> getBingoGridForPlayer(UUID playerId) {
        return bingoGridRepository.findLastByPlayerId(playerId).map(this::toDto);
    }

    public BingoGridDTO createBingoGridForPlayer(UUID playerId) {
        List<BingoTask> selectedTasks = bingoTaskSelectionService.selectRandomTasks();
        BingoGrid bingoGrid = new BingoGrid();
        bingoGrid.setPlayerId(playerId);
        bingoGrid.setTasks(selectedTasks);
        bingoGridRepository.save(bingoGrid);

        return toDto(bingoGrid);
    }

    private BingoGridDTO toDto(BingoGrid bingoGrid) {
        List<BingoTaskDTO> taskDtos = bingoGrid.getTasks().stream()
                .map(task -> new BingoTaskDTO(task.getTitle(), task.getDescription(), task.getPoints(), task.isCompleted()))
                .toList();
        return new BingoGridDTO(taskDtos);
    }

    @Transactional
    public void updateTaskCompletion(UUID playerId, int taskIndex, boolean completed) {
        BingoGrid bingoGrid = bingoGridRepository.findLastByPlayerId(playerId).orElseThrow();
        List<BingoTask> tasks = bingoGrid.getTasks();
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).setCompleted(completed);
            bingoGridRepository.save(bingoGrid);
        }
    }
}
