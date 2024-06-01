package io.github.pedrohss2.msAvaliadorDeCreditos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {

    List<CartaoAprovado> cartaoAprovados;
}
