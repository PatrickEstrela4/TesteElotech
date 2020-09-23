package com.teste.elotech.controller;

import java.util.LinkedList;
import java.util.List;

import com.teste.elotech.model.Contato;
import com.teste.elotech.model.Pessoa;
import com.teste.elotech.model.dto.ContatoDTO;
import com.teste.elotech.repository.ContatoRepository;
import com.teste.elotech.repository.PessoaRepository;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
public class ContatoController {

	PessoaRepository pessoaRepository;
	ContatoRepository contatoRepository;
	
	public ContatoController(PessoaRepository pessoaRepository, ContatoRepository contatoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.contatoRepository = contatoRepository;
	}

	public Contato cadastrar(long id, Contato contato) {
		Pessoa p = pessoaRepository.findById(id);
		Contato c = new Contato(contato, p);
		contatoRepository.save(c);
		return c;
	}

	public Contato atualizar(long id, Contato contato) {
		Contato c = contatoRepository.findById(id);
		c.setTelefone((contato.getTelefone() == null || contato.getTelefone().equals("")) ?  c.getTelefone() : contato.getTelefone() );
		c.setNome((contato.getNome() == null || contato.getNome().equals("")) ? c.getNome() : contato.getNome());
		c.setEmail(contato.getEmail() == null || (contato.getEmail().equals("")) ? c.getEmail() : contato.getEmail());
		contatoRepository.save(c);
		return c;
	}

	public Contato deletar(long id) {
		Contato c = contatoRepository.findById(id);
		pessoaRepository.deleteById(id);
		return c;
	}

	public List<ContatoDTO> listarContatos(long id) {
		Pessoa p = pessoaRepository.findById(id);
		List<Contato> list = contatoRepository.findByPessoa(p);
		List<ContatoDTO> dto = new LinkedList<>();
		for (Contato c : list) {
			dto.add(c.toDTO());
		}
		return dto;
	}



	public List<ContatoDTO> pesquisarContatos(long id, String telefone, String nome, String email) {
		int peso = 0;
		Pessoa p = pessoaRepository.findById(id);
		List<Contato> list2 = contatoRepository.findByPessoa(p);
		p.setListaDeContatos(list2);
		List<Contato> list;
		List<ContatoDTO> dto;
		
		if (telefone != null && !telefone.equals("")) {
			peso += 1;
		}
		if (nome != null && !nome.equals("")) {
			peso += 2;
		}
		if (email != null && !email.equals("")) {
			peso += 4;
		}
		
		switch (peso) {
		case 1:
			list = contatoRepository.findByTelefoneContainsIgnoreCase(telefone);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		case 2:
			list = contatoRepository.findByNomeContainsIgnoreCase(nome);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		case 3:
			list = contatoRepository.searchByNomeAndTelefoneLike(nome, telefone, null);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		case 4:
			list = contatoRepository.findByEmailContainsIgnoreCase(email);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		case 5:
			list = contatoRepository.searchByTelefoneAndEmailLike(telefone, email, p);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		case 6:
			list = contatoRepository.searchByNomeAndEmailLike(nome, email, p);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		case 7:
			list = contatoRepository.searchByNomeAndTelefoneAndEmailLike(nome, email, telefone, p);
			dto = new LinkedList<>();
			for (Contato c : list) {
				dto.add(c.toDTO());
			}
			return dto;
		default:
			return listarContatos(id);
		}
	}




}
