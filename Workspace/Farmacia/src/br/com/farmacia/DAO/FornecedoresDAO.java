package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public Fornecedores buscaPorCodigo (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE ID = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setInt(1, f.getId());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setId(resultado.getInt("id"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Fornecedores>buscarPorDescricao(Fornecedores f)throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" +  f.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setId(resultado.getInt("id"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}
		
		return lista;
	}
	
	public ArrayList<Fornecedores> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setId(resultado.getInt("id"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		
		return lista;
	}

	public static void main(String[] args) {
		/*Fornecedores f1 = new Fornecedores();
		f1.setDescricao("descricao corno");

		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("descricao viado");

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
		
	/*	Fornecedores f1 = new Fornecedores();
		f1.setId(1);
		f1.setDescricao("editado");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.editar(f1);
			System.out.println("Editado com sucesso");
		} catch (SQLException e) {
			System.out.println("erro ao editar");
			e.printStackTrace();
		} */
		
		
		//Metodo buscar
		/* Fornecedores f1 = new Fornecedores();
		f1.setId(3);

		Fornecedores f2 = new Fornecedores();
		f2.setId(2);

		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			Fornecedores f3 = fdao.buscaPorCodigo(f1);
			Fornecedores f4 = fdao.buscaPorCodigo(f2);
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {
			System.out.println("erro ao buscar");
			e.printStackTrace();
		} */
		
		
		
		//Metodo listar 
		/* FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			
			ArrayList<Fornecedores>lista = fdao.listar();
			
			for (Fornecedores f : lista) {
				System.out.println("Resultado " + f);
			}
		} catch (SQLException e) {
			System.out.println("erro ao buscar");
			e.printStackTrace();
		} */
		
		//M�todo buscar por descri��o
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("cor");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			
			ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
			
			for (Fornecedores f : lista) {
				System.out.println("Resultado " + f);
			}
		} catch (SQLException e) {
			System.out.println("erro ao buscar");
			e.printStackTrace();
		}
		
	}
}
