package com.jeffrey.orderapi.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
class AES256KeyLoader {

    @Value("${aes.key}")
    private String key;

    @Value("${aes.iv}")
    private String iv;

    @PostConstruct
    public void initAES() {
        AES256Util.init(key, iv);
    }
}