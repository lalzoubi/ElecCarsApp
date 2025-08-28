package com.eleccars.ElecCarsApp.model.entities.securityEntities;

import com.eleccars.ElecCarsApp.model.entities.stationsEntities.StationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "sec_users_roles")
public class SecUsersRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private UserRoles userRoles;
}
