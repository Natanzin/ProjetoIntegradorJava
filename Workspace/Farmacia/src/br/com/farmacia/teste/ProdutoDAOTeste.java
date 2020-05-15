package br.com.farmacia.teste;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;



public class ProdutoDAOTeste {
	@Test
	@Ignore
	public void salvar()throws SQLException {
	
	Produtos p1 = new Produtos();
	p1.setDescricao("Dorflex");
	p1.setPreco(2.99);
	p1.setQuantidade(254);
	
	Fornecedores f = new Fornecedores();
	f.setId(19);
	p1.setFornecedores(f);

	ProdutoDAO fdao = new ProdutoDAO();
	
	fdao.salvar(p1);
	}
	
	@Test
	
	public void listar()throws SQLException{
		ProdutoDAO fdao = new ProdutoDAO();
		ArrayList<Produtos> lista = fdao.listar();
		
		for (Produtos p : lista) {
			System.out.println("Código do produto: " + p.getId());
			System.out.println("Descrição do produto: " + p.getDescricao());
			System.out.println("Valor do produto: " + p.getPreco());
			System.out.println("Quantidade do produto: " + p.getQuantidade());
			System.out.println("Código do fornecedor: " + p.getFornecedores().getId());
			System.out.println("Descrição do fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println(" ");
			
		}
	}
	
	
	
}
