package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.MeetingTechRepository;
import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import com.josh.joinus.storage.db.core.entity.MeetingTechEntity;
import com.josh.joinus.storage.db.core.entity.TechEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingTechJpaRepository;
import com.josh.joinus.storage.db.core.persistence.TechJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MeetingTechEntityRepository implements MeetingTechRepository {

    private final MeetingTechJpaRepository meetingTechJpaRepository;
    private final MeetingJpaRepository meetingJpaRepository;
    private final TechJpaRepository techJpaRepository;

    @Override
    public void create(Long meetingId, List<Long> techIdList) {
        MeetingEntity meetingEntity = meetingJpaRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalArgumentException("모임이 존재하지 않습니다."));

        List<TechEntity> techEntityList = techJpaRepository.findByIdIn(techIdList);

        techEntityList.forEach(techEntity -> {
            meetingTechJpaRepository.save(MeetingTechEntity.create(meetingEntity, techEntity));
        });
    }
}
