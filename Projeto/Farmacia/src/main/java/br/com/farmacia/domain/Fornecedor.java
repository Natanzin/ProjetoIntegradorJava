package br.com.farmacia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Fornecedor extends GenericDomain {

    @Getter
    @Setter
    @Column
    private String descricao;

    public Fornecedor() {
        super();
    }

    public Fornecedor(Long id, String descricao) {
        super();
        setId(id);
        this.descricao = descricao;
    }
}
