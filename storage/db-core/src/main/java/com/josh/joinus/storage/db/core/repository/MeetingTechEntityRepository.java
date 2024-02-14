package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.MeetingTechRepository;
import com.josh.joinus.storage.db.core.entity.MeetingTechEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingTechJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingTechEntityRepository implements MeetingTechRepository {

    private final MeetingTechJpaRepository meetingTechJpaRepository;

    @Override
    public void create(Long meetingId, List<Long> techIdList) {
        techIdList.forEach(techId -> {
            meetingTechJpaRepository.save(MeetingTechEntity.create(meetingId, techId));
        });
    }
}
