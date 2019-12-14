package com.bazinga.base.configuration;

import com.bazinga.auth.model.ClaimName;
import com.bazinga.auth.model.util.JwtUtil;
import com.bazinga.base.exception.ServiceException;
import com.bazinga.base.model.dto.ErrorCode;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                Claims parse = jwtUtil.parse(jwtToken);

                if (parse != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String username = parse.getSubject();
                    //TODO check token is valid use hazelcast or redis
                    List<String> roles = (List<String>) parse.get(ClaimName.ROLES.name());
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            } catch (Exception e) {
                logger.error("logFor=auth Could not auth user ", e);
                throw new ServiceException(ErrorCode.build("auth", "permission-denied"));
            }
        }
        filterChain.doFilter(request, response);


    }
}
