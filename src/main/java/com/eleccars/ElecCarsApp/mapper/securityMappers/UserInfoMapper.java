package com.eleccars.ElecCarsApp.mapper.securityMappers;

import com.eleccars.ElecCarsApp.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;
import com.eleccars.ElecCarsApp.model.securityModels.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

//    @Mappings({
//            @Mapping(source = "stationIdRef", target = "users_station.id"),
//            @Mapping(source = "user_roles", target = "user_roles"),
//            @Mapping(source = "userActive", target = "userActive"),
//            @Mapping(source = "userConfirmed", target = "userConfirmed")})
//    UserInfo toEntity(UserInfoDto dto);

    @Mappings({
            @Mapping(source = "users_station.id", target = "stationIdRef"),
            @Mapping(source = "user_roles", target = "user_roles"),
            @Mapping(source = "userActive", target = "userActive"),
            @Mapping(source = "userConfirmed", target = "userConfirmed")})
    UserInfoDto toDto(UserInfo entity);



}
