package com.eleccars.ElecCarsApp.service.securityServices;

import com.eleccars.ElecCarsApp.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.exceptionHandler.UserNotFoundException;
import com.eleccars.ElecCarsApp.mapper.securityMappers.UserInfoMapper;
import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;
import com.eleccars.ElecCarsApp.repository.securityRepositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository repo;

    @Autowired
    AuthenticationManager authenticationManager;

    UserInfoMapper userInfoMapper = new UserInfoMapper();

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public void registerUser(UserInfo user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    public int verifyUser(String username, String password) {
        try {

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated())
                return 1;

        } catch (BadCredentialsException e) {
            return 0;
        }
        return 0;
    }

    @Transactional(readOnly = true)
    public List<UserInfoDto> fetchUserDetails(String username) {
        List<UserInfoDto> user = repo.fetchUserDetails(username).stream().map(userInfoMapper).collect(Collectors.toList());
        if (user != null)
            return user;
        else
            throw new UserNotFoundException(0, "خطأ في اسم المستخدم او كلمة المرور", "The username or password is not correct");
    }

    @Transactional(readOnly = true)
    public List<UserInfoDto> findUser(String identifier) {
        // إذا كان query رقم، اعتبره ID
        if (identifier.matches("\\d+")) {
            return repo.findById(Long.parseLong(identifier)).stream().map(userInfoMapper).collect(Collectors.toList());
        }

        // إذا يحتوي @، اعتبره Email
        if (identifier.contains("@")) {
            return repo.findByEmail(identifier);
        }

        // غير ذلك اعتبره Username
        return repo.findByUsername(identifier);
    }

}


