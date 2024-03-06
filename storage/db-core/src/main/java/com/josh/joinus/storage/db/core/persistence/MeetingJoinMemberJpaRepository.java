package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.meeting.JoinStatus;
import com.josh.joinus.core.domain.meeting.MeetingJoinMember;
import com.josh.joinus.storage.db.core.entity.MeetingJoinMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingJoinMemberJpaRepository extends JpaRepository<MeetingJoinMemberEntity, Long> {
    List<MeetingJoinMemberEntity> findByMeetingId(Long meetingId);

    @Query("update MeetingJoinMemberEntity mjm set mjm.joinStatus = :accept where mjm.id = :meetingJoinMemberId")
    MeetingJoinMemberEntity updateAccept(@Param("meetingJoinMemberId") Long meetingJoinMemberId, @Param("accept") JoinStatus accept);
}
