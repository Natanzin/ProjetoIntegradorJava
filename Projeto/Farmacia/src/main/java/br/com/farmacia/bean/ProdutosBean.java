package br.com.farmacia.bean;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.domain.Produto;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean implements Serializable {

	@Getter
	@Setter
	private Produto produto;

	@Getter
	@Setter
	private List<Produto> produtos;

	@Getter
	@Setter
	private List<Fornecedor> fornecedores;

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			produto = new Produto();
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo produto");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		fornecedores = fornecedorDAO.listar();
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			produtos = produtoDAO.listar("descricao");
			Messages.addGlobalInfo("Dados salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produto);
			produtos = fdao.listar("descricao");
			Messages.addGlobalInfo("Registro excluído com sucesso!");
		} catch (RuntimeException err) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir este registro.");
			err.printStackTrace();
		}
	}
}
