package com.josh.joinus.storage.db.core.entity;

import com.josh.joinus.core.domain.Position;
import com.josh.joinus.core.domain.User;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Position position;
    private int careerYear;
    private Long userTechId;

    public static UserEntity create(UserCreateRequest userCreateRequest) {
        return UserEntity.builder()
                .nickname(userCreateRequest.getNickname())
                .position(userCreateRequest.getPosition())
                .careerYear(userCreateRequest.getCareerYear())
                .build();
    }

    @Builder
    private UserEntity(String nickname, Position position, int careerYear, Long userTechId) {
        this.nickname = nickname;
        this.position = position;
        this.careerYear = careerYear;
        this.userTechId = userTechId;
    }

    public User toDomain() {
        return User.builder()
                .id(id)
                .careerYear(careerYear)
                .nickname(nickname)
                .position(position)
                .build();
    }
}
