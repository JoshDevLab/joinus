package com.josh.joinus.storage.db.core.repository;

import com.josh.joinus.core.domain.UserTechRepository;
import com.josh.joinus.core.dto.request.UserCreateRequest;
import com.josh.joinus.storage.db.core.entity.UserTechEntity;
import com.josh.joinus.storage.db.core.persistence.UserTechJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserTechEntityRepository implements UserTechRepository {
    private final UserTechJpaRepository userTechJpaRepository;
    @Override
    public void register(Long userId, UserCreateRequest userCreateRequest) {
        List<UserTechEntity> userTechEntityList = userCreateRequest.getTechIdList()
                .stream().map(techId -> UserTechEntity.builder()
                        .userId(userId)
                        .techId(techId)
                        .build())
                .toList();

        userTechJpaRepository.saveAll(userTechEntityList);
    }
}
