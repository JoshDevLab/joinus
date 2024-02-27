package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class User extends Base {
    private Long id;
    private String nickname;
    private Long positionId;
    private int careerYear;
    private List<Tech> techList;

    public void setTechList(List<Tech> techList) {
        this.techList = techList;
    }

    @Builder
    private User(Long id, String nickname, Long positionId, int careerYear, List<Tech> techList) {
        this.id = id;
        this.nickname = nickname;
        this.positionId = positionId;
        this.careerYear = careerYear;
        this.techList = techList;
    }
}
