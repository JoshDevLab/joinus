package com.josh.joinus.core.dto.request;

import com.josh.joinus.core.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeetingSearchCondition {
    private MeetingType meetingType;
    private List<Tech> techList;
    private Position position;
    private ProcessWay processWay;
    private MeetingStatus meetingStatus;
}
