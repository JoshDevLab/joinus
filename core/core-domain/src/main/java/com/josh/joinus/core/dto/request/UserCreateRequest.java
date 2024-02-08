package com.josh.joinus.core.dto.request;

import com.josh.joinus.core.domain.Position;
import lombok.Getter;

import java.util.List;

@Getter
public class UserCreateRequest {
    private String nickname;
    private Position position;
    private int careerYear;
    private List<Long> techIdList;
}
