package br.iesb.projeto.managedbeans;

import br.iesb.projeto.entitybeans.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

    private Usuario usuario;
    private boolean autenticado;

    public UsuarioMB() {
        this.usuario = new Usuario();
        this.autenticado = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public String entrar() {
        this.autenticado = true;
        return "/pages/index?faces-redirect=true";
    }

    public String sair() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

}
