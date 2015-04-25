package com.company.kaliper.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"com.company.kaliper"})
public class RootConfig {

    @Resource
    private Environment env;
    
    
    
    
}