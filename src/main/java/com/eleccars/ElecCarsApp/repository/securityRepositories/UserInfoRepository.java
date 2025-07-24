package com.eleccars.ElecCarsApp.repository.securityRepositories;

import com.eleccars.ElecCarsApp.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfo, Long> {

    @Query("SELECT users FROM UserInfo users where users.username = :username")
    List<UserInfo> fetchUserDetails(@Param("username")  String username);

    @Query("SELECT sec FROM UserInfo sec where sec.username = :username")
    UserInfo getByUsernameForLogin(@Param("username") String username);


    @Query("SELECT sec.id,\n" +
            "    sec.username,\n" +
            "    sec.email,\n" +
            "    sec.first_name,\n" +
            "    sec.last_name,\n" +
            "    sec.is_user_confirmed,\n" +
            "    sec.is_active,\n" +
            "    sec.deletedBy,\n" +
            "    sec.deletedDate,\n" +
            "    sec.users_station.id,\n" +
            "    sec.user_roles.id FROM UserInfo sec where sec.email = :email")
    List<UserInfoDto> findByEmail(@Param("email") String email);

    @Query("SELECT sec.id,\n" +
            "    sec.username,\n" +
            "    sec.email,\n" +
            "    sec.first_name,\n" +
            "    sec.last_name,\n" +
            "    sec.is_user_confirmed,\n" +
            "    sec.is_active,\n" +
            "    sec.deletedBy,\n" +
            "    sec.deletedDate,\n" +
            "    sec.users_station.id,\n" +
            "    sec.user_roles.id FROM UserInfo sec where sec.username = :username")
    List<UserInfoDto> findByUsername(@Param("username") String username);


}
