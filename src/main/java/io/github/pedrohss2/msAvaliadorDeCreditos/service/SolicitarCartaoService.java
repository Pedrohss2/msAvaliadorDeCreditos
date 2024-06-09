package io.github.pedrohss2.msAvaliadorDeCreditos.service;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.DadosEmissaoCartao;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.ProtocoloSolicitacaoCartao;
import io.github.pedrohss2.msAvaliadorDeCreditos.mqueue.EmissaoCartaoPublisher;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.exception.SolicitacaoCartaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SolicitarCartaoService {

    @Autowired
    private EmissaoCartaoPublisher emissaoCartaoPublisher;

    public ProtocoloSolicitacaoCartao solicitarEmissaoDeCartao(DadosEmissaoCartao dadosEmissaoCartao) {
        try {
            emissaoCartaoPublisher.solicitarCartao(dadosEmissaoCartao);
            var protocolo = UUID.randomUUID().toString();

            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch(Exception erro) {
            throw new SolicitacaoCartaoException(erro.getMessage());
        }
    }

}
