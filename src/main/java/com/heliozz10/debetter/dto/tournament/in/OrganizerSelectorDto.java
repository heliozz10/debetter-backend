package com.heliozz10.debetter.dto.tournament.in;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OrganizerSelectorDto(
        @Positive Long id,
        @Size(min = 1, max = 20) String username
) {
}
