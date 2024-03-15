package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.RoleType;
import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String nickname;
    private Long positionId;
    private int careerYear;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public static UserEntity create(UserCreateRequest userCreateRequest) {
        return UserEntity.builder()
                .nickname(userCreateRequest.getNickname())
                .positionId(userCreateRequest.getPositionId())
                .careerYear(userCreateRequest.getCareerYear())
                .build();
    }

    @Builder
    public UserEntity(Long id, String userId, String nickname, Long positionId, int careerYear, Long userTechId, RoleType roleType) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.positionId = positionId;
        this.careerYear = careerYear;
        this.roleType = roleType;
    }

    public User toDomain() {
        return User.builder()
                .id(id)
                .userId(userId)
                .careerYear(careerYear)
                .nickname(nickname)
                .positionId(positionId)
                .roleType(roleType)
                .build();
    }
}
