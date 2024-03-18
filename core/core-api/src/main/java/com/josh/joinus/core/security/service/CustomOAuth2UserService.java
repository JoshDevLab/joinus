package com.josh.joinus.core.security.service;

import com.josh.joinus.core.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        return this.process(userRequest, user);
    }

    @Transactional
    public OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {

        //ProviderType 가져오기(GOOGLE, FACEBOOK, NAVER, KAKAO)
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        //ProviderType 에 따른 유저정보 객체로 가져오기
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());

//        log.info("userInfo.getId() ===========> {}", userInfo.getId());

        User savedUser = userRepository.findByUserId(userInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) { //저장된 유저가 ProviderType 이 다르게 접속이 온경우
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
            savedUser = updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    @Transactional
    public User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        User user = User.create(userInfo, providerType, clockHolder);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUser(User user, OAuth2UserInfo userInfo) {

        if (userInfo.getName() != null && !user.getUsername().equals(userInfo.getName())) {
            user.updateUserName(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.updateProfileImg(userInfo.getImageUrl());
        }

        return user;
    }
}
