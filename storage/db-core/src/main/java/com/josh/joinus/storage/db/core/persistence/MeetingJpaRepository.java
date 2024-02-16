package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingJpaRepository extends JpaRepository<MeetingEntity, Long> {

//    @Query(value = "select m from MeetingEntity m join fetch m.meetingTechEntityList mt join fetch mt.techEntity t")
//    List<MeetingEntity> findByCondition();
}
