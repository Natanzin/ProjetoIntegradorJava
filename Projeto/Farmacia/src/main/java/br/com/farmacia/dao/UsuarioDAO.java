package br.com.farmacia.dao;


import br.com.farmacia.domain.Usuario;
import br.com.farmacia.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO extends GenericDAO<Usuario> {

    @SuppressWarnings("unchecked")
    public Usuario autenticar(String login, String senha) {
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Usuario.class,"u");
            consulta.setFetchMode("pessoa",FetchMode.JOIN);
            consulta.createAlias("pessoa","p");
            consulta.add(Restrictions.eq("p.cpf", login));
            consulta.add(Restrictions.eq("u.senha", senha));
            consulta.add(Restrictions.eq("u.ativo", true));
            return (Usuario) consulta.uniqueResult();
        } catch (HibernateException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

}
