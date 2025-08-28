package com.eleccars.ElecCarsApp.service.securityServices;

import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoReqDto;
import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoResDto;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserInfoService {

    void registerUser(UserInfoReqDto user);

    String verifyUser(String username, String password);

    UserInfoResDto fetchUserDetails(String username);

    UserInfoResDto findByIsActiveTrue(String username);

    UserInfoResDto findByIsUserConfirmedTrue(String username);

    Optional<UserInfoResDto> findUser(String identifier);
}
