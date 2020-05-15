package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos");
		sql.append("(descricao, quantidade, preco, fornecedores_id) ");
		sql.append("VALUES (?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setInt(4, p.getFornecedores().getId());
		comando.executeUpdate();

	}
	
	public ArrayList<Produtos> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id, p.descricao, p.preco, p.quantidade, f.id, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.id = p.fornecedores_id ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setId(resultado.getInt("f.id"));
			f.setDescricao(resultado.getString("f.descricao"));
			
			Produtos p = new Produtos();
			p.setId(resultado.getInt("p.id"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setFornecedores(f);
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public void excluir (Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, p.getId());
		comando.executeUpdate();
	}
}
