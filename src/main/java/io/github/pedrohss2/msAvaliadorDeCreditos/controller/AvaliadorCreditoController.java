package io.github.pedrohss2.msAvaliadorDeCreditos.controller;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.*;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam(name = "cpf", defaultValue = "") String cpf) {
        SituacaoCliente situacaoCliente = avaliadorCreditoService.consultaSituacaoCliente(cpf);

        return ResponseEntity.ok().body(situacaoCliente);
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao) {

        RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacaoCliente(dadosAvaliacao.getCpf(), dadosAvaliacao.getRenda());

        return ResponseEntity.ok().body(retornoAvaliacaoCliente);
    }

}
