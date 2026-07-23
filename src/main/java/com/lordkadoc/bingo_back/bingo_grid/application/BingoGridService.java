package com.lordkadoc.bingo_back.bingo_grid.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lordkadoc.bingo_back.bingo_grid.domain.BingoGrid;
import com.lordkadoc.bingo_back.bingo_grid.domain.BingoTask;
import com.lordkadoc.bingo_back.bingo_grid.infrastructure.BingoGridRepository;
import com.lordkadoc.bingo_back.tasks.Task;
import com.lordkadoc.bingo_back.tasks.TaskService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BingoGridService {

    private final BingoGridRepository bingoGridRepository;
    private final TaskService taskService;

    @Transactional
    public Optional<BingoGridDTO> getBingoGridForPlayer(UUID playerId) {
        return bingoGridRepository.findLastByPlayerId(playerId).map(this::toDTO);
    }

    @Transactional
    public BingoGridDTO createBingoGridForPlayer(UUID playerId) {
        List<BingoTask> selectedTasks = taskService.selectRandomTasks()
                .stream()
                .map(t -> new BingoTask(null, t, false))
                .toList();
        BingoGrid bingoGrid = new BingoGrid();
        bingoGrid.setPlayerId(playerId);
        bingoGrid.setTasks(selectedTasks);
        bingoGridRepository.save(bingoGrid);

        return toDTO(bingoGrid);
    }

    private BingoGridDTO toDTO(BingoGrid bingoGrid) {
        List<BingoTaskDTO> taskDtos = bingoGrid.getTasks().stream()
                .map(this::toDTO)
                .toList();
        return new BingoGridDTO(taskDtos);
    }

    private BingoTaskDTO toDTO(BingoTask bingoTask) {
        Task task = bingoTask.getTask();
        return new BingoTaskDTO(task.getTitle(), task.getDescription(), task.getPoints(), bingoTask.isCompleted());
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
