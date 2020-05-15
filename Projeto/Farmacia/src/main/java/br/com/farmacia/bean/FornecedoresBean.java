package br.com.farmacia.bean;

import br.com.farmacia.dao.FornecedorDAO;
import br.com.farmacia.domain.Fornecedor;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import java.util.List;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

    @Getter
    @Setter
    private Fornecedor fornecedor;

    @Getter
    @Setter
    private List<Fornecedor> fornecedores;

    @Getter
    @Setter
    private ListDataModel<Fornecedor> itens;

    @PostConstruct
    public void prepararPesquisa() {

        try {
            FornecedorDAO fdao = new FornecedorDAO();
            fornecedores = fdao.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
            erro.printStackTrace();
        }
    }

    public void prepararNovo() {
        fornecedor = new Fornecedor();
    }

    public void novo() {
        try {
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.salvar(fornecedor);

            fornecedores = fdao.listar();

            Messages.addGlobalInfo("Fornecedor salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.excluir(fornecedor);
            fornecedores = fdao.listar();
            Messages.addGlobalInfo("Fornecedor excluído com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Não é possível excluir um fornecedor que tenha um produto vinculado");
            erro.printStackTrace();
        }
    }

    public void prepararEditar(ActionEvent evento) {
        fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");
    }

    public void editar() {
        try {
            FornecedorDAO fdao = new FornecedorDAO();
            fdao.editar(fornecedor);

            fornecedores = fdao.listar();
            Messages.addGlobalInfo("Fornecedor alterado com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao atualizar os dados do fornecedor.");
            erro.printStackTrace();
        }
    }
}
