package com.lordkadoc.bingo_back.party;

import java.util.List;

import com.lordkadoc.bingo_back.tasks.CreateTaskDTO;

public record CreatePartyDTO(String name, int maxPlayers, int gridSize, List<CreateTaskDTO> tasks, boolean includeDefaultTasks) {
    public CreatePartyDTO {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        } else if (maxPlayers <= 0) {
            throw new IllegalArgumentException("Max players must be greater than 0");
        } else if (maxPlayers > 10) {
            throw new IllegalArgumentException("Max players must be less than or equal to 10");
        } else if (gridSize < 3) {
            throw new IllegalArgumentException("Grid size must be greater than or equal to 3");
        } else if (gridSize > 5) {
            throw new IllegalArgumentException("Grid size must be less than or equal to 5");
        } else if(tasks.size() < gridSize*gridSize && !includeDefaultTasks) {
            throw new IllegalArgumentException("Not enough tasks");
        }
    }
}
