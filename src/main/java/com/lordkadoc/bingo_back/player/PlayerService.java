package com.lordkadoc.bingo_back.player;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public List<PlayerDTO> listPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> new PlayerDTO(player.getId(), player.getName()))
                .toList();
    }

    public PlayerDTO findPlayerById(UUID playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        return new PlayerDTO(player.getId(), player.getName());
    }

    public PlayerDTO findPlayerByName(String playerName) {
        Player player = playerRepository.findByName(playerName)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        return new PlayerDTO(player.getId(), player.getName());
    }

    public PlayerDTO createPlayer(String name, String password) {
        if (playerRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        Player player = new Player();
        player.setName(name);
        player.setPassword(passwordEncoder.encode(password));
        player = playerRepository.save(player);
        return new PlayerDTO(player.getId(), player.getName());
    }
}
