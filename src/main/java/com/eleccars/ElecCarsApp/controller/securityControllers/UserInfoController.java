package com.eleccars.ElecCarsApp.controller.securityControllers;


import com.eleccars.ElecCarsApp.model.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.javautils.JavaSpringUtils;
import com.eleccars.ElecCarsApp.javautils.RequestUtils;
import com.eleccars.ElecCarsApp.model.mapper.securityMappers.UserInfoMapper;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserLoginHistory;
import com.eleccars.ElecCarsApp.service.securityServices.Impl.UserInfoServiceImpl;
import com.eleccars.ElecCarsApp.service.securityServices.Impl.UserLoginHistoryServiceImpl;
import com.eleccars.ElecCarsApp.javautils.ApiCallResponse;
import com.eleccars.ElecCarsApp.service.securityServices.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserInfoController {

    final UserInfoService service;
    final UserLoginHistoryServiceImpl userLoginHistoryServiceImpl;
    final UserInfoMapper userInfoMapper;
    final JavaSpringUtils JavaSpringUtils;
    final HttpServletRequest request;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid UserInfo user) {

        Optional<UserInfo> info = service.findUser(user.getUsername());
        if (info.isPresent()) {
            return ApiCallResponse.generateResponse(0, "اسم المستخدم مسجل مسبقاً", "The user already registered", HttpStatus.OK, null);
        }

        service.registerUser(user);
        user.setPassword(null);
        return ApiCallResponse.generateResponse(0, "تم تسجيل المستخدم بنجاح", "The user registered  successfully", HttpStatus.OK, null);
    }

    @GetMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        String verifyUserResponse = service.verifyUser(username, password);
        if (verifyUserResponse != null) {
            UserInfoDto userInfoResponse = service.fetchUserDetails(username);
            if (userInfoResponse != null) {
                // insert into history table
                UserLoginHistory history = new UserLoginHistory();
                history.setLogin_time(JavaSpringUtils.getCurrentDate());
                String clientIp = RequestUtils.getClientIp(request);
                history.setIp_address(clientIp); // set the IP
                history.setDevice_type(RequestUtils.getDeviceDetails(request));
                history.setDevice_info(RequestUtils.getUserAgent(request));
                UserInfo user = new UserInfo();
                user.setId(userInfoResponse.getId()); // set the foreign key
                history.setUser_history(user);
                history.setJwt_token_ref(verifyUserResponse);
                userLoginHistoryServiceImpl.saveUserLoginHistory(history);
                return ApiCallResponse.generateResponseWithToken(1, "تم استرجاع البيانات بنجاح", "The data retrieved successfully", HttpStatus.OK, userInfoResponse, verifyUserResponse);
            } else
                return ApiCallResponse.generateResponse(0, "خطأ في كلمة السر او كلمة المرور", "The username or password is not correct", HttpStatus.OK, null);
        } else
            return ApiCallResponse.generateResponse(0, "خطأ في كلمة السر او كلمة المرور", "The username or password is not correct", HttpStatus.OK, null);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserByIdentifier(@RequestParam("id") String id) {
        Optional<UserInfo> info = service.findUser(id);
        if (info.isPresent()) {
            UserInfoDto userInfoResponse = userInfoMapper.toDto(info.get());
            return ApiCallResponse.generateResponse(1, "تم استرجاع البيانات بنجاح", "The data retrieved successfully", HttpStatus.OK, userInfoResponse);
        }
        return ApiCallResponse.generateResponse(0, "المستخدم غير موجود", "User not found", HttpStatus.OK, null);
    }

    @GetMapping("/userIsActive")
    public ResponseEntity<?> getIsActiveUser(@RequestParam("username") String username) {

        Optional<UserInfo> info = service.findUser(username);

        if (info.isPresent()) {
            UserInfo userInfoResponse = service.findByIsActiveTrue(username);
            if (userInfoResponse != null)
                return ApiCallResponse.generateResponse(1, "المستخدم  مفعل", "User is active", HttpStatus.OK, null);
            else
                return ApiCallResponse.generateResponse(0, "المستخدم غير مفعل", "User is inactive", HttpStatus.OK, null);
        }
        return ApiCallResponse.generateResponse(0, "المستخدم غير موجود", "User not found", HttpStatus.OK, null);

    }

    @GetMapping("/userIsConfirmed")
    public ResponseEntity<?> getIsConfirmedUserByIdentifier(@RequestParam("username") String username) {

        Optional<UserInfo> info = service.findUser(username);

        if (info.isPresent()) {
            UserInfo userInfoResponse = service.findByIsUserConfirmedTrue(username);
            if (userInfoResponse != null)
                return ApiCallResponse.generateResponse(1, "المستخدم موثق", "User is confirmed", HttpStatus.OK, null);
            else
                return ApiCallResponse.generateResponse(0, "المستخدم غير موثق", "User is confirmed", HttpStatus.OK, null);
        } else
            return ApiCallResponse.generateResponse(0, "المستخدم غير موجود", "User not found", HttpStatus.OK, null);


    }
}
