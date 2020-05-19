package br.com.farmacia.dao;

import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class FornecedorDAO extends GenericDAO<Fornecedor> {
    @SuppressWarnings("unchecked")
    public List<Fornecedor> buscarPorDescricao(String descricao) {
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        try {
            Criteria consulta = sessao.createCriteria(Fornecedor.class);
            consulta.add(Restrictions.eq("descricao", descricao));
            consulta.addOrder(Order.asc("descricao"));
            List<Fornecedor> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
