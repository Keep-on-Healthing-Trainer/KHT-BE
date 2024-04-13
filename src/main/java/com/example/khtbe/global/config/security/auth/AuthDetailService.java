package com.example.khtbe.global.config.security.auth;

import com.example.khtbe.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        return userRepository.findByUserId(userId)
                .map(AuthDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다!"));

    }
}