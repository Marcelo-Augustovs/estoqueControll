package dev.marcelo.estoqueControll.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "deposito")
@Getter
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "alimentoNoEstoque")
    private Alimento alimentoNoEstoque;
    @Column(name = "quantidade")
    private int quantidadeDeAlimentos;

}
