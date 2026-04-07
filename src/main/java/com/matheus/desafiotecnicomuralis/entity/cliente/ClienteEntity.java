package com.matheus.desafiotecnicomuralis.entity.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate data_nascimento;

    private String endereco;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<ContatoEntity> listaContatos;
}
