package com.example.shopconsumer.controller;

import com.example.common.utils.Result;
import com.example.shopconsumer.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/getList")
    public Result getList(){
        return shopService.findAll();
    }
}
