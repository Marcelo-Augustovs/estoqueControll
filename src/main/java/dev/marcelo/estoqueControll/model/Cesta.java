package dev.marcelo.estoqueControll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cesta")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor
public class Cesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "mes")
    private int mes;
    @Column(name = "quantidadeDeAlimentos")
    private int quantidadeDeAlimentos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cesta_id")
    private List<Alimento> alimentos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "familia_id")
    @JsonIgnore
    private Familia familia;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "criado_por")
    @LastModifiedBy
    private String criadoPor;

    public Cesta(int mes){
        this.mes = mes;
    }
}
