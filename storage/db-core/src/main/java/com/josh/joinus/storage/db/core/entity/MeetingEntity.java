package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long leaderUserId;
    private String meetingName;
    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;
    @Transient
    private List<Long> techList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ProcessWay processWay;
    @Enumerated(EnumType.STRING)
    private MeetingStatus meetingStatus;
    private LocalDateTime startDateTime;
    private int headCount;
    private LocalDateTime expiredDateTime;
    private List<Position> positionList = new ArrayList<>();
}
