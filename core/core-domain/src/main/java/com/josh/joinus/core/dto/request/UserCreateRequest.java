package com.josh.joinus.core.dto.request;

import com.josh.joinus.core.domain.Position;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserCreateRequest {
    private String nickname;
    private Long positionId;
    private int careerYear;
    private List<Long> techIdList;

    @Builder
    public UserCreateRequest(String nickname, Long positionId, int careerYear, List<Long> techIdList) {
        this.nickname = nickname;
        this.positionId = positionId;
        this.careerYear = careerYear;
        this.techIdList = techIdList;
    }
}
