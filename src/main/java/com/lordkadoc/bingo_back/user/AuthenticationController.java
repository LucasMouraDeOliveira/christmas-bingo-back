package com.lordkadoc.bingo_back.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lordkadoc.bingo_back.player.CreatePlayerDTO;
import com.lordkadoc.bingo_back.player.PlayerDTO;
import com.lordkadoc.bingo_back.player.PlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    private final PlayerService playerService;

    private final UserSecurityService userSecurityService;

    @PostMapping("login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginRequest.name(), loginRequest.password()
        ));

        UserDetails details = (UserDetails)auth.getPrincipal();
        String token = jwtService.generateToken(details);

        return new AuthResponse(token);
    }

    @PostMapping("register")
    @Operation(summary = "Create a player", description = "Create a new player if the provided name is not already taken.")
    @ApiResponse(responseCode = "200", description = "Player created successfully")
    public AuthResponse register(@RequestBody CreatePlayerDTO createPlayerRequest) {
        PlayerDTO playerDTO = playerService.createPlayer(createPlayerRequest.name(), createPlayerRequest.password());

        UserDetails details = userSecurityService.loadUserByUsername(playerDTO.name());
        String token = jwtService.generateToken(details);
        return new AuthResponse(token);
    }
    
}
