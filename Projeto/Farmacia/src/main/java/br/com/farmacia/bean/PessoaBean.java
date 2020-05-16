package br.com.farmacia.bean;

import br.com.farmacia.dao.PessoaDAO;
import br.com.farmacia.domain.Pessoa;
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
@ManagedBean(name = "MBPessoas")
@ViewScoped
public class PessoaBean implements Serializable {

    @Getter
    @Setter
    private Pessoa pessoa;

    @Getter
    @Setter
    private List<Pessoa> pessoas;

    @PostConstruct
    public void listar() {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            pessoa = new Pessoa();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
    }

    public void salvar() {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.merge(pessoa);
            pessoas = pessoaDAO.listar("nome");
            pessoa = new Pessoa();
            Messages.addGlobalInfo("Dados salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
            PessoaDAO fdao = new PessoaDAO();
            fdao.excluir(pessoa);
        } catch (RuntimeException err) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir este registro.");
            err.printStackTrace();
        }
    }
}
