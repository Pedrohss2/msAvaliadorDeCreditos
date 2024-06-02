package io.github.pedrohss2.msAvaliadorDeCreditos.service;

import feign.FeignException;
import io.github.pedrohss2.msAvaliadorDeCreditos.controller.exception.ComunicacaoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.controller.exception.DadosClienteNaoEncontradoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.controller.exception.SolicitacaoCartaoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.*;
import io.github.pedrohss2.msAvaliadorDeCreditos.mqueue.EmissaoCartaoPublisher;
import io.github.pedrohss2.msAvaliadorDeCreditos.repository.CartaoClienteRepository;
import io.github.pedrohss2.msAvaliadorDeCreditos.repository.ClientesRepository;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private CartaoClienteRepository cartaoClienteRepository;

    public SituacaoCliente consultaSituacaoCliente(String cpf) {
        try {
            ResponseEntity<DadosCliente> dadosClientes = clientesRepository.buscarPorCpf(cpf);
            ResponseEntity<List<CartaoCliente>> cartaoCliente = cartaoClienteRepository.procurarPorCpf(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClientes.getBody())
                    .cartoes(cartaoCliente.getBody())
                    .build();

        } catch (FeignException.FeignClientException erro) {
            int status = erro.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNaoEncontradoException("Dados do cliente não encontrados");
            }
            throw new ComunicacaoException(erro.contentUTF8());
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacaoCliente(String cpf, Long renda) {
        try {

            ResponseEntity<DadosCliente> dadosClientes = clientesRepository.buscarPorCpf(cpf);
            ResponseEntity<List<Cartao>> procurarPorRenda = cartaoClienteRepository.procurarPorRenda(renda);

            List<Cartao> cartaos = procurarPorRenda.getBody();

            return new RetornoAvaliacaoCliente(this.fazerCalculoDeLimiteParaCartao(cartaos, dadosClientes.getBody().getIdade(), renda));

        } catch (FeignException.FeignClientException erro) {
            int status = erro.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNaoEncontradoException("Dados do cliente não encontrados");
            }
            throw new ComunicacaoException(erro.contentUTF8());
        }
    }

    private List<CartaoAprovado> fazerCalculoDeLimiteParaCartao(List<Cartao> cartaos, Integer idadeCliente, Long renda) {

        return cartaos.stream().map(cartao -> {

            BigDecimal rendaBD = BigDecimal.valueOf(renda);
            BigDecimal idadeBD = BigDecimal.valueOf(idadeCliente);

            var fator = idadeBD.divide(BigDecimal.valueOf(10)).multiply(rendaBD);

            CartaoAprovado cartaoAprovado = new CartaoAprovado();
            cartaoAprovado.setNome(cartao.getNome());
            cartaoAprovado.setBandeira(cartao.getBandeira());
            cartaoAprovado.setLimiteAprovado(fator);

            return cartaoAprovado;
        }).collect(Collectors.toList());
    }

}
