package com.example.khtbe.global.config.security;

import com.example.khtbe.global.config.FilterConfig;
import com.example.khtbe.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends SecurityConfigurerAdapter {


    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .cors()

                .and()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .antMatchers("/exercise/qr").permitAll()
                .antMatchers("/ws/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtTokenProvider))
                .and().build();

    }

    @Configuration
    @EnableWebSocketMessageBroker
    public class WebSocketSecurityConfig implements WebSocketMessageBrokerConfigurer {
        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/exercise/qr").setAllowedOrigins("**").withSockJS();
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry config) {
            config.enableSimpleBroker("/topic");
            config.setApplicationDestinationPrefixes("/app");
        }

        @Override
        public void configureClientInboundChannel(ChannelRegistration registration) {
            registration.interceptors(new ChannelInterceptor() {
                @Override
                public Message<?> preSend(Message<?> message, MessageChannel channel) {
                    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                     //CONNECT 명령을 확인합니다.
                    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                        // "Authorization" 헤더를 확인합니다.
                        List<String> authorizationHeaders = accessor.getNativeHeader("Authorization");
                        // "Authorization" 헤더가 존재하고, JWT 토큰이 유효한 경우에만 인증 절차를 수행합니다.
                        if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
                            String token = authorizationHeaders.get(0).substring(7);
                            if (!jwtTokenProvider.validateToken(token)) {
                                throw new AuthenticationServiceException("Invalid JWT token");
                            }
                        }
                    }
                    return message;
                }
            });
        }
    }
}