package com.banking.transaction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.entity.TransactionEntity;

/**
 * @author Jesus Suarez
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends AbstractMapper<TransactionEntity, TransactionDTO> {

	TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

}
