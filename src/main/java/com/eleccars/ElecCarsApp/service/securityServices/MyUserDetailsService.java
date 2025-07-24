package com.eleccars.ElecCarsApp.service.securityServices;

import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;
import com.eleccars.ElecCarsApp.model.securityModels.UserPrincipal;
import com.eleccars.ElecCarsApp.repository.securityRepositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = repo.getByUsernameForLogin(username);

        if (user == null){
            user = new UserInfo();
            user.setUsername(username);
            user.setPassword("-1");
            new UserPrincipal(user);
        }

        return new UserPrincipal(user);
    }

}
