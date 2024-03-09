package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.comment.MeetingComment;
import com.josh.joinus.core.domain.comment.MeetingCommentCreate;
import com.josh.joinus.core.domain.comment.MeetingCommentRepository;
import com.josh.joinus.storage.db.core.entity.MeetingCommentEntity;
import com.josh.joinus.storage.db.core.entity.MeetingEntity;
import com.josh.joinus.storage.db.core.entity.UserEntity;
import com.josh.joinus.storage.db.core.persistence.MeetingCommentJpaRepository;
import com.josh.joinus.storage.db.core.persistence.MeetingJpaRepository;
import com.josh.joinus.storage.db.core.persistence.UserJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.josh.joinus.storage.db.core.entity.QMeetingCommentEntity.meetingCommentEntity;

@Repository
@RequiredArgsConstructor
public class MeetingCommentEntityRepository implements MeetingCommentRepository {

    private final MeetingCommentJpaRepository meetingCommentJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final MeetingJpaRepository meetingJpaRepository;
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
                        .user(entity.getUserEntity().toDomain())
                        .content(entity.getContent())
                        .build()
                ).toList();
    }

    @Override
    public MeetingComment create(MeetingCommentCreate meetingCommentCreate) {
        MeetingEntity meetingEntity = meetingJpaRepository.findById(meetingCommentCreate.getMeetingId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모임"));

        UserEntity userEntity = userJpaRepository.findById(meetingCommentCreate.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));
        return meetingCommentJpaRepository.save(MeetingCommentEntity
                .create(meetingCommentCreate.getContent(), userEntity, meetingEntity)).toDomain();
    }
}
