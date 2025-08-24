package com.eleccars.ElecCarsApp.service.securityServices;


import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserLoginHistory;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginHistoryService {

    void saveUserLoginHistory (UserLoginHistory user);
}
