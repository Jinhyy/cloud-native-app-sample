package com.elevenst.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@EnableDiscoveryClient
public class ProductController {

    @Autowired
    Environment environment;

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable String productId){
        System.out.println("current server port : " + environment.getProperty("local.server.port"));
        return "product id = " + productId +
                " at "  + System.currentTimeMillis();
    }

    @GetMapping(path="/name/{productId}")
    public String getProductName(@PathVariable String productId){
        return "product name = " + productId + " name ";
    }
}
