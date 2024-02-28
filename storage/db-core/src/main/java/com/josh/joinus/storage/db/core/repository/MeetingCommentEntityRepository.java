package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.meeting.MeetingComment;
import com.josh.joinus.core.domain.meeting.MeetingCommentRepository;
import com.josh.joinus.storage.db.core.entity.MeetingCommentEntity;
import com.josh.joinus.storage.db.core.entity.QMeetingCommentEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingCommentJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.josh.joinus.storage.db.core.entity.QMeetingCommentEntity.meetingCommentEntity;

@Repository
@RequiredArgsConstructor
public class MeetingCommentEntityRepository implements MeetingCommentRepository {

    private final MeetingCommentJpaRepository meetingCommentJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MeetingComment> findByMeetingId(Long meetingId) {
        List<MeetingCommentEntity> data = queryFactory
                .selectFrom(meetingCommentEntity)
                .where(meetingCommentEntity.meetingEntity.id.eq(meetingId))
                .fetch();

        return data.stream().map(entity ->
                    MeetingComment.builder()
                        .id(entity.getId())
                        .user(entity.getUser().toDomain())
                        .content(entity.getContent())
                        .build()
                ).toList();
    }
}
