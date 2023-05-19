package com.novoakademia.chatappbackend.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novoakademia.chatappbackend.TestConfig;
import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.User.UserFacade;
import com.novoakademia.chatappbackend.User.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    UserFacade facade;

    @Test
    void httpGet_returnsAllUsers() {
        // given
        int initial = facade.findAll().size();
        facade.save(new User("test1", "test1@gmail.com", "test",  false, false));
        facade.save(new User("test2", "test2@gmail.com", "test",  false, false));

        User[] result = restTemplate.getForObject("http://localhost:" + port + "/users", User[].class);

        assertThat(result).isInstanceOf(User[].class);
        assertThat(result.length).isEqualTo(initial + 2);
    }

    @Test
    void httpGet_returnUserWithGivenId() {
        String id = facade.save(new User("test1", "test1@gmail.com", "test",  false, false)).getUserId();

        User result = restTemplate.getForObject("http://localhost:" + port + "/users/" + id, User.class);

        assertThat(result).isInstanceOf(User.class);
        assertThat(result.getUserName()).isEqualTo("test1");
    }
    @Test
    void httpPost_returnsSavedUser() {
        String url = "http://localhost:" + port + "/users";
        var taskJsonObject = getJsonObject("test", "test@gmail.com", "test", false, false);
        HttpEntity<String> httpEntity = getHttpEntity(taskJsonObject);
        User result = restTemplate.postForObject(url, httpEntity, User.class);

        assertThat(result).isInstanceOf(User.class);
        assertThat(result.getUserName()).isEqualTo("test");
    }



    private static JSONObject getJsonObject(String userName, String email, String password, boolean isAdmin, boolean isBanned) {
        var taskJsonObject = new JSONObject();
        try {
            taskJsonObject.put("userName", userName);
            taskJsonObject.put("email", email);
            taskJsonObject.put("password", password);
            taskJsonObject.put("admin", isAdmin);
            taskJsonObject.put("banned", isBanned);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return taskJsonObject;
    }

    private static HttpEntity<String> getHttpEntity(JSONObject taskJsonObject) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(taskJsonObject.toString(), headers);
    }
}