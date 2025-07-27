package com.eleccars.ElecCarsApp.controller.securityControllers;


import com.eleccars.ElecCarsApp.dto.securityDTOs.UserInfoDto;
import com.eleccars.ElecCarsApp.javautils.JavaSpringUtils;
import com.eleccars.ElecCarsApp.javautils.RequestUtils;
import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;
import com.eleccars.ElecCarsApp.model.securityModels.UserLoginHistory;
import com.eleccars.ElecCarsApp.service.securityServices.UserInfoService;
import com.eleccars.ElecCarsApp.service.securityServices.UserLoginHistoryService;
import com.eleccars.ElecCarsApp.types.ApiCallResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserInfoController {

    @Autowired
    UserInfoService service;

    @Autowired
    UserLoginHistoryService userLoginHistoryService;

    @Autowired
    private JavaSpringUtils JavaSpringUtils;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserInfo user) {
        List<UserInfoDto> secSystemUserResponse = service.fetchUserDetails(user.getUsername());
        if (secSystemUserResponse.isEmpty()) {
            service.registerUser(user);
            return ApiCallResponse.generateResponse(0, "تم تسجيل المستخدم بنجاح", "The user registered  successfully", HttpStatus.OK, user);
        }
        return ApiCallResponse.generateResponse(0, "اسم المستخدم مسجل مسبقاً", "The user already registered", HttpStatus.OK, null);
    }

    @GetMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        int verifyUserResponse = service.verifyUser(username, password);
        if (verifyUserResponse == 1) {
            List<UserInfoDto> userInfoResponse = service.fetchUserDetails(username);
            if (userInfoResponse != null) {

                // insert into history table
                UserLoginHistory history = new UserLoginHistory();
                history.setLogin_time(JavaSpringUtils.getCurrentDate());
                String clientIp = RequestUtils.getClientIp(request);
                history.setIp_address(clientIp); // set the IP
                history.setDevice_type(RequestUtils.getDeviceDetails(request));
                history.setDevice_info(RequestUtils.getUserAgent(request));
                UserInfo user = new UserInfo();
                user.setId(userInfoResponse.get(0).id()); // set the foreign key
                history.setUser_history(user);
                userLoginHistoryService.saveUserLoginHistory(history);

                return ApiCallResponse.generateResponse(1, "تم استرجاع البيانات بنجاح", "The data retrieved successfully", HttpStatus.OK, userInfoResponse);
            } else
                return ApiCallResponse.generateResponse(0, "خطأ في كلمة السر او كلمة المرور", "The username or password is not correct", HttpStatus.OK, null);
        } else
            return ApiCallResponse.generateResponse(0, "خطأ في كلمة السر او كلمة المرور", "The username or password is not correct", HttpStatus.OK, null);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserByIdentifier(@RequestParam("id") String id) {
        List<UserInfoDto> userInfoResponse = service.findUser(id);

        if (userInfoResponse != null) {
            return ApiCallResponse.generateResponse(1, "تم استرجاع البيانات بنجاح", "The data retrieved successfully", HttpStatus.OK, userInfoResponse);

        } else
            return ApiCallResponse.generateResponse(0, "المستخدم غير موجود", "User not found", HttpStatus.OK, null);
    }

    @GetMapping("/userIsActive")
    public ResponseEntity<?> getIsActiveUserByIdentifier(@RequestParam("id") String id) {
        List<UserInfoDto> userInfoResponse = service.findUser(id);

        if (!userInfoResponse.isEmpty()) {
            if (userInfoResponse.get(0).is_active() == 1)
                return ApiCallResponse.generateResponse(1, "المستخدم  مفعل", "User is active", HttpStatus.OK, null);
            else
                return ApiCallResponse.generateResponse(0, "المستخدم غير مفعل", "User is inactive", HttpStatus.OK, null);
        } else
            return ApiCallResponse.generateResponse(0, "المستخدم غير موجود", "User not found", HttpStatus.OK, null);


    }

    @GetMapping("/userIsConfirmed")
    public ResponseEntity<?> getIsConfirmedUserByIdentifier(@RequestParam("id") String id) {
        List<UserInfoDto> userInfoResponse = service.findUser(id);

        if (!userInfoResponse.isEmpty()) {
            if (userInfoResponse.get(0).is_user_confirmed() == 1)
                return ApiCallResponse.generateResponse(1, "المستخدم موثق", "User is confirmed", HttpStatus.OK, null);
            else
                return ApiCallResponse.generateResponse(0, "المستخدم غير موثق", "User is confirmed", HttpStatus.OK, null);
        } else
            return ApiCallResponse.generateResponse(0, "المستخدم غير موجود", "User not found", HttpStatus.OK, null);


    }
}
