package com.huang.demo1.controller;

import com.huang.demo1.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/dataImport")
    public Object dataImport(){
        System.out.println("dataImport ......");

        return searchItemService.dataImport();
    }

    @PostMapping("/query")
    public Object queryItem(@RequestParam(defaultValue = "q") String param, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "60") Integer rows){
        return searchItemService.queryItem(param,page,rows);
    }
}
