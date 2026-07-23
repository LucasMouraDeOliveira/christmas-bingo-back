package com.lordkadoc.bingo_back.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final Random random = new Random();

    private final TaskRepository taskRepository;

    public List<Task> selectRandomTasks() {
        List<Task> tasks = taskRepository.findAll();
        Collections.shuffle(tasks, random);
        return new ArrayList<>(tasks.subList(0, Math.min(9, tasks.size())));
    }
    
}
