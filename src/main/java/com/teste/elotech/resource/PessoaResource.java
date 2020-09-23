package com.teste.elotech.resource;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.elotech.controller.PessoaController;
import com.teste.elotech.model.Pessoa;
import com.teste.elotech.model.dto.PessoaDTO;
import com.teste.elotech.repository.ContatoRepository;
import com.teste.elotech.repository.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
@RestController
@RequestMapping(value="/api/elotech")
@Api(value="API REST EMPRESTIMOS")
@CrossOrigin(origins="*")
@Validated
public class PessoaResource {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	ContatoRepository contatoRepository;

	@PostMapping("/pessoa")
	@ApiOperation(value="Cadastrar Pessoa")
	public PessoaDTO cadastrarPessoa(@Valid @RequestBody Pessoa pessoa) throws ParseException {
		return new PessoaController(pessoaRepository,contatoRepository).cadastrar(pessoa);
	}
	
	@DeleteMapping("/pessoa/{idPessoa}")
	@ApiOperation(value="Remover Pessoa")
	public Pessoa deletarPessoa(@PathVariable(value="idPessoa") long id){
		return new PessoaController(pessoaRepository,contatoRepository).deletar(id);
	}
	
	@PutMapping("/pessoa/{idPessoa}")
	@ApiOperation(value="Atualizar Pessoa")
	public Pessoa atualizarPessoa(@Valid @RequestBody Pessoa pessoa) throws ParseException {
		return new PessoaController(pessoaRepository,contatoRepository).atualizar(pessoa);
	}
	
	@GetMapping("/pessoa/{idPessoa}")
	@ApiOperation(value="Listar Pessoa Especifica")
	public PessoaDTO getPessoa(@PathVariable(value="idPessoa") long id) {
		return pessoaRepository.findById(id).toDTO();
	}
	
	@GetMapping("/pessoa")
	@ApiOperation(value="Listar Pessoa")
	public List<PessoaDTO> getTodasPessoas() {
		return new PessoaController(pessoaRepository,contatoRepository).listarTodos();
	}
	
	@GetMapping("/pessoa/pesquisar")
	@ApiOperation(value="Pesquisar Pessoa")
	public List<PessoaDTO> pesquisarPessoa(@RequestParam(value="CPF",required = false) @CPF @Valid String cpf,
									 @RequestParam(value="NOME",required = false) String nome) {
		return new PessoaController(pessoaRepository,contatoRepository).pesquisar(cpf,nome);
	}
	
}
