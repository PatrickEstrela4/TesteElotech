package com.teste.elotech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.teste.elotech.model.Contato;
import com.teste.elotech.model.Pessoa;
import com.teste.elotech.model.dto.PessoaDTO;
import com.teste.elotech.repository.ContatoRepository;
import com.teste.elotech.repository.PessoaRepository;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
public class PessoaController {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	PessoaRepository pessoaRepository;
	ContatoRepository contatoRepository;
	
	public PessoaController(PessoaRepository pessoaRepository,ContatoRepository contatoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.contatoRepository = contatoRepository;
	}

	public PessoaDTO cadastrar(Pessoa pessoa) throws ParseException {
		String cpf = pessoa.getCpf();
		String nome = pessoa.getNome();
		Date  dn =  pessoa.getDataNascimento();
		Pessoa p;
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT); 
		p = new Pessoa(nome, cpf, fmt.parse(fmt.format(dn)),pessoa.getListaDeContatos());
		for (Contato c : p.getListaDeContatos()) {
			c.setPessoa(p);
		}
		pessoaRepository.save(p);
		return p.toDTO();
	}

	public Pessoa deletar(long id) {
		Pessoa p = pessoaRepository.findById(id);
		List<Contato> contatos = contatoRepository.findByPessoa(p);
		contatoRepository.deleteAll(contatos);
		pessoaRepository.deleteById(id);
		return p;
	}

	public Pessoa atualizar(Pessoa pessoa) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		Pessoa p = pessoaRepository.findById(pessoa.getId());
		p.setCpf((pessoa.getCpf() == null || pessoa.getCpf().equals("")) ?  p.getCpf() : pessoa.getCpf());
		p.setNome(( pessoa.getNome() == null || pessoa.getNome().equals("")) ? p.getNome() : pessoa.getNome());
		p.setDataNascimento(pessoa.getDataNascimento() == null ? p.getDataNascimento() : fmt.parse(fmt.format(pessoa.getDataNascimento())));
		pessoaRepository.save(p);
		return p;
	}

	public List<PessoaDTO> listarTodos() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<PessoaDTO> dto = new LinkedList<>();
		for (Pessoa p : pessoas) {
			List<Contato> contatos = contatoRepository.findByPessoa(p);
			p.setListaDeContatos(contatos);
			dto.add(p.toDTO());
		}
		return dto;
	}

	public List<PessoaDTO> pesquisar(String cpf, String nome) {
		int peso=0;
		List<Pessoa> pessoas;
		List<PessoaDTO> dto;
		List<Contato> contatos;
		
		if (cpf != null && !cpf.equals("")) {
			peso += 1;
		}
		if (nome != null && !nome.equals("")) {
			peso += 2;
		}
		
		switch (peso) {
		case 1:
			pessoas = pessoaRepository.findByCpfContainsIgnoreCase(cpf);
			dto = new LinkedList<>();
			for (Pessoa p : pessoas) {
				contatos = contatoRepository.findByPessoa(p);
				p.setListaDeContatos(contatos);
				dto.add(p.toDTO());
			}
			return dto;
		case 2:
			pessoas = pessoaRepository.findByNomeContainsIgnoreCase(nome);
			dto = new LinkedList<>();
			for (Pessoa p : pessoas) {
				contatos = contatoRepository.findByPessoa(p);
				p.setListaDeContatos(contatos);
				dto.add(p.toDTO());
			}
			return dto;
		case 3:
			pessoas = pessoaRepository.searchByNomeAndCpfLike(nome, cpf);
			dto = new LinkedList<>();
			for (Pessoa p : pessoas) {
				contatos = contatoRepository.findByPessoa(p);
				p.setListaDeContatos(contatos);
				dto.add(p.toDTO());
			}
			return dto;
		default:
			return listarTodos();
		}
			
	}
	

}
