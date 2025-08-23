package com.heliozz10.debetter.controller.user;

import com.heliozz10.debetter.content.user.User;
import com.heliozz10.debetter.content.util.socials.SocialPlatform;
import com.heliozz10.debetter.dto.common.out.PageableResult;
import com.heliozz10.debetter.dto.user.in.ProfilePictureDto;
import com.heliozz10.debetter.dto.user.in.UserGetParams;
import com.heliozz10.debetter.dto.user.in.UserUpdateDto;
import com.heliozz10.debetter.dto.user.out.SimpleUserView;
import com.heliozz10.debetter.dto.user.out.UserView;
import com.heliozz10.debetter.dto.util.socials.in.SocialProfileDto;
import com.heliozz10.debetter.mapper.user.UserMapper;
import com.heliozz10.debetter.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public PageableResult<SimpleUserView> getUsers(
            @ModelAttribute UserGetParams params,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        Page<User> users = userService.getUsers(params, pageable);
        return new PageableResult<>(
                userMapper.toSimpleUserViews(users.getContent()),
                users.getTotalElements(),
                users.getTotalPages()
        );
    }

    @GetMapping("/{id}")
    public UserView getUserById(@PathVariable Long id) {
        return userMapper.toUserView(userService.getUserById(id));
    }

    @GetMapping("/me")
    public UserView getMe(Authentication authentication) {
        Long id = ((User) authentication.getPrincipal()).getId();
        return userMapper.toUserView(userService.getUserById(id));
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @PatchMapping("/{id}")
    public UserView updateUser(@PathVariable Long id, @RequestBody UserUpdateDto user) {
        return userMapper.toUserView(userService.updateUser(user, id));
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @PostMapping("/{id}/profile-picture")
    public void addOrUpdateProfilePicture(@PathVariable Long id, @RequestBody ProfilePictureDto dto) {
        userService.addOrUpdateProfilePicture(id, dto);
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @PostMapping("/me/profile-picture")
    public void addOrUpdateMyProfilePicture(Authentication authentication, @RequestBody ProfilePictureDto dto) {
        Long id = ((User) authentication.getPrincipal()).getId();
        userService.addOrUpdateProfilePicture(id, dto);
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @DeleteMapping("/{id}/profile-picture")
    public void deleteProfilePicture(@PathVariable Long id) {
        userService.deleteProfilePicture(id);
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @DeleteMapping("/me/profile-picture")
    public void deleteMyProfilePicture(Authentication authentication) {
        Long id = ((User) authentication.getPrincipal()).getId();
        userService.deleteProfilePicture(id);
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @PostMapping("/{id}/social-profiles")
    public void addOrUpdateSocialProfiles(@PathVariable Long id, Collection<SocialProfileDto> newProfiles) {
        userService.addOrUpdateSocialProfiles(id, newProfiles);
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @PostMapping("/me/social-profiles")
    public void addOrUpdateMySocialProfiles(Authentication authentication, Collection<SocialProfileDto> newProfiles) {
        Long id = ((User) authentication.getPrincipal()).getId();
        userService.addOrUpdateSocialProfiles(id, newProfiles);
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @DeleteMapping("/{id}/social-profiles")
    public void removeAllSocialProfiles(@PathVariable Long id, @RequestParam(required = false) List<SocialPlatform> platforms) {
        if(platforms != null) {
            userService.removeSocialProfiles(id, platforms);
        } else {
            userService.removeAllSocialProfiles(id);
        }
    }

    @PreAuthorize("@userSecurity.canEditUser(principal, #id)")
    @DeleteMapping("/me/social-profiles")
    public void removeAllMySocialProfiles(Authentication authentication, @RequestParam(required = false) List<SocialPlatform> platforms) {
        Long id = ((User) authentication.getPrincipal()).getId();
        if(platforms != null) {
            userService.removeSocialProfiles(id, platforms);
        } else {
            userService.removeAllSocialProfiles(id);
        }
    }
}
