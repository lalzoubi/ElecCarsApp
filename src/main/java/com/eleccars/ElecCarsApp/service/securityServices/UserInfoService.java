package com.eleccars.ElecCarsApp.service.securityServices;

import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserInfoService {

    void registerUser(UserInfo user);

    String verifyUser(String username, String password);

    UserInfoDto fetchUserDetails(String username);

    UserInfo findByIsActiveTrue(String username);

    UserInfo findByIsUserConfirmedTrue(String username);

    Optional<UserInfo> findUser(String identifier);
}
