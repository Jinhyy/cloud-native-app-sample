package com.elevenst.controller.api;

import com.elevenst.mapper.MyTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MyTableController {
    @Autowired
    MyTableMapper myTableMapper;

    @GetMapping(path="/mytableAll")
    public ResponseEntity<?> getMyTableAll(){
        return ResponseEntity.ok(myTableMapper.findMyTableModelAll());
    }
}
