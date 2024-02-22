package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.MeetingPositionRepository;
import com.josh.joinus.storage.db.core.entity.*;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingPositionJpaRepository;
import com.josh.joinus.storage.db.core.persistence.PositionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingPositionEntityRepository implements MeetingPositionRepository {
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
}
