package com.josh.joinus.core.domain.meeting;

import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.comment.MeetingComment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MeetingDetailResponse {

    private Meeting meeting;
    private List<Tech> techList;
    private List<Position> positionList;
    private List<MeetingComment> meetingCommentList;

    public static MeetingDetailResponse create(Meeting meeting, List<Tech> techList, List<Position> positionList,
                                               List<MeetingComment> meetingCommentList)
    {
        return MeetingDetailResponse.builder()
                .meeting(meeting)
                .techList(techList)
                .positionList(positionList)
                .meetingCommentList(meetingCommentList)
                .build();
    }

    @Builder
    private MeetingDetailResponse(Meeting meeting, List<Tech> techList, List<Position> positionList, List<MeetingComment> meetingCommentList) {
        this.meeting = meeting;
        this.techList = techList;
        this.positionList = positionList;
        this.meetingCommentList = meetingCommentList;
    }

}
