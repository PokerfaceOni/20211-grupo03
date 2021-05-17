package com.fatec.pwcap04.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	public Cliente findByCpf(@Param("cpf") String cpf);
}