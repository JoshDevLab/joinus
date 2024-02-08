package com.josh.joinus.core.domain;

import com.josh.joinus.core.dto.request.UserCreateRequest;
import com.josh.joinus.core.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserWriter userWriter;
    private final TechReader techReader;
    private final UserTechWriter userTechWriter;

    public User register(UserCreateRequest userCreateRequest) {
        User user = userWriter.register(userCreateRequest);
        userTechWriter.register(user.getId(), userCreateRequest);
        List<Tech> techList = techReader.findByTechIdIn(userCreateRequest.getTechIdList());
        user.setTechList(techList);

        return user;
    }
}
