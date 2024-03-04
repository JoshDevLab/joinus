package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.meeting.MeetingJoinMemberRepository;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.storage.db.core.entity.QMeetingEntity;
import com.josh.joinus.storage.db.core.entity.QMeetingJoinMemberEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJoinMemberJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import static com.josh.joinus.storage.db.core.entity.QMeetingEntity.meetingEntity;
import static com.josh.joinus.storage.db.core.entity.QMeetingJoinMemberEntity.meetingJoinMemberEntity;

@Repository
@RequiredArgsConstructor
public class MeetingJoinMemberEntityRepository implements MeetingJoinMemberRepository {
    private final JPAQueryFactory queryFactory;

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
}
