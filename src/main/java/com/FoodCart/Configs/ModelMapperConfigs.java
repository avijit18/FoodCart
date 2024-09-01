package com.FoodCart.Configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfigs {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
