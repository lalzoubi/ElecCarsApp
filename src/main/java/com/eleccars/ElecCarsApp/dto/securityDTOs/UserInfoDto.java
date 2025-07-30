package com.eleccars.ElecCarsApp.dto.securityDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto{

    private long id;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private Boolean userConfirmed;
    private Boolean userActive;
    private String deletedBy;
    private String deletedDate;
    private long stationIdRef;
    private long roleIdRef;

}
