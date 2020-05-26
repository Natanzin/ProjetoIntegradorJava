package br.com.farmacia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

    @Getter
    @Setter
    @Column
    private String descricao;

    @Getter
    @Setter
    @Column
    private Integer quantidade;

    @Getter
    @Setter
    @Column
    private Double preco;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    public Produto() {
    	super();
    }

    public Produto(Long id, String descricao, int quantidade, Double preco, Fornecedor fornecedor) {
        super();
    	setId(id);
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }
}
