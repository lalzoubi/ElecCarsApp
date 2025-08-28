package com.eleccars.ElecCarsApp.model.dto.securityDTOs;

import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResDto {

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
    private List<UserRoles> user_roles;

}
