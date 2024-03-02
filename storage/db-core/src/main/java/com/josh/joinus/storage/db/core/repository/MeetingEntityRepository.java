package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.domain.meeting.*;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.core.dto.response.MeetingPositionDto;
import com.josh.joinus.core.dto.response.MeetingTechDto;
import com.josh.joinus.storage.db.core.dto.MeetingPositionEntityDto;
import com.josh.joinus.storage.db.core.dto.MeetingTechEntityDto;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.josh.joinus.storage.db.core.entity.QMeetingCommentEntity.meetingCommentEntity;
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

    }

    @Override
    public List<MeetingTechDto> findByMeetingTechByMeetingIds(List<Long> meetingIds) {
        List<MeetingTechEntityDto> data = queryFactory
                .select(
                        Projections.constructor(
                                MeetingTechEntityDto.class,
                                meetingTechEntity.meetingEntity.id,
                                meetingTechEntity.techEntity
                        )
                )
                .from(meetingTechEntity)
                .join(meetingTechEntity.techEntity, techEntity)
                .where(meetingTechEntity.meetingEntity.id.in(meetingIds))
                .fetch();


        return data.stream().map(MeetingTechEntityDto::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<MeetingPositionDto> findByMeetingPositionByMeetingIds(List<Long> meetingIds) {
        List<MeetingPositionEntityDto> data = queryFactory
                .select(
                        Projections.constructor(
                                MeetingPositionEntityDto.class,
                                meetingPositionEntity.meetingEntity.id,
                                positionEntity.name
                        )
                )
                .from(meetingPositionEntity)
                .join(meetingPositionEntity.positionEntity, positionEntity)
                .where(meetingPositionEntity.meetingEntity.id.in(meetingIds))
                .fetch();


        return data.stream().map(MeetingPositionEntityDto::toDomain).collect(Collectors.toList());
    }

    @Override
    public boolean duplicateLeaderUser(Long leaderUserId, MeetingType meetingType) {
        List<MeetingEntity> meetingEntityList = queryFactory.selectFrom(meetingEntity)
                .where(meetingEntity.leaderUserId.eq(leaderUserId),
                        meetingEntity.meetingType.eq(meetingType))
                .fetch();

        return !meetingEntityList.isEmpty();
    }

    @Override
    public Meeting findById(Long meetingId) {
        return meetingJpaRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미팅입니다."))
                .toDomain();
    }

    @Override
    public void updateViewCount(Long meetingId, int viewCount) {
        meetingJpaRepository.updateViewCount(meetingId, viewCount);
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
