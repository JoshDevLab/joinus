package com.josh.joinus.core.dto.request;

import com.josh.joinus.core.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingSearchCondition {
    private MeetingType meetingType;
    private List<Tech> techList;
    private Position position;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;

}
