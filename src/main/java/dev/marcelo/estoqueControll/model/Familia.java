package dev.marcelo.estoqueControll.model;

import jakarta.persistence.*;

@Entity
@Table(name = "familia")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "endereco")
    private String endereco;

}
