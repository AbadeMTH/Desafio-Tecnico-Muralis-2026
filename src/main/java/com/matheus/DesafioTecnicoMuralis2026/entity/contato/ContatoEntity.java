package com.matheus.DesafioTecnicoMuralis2026.entity.contato;

import com.matheus.DesafioTecnicoMuralis2026.entity.cliente.ClienteEntity;
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
    private int id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String valor;

    private String observacao;

    @ManyToOne(targetEntity = ClienteEntity.class)
    private ClienteEntity cliente_id;
}
