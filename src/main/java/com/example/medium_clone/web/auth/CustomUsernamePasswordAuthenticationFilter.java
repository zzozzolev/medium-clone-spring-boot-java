package com.example.medium_clone.web.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    /**
     * Process json or form request.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String email, password;

        try {
            if (request.getContentType().equals(MimeTypeUtils.APPLICATION_JSON_VALUE)) {
                Map<String, String> requestMap = objectMapper.readValue(request.getInputStream(), Map.class);
                email = requestMap.getOrDefault("email", "");
                password = requestMap.getOrDefault("password", "");
            }
            else {
                email = obtainUsername(request);
                password = obtainPassword(request);
            }

        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, password);
        setDetails(request, authToken);
        return getAuthenticationManager().authenticate(authToken);
    }
}
