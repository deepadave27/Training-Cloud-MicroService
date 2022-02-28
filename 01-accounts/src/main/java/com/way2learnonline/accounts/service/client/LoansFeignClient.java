package com.way2learnonline.accounts.service.client;

import java.util.List;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.way2learnonline.accounts.model.Customer;
import com.way2learnonline.accounts.model.Loans;


public interface LoansFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "myLoans", consumes = "application/json")
	List<Loans> getLoansDetails(@RequestHeader("eazybank-correlation-id") String correlationid,@RequestBody Customer customer);
}
