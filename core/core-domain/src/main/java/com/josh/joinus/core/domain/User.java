package com.josh.joinus.core.domain;

import com.josh.joinus.core.domain.oauth2.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class User extends Base {
    private Long id;
    private String userId;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String emailVerifiedYn;
    private String profileImageUrl;
    private Long positionId;
    private int careerYear;
    private RoleType roleType;
    private ProviderType providerType;
    private List<Tech> techList;

    public static User create(OAuth2UserInfo userInfo, ProviderType providerType) {
        return User.builder()
                .userId(userInfo.getId())
                .username(userInfo.getName())
                .email(userInfo.getEmail())
                .password("Y")
                .profileImageUrl(userInfo.getImageUrl())
                .providerType(providerType)
                .emailVerifiedYn("Y")
                .roleType(RoleType.USER)
                .build();
    }

    public void setTechList(List<Tech> techList) {
        this.techList = techList;
    }

    public void updateUserName(String name) {
        this.username = name;
    }

    public void updateProfileImg(String imageUrl) {
        this.profileImageUrl = imageUrl;
    }

    @Builder
    private User(Long id, String userId, String nickname, String username, String password, String email,
                String emailVerifiedYn, String profileImageUrl, Long positionId, int careerYear, RoleType roleType,
                ProviderType providerType, List<Tech> techList)
    {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl;
        this.positionId = positionId;
        this.careerYear = careerYear;
        this.roleType = roleType;
        this.providerType = providerType;
        this.techList = techList;
    }
}
