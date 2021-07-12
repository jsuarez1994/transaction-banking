package com.banking.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.transaction.entity.BaseEntity;

/**
 * @author Jesus Suarez Lopez
 *
 * @param <ENTITY>
 */
public interface AbstractRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, Long> {

}
