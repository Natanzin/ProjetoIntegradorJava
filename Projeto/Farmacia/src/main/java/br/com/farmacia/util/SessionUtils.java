package br.com.farmacia.util;

import br.com.farmacia.domain.Usuario;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionUtils {

    private static SessionUtils instance;

    public static SessionUtils getInstance() {
        if (instance == null) {
            instance = new SessionUtils();
        }

        return instance;
    }

    private SessionUtils() {

    }

    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

    public String getUsuario() {
        return getAttribute("usuario").toString();
    }

    public String getTipoUsuario() {
        return getAttribute("tipoUsuario").toString();
    }

    public String getNomeUsuario() {
        return getAttribute("nomeUsuario").toString();
    }

    public Usuario getUsuarioLogado() {
        return (Usuario) getAttribute("usuarioLogado");
    }

    public void setUsuarioLogado(Usuario usuario) {
        setAttribute("usuarioLogado", usuario);
    }

    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

}
