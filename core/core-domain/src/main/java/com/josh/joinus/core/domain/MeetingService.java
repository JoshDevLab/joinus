package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.MeetingSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingWriter meetingWriter;
    private final MeetingTechWriter meetingTechWriter;
    private final MeetingReader meetingReader;

    // 추후 트랜잭션 의존성 주입 후 어노테이션 추가
    @Transactional
    public Meeting create(MeetingCreate meetingCreate) {
        Meeting meeting = meetingWriter.create(meetingCreate);
        meetingTechWriter.create(meeting.getId(), meetingCreate.getTechIdList());
        return meeting;
    }

//    public List<Meeting> search(MeetingSearchCondition condition) {
//        //List<Meeting> meeting = meetingReader.searchByCondition(condition);
//        //return meeting;
//    }
}
