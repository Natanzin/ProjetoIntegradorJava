package br.com.farmacia.dao;

import br.com.farmacia.domain.Produto;

import br.com.farmacia.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ProdutoDAO extends GenericDAO<Produto> {
	
	    //@SuppressWarnings("unchecked")
	    public Produto buscarPorDescricao(String descricao) {
	        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	        try {
	            Criteria consulta = sessao.createCriteria(Produto.class);
	            consulta.add(Restrictions.eq("descricao", descricao));
	            return (Produto) consulta.uniqueResult();
	        } catch (RuntimeException erro) {
	            throw erro;
	        } finally {
	            sessao.close();
	        }
	    }
	}

