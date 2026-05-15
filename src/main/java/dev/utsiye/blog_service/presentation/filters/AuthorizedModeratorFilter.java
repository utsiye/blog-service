package dev.utsiye.blog_service.presentation.filters;

import dev.utsiye.blog_service.application.services.JWTTokenProvider;
import dev.utsiye.blog_service.domain.repositories.UserRepository;
import dev.utsiye.blog_service.domain.entities.User;
import dev.utsiye.blog_service.domain.enums.UserRole;

import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Component
public class AuthorizedModeratorFilter extends BaseAuthorizedFilter {
    private static final List<PathMethod> ALLOWED_PATHS = List.of(
    new PathMethod("/categories/", "POST")
    );
    private final UserRepository userRepo;

    public AuthorizedModeratorFilter(JWTTokenProvider jwtProvider, UserRepository userRepo) {
        super(jwtProvider);
        this.userRepo = userRepo;
    }

    protected void handleValidToken(
        String token, HttpServletRequest request,
        HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
            long userId = jwtProvider.extractUserId(token);
            Optional<User> user = userRepo.findById(userId);
            boolean isModerator = user.filter(u -> u.getRole() == UserRole.MODERATOR).isPresent();
            if (!isModerator){
                setResponseUnauthorized(response);
                return;
            }
            chain.doFilter(request, response);
    }

    @Override
    protected List<PathMethod> getAllowedPaths() {
        return ALLOWED_PATHS;
    }
}
