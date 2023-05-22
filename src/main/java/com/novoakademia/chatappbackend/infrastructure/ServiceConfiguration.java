package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.UserFacade;
import com.novoakademia.chatappbackend.User.UserRepository;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupFacade;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    UserFacade userFacade(final UserRepository userRepository) {
        return new UserFacade(userRepository);
    }

    @Bean
    ChatGroupFacade chatGroupFacade(final ChatGroupRepository chatGroupRepository) {
        return new ChatGroupFacade(chatGroupRepository);
    }
}
