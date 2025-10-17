package dev.marcelo.estoqueControll.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "familia")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "cesta_mes_recebido")
    private Boolean cestaDoMesRecebido;
    @Column(name = "quantidadeDeCestaRecebida")
    private Integer quantidadeDeCestasRecebidas;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "familia_id")
    private List<Cesta> cestasRecebidas = new ArrayList<>();

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    @Column(name = "criado_por")
    @CreatedBy
    private String criadoPor;
    @Column(name = "atualizado-por")
    @LastModifiedBy
    private String modificadoPor;
}
