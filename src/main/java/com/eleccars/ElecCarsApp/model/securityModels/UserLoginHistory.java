package com.eleccars.ElecCarsApp.model.securityModels;

import com.eleccars.ElecCarsApp.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginHistory extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login_time;
    private String ip_address;
    private String device_type;
    private String device_info;
    private String jwt_token_ref;


    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "userIdRef")
    private UserInfo user_history;

}
