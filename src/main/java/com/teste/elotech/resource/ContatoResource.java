package com.teste.elotech.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

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

import com.teste.elotech.controller.ContatoController;
import com.teste.elotech.model.Contato;
import com.teste.elotech.model.dto.ContatoDTO;
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
public class ContatoResource {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@PostMapping("/pessoa/{idPessoa}/contato")
	@ApiOperation(value="Adicionar Contato")
	public Contato cadastrarContato(@PathVariable(value="idPessoa") long id,@RequestBody Contato contato){
		return new ContatoController(pessoaRepository,contatoRepository).cadastrar(id,contato);
	}
	
	@PutMapping("/contato/{idContato}")
	@ApiOperation(value="Atualizar Contato")
	public Contato atualizarContato(@PathVariable(value="idContato") long id,@RequestBody Contato contato){
		return new ContatoController(pessoaRepository,contatoRepository).atualizar(id,contato);
	}
	
	@DeleteMapping("/contato/{idContato}")
	@ApiOperation(value="Deletar Contato")
	public Contato deletarContato(@PathVariable(value="idContato") long id){
		return new ContatoController(pessoaRepository,contatoRepository).deletar(id);
	}
	
	@GetMapping("/pessoa/{idPessoa}/contato")
	@ApiOperation(value="Listar Contatos de uma pessoa")
	public List<ContatoDTO> listarContatos(@PathVariable(value="idPessoa") long id){
		return new ContatoController(pessoaRepository,contatoRepository).listarContatos(id);
	}
	
	@GetMapping("/pessoa/{idPessoa}/contato/pesquisar")
	@ApiOperation(value="Pesquisar Contato")
	public List<ContatoDTO> pesquisarContatos(@PathVariable(value="idPessoa") long id,
											  @RequestParam(value="TELEFONE",required = false) String telefone,
											  @RequestParam(value="NOME",required = false) String nome,
											  @RequestParam(value="EMAIL",required = false) @Email @Valid String email){
		return new ContatoController(pessoaRepository,contatoRepository).pesquisarContatos(id,telefone,nome,email);
	}
}
