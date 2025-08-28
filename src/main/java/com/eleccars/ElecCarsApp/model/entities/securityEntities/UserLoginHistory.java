package com.eleccars.ElecCarsApp.model.entities.securityEntities;

import com.eleccars.ElecCarsApp.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user_login_history")
public class UserLoginHistory extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login_time")
    private String loginTime;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "device_type")
    private String deviceType;
    @Column(name = "device_info")
    private String deviceInfo;
    @Column(name = "jwt_token_ref")
    private String jwtTokenRef;


    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "user_id_ref")
    private UserInfo user_history;

}
