package com.dbs.cp3.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime dataCadastro;

    public Produto(salvarProdutoDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
    }

    public Produto(atualizarProdutoDTO dados) {
        this.preco = dados.preco();
    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.dataCadastro = LocalDateTime.now();
    }


    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDateTime.now();
    }
}

