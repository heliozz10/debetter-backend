package com.heliozz10.debetter.dto.tournament.team.in;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ParticipantSelectorDto(
        @Positive Long id,
        @Size(min = 1, max = 20) String username
) {
}
