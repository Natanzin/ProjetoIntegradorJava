package br.com.farmacia.bean;

import br.com.farmacia.dao.PessoaDAO;
import br.com.farmacia.dao.UsuarioDAO;
import br.com.farmacia.domain.Pessoa;
import br.com.farmacia.domain.Usuario;
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
@ManagedBean(name = "MBUsuarios")
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private List<Pessoa> pessoas;

    @Getter
    @Setter
    private List<Usuario> usuarios;

    @PostConstruct
    public void listar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar("tipo");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            usuario = new Usuario();
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
    }

    public void salvar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.merge(usuario);
            usuario = new Usuario();
            usuarios = usuarioDAO.listar("tipo");
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoas = pessoaDAO.listar("nome");
            Messages.addGlobalInfo("Usuário salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
            UsuarioDAO fdao = new UsuarioDAO();
            fdao.excluir(usuario);
            Messages.addGlobalInfo("Registro excluído com sucesso.");
        } catch (RuntimeException err) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir este registro.");
            err.printStackTrace();
        }
    }
}
