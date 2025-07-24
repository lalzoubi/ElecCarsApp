package com.eleccars.ElecCarsApp.mapper.securityMappers;

import com.eleccars.ElecCarsApp.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;

import java.util.function.Function;

public class UserInfoMapper implements Function<UserInfo, UserInfoDto> {

    @Override
    public UserInfoDto apply(UserInfo userInfo) {

        return new UserInfoDto(userInfo.getId(),
                userInfo.getUsername(),
                userInfo.getEmail(),
                userInfo.getFirst_name(),
                userInfo.getLast_name(),
                userInfo.getIs_user_confirmed(),
                userInfo.getIs_active(),
                userInfo.getDeletedBy(),
                userInfo.getDeletedDate(),
                userInfo.getUsers_station().getId(),
                userInfo.getUser_roles().getId()
        );
    }

}
