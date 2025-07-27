package com.eleccars.ElecCarsApp.exceptionHandler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException{

	int isSuccess;
	String message_ar;
	String message_en;

}
