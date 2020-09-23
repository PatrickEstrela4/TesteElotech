package com.teste.elotech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.elotech.model.Contato;
import com.teste.elotech.model.Pessoa;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	List<Contato> findByPessoa(Pessoa p);
	
	Contato findById(long id);
	
	List<Contato> findByNomeContainsIgnoreCase(String nome);
	
	List<Contato> findByTelefoneContainsIgnoreCase(String telefone);
	
	List<Contato> findByEmailContainsIgnoreCase(String email);
	
	@Query("select tc from Contato tc where tc.nome like '%:nome%' and tc.email like '%:email%' and tc.pessoa = :id")
	List<Contato> searchByNomeAndEmailLike(@Param("nome") String nome, @Param("email") String email, @Param("id") Pessoa id);


	@Query("select tc from Contato tc where tc.nome like '%:nome%' and tc.telefone like '%:telefone%' and tc.pessoa is null")
	List<Contato> searchByNomeAndTelefoneLike(@Param("nome") String nome, @Param("telefone") String telefone, @Param("id") Pessoa id);

	@Query("select tc from Contato tc where tc.telefone like '%:telefone%' and tc.email like '%:email%' and tc.pessoa = :id")
	List<Contato> searchByTelefoneAndEmailLike(@Param("telefone") String telefone, @Param("email") String email, @Param("id") Pessoa id);

	@Query("select tc from Contato tc where tc.nome like '%:nome%' and tc.nome like '%:email%' and tc.telefone like '%:telefone%' and tc.pessoa = :id")
	List<Contato> searchByNomeAndTelefoneAndEmailLike(@Param("nome") String nome, @Param("email") String email, @Param("telefone") String telefone, @Param("id") Pessoa id);
}
