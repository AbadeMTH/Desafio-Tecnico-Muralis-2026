package com.matheus.desafiotecnicomuralis.dto.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheus.desafiotecnicomuralis.entity.contato.ContatoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;

    private String endereco;
    private List<ContatoEntity> listaContatos;
}
