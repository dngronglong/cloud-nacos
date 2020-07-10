package com.example.shopconsumer.service;

import com.example.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("shop")
public interface ShopService {

    @GetMapping("/shop/list")
    Result findAll();
}
