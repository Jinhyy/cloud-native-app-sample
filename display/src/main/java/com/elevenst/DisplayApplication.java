package com.elevenst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker   // # = @EnableHystrix
@EnableCaching
public class DisplayApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean   // 폴백 요청 실패시, 캐싱된 데이터를 반환 하게 하기위하여 만듬.
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("products");
    }

    public static void main(String[] args) {
        SpringApplication.run(DisplayApplication.class);
    }

}
