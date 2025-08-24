package com.eleccars.ElecCarsApp.service.securityServices.Impl;

import com.eleccars.ElecCarsApp.exceptionHandler.UserNotFoundException;
import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import com.eleccars.ElecCarsApp.model.mapper.securityMappers.UserInfoMapper;
import com.eleccars.ElecCarsApp.repository.securityRepositories.UserInfoRepository;
import com.eleccars.ElecCarsApp.service.securityServices.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    final UserInfoRepository repo;
    final AuthenticationManager authenticationManager;
    final UserInfoMapper userInfoMapper;
    final JWTServiceImpl jwtServiceImpl;

    @Transactional
    @Override
    public void registerUser(UserInfo user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    @Override
    public String verifyUser(String username, String password) {
        try {

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated())
                return jwtServiceImpl.generateToken(username);

        } catch (BadCredentialsException e) {
            return null;
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto fetchUserDetails(String username) {
        Optional<UserInfo> info = repo.findByUsername(username);
        if (info.isPresent()) {
            return userInfoMapper.toDto(info.get());
        } else
            throw new UserNotFoundException(0, "خطأ في اسم المستخدم او كلمة المرور", "The username or password is not correct");
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfo findByIsActiveTrue(String username) {
        Optional<UserInfo> info = repo.findByUsernameAndUserActiveTrue(username);

        return info.orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfo findByIsUserConfirmedTrue(String username) {
        Optional<UserInfo> info = repo.findByUsernameAndUserConfirmedTrue(username);

        return info.orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserInfo> findUser(String identifier) {
        // إذا كان query رقم، اعتبره ID
        if (identifier.matches("\\d+")) {
            return repo.findById(Long.parseLong(identifier));
        }

        // إذا يحتوي @، اعتبره Email
        if (identifier.contains("@")) {
            return repo.findByEmail(identifier);
        }

        // غير ذلك اعتبره Username
        return repo.findByUsername(identifier);
    }

}


