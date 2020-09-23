package com.teste.elotech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.elotech.model.Pessoa;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{
	Pessoa findById(long id);
	
	List<Pessoa> findByNomeContainsIgnoreCase(String nome);
	
	List<Pessoa> findByCpfContainsIgnoreCase(String cpf);
	
	@Query("select tp from Pessoa tp where tp.nome like '%:nome%' and tp.cpf like '%:cpf%'")
	List<Pessoa> searchByNomeAndCpfLike(@Param("nome") String nome,@Param("cpf") String cpf);

}
