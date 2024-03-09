package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.domain.comment.MeetingComment;
import com.josh.joinus.core.domain.comment.MeetingCommentCreate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingCommentEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meetingEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    private String content;

    @Builder
    private MeetingCommentEntity(Long id, MeetingEntity meetingEntity, UserEntity userEntity, String content) {
        this.id = id;
        this.meetingEntity = meetingEntity;
        this.userEntity = userEntity;
        this.content = content;
    }

    public static MeetingCommentEntity create(String content, UserEntity userEntity, MeetingEntity meetingEntity) {
        return MeetingCommentEntity.builder()
                .userEntity(userEntity)
                .meetingEntity(meetingEntity)
                .content(content)
                .build();
    }

    public MeetingComment toDomain() {
        return MeetingComment.builder()
                .id(this.id)
                .build();
    }
}
