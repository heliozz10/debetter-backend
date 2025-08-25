package com.heliozz10.debetter.repository.util.request;

import com.heliozz10.debetter.content.util.request.ParticipantInvitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantInvitationRepository extends JpaRepository<ParticipantInvitation, Long> {
    @EntityGraph(value = "ParticipantInvitation.withInviteeAndTeam", type = EntityGraph.EntityGraphType.LOAD)
    Optional<ParticipantInvitation> findByInviteeIdAndId(Long inviteeId, Long id);

    Optional<ParticipantInvitation> findRawByInviteeIdAndId(Long inviteeId, Long id);

    @EntityGraph(value = "ParticipantInvitation.forView", type = EntityGraph.EntityGraphType.LOAD)
    Page<ParticipantInvitation> findByInviterId(Long inviterId, Pageable pageable);

    @EntityGraph(value = "ParticipantInvitation.forView", type = EntityGraph.EntityGraphType.LOAD)
    Page<ParticipantInvitation> findByInviteeId(Long inviteeId, Pageable pageable);

    @EntityGraph(value = "ParticipantInvitation.forView", type = EntityGraph.EntityGraphType.LOAD)
    Page<ParticipantInvitation> findByTeamId(Long teamId, Pageable pageable);

    long countByTeam_Id(Long id);

    @Query("""
            select count(p) from ParticipantInvitation p
            where p.inviter.id = ?1 and p.team.id = ?2 and p.invitee.id = ?3""")
    long countExistingInvitation(Long id, Long id1, Long id2);
}
