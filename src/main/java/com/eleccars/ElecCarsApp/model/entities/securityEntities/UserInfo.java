package com.eleccars.ElecCarsApp.model.entities.securityEntities;

import com.eleccars.ElecCarsApp.model.base.BaseEntity;
import com.eleccars.ElecCarsApp.model.entities.stationsEntities.StationInfo;
import com.eleccars.ElecCarsApp.model.base.BaseEntity;
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
public class UserInfo extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany
    @JoinTable(
            name = "users_roles", // اسم الجدول الوسيط
            joinColumns = @JoinColumn(name = "user_id"), // اسم العمود من جهة الطالب
            inverseJoinColumns = @JoinColumn(name = "role_id") // من جهة الكورس
    )
    private List<UserRoles> user_roles = new ArrayList<>();

    @OneToMany(mappedBy = "user_history")
    private List<UserLoginHistory> userLoginHistoryList;


}
