package com.example.security.fegin;

import com.example.security.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("security")
public interface UserFegin {
    @GetMapping("/user/detail/{id}")
    User findById(@PathVariable("id") String id);
}
