package com.example.security.controller;

import com.example.security.config.WebSecurityConfig;
import com.example.security.damain.CustomData;
import com.example.security.damain.LoginResultDetails;
import com.example.security.damain.ResultDetails;
import com.example.security.service.LoginService;
import com.example.security.service.SystemDataService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final SystemDataService systemDataService;
    private final LoginService loginService;
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(SystemDataService systemDataService, LoginService loginService) {
        this.systemDataService = systemDataService;
        this.loginService = loginService;
    }

    @GetMapping("/loginJudge")
    public LoginResultDetails showPage() {
        return loginService.get();
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id){
        return userService.findById(id).toString();
    }

    @PostMapping("/data")
    public CustomData create(@RequestBody CustomData customData) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String data = customData.getData();
        data = HtmlUtils.htmlEscape(data);
        customData.setData("#这是用户创建的数据：" + data);
        return systemDataService.create(customData);
    }


    @DeleteMapping("/data/{id}")
    public ResultDetails delete(@PathVariable("id") String id) {
        return systemDataService.delete(id, WebSecurityConfig.USER);
    }
}
