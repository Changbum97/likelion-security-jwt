package com.security.jwt.configuration;

import com.security.jwt.domain.entity.User;
import com.security.jwt.service.UserService;
import com.security.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {  // OncePerRequestFilter : 매번 들어갈때 마다 체크해주는 필터

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Token 꺼내기
        final String authroizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader : {}", authroizationHeader);

        if(authroizationHeader == null || !authroizationHeader.startsWith("Bearer ")) {
            log.info("헤더를 가져오는 과정에서 에러가 발생했습니다. 헤더가 null이거나 잘못되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        try {
            token = authroizationHeader.split(" ")[1];
            log.info("token : {}", token);
        } catch (Exception e) {
            log.error("토큰 추출에 실패하였습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰이 만료되었는지 check
        if(JwtTokenUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token에서 username 꺼내기
        String username = JwtTokenUtil.getUsername(token, secretKey);
        log.info("username : {}", username);

        // username으로 User 찾아오기
        User user = userService.getUserByUsername(username);
        log.info("userRole : {}", user.getRole());

        // 권한을 주거나 안주기
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, List.of(new SimpleGrantedAuthority(user.getRole().name())));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);  // 권한 부여
        filterChain.doFilter(request, response);
    }
}
