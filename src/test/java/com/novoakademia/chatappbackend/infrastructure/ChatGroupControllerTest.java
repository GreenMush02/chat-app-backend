package com.novoakademia.chatappbackend.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novoakademia.chatappbackend.TestConfig;
import com.novoakademia.chatappbackend.chatgroup.ChatGroup;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatGroupControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    ChatGroupFacade facade;

    @Test
    void httpGet_returnAllChatGroups () {
        int initial = facade.findAll().size();
        facade.save(new ChatGroup("Test"));
        facade.save(new ChatGroup("Test1"));

        ChatGroup[] result = restTemplate.getForObject("http://localhost:" + port + "/groups", ChatGroup[].class);

        assertThat(result)
                .isInstanceOf(ChatGroup[].class)
                .hasSize(initial + 2);
    }

    @Test
    void httpGet_returnGroupByGivenId () {

        facade.save(new ChatGroup("Test"));


    }
}