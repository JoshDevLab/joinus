package com.josh.joinus.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class User extends Base {
    private Long id;
    private String nickname;
    private Position position;
    private int careerYear;
    private List<Tech> techList;

    public void setTechList(List<Tech> techList) {
        this.techList = techList;
    }

    @Builder
    private User(Long id, String nickname, Position position, int careerYear, List<Tech> techList) {
        this.id = id;
        this.nickname = nickname;
        this.position = position;
        this.careerYear = careerYear;
        this.techList = techList;
    }
}
