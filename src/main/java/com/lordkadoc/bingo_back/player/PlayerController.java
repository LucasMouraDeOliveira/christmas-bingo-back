package com.lordkadoc.bingo_back.player;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlayerController implements PlayerAPI {

    private final PlayerService playerService;

    @Override
    public List<PlayerDTO> listPlayers(String name) {
        if(StringUtils.isBlank(name)) {
            return this.playerService.listPlayers();
        } else {
            return this.playerService.searchPlayers(name);
        }
    }

    @Override
    public PlayerDTO getPlayer(UUID playerId) {
        return this.playerService.findPlayerById(playerId);
    }
    
    
}
