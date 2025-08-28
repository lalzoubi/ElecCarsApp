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
public class UserInfoReqDto {

    private long id;
    @NotNull(message = "The username should not be null.")
    @NotBlank(message = "The username should not be empty.")
    private String username;
    @NotNull(message = "The password should not be null.")
    @NotBlank(message = "The password should not be empty.")
    private String password;
    @NotNull(message = "The email should not be null.")
    @NotBlank(message = "The email should not be empty.")
    @Email(message = "Please enter a valid email.")
    private String email;
    @NotNull(message = "The first_name should not be null.")
    @NotBlank(message = "The first_name should not be empty.")
    private String first_name;
    @NotNull(message = "The last_name should not be null.")
    @NotBlank(message = "The last_name should not be empty.")
    private String last_name;
    private Boolean userConfirmed;
    private Boolean userActive;
    private String deletedBy;
    private String deletedDate;
    private long stationIdRef;
    //    private long roleIdRef;
    private List<UserRoles> user_roles;

}
