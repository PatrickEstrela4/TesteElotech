package com.teste.elotech.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.teste.elotech.model.dto.ContatoDTO;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
@Entity
@Table(name="TB_CONTATO")
public class Contato {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contato_sequence")
	@SequenceGenerator(name="contato_sequence", sequenceName="con_seq",allocationSize = 1)
	private long id;
	
	@NotEmpty(message = "O Campo nome é obrigatorio")
	private String nome;
	
	@NotEmpty(message = "O campo telefone é obrigatorio")
	private String telefone;
	
	@Email
	@NotEmpty(message = "O campo email é obrigatorio")
	private String email;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Pessoa pessoa;

	public Contato() {
		super();
	}

	public Contato(String nome, String telefone, String email, Pessoa pessoa) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.pessoa = pessoa;
	}
	
	public Contato(String nome, String telefone, String email) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}
	
	public Contato(Contato contato, Pessoa pessoa) {
		super();
		this.nome = contato.getNome();
		this.telefone = contato.getTelefone();
		this.email = contato.getEmail();
		this.pessoa = pessoa;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public ContatoDTO toDTO(){
		return new ContatoDTO(this.id,this.nome, this.telefone, this.email);
	}
	
}