package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.meeting.JoinStatus;
import com.josh.joinus.core.domain.meeting.MeetingJoinMember;
import com.josh.joinus.core.domain.meeting.MeetingJoinMemberRepository;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.storage.db.core.entity.MeetingJoinMemberEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJoinMemberJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.josh.joinus.storage.db.core.entity.QMeetingEntity.meetingEntity;
import static com.josh.joinus.storage.db.core.entity.QMeetingJoinMemberEntity.meetingJoinMemberEntity;

@Repository
@RequiredArgsConstructor
public class MeetingJoinMemberEntityRepository implements MeetingJoinMemberRepository {
    private final JPAQueryFactory queryFactory;
    private final MeetingJoinMemberJpaRepository meetingJoinMemberJpaRepository;

    @Override
    public boolean findByMeetingTypeAndJoinUserId(MeetingType meetingType, Long joinUserId) {
        Long cnt = queryFactory
                .select(
                        meetingJoinMemberEntity.count().as("cnt")
                )
                .from(meetingJoinMemberEntity)
                .join(meetingEntity)
                .on(meetingJoinMemberEntity.meetingId.eq(meetingEntity.id))
                .where(
                        meetingJoinMemberEntity.userId.eq(joinUserId),
                        meetingEntity.meetingType.eq(meetingType)
                )
                .fetchOne();

        return cnt > 0;
    }

    @Override
    public Long registerRequest(Long meetingId, Long joinUserId) {
        return meetingJoinMemberJpaRepository.save(MeetingJoinMemberEntity.createRequest(meetingId, joinUserId)).getId();
    }

    @Override
    public List<MeetingJoinMember> findByMeetingId(Long meetingId) {
        return meetingJoinMemberJpaRepository.findByMeetingId(meetingId).stream()
                .map(MeetingJoinMemberEntity::toDomain)
                .toList();
    }

    @Override
    public MeetingJoinMember findById(Long id) {
        return meetingJoinMemberJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모임 멤버입니다."))
                .toDomain();
    }

    @Override
    public int updateAccept(Long meetingJoinMemberId) {
        return meetingJoinMemberJpaRepository.updateAccept(meetingJoinMemberId, JoinStatus.ACCEPT);
    }
}
