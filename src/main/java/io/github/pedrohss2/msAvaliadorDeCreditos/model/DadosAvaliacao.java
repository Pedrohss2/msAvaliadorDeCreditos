package io.github.pedrohss2.msAvaliadorDeCreditos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosAvaliacao {

    private String cpf;
    private Long renda;
}
