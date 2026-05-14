package dev.utsiye.blog_service.presentation.filters;

import dev.utsiye.blog_service.application.services.JWTTokenProvider;

import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class AuthorizedUserFilter extends BaseAuthorizedFilter {

    private static final List<String> ALLOWED_PATHS = List.of(
        "/api/user/**" // TODO
    );

    public AuthorizedUserFilter(JWTTokenProvider jwtProvider) {
        super(jwtProvider);
    }

    @Override
    protected List<String> getAllowedPaths() {
        return ALLOWED_PATHS;
    }
}
