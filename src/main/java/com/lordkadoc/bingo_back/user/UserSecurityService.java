package com.lordkadoc.bingo_back.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lordkadoc.bingo_back.player.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.playerRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
