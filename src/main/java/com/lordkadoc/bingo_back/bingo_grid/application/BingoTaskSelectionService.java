package com.lordkadoc.bingo_back.bingo_grid.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordkadoc.bingo_back.bingo_grid.domain.BingoTask;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BingoTaskSelectionService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;
    private final Random random = new Random();

    public List<BingoTask> selectRandomTasks() {
        List<BingoTask> tasks = loadTasks();
        Collections.shuffle(tasks, random);
        return new ArrayList<>(tasks.subList(0, Math.min(9, tasks.size())));
    }

    private List<BingoTask> loadTasks() {
        Resource resource = resourceLoader.getResource("classpath:tasks.json");
        try (InputStream inputStream = resource.getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load bingo tasks", e);
        }
    }
}
