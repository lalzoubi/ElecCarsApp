package com.eleccars.ElecCarsApp.exceptionHandler;

public class UserNotFoundException extends RuntimeException {
    int isSuccess;
    String message_ar;
    String message_en;

    public UserNotFoundException(int isSuccess, String message_ar, String message_en) {
        this.isSuccess = isSuccess;
        this.message_ar = message_ar;
        this.message_en = message_en;
    }
}
