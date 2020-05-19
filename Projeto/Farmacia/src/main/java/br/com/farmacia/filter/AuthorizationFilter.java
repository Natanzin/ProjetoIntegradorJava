package br.com.farmacia.filter;

import br.com.farmacia.domain.Usuario;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            Usuario usuario = null;
            HttpSession sess = ((HttpServletRequest) request).getSession(false);

            if (sess != null) {
                usuario = (Usuario) sess.getAttribute("usuarioLogado");
            }

            if (usuario == null) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/acesso/login.xhtml");
            } else
                chain.doFilter(request, response);

        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
    }

    @Override
    public void destroy() {
    }
}
