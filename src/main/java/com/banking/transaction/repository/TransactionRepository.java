package com.banking.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.banking.transaction.entity.TransactionEntity;

/**
 * @author Jesus Suarez
 */
public interface TransactionRepository extends AbstractRepository<TransactionEntity> {
	
	
	/**
	 * Return alls reference
	 * @return List<String>
	 */
	@Query("select it.reference from TransactionEntity it")
	List<String> findAllReferences();
	
	/**
	 * Find all transaction by account iban ASC
	 * @param accountIban
	 * @return
	 */
	List<TransactionEntity> findByAccountIbanOrderByAmountAsc(String accountIban);
	
	/**
	 * Find all transaction by account iban DESC
	 * @param accountIban
	 * @return
	 */
	List<TransactionEntity> findByAccountIbanOrderByAmountDesc(String accountIban);
	
	/**
	 * Find all transaction by reference
	 * @param reference
	 * @return
	 */
	TransactionEntity findByReference(String reference);
	
}
