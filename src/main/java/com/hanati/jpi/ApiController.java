package com.hanati.jpi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/call/{apiId}")
    public String apiCall(@PathVariable String apiId){
        return apiId;
    }

}
