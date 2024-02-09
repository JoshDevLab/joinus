package com.josh.joinus.core.dto.request;

import com.josh.joinus.core.domain.Position;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserCreateRequest {
    private String nickname;
    private Position position;
    private int careerYear;
    private List<Long> techIdList;

    @Builder
    private UserCreateRequest(String nickname, Position position, int careerYear, List<Long> techIdList) {
        this.nickname = nickname;
        this.position = position;
        this.careerYear = careerYear;
        this.techIdList = techIdList;
    }
}
