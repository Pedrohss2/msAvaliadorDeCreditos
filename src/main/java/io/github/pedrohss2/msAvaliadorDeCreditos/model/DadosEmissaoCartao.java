package io.github.pedrohss2.msAvaliadorDeCreditos.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosEmissaoCartao {

    private Long id;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
