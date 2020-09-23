package com.teste.elotech.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.teste.elotech.controller.PessoaController;
import com.teste.elotech.model.Contato;
import com.teste.elotech.model.Pessoa;
import com.teste.elotech.repository.ContatoRepository;
import com.teste.elotech.repository.PessoaRepository;
import com.teste.elotech.resource.PessoaResource;

import io.restassured.http.ContentType;

import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class PessoaControllerTest {
	
	@Autowired
	private PessoaResource pessoaResource;
	
	@MockBean
	private PessoaRepository pessoaRepository;
	
	@MockBean
	private ContatoRepository contatoRepository;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.pessoaResource);
	}
	
	@Test
	public void getTodasPessoasTest(){
		
		when(this.pessoaRepository.findById(6l))
		.thenReturn(new Pessoa("PATRICK", "22036739075", new Date(), new ArrayList<Contato>()));
		
		given()
		.accept(ContentType.JSON)
		.when()
		.get("/api/elotech/pessoa/{id}",6L)
		.then()
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void cadastrarPessoaTest() throws ParseException{
		List<Contato> lista = new ArrayList<>();
		lista.add(new Contato("rafa", "41987267311", "patrickestrela4@hotmail.com"));
		when(new PessoaController(pessoaRepository,contatoRepository).cadastrar(new Pessoa("PATRICK", "22036739075", new Date(),lista)))
		.thenReturn(new Pessoa("PATRICK", "22036739075", new Date(), lista).toDTO());
		
		given()
		.accept(ContentType.JSON)
		.when()
		.post("/api/elotech/pessoa")
		.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deletarPessoaTest() throws ParseException{
		List<Contato> lista = new ArrayList<>();
		lista.add(new Contato("rafa", "41987267311", "patrickestrela4@hotmail.com"));
		when(new PessoaController(pessoaRepository,contatoRepository).deletar(1l))
		.thenReturn(new Pessoa("PATRICK", "08325136952", new Date(), lista));
		
		given()
		.accept(ContentType.JSON)
		.when()
		.delete("/api/elotech/pessoa")
		.then()
		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void atualizarPessoaTest() throws ParseException{
		List<Contato> lista = new ArrayList<>();
		lista.add(new Contato("rafa", "41987267311", "patrickestrela4@hotmail.com"));
		when(new PessoaController(pessoaRepository,contatoRepository).atualizar(new Pessoa("PATRICK", "22036739075", new Date(),lista)))
		.thenReturn(new Pessoa("PATRICK", "22036739075", new Date(), lista));
		
		given()
		.accept(ContentType.JSON)
		.when()
		.put("/api/elotech/pessoa")
		.then()
		.statusCode(HttpStatus.OK.value());
	}
	
	
}
