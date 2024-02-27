package com.josh.joinus.core.api.dto.meeting;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.josh.joinus.core.api.util.ValidEnum;
import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.meeting.MeetingType;
import com.josh.joinus.core.domain.ProcessWay;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MeetingCreateRequest {
    @NotNull
    private Long leaderUserId;

    @NotBlank(message = "모임의 이름은 필수입니다.")
    private String meetingName;

    @NotBlank(message = "모임의 내용은 필수입니다.")
    @Size(min = 1, max = 300)
    private String content;

    @ValidEnum(enumClass = MeetingType.class)
    private MeetingType meetingType;

    @NotEmpty(message = "모임의 필요기술스택은 필수입니다.")
    private List<Long> techIdList;

    @ValidEnum(enumClass = ProcessWay.class)
    private ProcessWay processWay;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull(message = "모임의 시작진행날짜는 필수입니다.")
    private LocalDateTime startDateTime;

    @Min(2)
    private int headCount;

    @NotEmpty(message = "모임의 인원포지션은 필수입니다.")
    private List<Long> positionList;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime expiredDateTime;

    public MeetingCreate toServiceDto() {
        return MeetingCreate.builder()
                .leaderUserId(this.leaderUserId)
                .meetingName(this.meetingName)
                .meetingType(this.meetingType)
                .techIdList(this.techIdList)
                .processWay(this.processWay)
                .startDateTime(this.startDateTime)
                .headCount(this.headCount)
                .positionList(this.positionList)
                .content(this.content)
                .expiredDateTime(this.expiredDateTime)
                .build();
    }

    @Builder
    private MeetingCreateRequest(Long leaderUserId, String meetingName, String content, MeetingType meetingType,
                                List<Long> techIdList, ProcessWay processWay, LocalDateTime startDateTime,
                                int headCount, List<Long> positionList, LocalDateTime expiredDateTime) {
        this.leaderUserId = leaderUserId;
        this.meetingName = meetingName;
        this.content = content;
        this.meetingType = meetingType;
        this.techIdList = techIdList;
        this.processWay = processWay;
        this.startDateTime = startDateTime;
        this.headCount = headCount;
        this.positionList = positionList;
        this.expiredDateTime = expiredDateTime;
    }
}
