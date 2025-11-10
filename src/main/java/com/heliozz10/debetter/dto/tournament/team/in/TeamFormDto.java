package com.heliozz10.debetter.dto.tournament.team.in;

import com.heliozz10.debetter.validation.OnCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TeamFormDto(
        @NotNull @Size(min = 1, max = 50) String name,
        @NotNull @Size(min = 1, max = 50) String club,
        @NotNull @Positive Long creatorId,
        @Valid List<String> invitedParticipants
) {
}
