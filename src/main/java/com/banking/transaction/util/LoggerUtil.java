package com.banking.transaction.util;

import org.springframework.stereotype.Component;

@Component
public class LoggerUtil {

	public String initMethod(String classMethod, String methodName) {
		StringBuilder log = new StringBuilder();
		return log.append("### Inicio ").append(classMethod).append(".").append(methodName).append(" ###").toString();
	}

	public String finishMethod(String classMethod, String methodName) {
		StringBuilder log = new StringBuilder();
		return log.append("### Fin ").append(classMethod).append(".").append(methodName).append(" ###").toString();
	}

	public String errorMethod(String classMethod, String methodName, String errorMessage) {
		StringBuilder log = new StringBuilder();
		return log.append("### Error ").append(classMethod).append(".").append(methodName).append(": ")
				.append(errorMessage).append(" ###").toString();
	}

}
