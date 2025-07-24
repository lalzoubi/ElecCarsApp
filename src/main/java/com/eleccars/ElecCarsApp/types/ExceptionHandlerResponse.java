package com.eleccars.ElecCarsApp.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionHandlerResponse {
    int isSuccess;
    String message_ar;
    String message_en;

    public ExceptionHandlerResponse(int isSuccess, String message_ar, String message_en) {
        this.isSuccess = isSuccess;
        this.message_ar = message_ar;
        this.message_en = message_en;
    }
}
