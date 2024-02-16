package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.*;
import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingTechJpaRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.stream.Collectors;

import static com.josh.joinus.storage.db.core.entity.QMeetingEntity.meetingEntity;
import static com.josh.joinus.storage.db.core.entity.QMeetingTechEntity.meetingTechEntity;
import static com.josh.joinus.storage.db.core.entity.QTechEntity.techEntity;

@Repository
@RequiredArgsConstructor
public class MeetingEntityRepository implements MeetingRepository {

    private final MeetingJpaRepository meetingJpaRepository;
    private final JPAQueryFactory queryFactory;

    //
    private final MeetingTechJpaRepository meetingTechJpaRepository;

    @Override
    public Meeting create(MeetingCreate meetingCreate) {
        return meetingJpaRepository.save(MeetingEntity.create(meetingCreate)).toDomain();
    }

    @Override
    public List<Meeting> searchByCondition(MeetingSearchCondition meetingSearchCondition) {

        List<MeetingEntity> meetingEntityList = queryFactory
                .select(meetingEntity)
                .from(meetingEntity)
                .join(meetingEntity.meetingTechEntityList, meetingTechEntity).fetchJoin()
                .join(meetingTechEntity.techEntity, techEntity).fetchJoin()
//                .where(
//                        searchMeetingType(meetingSearchCondition.getMeetingType()),
//                        searchTech(meetingSearchCondition.getTechList())
//                )
                .fetch();

        List<MeetingTechEntity> all = meetingTechJpaRepository.findAll();
        System.out.println("all = " + all);

        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();

        List<MeetingTechEntity> meetingTechEntityList = meetingEntityList.get(0).getMeetingTechEntityList();
        System.out.println("meetingTechEntityList = " + meetingTechEntityList);
        return meetingEntityList.stream().map(MeetingEntity::toDomain).collect(Collectors.toList());


//        List<MeetingEntity> meetingEntityList = meetingJpaRepository.findByCondition();
//        return meetingEntityList.stream().map(MeetingEntity::toDomain).collect(Collectors.toList());
    }

//    private BooleanExpression searchTech(List<Tech> techList) {
//        return techList.isEmpty() ? null : meetingEntity.eq(meetingType);
//    }

    private BooleanExpression searchMeetingType(MeetingType meetingType) {
        return meetingType == null ? null : meetingEntity.meetingType.eq(meetingType);
    }
}
