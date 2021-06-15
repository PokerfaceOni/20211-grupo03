package com.fatec.pwcap04.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoRepository extends CrudRepository<Locacao, Long> {
	@Query("SELECT e FROM Locacao e WHERE e.veiculo = :placa AND e.cliente = :cpf")
	public List <Locacao> findByPlacaCpf(@Param("placa") String placa, @Param("cpf") String cpf);

}
