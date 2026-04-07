package com.matheus.DesafioTecnicoMuralis2026.entity.cliente;

import com.matheus.DesafioTecnicoMuralis2026.entity.contato.ContatoEntity;
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

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate data_nascimento;
    private String endereco;

    @OneToMany
    private List<ContatoEntity> listaContatos;
}
