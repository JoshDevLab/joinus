package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class User extends Base {
    private Long id;
    private String userId;
    private String nickname;
    private Long positionId;
    private int careerYear;
    private RoleType roleType;
    private List<Tech> techList;

    public void setTechList(List<Tech> techList) {
        this.techList = techList;
    }

    @Builder
    private User(Long id, String userId, String nickname, Long positionId, int careerYear, RoleType roleType, List<Tech> techList) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.positionId = positionId;
        this.careerYear = careerYear;
        this.roleType = roleType;
        this.techList = techList;
    }
}
