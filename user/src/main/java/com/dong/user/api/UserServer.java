package com.dong.user.api;

import com.example.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("security")
public interface UserServer {
    @GetMapping("/api/user/{id}")
    String findById(@PathVariable("id") String id);
}
