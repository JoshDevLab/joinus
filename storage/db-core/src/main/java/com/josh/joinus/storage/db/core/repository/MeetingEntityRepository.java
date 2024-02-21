package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.storage.db.core.dto.MeetingTechPositionDto;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.josh.joinus.storage.db.core.entity.QMeetingEntity.meetingEntity;
import static com.josh.joinus.storage.db.core.entity.QMeetingPositionEntity.meetingPositionEntity;
import static com.josh.joinus.storage.db.core.entity.QMeetingTechEntity.meetingTechEntity;
import static com.josh.joinus.storage.db.core.entity.QPositionEntity.positionEntity;
import static com.josh.joinus.storage.db.core.entity.QTechEntity.techEntity;
import static com.querydsl.jpa.JPAExpressions.selectDistinct;

@Repository
@RequiredArgsConstructor
public class MeetingEntityRepository implements MeetingRepository {

    private final MeetingJpaRepository meetingJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Meeting create(MeetingCreate meetingCreate) {
        return meetingJpaRepository.save(MeetingEntity.create(meetingCreate)).toDomain();
    }

    @Override
    public List<Meeting> searchByCondition(MeetingSearchCondition meetingSearchCondition) {
        queryFactory
                .select(
                        Projections.constructor(
                                MeetingTechPositionDto.class,
                                meetingTechEntity.meetingEntity.id,
                                meetingTechEntity.techEntity,
                                meetingPositionEntity.positionEntity.name
                        )
                )
                .from(meetingTechEntity)
                .leftJoin(meetingPositionEntity)
                .on(meetingTechEntity.meetingEntity.eq(meetingPositionEntity.meetingEntity))
                .join(meetingTechEntity.techEntity, techEntity).fetchJoin()
                .join(meetingPositionEntity.positionEntity, positionEntity).fetchJoin()
                .where(meetingTechEntity.meetingEntity.id.in(List.of(1L, 2L)))
                .fetch();

        List<MeetingEntity> meetingEntityList = queryFactory
                .select(meetingEntity)
                .from(meetingEntity)
                .where(
                        meetingEntity.in(
                                selectDistinct(meetingTechEntity.meetingEntity)
                                        .from(meetingTechEntity)
                                        .leftJoin(meetingPositionEntity)
                                        .on(meetingTechEntity.meetingEntity.eq(meetingPositionEntity.meetingEntity))
                                        .where(
                                                searchMeetingType(meetingSearchCondition.getMeetingType()),
                                                searchTech(meetingSearchCondition.getTechIdList()),
                                                searchPosition(meetingSearchCondition.getPositionId()),
                                                searchProcessWqy(meetingSearchCondition.getProcessWay()),
                                                searchMeetingStatus(meetingSearchCondition.getMeetingStatus())
                                        )
                        )
                )
                .fetch();
        return meetingEntityList.stream().map(MeetingEntity::toDomainBySearch).collect(Collectors.toList());

//        List<MeetingEntity> meetingEntityList = queryFactory
//                .select(meetingEntity)
//                .from(meetingEntity)
//                .join(meetingEntity.meetingTechEntityList, meetingTechEntity).fetchJoin()
//                .join(meetingTechEntity.techEntity, techEntity)
//                .join(meetingEntity.meetingPositionEntityList, meetingPositionEntity).fetchJoin()
//                .join(meetingPositionEntity.positionEntity, positionEntity)
//                .where(
//                        searchMeetingType(meetingSearchCondition.getMeetingType()),
//                        searchTech(meetingSearchCondition.getTechIdList()),
//                        searchPosition(meetingSearchCondition.getPositionId()),
//                        searchProcessWqy(meetingSearchCondition.getProcessWay()),
//                        searchMeetingStatus(meetingSearchCondition.getMeetingStatus())
//                )
//                .fetch();
//
//        return meetingEntityList.stream().map(MeetingEntity::toDomainBySearch).collect(Collectors.toList());
    }

    public List<MeetingTechPositionDto> findByMeetingTechPositionByMeetingIds(List<Long> meetingIds) {
        List<MeetingTechPositionDto> data = queryFactory
                .select(
                        Projections.constructor(
                                MeetingTechPositionDto.class,
                                meetingTechEntity.meetingEntity.id,
                                meetingTechEntity.techEntity,
                                meetingPositionEntity.positionEntity.name
                        )
                )
                .from(meetingTechEntity)
                .leftJoin(meetingPositionEntity)
                .on(meetingTechEntity.meetingEntity.eq(meetingPositionEntity.meetingEntity))
                .join(meetingTechEntity.techEntity, techEntity).fetchJoin()
                .join(meetingPositionEntity.positionEntity, positionEntity).fetchJoin()
                .where(meetingTechEntity.meetingEntity.id.in(List.of(1L, 2L)))
                .fetch();

        data.forEach(p -> p.getTechEntity().toDomain());

        return data;
    }

    private BooleanExpression searchMeetingStatus(MeetingStatus meetingStatus) {
        return meetingStatus == null ? null : meetingEntity.meetingStatus.eq(MeetingStatus.RECRUITING);
    }

    private BooleanExpression searchProcessWqy(ProcessWay processWay) {
        return processWay == null ? null : meetingEntity.processWay.eq(processWay);
    }

    private BooleanExpression searchPosition(Long positionId) {
        return positionId == null ? null : positionEntity.id.eq(positionId);
    }

    private BooleanExpression searchTech(List<Long> techIdList) {
        return techIdList == null ? null : techEntity.id.in(techIdList);
    }

    private BooleanExpression searchMeetingType(MeetingType meetingType) {
        return meetingType == null ? null : meetingEntity.meetingType.eq(meetingType);
    }
}
