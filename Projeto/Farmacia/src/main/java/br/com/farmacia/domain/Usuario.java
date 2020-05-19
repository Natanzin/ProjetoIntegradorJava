package br.com.farmacia.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

    @Getter
    @Setter
    @Column(length = 32, nullable = false)
    private String senha;

    @Getter
    @Setter
    @Column(nullable = false)
    private Character tipo;

    @Getter
    @Setter
    @Column(nullable = false)
    private Boolean ativo;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    private Pessoa pessoa = new Pessoa();

    public Usuario() {
        super();
    }


    @Transient
    public String getTipoFormatado() {
        String tipoFormatado = null;

        if (tipo == 'A') {
            tipoFormatado = "Administrador";
        } else if (tipo == 'B') {
            tipoFormatado = "Balconista";
        }
        return tipoFormatado;
    }


    @Transient
    public String getAtivoFormatado() {
        String ativoFormatado = "Não";

        if (ativo) {
            ativoFormatado = "Sim";
        }

        return ativoFormatado;
    }
}
