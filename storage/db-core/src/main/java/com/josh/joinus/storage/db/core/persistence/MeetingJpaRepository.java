package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.meeting.Meeting;
import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingJpaRepository extends JpaRepository<MeetingEntity, Long> {

    @Modifying
    @Query("update MeetingEntity m set m.viewCount = :viewCount where m.id = :meetingId")
    void updateViewCount(@Param("meetingId") Long meetingId, @Param("viewCount") int viewCount);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select m from MeetingEntity m where m.id = :meetingId")
    MeetingEntity findByIdLock(@Param("meetingId") Long meetingId);
}
