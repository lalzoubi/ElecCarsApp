package com.eleccars.ElecCarsApp.types;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionHandlerResponse {
    int isSuccess;
    String message_ar;
    String message_en;

}
