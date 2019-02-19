package com.elevenst.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {


    private final String url= "http://product/products/";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CacheManager cacheManager;

    @Override
    @CachePut("products")   // 캐시맵 products에 메소드 호출결과가 저장된다.
    @HystrixCommand(fallbackMethod = "getProductInfoFallback")
    public String getProductInfo(String productId) {
    /*    try{
            Thread.sleep(2000);
        }   catch(InterruptedException e){
            e.printStackTrace();
        }
    */

        //throw new RuntimeException("runtime error");
        return restTemplate.getForObject(url + productId,String.class);
    }

    @Override
    public String getProductName(String productId) {
        return restTemplate.getForObject(url + productId,String.class);
    }

    public String getProductInfoFallback(String productId, Throwable t){
        System.out.println(t);
        //Cache.ValueWrapper w = cacheManager.getCache("products").get(productId);
        // 실패한 요청의 productId에 관한 메소드 호출결과를 얻어온다.
       // System.out.println(productId + " fail cache info : " + w.get().toString());
        return "Fallback : getProductInfoFallback Called [ " + productId + " ]";
    }
}
