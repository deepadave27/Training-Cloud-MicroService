package com.way2learnonline.loans.context;

import org.springframework.stereotype.Component;


@Component
public class RequestContext {

	
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public static String getCorrelationId() {
		return CORRELATION_ID;
	}

	public static final String CORRELATION_ID = "bank-correlation-id";

	private String correlationId = new String();

}
