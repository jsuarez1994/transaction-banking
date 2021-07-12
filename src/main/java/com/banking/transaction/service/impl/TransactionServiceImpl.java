package com.banking.transaction.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.transaction.dto.PayloadTransactionStatusDTO;
import com.banking.transaction.dto.ResponseStatusAmountDTO;
import com.banking.transaction.dto.ResponseStatusBasicDTO;
import com.banking.transaction.dto.ResponseStatusFeeDTO;
import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.entity.TransactionEntity;
import com.banking.transaction.error.FindTransactionException;
import com.banking.transaction.error.StatusTransactionException;
import com.banking.transaction.mapper.TransactionMapper;
import com.banking.transaction.repository.TransactionRepository;
import com.banking.transaction.service.TransactionService;
import com.banking.transaction.util.Channel;
import com.banking.transaction.util.Exceptions;
import com.banking.transaction.util.Order;
import com.banking.transaction.util.Status;
import com.banking.transaction.util.Utils;
import com.banking.transaction.util.Validations;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	private final String orderDefault = "ASC";

	private final List<String> channelsVerify = Arrays.asList(Channel.CLIENT.name(), Channel.ATM.name());

	@Override
	public TransactionDTO save(TransactionDTO dto) throws AccountException {
		TransactionEntity entity = TransactionMapper.INSTANCE.toEntity(dto);

		// Verify has reference
		Validations.prepareSave(entity, transactionRepository.findAllReferences());

		return TransactionMapper.INSTANCE.toDTO(transactionRepository.save(entity));
	}

	@Override
	public List<TransactionDTO> findByAccountIban(String accountIban, String order) throws FindTransactionException {

		Boolean check = Boolean.FALSE;
		for (Order value : Order.values()) {
			if (StringUtils.equals(order, value.name())) {
				order = value.name();
				check = Boolean.TRUE;
			}
		}

		if (!check) {
			order = orderDefault;
		}

		if (StringUtils.trimToEmpty(accountIban).isEmpty()) {
			throw new FindTransactionException(Exceptions.ACCOUNT_IBAN_EMPTY);
		}

		if (StringUtils.equals(order, Order.ASC.name())) {
			return TransactionMapper.INSTANCE
					.toDTO(transactionRepository.findByAccountIbanOrderByAmountAsc(accountIban));
		} else {
			return TransactionMapper.INSTANCE
					.toDTO(transactionRepository.findByAccountIbanOrderByAmountDesc(accountIban));
		}
	}

	@Override
	public ResponseStatusBasicDTO status(PayloadTransactionStatusDTO payload) throws StatusTransactionException {

		Timestamp today = new Timestamp(System.currentTimeMillis());
		final String reference = Utils.replaceIfEmpty(payload.getReference().toUpperCase(), StringUtils.EMPTY);
		final String channel = Utils.replaceIfEmpty(payload.getChannel().toUpperCase(), StringUtils.EMPTY);
		TransactionEntity transactionBD = transactionRepository.findByReference(reference);

		// CASE A
		if (null == transactionBD || StringUtils.isEmpty(reference)) {
			return getResponse(reference, Status.INVALID, false, false, null);
		}

		// CASE B
		if (!channel.isEmpty() && channelsVerify.contains(channel) && Utils.isBefore(today, transactionBD.getDate())) {
			return getResponse(reference, Status.SETTLED, true, false, transactionBD);
		}

		// CASE C
		if (!channel.isEmpty() && StringUtils.equals(channel, Channel.INTERNAL.name())
				&& Utils.isBefore(today, transactionBD.getDate())) {
			return getResponse(reference, Status.SETTLED, false, true, transactionBD);
		}

		// CASE D
		if (!channel.isEmpty() && channelsVerify.contains(channel) && Utils.isEquals(today, transactionBD.getDate())) {
			return getResponse(reference, Status.PENDING, true, false, transactionBD);
		}

		// CASE E
		if (!channel.isEmpty() && StringUtils.equals(channel, Channel.INTERNAL.name())
				&& Utils.isEquals(today, transactionBD.getDate())) {
			return getResponse(reference, Status.PENDING, false, true, transactionBD);
		}

		// CASE F
		if (!channel.isEmpty() && StringUtils.equals(channel, Channel.CLIENT.name())
				&& Utils.isAfter(today, transactionBD.getDate())) {
			return getResponse(reference, Status.FUTURE, true, false, transactionBD);
		}

		// CASE G
		if (!channel.isEmpty() && StringUtils.equals(channel, Channel.ATM.name())
				&& Utils.isAfter(today, transactionBD.getDate())) {
			return getResponse(reference, Status.PENDING, true, false, transactionBD);
		}

		// CASE H
		if (!channel.isEmpty() && StringUtils.equals(channel, Channel.INTERNAL.name())
				&& Utils.isAfter(today, transactionBD.getDate())) {
			return getResponse(reference, Status.FUTURE, false, true, transactionBD);
		}

		return getResponse(reference, Status.INVALID, false, false, null);

	}

	/**
	 * Return Response
	 * 
	 * @param reference
	 * @param status
	 * @param applyRate
	 * @param showAmountAndFee
	 * @param transaction
	 * @return
	 */
	private ResponseStatusBasicDTO getResponse(final String reference, Status status, Boolean applyRate,
			Boolean showAmountAndFee, TransactionEntity transaction) {
		
		if(null == transaction) {
			ResponseStatusBasicDTO response = new ResponseStatusBasicDTO();
			response.setReference(reference);
			response.setStatus(status.name().toUpperCase());
			return response;
		}
		
		if(applyRate) {
			ResponseStatusAmountDTO response = new ResponseStatusAmountDTO();
			response.setReference(reference);
			response.setStatus(status.name().toUpperCase());
			if(null != transaction && null != transaction.getFee()) {
				response.setAmount(transaction.getAmount() - transaction.getFee());
			}
			return response;
		}
		

		if(showAmountAndFee) {
			ResponseStatusFeeDTO response = new ResponseStatusFeeDTO();
			response.setReference(reference);
			response.setStatus(status.name().toUpperCase());
			if(null != transaction && null != transaction.getFee()) {
				response.setAmount(transaction.getAmount());
				response.setFee(transaction.getFee());
			}
			return response;
		}
		
		return new ResponseStatusBasicDTO();
	}

}
