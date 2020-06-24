package com.dong.gateway.config;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.filter.TokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("request = {}", JSONArray.toJSONString( exchange.getRequest()));
        HttpCookie cookie = exchange.getRequest().getCookies().getFirst("COOKIE-TOKEN") ;
        String token1=cookie!=null?cookie.getValue():null;
        logger.info("token = {}",token1);
        String token2=exchange.getRequest().getHeaders().getFirst("token");
        logger.info("token2 = {}",token2);
        String token=token1!=null?token1:token2;
        //向headers中放文件，记得build
        ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add("token",token);
        }).build();
//        exchange.getRequest().mutate().
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        logger.info("request = {}",JSONArray.toJSONString(build.getRequest()));
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
