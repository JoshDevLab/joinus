package com.josh.joinus.core.dto.response;

import com.josh.joinus.core.domain.Tech;
import com.josh.joinus.core.domain.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponse {
    private Long id;
    private String nickname;
    private List<Tech> techList;
}
