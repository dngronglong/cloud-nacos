package com.example.shop.controller;

import com.example.common.utils.Result;
import com.example.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shop")
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/list")
    public Result list(){
        return shopService.findAll();
    }
}
