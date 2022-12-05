package com.ssafy.ssafit.interceptor;

import com.ssafy.ssafit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "access-token"; // vue에서 넘길 때 이 이름으로?


    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")) return true;

        final String token = request.getHeader(HEADER_AUTH);
        if (token != null) { // 토큰이 있으면
            Map<String, Object> claims = jwtUtil.verifyJWT(token);
            return true;
        }
        // ??????????????
        // ?????

        throw new Exception("invalid access");
    }
}
