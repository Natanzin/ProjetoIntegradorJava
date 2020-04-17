package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();

	}
	public void excluir (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, f.getId());
		comando.executeUpdate();
	}
	
	public void editar (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE ID = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getId());
		comando.executeUpdate();
	}
	
	

	public static void main(String[] args) {
		/*Fornecedores f1 = new Fornecedores();
		f1.setDescricao("descricao 1");

		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("descricao 2");

		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			System.out.println("Salvo com sucesso");
		} catch (SQLException e) {
			System.out.println("erro ao salvar");
			e.printStackTrace();
		} */
		
		/* Fornecedores f1 = new Fornecedores();
		f1.setId(2);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.excluir(f1);
			System.out.println("Deletado com sucesso");
		} catch (SQLException e) {
			System.out.println("erro ao deletar");
			e.printStackTrace();
		} */
		
		Fornecedores f1 = new Fornecedores();
		f1.setId(1);
		f1.setDescricao("editado");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.editar(f1);
			System.out.println("Editado com sucesso");
		} catch (SQLException e) {
			System.out.println("erro ao editar");
			e.printStackTrace();
		}
		
	}
}
