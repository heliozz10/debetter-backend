package com.heliozz10.debetter.controller.tournament.round;

import com.heliozz10.debetter.dto.tournament.round.in.RoundUpdateDto;
import com.heliozz10.debetter.dto.tournament.round.out.RoundView;
import com.heliozz10.debetter.mapper.tournament.round.RoundMapper;
import com.heliozz10.debetter.service.tournament.round.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments/{tournamentId}/round-groups/{roundGroupId}/rounds")
public class RoundController {
    private final RoundService roundService;
    private final RoundMapper roundMapper;

    @PreAuthorize("@tournamentSecurity.hasViewPermission(principal, #tournamentId)")
    @GetMapping
    public List<RoundView> getRoundsByRoundGroupId(@PathVariable Long tournamentId, @PathVariable Long roundGroupId) {
        return roundMapper.toRoundViews(roundService.getRoundsByTournamentIdAndRoundGroupId(tournamentId, roundGroupId));
    }

    @PreAuthorize("principal.role.name() == 'ORGANIZER' and @tournamentSecurity.hasEditPermission(principal, #tournamentId)")
    @PatchMapping("/{roundId}")
    public void updateRound(@PathVariable Long tournamentId, @PathVariable Long roundId, @RequestBody RoundUpdateDto roundUpdateDto) {
        roundService.updateRound(roundUpdateDto, tournamentId, roundId);
    }

    @PreAuthorize("principal.role.name() == 'ORGANIZER' and @tournamentSecurity.hasEditPermission(principal, #tournamentId)")
    @DeleteMapping("/{roundId}")
    public void deleteRound(@PathVariable Long tournamentId, @PathVariable Long roundId) {
        roundService.deleteRound(tournamentId, roundId);
    }
}
