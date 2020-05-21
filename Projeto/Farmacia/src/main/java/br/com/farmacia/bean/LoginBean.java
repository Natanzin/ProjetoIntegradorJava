package br.com.farmacia.bean;

import br.com.farmacia.dao.PessoaDAO;
import br.com.farmacia.dao.UsuarioDAO;
import br.com.farmacia.domain.Pessoa;
import br.com.farmacia.domain.Usuario;
import br.com.farmacia.util.SessionUtils;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.logging.Logger;

@ManagedBean(name = "MBLogin")
@SessionScoped
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

    private static Logger logger = Logger.getLogger(String.valueOf(LoginBean.class));

    @PostConstruct
    public void telaInicial() {
        usuario = new Usuario();
        pessoaDAO = new PessoaDAO();
        usuarioDAO = new UsuarioDAO();
    }

    public String doLogin() throws Exception {
        try {
        	Pessoa pessoa = new Pessoa();
        	pessoa = pessoaDAO.buscarPorCPF(cpf);
            if(pessoa.getId()==null)
            	throw new Exception();
            usuario = usuarioDAO.autenticar(pessoa.getCpf(), usuario.getSenha());
            if (usuario.getId() == null) {
                throw new Exception();
            } else {
                logger.info("Login efetuado com sucesso");
                SessionUtils.getInstance().setAttribute("usuarioLogado", usuario);
                return "/pages/index.xhtml?faces-redirect=true";
            }

        } catch (Exception ex) {
            this.cpf = "";
            this.senha = "";
            Messages.addGlobalError("Usuário ou senha incorretos.");
        }

        return "/acesso/login.xhtml";
    }


    public Usuario getUsuarioLogado() {
        return (Usuario) SessionUtils.getInstance().getUsuarioLogado();
    }

    public String doLogout() throws Exception {
        logger.info("Fazendo logout com usuário ");
        SessionUtils.getInstance().encerrarSessao();
        return "/acesso/login.xhtml?faces-redirect=true";
    }

    public boolean isUsuarioAdm() {
        return SessionUtils.getInstance().getUsuarioLogado().getTipoFormatado().equals("Administrador");
    }

}

