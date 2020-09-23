package com.teste.elotech.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.elotech.model.dto.ContatoDTO;
import com.teste.elotech.model.dto.PessoaDTO;

/**
 * @author Patrick E. Estrela
 * @Data 21/09/20
 */
@Entity
@Table(name="TB_PESSOA")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_sequence")
	@SequenceGenerator(name="pessoa_sequence", sequenceName="pes_seq",allocationSize = 1)
	private long id;
	
	@NotEmpty(message = "O Campo nome é obrigatorio")
	private String nome;
	
	@NotEmpty(message = "O Campo CPF é obrigatorio")
	@CPF
	private String cpf;
	
	@NotNull(message = "O Campo Data é obrigatorio")
	@PastOrPresent
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataNascimento;
	
	
	@NotEmpty(message = "É nescessario cadastrar ao minimo um contato")
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> listaDeContatos;
	
	
	
	public Pessoa() {
		super();
	}

	public Pessoa(String nome, String cpf, Date dataNascimento,List<Contato> listaDeContatos) {
		super();
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
	public List<Contato> getListaDeContatos() {
		return listaDeContatos;
	}
	public void setListaDeContatos(List<Contato> listaDeContatos) {
		this.listaDeContatos = listaDeContatos;
	}

	public PessoaDTO toDTO(){
		List<ContatoDTO> dto = new LinkedList<>();
		for (Contato c : listaDeContatos) {
			dto.add(c.toDTO());
		}
		return new PessoaDTO(this.id,this.nome, this.cpf, this.dataNascimento, dto);
	}
}
