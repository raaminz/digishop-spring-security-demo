package com.demisco.digishop.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.demisco.digishop.repository")
public class DataConfiguration {

}
