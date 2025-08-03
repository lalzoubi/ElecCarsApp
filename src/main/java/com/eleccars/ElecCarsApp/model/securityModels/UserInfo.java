package com.eleccars.ElecCarsApp.model.securityModels;

import com.eleccars.ElecCarsApp.base.BaseEntity;
import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    @Column(name = "is_user_confirmed")
    private Boolean userConfirmed;
    @Column(name = "is_user_active")
    private Boolean userActive;
    private String deletedBy;
    private String deletedDate;

    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "stationIdRef")
    private StationInfo users_station;

    @ManyToOne()
    @JoinColumn(name = "roleIdRef")
    private UserRoles user_roles;

    @OneToMany(mappedBy = "user_history")
    private List<UserLoginHistory> userLoginHistoryList;



}
