package com.heliozz10.debetter.dto.user.profile.out;

import com.heliozz10.debetter.dto.tournament.out.SimpleTournamentView;
import com.heliozz10.debetter.dto.user.out.SimpleUserView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class OrganizerProfileView {
    private List<SimpleTournamentView> organizedTournaments;
    private List<SimpleTournamentView> coOrganizedTournaments;
}
