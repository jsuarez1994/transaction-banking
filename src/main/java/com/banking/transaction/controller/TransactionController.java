package com.banking.transaction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.transaction.dto.PayloadTransactionStatusDTO;
import com.banking.transaction.dto.ResponseStatusBasicDTO;
import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.error.FindTransactionException;
import com.banking.transaction.error.StatusTransactionException;
import com.banking.transaction.service.TransactionService;
import com.banking.transaction.util.LoggerUtil;
import com.banking.transaction.util.SwaggerContants;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/transaction")
@Slf4j
public class TransactionController {

	@Autowired
	@Qualifier("transactionService")
	private TransactionService transactionService;

	@Autowired
	private LoggerUtil logUtil;

	/**
	 * Create transaction
	 * 
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = SwaggerContants.CREATE_TRANSACTION)
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody TransactionDTO dto) {

		final String methodName = "create";
		try {
			log.info(logUtil.initMethod(this.getClass().getSimpleName(), methodName));
			dto = transactionService.save(dto);
		} catch (AccountException e) {
			log.info(logUtil.errorMethod(this.getClass().getSimpleName(), methodName, e.getMessage()));
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(logUtil.finishMethod(this.getClass().getSimpleName(), methodName));
		return ResponseEntity.ok(dto);
	}
	
	/**
	 * Create transaction
	 * 
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = SwaggerContants.FIND_TRANSACTION)
	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByAccountIban(@RequestParam String accountIban,
			@RequestParam(defaultValue = "ASC") String order) {

		final String methodName = "findByAccountIban";
		List<TransactionDTO> response = new ArrayList<>();

		try {
			log.info(logUtil.initMethod(this.getClass().getSimpleName(), methodName));
			response = transactionService.findByAccountIban(accountIban, order.toUpperCase());

		} catch (FindTransactionException e) {
			log.info(logUtil.errorMethod(this.getClass().getSimpleName(), methodName, e.getMessage()));
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(logUtil.finishMethod(this.getClass().getSimpleName(), methodName));
		return ResponseEntity.ok(response);
	}

	/**
	 * Get Status
	 * 
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = SwaggerContants.STATUS_TRANSACTION)
	@PostMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> status(@RequestBody PayloadTransactionStatusDTO payload) {

		final String methodName = "status";
		ResponseStatusBasicDTO response = new ResponseStatusBasicDTO();

		try {
			log.info(logUtil.initMethod(this.getClass().getSimpleName(), methodName));
			response = transactionService.status(payload);
		} catch (StatusTransactionException e) {
			log.info(logUtil.errorMethod(this.getClass().getSimpleName(), methodName, e.getMessage()));
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(logUtil.finishMethod(this.getClass().getSimpleName(), methodName));
		return ResponseEntity.ok(response);
	}
	
}
