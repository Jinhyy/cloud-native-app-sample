package com.elevenst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker   // # = @EnableHystrix
@EnableCaching
@EnableHystrixDashboard
@EnableDiscoveryClient
public class DisplayApplication {

    @Bean
    @LoadBalanced // 리본설정
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean   // 폴백 요청 실패시, 캐싱된 데이터를 반환 하게 하기위하여 만듬.
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("products");
    }

    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DisplayApplication.class);
    }

}
