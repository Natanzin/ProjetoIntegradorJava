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
    public void telaInicial() {
        usuario = new Usuario();
        pessoaDAO = new PessoaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public String envia() throws Exception {
        try {
            usuario.setPessoa(pessoaDAO.buscarPorCPF(cpf));
            usuario = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());

            if (usuario == null) {
                usuario = new Usuario();
                throw new Exception();
            } else {
                Messages.addGlobalInfo("Seja bem vindo.");
                return "/index.xhtml";
            }

        } catch (Exception ex) {
            Messages.addGlobalError("Usuário ou senha incorretos.");
        }

        return "/login.xhtml";
    }

}
