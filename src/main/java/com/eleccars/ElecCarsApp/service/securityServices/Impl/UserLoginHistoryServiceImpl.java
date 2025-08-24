package com.eleccars.ElecCarsApp.service.securityServices.Impl;


import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserLoginHistory;
import com.eleccars.ElecCarsApp.repository.securityRepositories.UserLoginHistoryRepository;
import com.eleccars.ElecCarsApp.service.securityServices.UserLoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginHistoryServiceImpl implements UserLoginHistoryService {

    final UserLoginHistoryRepository repo;

    @Transactional
    @Override
    public void saveUserLoginHistory (UserLoginHistory user) {
        repo.save(user);
    }
}
