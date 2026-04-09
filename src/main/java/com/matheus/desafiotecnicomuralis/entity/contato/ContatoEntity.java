package com.matheus.desafiotecnicomuralis.entity.contato;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.desafiotecnicomuralis.entity.cliente.ClienteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_contatos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String valor;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private ClienteEntity cliente;
}
