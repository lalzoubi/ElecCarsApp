package com.eleccars.ElecCarsApp.model.entities.securityEntities;

import com.eleccars.ElecCarsApp.model.base.BaseEntity;
import com.eleccars.ElecCarsApp.model.entities.stationsEntities.StationInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_info")
public class UserInfo extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String username;
    private String password;
    private String email;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "is_user_confirmed")
    private Boolean userConfirmed;
    @Column(name = "is_user_active")
    private Boolean userActive;
    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "deleted_date")
    private String deletedDate;

    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "station_id_ref")
    private StationInfo users_station;

    @OneToMany(mappedBy = "user_history")
    private List<UserLoginHistory> userLoginHistoryList;

    @OneToMany(mappedBy = "userInfo")
    private List<SecUsersRoles> secUsersRoles;


  /*  @ManyToMany
    @JoinTable(
            name = "users_roles", // اسم الجدول الوسيط
            joinColumns = @JoinColumn(name = "user_id"), // اسم العمود من جهة الطالب
            inverseJoinColumns = @JoinColumn(name = "role_id") // من جهة الكورس
    )

    private List<UserRoles> user_roles = new ArrayList<>();*/



}
