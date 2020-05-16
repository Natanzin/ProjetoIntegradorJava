package br.com.farmacia.dao;


import br.com.farmacia.domain.Usuario;
import br.com.farmacia.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO extends GenericDAO<Usuario> {

    @SuppressWarnings("unchecked")
    public Usuario autenticar(String login, String senha) {
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Usuario.class);
            consulta.add(Restrictions.eq("pessoa.cpf", login));
            consulta.add(Restrictions.eq("senha", senha));
            consulta.add(Restrictions.eq("ativo", true));
            return (Usuario) consulta.uniqueResult();
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

}
