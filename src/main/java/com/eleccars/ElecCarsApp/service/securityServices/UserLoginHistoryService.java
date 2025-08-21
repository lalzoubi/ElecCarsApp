package com.eleccars.ElecCarsApp.service.securityServices;


import com.eleccars.ElecCarsApp.model.entities.securityModels.UserLoginHistory;
import com.eleccars.ElecCarsApp.repository.securityRepositories.UserLoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLoginHistoryService {

    @Autowired
    UserLoginHistoryRepository repo;

    @Transactional
    public void saveUserLoginHistory (UserLoginHistory user) {
        repo.save(user);
    }
}
