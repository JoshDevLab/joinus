package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.meeting.MeetingTechRepository;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingTechJpaRepository;
import com.josh.joinus.storage.db.core.persistence.TechJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.josh.joinus.storage.db.core.entity.QMeetingEntity.meetingEntity;
import static com.josh.joinus.storage.db.core.entity.QMeetingTechEntity.meetingTechEntity;
import static com.josh.joinus.storage.db.core.entity.QTechEntity.techEntity;

@Repository
@RequiredArgsConstructor
public class MeetingTechEntityRepository implements MeetingTechRepository {

    private final JPAQueryFactory queryFactory;

    private final MeetingTechJpaRepository meetingTechJpaRepository;
    private final MeetingJpaRepository meetingJpaRepository;
    private final TechJpaRepository techJpaRepository;

    @Override
    public void create(Long meetingId, List<Long> techIdList) {
        MeetingEntity meetingEntity = meetingJpaRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다."));

        List<TechEntity> techEntityList = techJpaRepository.findByIdIn(techIdList);

        techEntityList.forEach(techEntity -> {
            meetingEntity.addMeetingTechEntity(
                    meetingTechJpaRepository.save(MeetingTechEntity.create(meetingEntity, techEntity))
            );
        });
    }

    @Override
    public List<Tech> findByMeetingId(Long meetingId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                Tech.class,
                                techEntity.name,
                                techEntity.techImg
                        )
                )
                .from(meetingTechEntity)
                .join(meetingTechEntity.techEntity, techEntity)
                .where(meetingTechEntity.meetingEntity.id.eq(meetingId))
                .fetch();
    }
}
