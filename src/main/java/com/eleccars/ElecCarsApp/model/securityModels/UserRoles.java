package com.eleccars.ElecCarsApp.model.securityModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role_name_ar;
    private String role_name_en;


    @OneToMany(mappedBy = "user_roles")
    private List<UserInfo> userInfoList;
}
