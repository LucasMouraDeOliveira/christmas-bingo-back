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
                .map(this::toDTO)
                .toList();
    }

    public List<PlayerDTO> searchPlayers(String nameLike) {
        return playerRepository.findAllByNameContaining(nameLike).stream()
                .map(this::toDTO)
                .toList();
    }

    public PlayerDTO findPlayerById(UUID playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        return toDTO(player);
    }

    public PlayerDTO findPlayerByName(String playerName) {
        Player player = playerRepository.findByName(playerName)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        return toDTO(player);
    }

    public PlayerDTO createPlayer(String name, String password) {
        if (playerRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        Player player = new Player();
        player.setName(name);
        player.setPassword(passwordEncoder.encode(password));
        player = playerRepository.save(player);
        return toDTO(player);
    }

    public PlayerDTO toDTO(Player player) {
        return new PlayerDTO(player.getId(), player.getName());
    }
}
