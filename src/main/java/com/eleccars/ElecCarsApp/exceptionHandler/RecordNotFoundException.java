package com.eleccars.ElecCarsApp.exceptionHandler;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends RuntimeException{

    int suc;
    String message;
    public RecordNotFoundException(int suc, String message) {
        this.suc = suc;
        this.message = message;
    }
	public int getSuc() {
		return suc;
	}
	public void setSuc(int suc) {
		this.suc = suc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
