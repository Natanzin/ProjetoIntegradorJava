package br.com.farmacia.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.farmacia.domain.Usuario;


@ManagedBean(name = "usuariosBean")
@SessionScoped
public class UsuariosBean  {
	
	private Usuario usuario;
	private boolean autenticado;
	
	public void UsuarioBean() {
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
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
}
