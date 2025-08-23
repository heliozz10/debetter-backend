package com.heliozz10.debetter.dto.util.request.in;

import jakarta.validation.constraints.Positive;

public record ParticipantInvitationDto (
        @Positive Long inviteeId,
        @Positive Long teamId
) {}
