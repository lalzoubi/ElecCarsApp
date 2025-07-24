package com.eleccars.ElecCarsApp.repository.securityRepositories;


import com.eleccars.ElecCarsApp.model.securityModels.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, Long> {
}
