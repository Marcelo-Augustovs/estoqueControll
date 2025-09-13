package dev.marcelo.estoqueControll.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "alimento")
@Getter
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
}
