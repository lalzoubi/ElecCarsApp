package com.eleccars.ElecCarsApp.config;

import com.eleccars.ElecCarsApp.service.securityServices.Impl.JWTServiceImpl;
import com.eleccars.ElecCarsApp.service.securityServices.Impl.MyUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWTServiceImpl jwtServiceImpl;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String path = request.getRequestURI();
            // تجاهل مسارات معينة
            if (path.startsWith("/api/users/login") || path.startsWith("/api/users/register") || path.startsWith("/error")) {
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader("Authorization");
            String token = "", username = "";

            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                token = authHeader.substring(7);
                username = jwtServiceImpl.extractUserNameFromToken(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = context.getBean(MyUserDetailsServiceImpl.class).loadUserByUsername(username);

                if (jwtServiceImpl.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");

            String jsonResponse = "{\n" +
                    "  \"isSuccess\": 0,\n" +
                    "  \"message_ar\": \"JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.\",\n" +
                    "  \"message_en\": \"JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.\"\n" +
                    "}";

            response.getWriter().write(jsonResponse);
            response.getWriter().flush();
        }
    }
}
