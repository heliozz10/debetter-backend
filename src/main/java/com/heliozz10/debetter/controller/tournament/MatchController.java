package com.heliozz10.debetter.controller.tournament;

import com.heliozz10.debetter.content.tournament.match.Match;
import com.heliozz10.debetter.dto.common.out.PageableResult;
import com.heliozz10.debetter.dto.tournament.match.in.MatchResultDto;
import com.heliozz10.debetter.dto.tournament.match.out.MatchView;
import com.heliozz10.debetter.mapper.tournament.MatchMapper;
import com.heliozz10.debetter.service.tournament.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments/{tournamentId}/round-groups/{roundGroupId}/rounds/{roundId}/matches")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @PreAuthorize("@tournamentSecurity.hasRoundViewPermission(principal, #roundId)")
    @GetMapping
    public PageableResult<MatchView> getMatchesByRoundId(
            @PathVariable Long roundId,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        Page<Match> matches = matchService.getMatchesByRoundId(roundId, pageable);
        return new PageableResult<>(
                matchMapper.toMatchViews(matches.getContent()),
                matches.getTotalElements(),
                matches.getTotalPages()
        );
    }

    @PreAuthorize("principal.role.name() == 'ORGANIZER' and @tournamentSecurity.hasEditPermission(principal, #tournamentId)")
    @PatchMapping("/{matchId}/results")
    public void submitMatchResults(
            @PathVariable Long tournamentId,
            @RequestBody List<MatchResultDto> matchResultDto
    ) {
        matchService.submitMatchResults(tournamentId, matchResultDto);
    }
}
