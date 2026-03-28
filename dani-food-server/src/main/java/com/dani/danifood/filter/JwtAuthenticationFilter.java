package com.dani.danifood.filter;

import com.dani.danifood.config.JwtProperties;
import com.dani.danifood.constant.JwtClaimsConstant;
import com.dani.danifood.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 从请求头取Token
        String token = request.getHeader(jwtProperties.getEmployeeTokenName());

        if (token == null) {
            // 没有Token，继续走，让Security自己决定放不放行
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 验证Token
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            log.info("JWT验证通过, empId: {}", claims.get(JwtClaimsConstant.EMP_ID));

            // 告诉Spring Security这个用户合法
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(claims, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.info("JWT验证失败: {}", e.getMessage());
            // Token无效，不设置Authentication，Security会拦截
        }

        filterChain.doFilter(request, response);
    }
}