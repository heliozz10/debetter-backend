package com.heliozz10.debetter.service.tournament;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.heliozz10.debetter.content.tournament.match.Match;
import com.heliozz10.debetter.dto.tournament.match.in.MatchResultDto;
import com.heliozz10.debetter.dto.tournament.match.in.ParticipantScoreDto;
import com.heliozz10.debetter.repository.tournament.match.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepository matchRepository;

    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public Page<Match> getMatchesByRoundId(Long roundId, Pageable pageable) {
        return matchRepository.findByRoundId(roundId, pageable);
    }

    //TODO: fix this, this doesnt set the scores for the teams and debaters, it only sets the scores for the matches. Already done but saving the todo
    @Transactional
    public void submitMatchResults(Long tournamentId, Collection<MatchResultDto> results) {
        ArrayNode arrayNode = objectMapper.createArrayNode();

        results.forEach(result -> {
            ObjectNode objectNode = objectMapper.createObjectNode();

            objectNode.put("tournament_id", tournamentId);

            objectNode.put("match_id", result.matchId());

            for (int i = 0; i < 4; i++) {
                String fieldName = "team" + (i + 1) + "score";
                Integer score = null;
                if (i < result.teamResults().size()) {
                    List<ParticipantScoreDto> ps = result.teamResults().get(i).participantScores();
                    score = ps.stream()
                            .map(ParticipantScoreDto::score)
                            .filter(Objects::nonNull)
                            .reduce(0, Integer::sum);
                }
                objectNode.put(fieldName, score);
            }

            for (int i = 0; i < 2; i++) {
                String fieldName = "debater" + (i + 1) + "score";
                Integer score = null;
                if (i < result.participantScores().size()) {
                    score = result.participantScores().get(i).score();
                }
                objectNode.put(fieldName, score);
            }

            arrayNode.add(objectNode);
        });

        String json = arrayNode.toString();
        matchRepository.updateMatchScoresBulk(json);
    }
}
