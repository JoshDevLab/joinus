package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.Meeting;
import com.josh.joinus.core.domain.MeetingCreate;
import com.josh.joinus.core.domain.MeetingRepository;
import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MeetingEntityRepository implements MeetingRepository {

    private final MeetingJpaRepository meetingJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Meeting create(MeetingCreate meetingCreate) {
        return meetingJpaRepository.save(MeetingEntity.create(meetingCreate)).toDomain();
    }
}
