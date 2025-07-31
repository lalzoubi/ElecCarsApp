package com.eleccars.ElecCarsApp.types;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


public class ApiCallResponse {


    public static ResponseEntity<Object> generateResponse(int suc, String message_ar, String message_en , HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", suc);
        map.put("message_ar", message_ar);
        map.put("message_en", message_en);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponseWithToken(int suc, String message_ar, String message_en , HttpStatus status, Object responseObj, String jwtToken) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", suc);
        map.put("message_ar", message_ar);
        map.put("message_en", message_en);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("token", jwtToken);

        return new ResponseEntity<Object>(map, status);
    }
}
