package com.eleccars.ElecCarsApp.model.securityModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login_time;
    private String ip_address;
    private String device_type;
    private String device_info;

    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "userIdRef")
    private UserInfo user_history;

}
