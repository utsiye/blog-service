package dev.utsiye.blog_service.presentation.filters;

import dev.utsiye.blog_service.application.services.JWTTokenProvider;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AuthorizedUserFilter extends BaseAuthorizedFilter {

    private static final List<PathMethod> ALLOWED_PATHS = List.of(
        new PathMethod("/categories/", "GET")
    );

    public AuthorizedUserFilter(JWTTokenProvider jwtProvider) {
        super(jwtProvider);
    }

    @Override
    protected List<PathMethod> getAllowedPaths() {
        return ALLOWED_PATHS;
    }
}
