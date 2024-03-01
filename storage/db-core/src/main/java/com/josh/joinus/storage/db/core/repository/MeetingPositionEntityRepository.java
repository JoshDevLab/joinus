package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.meeting.MeetingPositionRepository;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingPositionJpaRepository;
import com.josh.joinus.storage.db.core.persistence.PositionJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.josh.joinus.storage.db.core.entity.QMeetingPositionEntity.meetingPositionEntity;
import static com.josh.joinus.storage.db.core.entity.QPositionEntity.positionEntity;

@Repository
@RequiredArgsConstructor
public class MeetingPositionEntityRepository implements MeetingPositionRepository {
    private final JPAQueryFactory queryFactory;
    private final MeetingPositionJpaRepository meetingPositionJpaRepository;
    private final MeetingJpaRepository meetingJpaRepository;
    private final PositionJpaRepository positionJpaRepository;

    @Override
    public void create(Long meetingId, List<Long> positionList) {
        MeetingEntity meetingEntity = meetingJpaRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다."));

        List<PositionEntity> positionEntityList = positionJpaRepository.findByIdIn(positionList);

        positionEntityList.forEach(positionEntity -> {
            meetingEntity.addMeetingPositionEntity(
                    meetingPositionJpaRepository.save(MeetingPositionEntity.create(meetingEntity, positionEntity))
            );
        });
    }

    @Override
    public List<Position> findByMeetingId(Long meetingId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                Position.class,
                                positionEntity.name
                        )
                )
                .from(meetingPositionEntity)
                .join(meetingPositionEntity.positionEntity, positionEntity)
                .where(meetingPositionEntity.meetingEntity.id.eq(meetingId))
                .fetch();
    }
}
