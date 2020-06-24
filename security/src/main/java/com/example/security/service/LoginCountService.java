package com.example.security.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginCountService {
    /**
     * 增加登陆次数
     *
     * @param request 请求
     * @param count   次数
     */
    void addLoginCount(HttpServletRequest request, int count);

    /**
     * 登陆次数归零
     *
     * @param request 请求
     */
    void cleanLoginCount(HttpServletRequest request);


    /**
     * 判断是否抛出登陆错误次数过多异常
     *
     * @param request 请求
     */
    void judgeLoginCount(HttpServletRequest request);
}

