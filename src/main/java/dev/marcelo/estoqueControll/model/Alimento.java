package dev.marcelo.estoqueControll.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "alimento")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "tipoDoAlimento")
    private String tipoDoAlimento;
    @Column(name = "marca")
    private String marca;
    @Column(name = "validade")
    private String validade;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "criado_por")
    @CreatedBy
    private String criadoPor;
    @Column(name = "atualizado-por")
    @LastModifiedBy
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alimento)) return false;
        return id != null && id.equals(((Alimento) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
