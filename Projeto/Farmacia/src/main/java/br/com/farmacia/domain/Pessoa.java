package br.com.farmacia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Pessoa extends GenericDomain {

    @Getter
    @Setter
    @Column(length = 50, nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(length = 14, nullable = false)
    private String cpf;

    @Getter
    @Setter
    @Column(length = 14, nullable = false)
    private String celular;

    @Getter
    @Setter
    @Column(length = 100, nullable = false)
    private String email;

}
