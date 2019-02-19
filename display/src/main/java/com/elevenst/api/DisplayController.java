package com.elevenst.api;

import com.elevenst.service.ProductInfoService;
import com.elevenst.service.ProductInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/displays")
public class DisplayController {

    @Autowired
    ProductInfoService productInfoService;

    @GetMapping(path = "/{displayId}")
    public String getDisplayDetail(@PathVariable String displayId) {
        String productInfo = productInfoService.getProductInfo(displayId);
        return String.format("[display id = %s at %s %s ]", displayId, System.currentTimeMillis(),
                productInfo);
    }
}