package br.com.farmacia.bean;

import br.com.farmacia.dao.PessoaDAO;
import br.com.farmacia.dao.UsuarioDAO;
import br.com.farmacia.domain.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;

@ManagedBean(name = "MBLogin")
@ViewScoped
public class LoginBean {

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private String senha;

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private UsuarioDAO usuarioDAO;

    @Getter
    @Setter
    private PessoaDAO pessoaDAO;

    @PostConstruct
    public void telaInicial(){
        usuario = new Usuario();
        pessoaDAO = new PessoaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public String envia() {
        // throw - CPF inexistente no sistema.
        usuario.setPessoa(pessoaDAO.buscarPorCPF(cpf));

        // throw - Senha inválida;
        usuario = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            Messages.addGlobalError("Não foi possível realizar o login no sistema.");
            return null;
        } else {
            Messages.addGlobalInfo("Seja bem vindo.");
            return "/index.xhtml";
        }


    }

}
