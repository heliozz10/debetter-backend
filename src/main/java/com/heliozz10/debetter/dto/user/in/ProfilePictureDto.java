package com.heliozz10.debetter.dto.user.in;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ProfilePictureDto (
    @NotNull MultipartFile file
) {}
