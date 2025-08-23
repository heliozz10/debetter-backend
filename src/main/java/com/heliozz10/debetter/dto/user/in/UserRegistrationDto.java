package com.heliozz10.debetter.dto.user.in;

import com.heliozz10.debetter.content.user.Role;
import com.heliozz10.debetter.dto.user.profile.in.CityDto;
import com.heliozz10.debetter.dto.user.profile.in.InstitutionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto (
        @NotNull @Size(min = 3, max = 20) String username,
        @NotNull @Size(min = 8, max = 32) String password,
        @NotNull @Size(min = 1, max = 50) String email,
        @NotNull @Size(min = 1, max = 20) String firstName,
        @NotNull @Size(min = 1, max = 20) String lastName,
        @NotNull Role role,
        @Valid CityDto city,
        @Valid InstitutionDto institution
) {
}
