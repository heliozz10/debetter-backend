package com.heliozz10.debetter.dto.util.socials.in;

import com.heliozz10.debetter.content.util.socials.SocialPlatform;
import jakarta.validation.constraints.NotNull;

public record SocialProfileDto(
        @NotNull SocialPlatform platform,
        @NotNull String handle,
        Boolean isPublic
) {
}
