package com.teste.elotech.exception;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ErrorObject> errors;
	public ErrorResponse(String message, int code, String status, String objectName, List<ErrorObject> errors) {
		super();
		this.message = message;
		this.code = code;
		this.status = status;
		this.objectName = objectName;
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public int getCode() {
		return code;
	}
	public String getStatus() {
		return status;
	}
	public String getObjectName() {
		return objectName;
	}
	public List<ErrorObject> getErrors() {
		return errors;
	}
    
}