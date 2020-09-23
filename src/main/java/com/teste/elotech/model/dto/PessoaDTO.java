package com.teste.elotech.model.dto;

import java.util.Date;
import java.util.List;


/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
public class PessoaDTO {
	
	private long id;	
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private List<ContatoDTO> listaDeContatos;
	
	
	
	public PessoaDTO() {
		super();
	}

	public PessoaDTO(long id,String nome, String cpf, Date dataNascimento,List<ContatoDTO> listaDeContatos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.listaDeContatos = listaDeContatos;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<ContatoDTO> getListaDeContatos() {
		return listaDeContatos;
	}
	public void setListaDeContatos(List<ContatoDTO> listaDeContatos) {
		this.listaDeContatos = listaDeContatos;
	}

}
