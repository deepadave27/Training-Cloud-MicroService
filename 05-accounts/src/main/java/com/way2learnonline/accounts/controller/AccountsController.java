
package com.way2learnonline.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.way2learnonline.accounts.config.AccountsServiceConfig;
import com.way2learnonline.accounts.model.Accounts;
import com.way2learnonline.accounts.model.Cards;
import com.way2learnonline.accounts.model.Customer;
import com.way2learnonline.accounts.model.CustomerDetails;
import com.way2learnonline.accounts.model.Loans;
import com.way2learnonline.accounts.model.Properties;
import com.way2learnonline.accounts.repository.AccountsRepository;
import com.way2learnonline.accounts.service.client.CardsFeignClient;
import com.way2learnonline.accounts.service.client.LoansFeignClient;


@RestController
public class AccountsController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	AccountsServiceConfig accountsConfig;

	//@Autowired
	//LoansFeignClient loansFeignClient;

	//@Autowired
	//CardsFeignClient cardsFeignClient;

	@PostMapping("/myAccount")
	
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

	/*
	
	@PostMapping("/myCustomerDetails")
	public CustomerDetails myCustomerDetails(@RequestHeader("bank-correlation-id") String correlationid,@RequestBody Customer customer) {
		logger.info("myCustomerDetails() method started");
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(correlationid,customer);
		List<Cards> cards = cardsFeignClient.getCardDetails(correlationid,customer);

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);
		logger.info("myCustomerDetails() method ended");
		return customerDetails;

	}

	private CustomerDetails myCustomerDetailsFallBack(@RequestHeader("eazybank-correlation-id") String correlationid,Customer customer, Throwable t) {
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(correlationid,customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		return customerDetails;

	}
	*/

	@GetMapping("/sayHello")
	public String sayHello() {
		return "Hello, Welcome to Bank Kubernetes cluster";
	}

	private String sayHelloFallback(Throwable t) {
		return "Hi, Welcome to Bank";
	}

}
