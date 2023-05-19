package com.novoakademia.chatappbackend;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.test.context.TestConfiguration;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:h2:mem:testdb") // Użyj pamięci podręcznej H2 jako bazy danych testowej
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }
}