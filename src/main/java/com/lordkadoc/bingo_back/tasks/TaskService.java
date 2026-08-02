package com.lordkadoc.bingo_back.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lordkadoc.bingo_back.party.Party;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final Random random = new Random();

    private final TaskRepository taskRepository;

    @Transactional
    public void createTasks(List<CreateTaskDTO> tasks, Party party) {
        tasks.stream().forEach(task -> this.createTask(task, party));
    }

    private void createTask(CreateTaskDTO taskDTO, Party party) {
        Task task = new Task();
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setPoints(taskDTO.points());
        task.setParty(party);
        taskRepository.save(task);
    }

    public List<Task> selectRandomTasks(Party party) {
        List<Task> tasks;
        if(party.isIncludeDefaultTasks()) {
            tasks = taskRepository.findAllByPartyIncludingDefault(party);
        } else {
            tasks = taskRepository.findAllByParty(party);
        }
        Collections.shuffle(tasks, random);
        return new ArrayList<>(tasks.subList(0, Math.min(party.getGridSize() * party.getGridSize(), tasks.size())));
    }
    
}
