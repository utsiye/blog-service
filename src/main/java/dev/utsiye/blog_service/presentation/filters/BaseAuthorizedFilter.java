package dev.utsiye.blog_service.presentation.filters;

import dev.utsiye.blog_service.application.services.JWTTokenProvider;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
Class to validate JWT token. Base logic contains out of validation, if token is parsable or not.
*/


public abstract class BaseAuthorizedFilter extends OncePerRequestFilter {

    private static final String HEADER_NAME = "Authorization";
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    protected final JWTTokenProvider jwtProvider;

    protected abstract List<String> getAllowedPaths();

    protected BaseAuthorizedFilter(JWTTokenProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();

        // No match in allowed paths => no filtration
        return getAllowedPaths().stream()
                .noneMatch(pattern -> PATH_MATCHER.match(pattern, uri));
    }

    @Override
    protected final void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = extractToken(request);

        if (token == null || !jwtProvider.isValid(token)) {
            setResponseUnauthorized(response);
            return;
        }

        handleValidToken(token, request, response, filterChain);
    }

    // This method should be overriden by inheritors in order to add filtration logic.
    protected void handleValidToken(String token, HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    /////////////////////////////////////////////// HELPERS //////////////////////////////////////////////////////

    protected String extractToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_NAME);

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);
    }

    protected static void setResponseUnauthorized(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Missing or invalid API key\"}");
    }
}
