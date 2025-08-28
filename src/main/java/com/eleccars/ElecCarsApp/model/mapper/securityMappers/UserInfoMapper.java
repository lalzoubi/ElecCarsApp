package com.eleccars.ElecCarsApp.model.mapper.securityMappers;

import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoReqDto;
import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoResDto;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    @Mappings({
            @Mapping(source = "users_station.id", target = "stationIdRef"),
            @Mapping(source = "user_roles", target = "user_roles"),
            @Mapping(source = "userActive", target = "userActive"),
            @Mapping(source = "userConfirmed", target = "userConfirmed")})
    UserInfoReqDto toReqDto(UserInfo entity);
    UserInfoResDto toResDto(UserInfo entity);
    UserInfo toEntity(UserInfoReqDto dto);

}
