package com.josh.joinus.core.api.dto.meeting;

import com.josh.joinus.core.domain.meeting.MeetingCreate;
import com.josh.joinus.core.domain.MeetingType;
import com.josh.joinus.core.domain.ProcessWay;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingCreateRequest {
    @NotNull
    private Long leaderUserId;

    @NotBlank(message = "모임의 이름은 필수입니다.")
    private String meetingName;

    @NotBlank(message = "모임의 내용은 필수입니다.")
    @Size(min = 1, max = 300)
    private String content;

    @NotBlank(message = "모임의 목적은 필수입니다.")
    private MeetingType meetingType;

    @NotBlank(message = "모임의 필요기술스택은 필수입니다.")
    private List<Long> techIdList;

    @NotBlank(message = "모임의 진행방식은 필수입니다.")
    private ProcessWay processWay;

    @NotBlank(message = "모임의 시작진행날짜는 필수입니다.")
    private LocalDateTime startDateTime;

    @NotBlank(message = "모임의 인원은 필수입니다.")
    private int headCount;

    @NotBlank(message = "모임의 인원포지션은 필수입니다.")
    @NotBlank
    private List<Long> positionList;

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
}
