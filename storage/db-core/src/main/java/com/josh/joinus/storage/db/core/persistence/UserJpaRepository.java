package com.josh.joinus.storage.db.core.persistence;

import com.josh.joinus.core.domain.User;
import com.josh.joinus.storage.db.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String username);

    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.userId = :name where u.id = :id")
    void updateUserName(@Param("id") Long id, @Param("name")String name);

    @Modifying(clearAutomatically = true)
    @Query("update UserEntity u set u.imgUrl = :imageUrl where u.id = :id")
    void updateProfileImg(@Param("id")Long id, @Param("imageUrl")String imageUrl);
}
