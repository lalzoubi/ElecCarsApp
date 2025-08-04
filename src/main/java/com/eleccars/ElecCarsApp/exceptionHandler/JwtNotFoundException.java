package com.eleccars.ElecCarsApp.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtNotFoundException extends RuntimeException{

	int isSuccess;
	String message_ar;
	String message_en;

}
