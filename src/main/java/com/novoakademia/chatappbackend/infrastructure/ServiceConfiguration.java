package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.UserFacade;
import com.novoakademia.chatappbackend.User.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    UserFacade userFacade(final UserRepository userRepository) {
        return new UserFacade(userRepository);
    }
}
