package dev.marcelo.estoqueControll.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "deposito")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mes")
    private int mes;

    @Column(name = "ano")
    private int ano;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "deposito_id")
    private List<Alimento> alimentos = new ArrayList<>();

    @Column(name = "quantidade")
    private int quantidadeDeAlimentos;

    public Deposito(Alimento alimento, int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
        this.alimentos.add(alimento);
        this.quantidadeDeAlimentos = 1;
    }

    public void adicionarAlimento(Alimento alimento) {
        this.alimentos.add(alimento);
        this.quantidadeDeAlimentos = this.alimentos.size();
    }
}

