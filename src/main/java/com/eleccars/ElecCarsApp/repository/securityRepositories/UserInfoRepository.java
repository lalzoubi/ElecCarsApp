package com.eleccars.ElecCarsApp.repository.securityRepositories;

import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfo, Long> {

    @Query("SELECT users FROM UserInfo users where users.username = :username")
    UserInfo fetchUserDetails(@Param("username")  String username);

    // JPQL
    @Query("SELECT sec FROM UserInfo sec where sec.username = :username")
    UserInfo getByUsernameForLogin(@Param("username") String username);

    Optional<UserInfo> findByEmail(@Param("email") String email);
    Optional<UserInfo> findByUsername(@Param("username") String username);
    Optional<UserInfo> findByUsernameAndUserActiveTrue(@Param("username") String username);
    Optional<UserInfo> findByUsernameAndUserConfirmedTrue(@Param("username") String username);

}
