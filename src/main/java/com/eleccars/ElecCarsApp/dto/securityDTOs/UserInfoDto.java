package com.eleccars.ElecCarsApp.dto.securityDTOs;

public record UserInfoDto(

        long id,
        String username,
        String email,
        String first_name,
        String last_name,
        Integer is_user_confirmed,
        Integer is_active,
        String deletedBy,
        String deletedDate,
        long stationIdRef,
        long roleIdRef

) {

}
