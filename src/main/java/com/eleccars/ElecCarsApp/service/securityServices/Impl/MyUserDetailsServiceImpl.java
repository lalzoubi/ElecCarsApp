package com.eleccars.ElecCarsApp.service.securityServices.Impl;

import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserPrincipal;
import com.eleccars.ElecCarsApp.repository.securityRepositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {

    final UserInfoRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = repo.getByUsernameForLogin(username);
        return new UserPrincipal(user);
    }

}
