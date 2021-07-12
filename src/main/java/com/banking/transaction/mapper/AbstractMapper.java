package com.banking.transaction.mapper;

import java.util.List;

public interface AbstractMapper <ENTITY, DTO>{

	DTO toDTO(ENTITY entity);
	
	List<DTO> toDTO(List<ENTITY> listEntity);

	ENTITY toEntity(DTO dto);
	
	List<ENTITY> toEntity(List<DTO> listDTO);
	
}
