package com.heliozz10.debetter.mapper.tournament.round;

import com.heliozz10.debetter.content.tournament.round.Round;
import com.heliozz10.debetter.dto.tournament.round.in.RoundUpdateDto;
import com.heliozz10.debetter.dto.tournament.round.out.RoundView;
import com.heliozz10.debetter.dto.tournament.round.out.SimpleRoundView;
import com.heliozz10.debetter.mapper.tournament.MatchMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
            MatchMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RoundMapper {
    void updateRound(RoundUpdateDto dto, @MappingTarget Round round);

    SimpleRoundView toSimpleRoundView(Round round);

    List<SimpleRoundView> toSimpleRoundViews(List<Round> rounds);

    @InheritConfiguration(name = "toSimpleRoundView")
    @Mapping(target = "matches", expression = "java(round.getMatchesArePublic() ? matchMapper.toMatchViewList(round.getMatches()) : null)")
    RoundView toRoundView(Round round);

    List<RoundView> toRoundViews(List<Round> rounds);
}
