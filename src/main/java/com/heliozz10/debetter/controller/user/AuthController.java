package com.heliozz10.debetter.controller.user;

import com.heliozz10.debetter.dto.user.in.UserLoginDto;
import com.heliozz10.debetter.dto.user.in.UserRegistrationDto;
import com.heliozz10.debetter.security.AuthProvider;
import com.heliozz10.debetter.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    private final AuthProvider authProvider;

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private RememberMeServices rememberMeServices;

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegistrationDto dto) {
        userService.createUser(dto);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginDto dto, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(dto.username(), dto.password());
        Authentication auth = authProvider.authenticate(authToken);

        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
        context.setAuthentication(auth);

        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        if(dto.rememberMe()) {
            rememberMeServices.loginSuccess(request, response, auth);
        }
    }
}
