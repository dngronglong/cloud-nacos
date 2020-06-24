package com.example.security.config;

import com.example.security.damain.LoginResultDetails;
import com.example.security.damain.ResultDetails;
import com.example.security.entity.LoginDetails;
import com.example.security.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import java.util.Arrays;
import java.util.Collection;

public class TokenAuthenticationHelper {
    /**
     * 未设置记住我时 token 过期时间
     * */
    private static final long EXPIRATION_TIME = 7200000;

    /**
     * 记住我时 cookie token 过期时间
     * */
    private static final int COOKIE_EXPIRATION_TIME = 1296000;

    private static final String SECRET_KEY = "ThisIsASpringSecurityDemo";
    public static final String COOKIE_TOKEN = "COOKIE-TOKEN";
    public static final String XSRF = "XSRF-TOKEN";

    /**
     * 设置登陆成功后令牌返回
     * */
    public static void addAuthentication(HttpServletRequest request,  HttpServletResponse response, Authentication authResult) throws IOException {
        // 获取用户登陆角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        // 遍历用户角色
        StringBuffer stringBuffer = new StringBuffer();
        authorities.forEach(authority -> {
            stringBuffer.append(authority.getAuthority()).append(",");
        });
        long expirationTime = EXPIRATION_TIME;
        int cookExpirationTime = -1;
        // 处理登陆附加信息
        LoginDetails loginDetails = (LoginDetails) authResult.getDetails();
        if (loginDetails.getRememberMe() != null && loginDetails.getRememberMe()) {
            expirationTime = COOKIE_EXPIRATION_TIME * 1000;
            cookExpirationTime = COOKIE_EXPIRATION_TIME;
        }

        String jwt = Jwts.builder()
                // Subject 设置用户名
                .setSubject(authResult.getName())
                // 设置用户权限
                .claim("authorities", stringBuffer)
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                // 签名算法
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        Cookie cookie = new Cookie(COOKIE_TOKEN, jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(cookExpirationTime);
        response.setHeader("token",jwt);
        response.addCookie(cookie);

        // 向前端写入数据
        LoginResultDetails loginResultDetails = new LoginResultDetails();
        ResultDetails resultDetails = new ResultDetails();
        resultDetails.setStatus(HttpStatus.OK.value());
        resultDetails.setMessage("登陆成功！");
        resultDetails.setSuccess(true);
        resultDetails.setTimestamp(LocalDateTime.now());
        User user = new User();
        user.setUsername(authResult.getName());
        user.setPower(stringBuffer.toString());
        user.setExpirationTime(System.currentTimeMillis() + expirationTime);

        loginResultDetails.setResultDetails(resultDetails);
        loginResultDetails.setUser(user);
        loginResultDetails.setStatus(200);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(loginResultDetails));
        out.flush();
        out.close();
    }

    /**
     * 对请求的验证
     * */
    public static Authentication getAuthentication(HttpServletRequest request) {

        Cookie cookie = WebUtils.getCookie(request, COOKIE_TOKEN);
        System.out.println("request: "+request);
        String token1 = request.getHeader("token");
        System.out.println("token1: "+token1);
        String token2 = cookie != null ? cookie.getValue() : null;
        String token = token1!=null?token1:token2;
        System.out.println(token);
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            // 获取用户权限
            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get("authorities").toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            String userName = claims.getSubject();
            if (userName != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, authorities);
                usernamePasswordAuthenticationToken.setDetails(claims);
                return usernamePasswordAuthenticationToken;
            }
            return null;
        }
        return null;
    }
}
