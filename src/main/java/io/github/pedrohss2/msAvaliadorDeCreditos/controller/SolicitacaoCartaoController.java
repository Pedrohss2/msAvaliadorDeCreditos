package io.github.pedrohss2.msAvaliadorDeCreditos.controller;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.DadosEmissaoCartao;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.ProtocoloSolicitacaoCartao;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.SolicitarCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("solicitacao-cartao")
public class SolicitacaoCartaoController {

    @Autowired
    private SolicitarCartaoService solicitarCartaoService;

    @PostMapping
    public ResponseEntity solicitaCartao(@RequestBody DadosEmissaoCartao dadosEmissaoCartao) {
        ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = solicitarCartaoService.solicitarEmissaoDeCartao(dadosEmissaoCartao);

        return ResponseEntity.ok().body(protocoloSolicitacaoCartao);
    }
}
