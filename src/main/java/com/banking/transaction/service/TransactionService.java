package com.banking.transaction.service;

import java.util.List;

import javax.security.auth.login.AccountException;

import com.banking.transaction.dto.PayloadTransactionStatusDTO;
import com.banking.transaction.dto.ResponseStatusBasicDTO;
import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.error.FindTransactionException;
import com.banking.transaction.error.StatusTransactionException;

public interface TransactionService {

	/**
	 * Save item TransactionDTO
	 * 
	 * @param dto
	 * @return TransactionDTO
	 */
	TransactionDTO save(TransactionDTO dto) throws AccountException;

	/**
	 * Find all transaction by accountIban
	 * 
	 * @param accountIban
	 * @param order
	 * @return
	 */
	List<TransactionDTO> findByAccountIban(String accountIban, String order) throws FindTransactionException;

	
	/**
	 * Verify status
	 * @param payload
	 * @return
	 */
	ResponseStatusBasicDTO status(PayloadTransactionStatusDTO payload) throws StatusTransactionException;

}
