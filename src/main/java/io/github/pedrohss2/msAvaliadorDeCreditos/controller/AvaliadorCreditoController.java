package io.github.pedrohss2.msAvaliadorDeCreditos.controller;

import io.github.pedrohss2.msAvaliadorDeCreditos.controller.exception.ComunicacaoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.controller.exception.DadosClienteNaoEncontradoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.DadosAvaliacao;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.RetornoAvaliacaoCliente;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.SituacaoCliente;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam(name = "cpf", defaultValue = "") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.consultaSituacaoCliente(cpf);

            return ResponseEntity.ok().body(situacaoCliente);
        }
        catch (DadosClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ComunicacaoException e) {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao) {
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacaoCliente(dadosAvaliacao.getCpf(), dadosAvaliacao.getRenda());

            return ResponseEntity.ok().body(retornoAvaliacaoCliente);
        }
        catch (DadosClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ComunicacaoException e) {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(e.getMessage());
        }
    }

}
