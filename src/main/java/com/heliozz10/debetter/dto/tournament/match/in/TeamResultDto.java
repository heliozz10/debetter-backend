package com.heliozz10.debetter.dto.tournament.match.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record TeamResultDto (
        @Positive Long teamId,
        @Valid List<ParticipantScoreDto> participantScores
) {}
