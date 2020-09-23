package com.teste.elotech.model.dto;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
public class ContatoDTO {

	private long id;
	private String nome;
	private String telefone;
	private String email;
	
	
	

	public ContatoDTO() {
		super();
	}

	public ContatoDTO(long id,String nome, String telefone, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}