package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.UserFacade;
import com.novoakademia.chatappbackend.User.UserRepository;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupFacade;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupRepository;
import com.novoakademia.chatappbackend.message.MessageFacade;
import com.novoakademia.chatappbackend.message.MessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfiguration {
    @Bean
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    UserFacade userFacade(final UserRepository userRepository) {
        return new UserFacade(userRepository);
    }

    @Bean
    ChatGroupFacade chatGroupFacade(final ChatGroupRepository chatGroupRepository) {
        return new ChatGroupFacade(chatGroupRepository);
    }

    @Bean
    MessageFacade messageFacade(final MessageRepository messageRepository, final ChatGroupRepository chatGroupRepository, final UserRepository userRepository ) {
        return new MessageFacade(messageRepository, userRepository, chatGroupRepository);
    }

}
