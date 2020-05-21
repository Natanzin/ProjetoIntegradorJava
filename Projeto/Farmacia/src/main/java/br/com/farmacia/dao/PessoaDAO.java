package br.com.farmacia.dao;


import br.com.farmacia.domain.Pessoa;
import br.com.farmacia.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PessoaDAO extends GenericDAO<Pessoa> {
    @SuppressWarnings("unchecked")
    public Pessoa buscarPorCPF(String cpf) {
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Pessoa.class);
            consulta.add(Restrictions.eq("cpf", cpf));
            return (Pessoa) consulta.uniqueResult();
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
