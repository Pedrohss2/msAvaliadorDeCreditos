package io.github.pedrohss2.msAvaliadorDeCreditos.service;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.CartaoCliente;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.DadosCliente;
import io.github.pedrohss2.msAvaliadorDeCreditos.model.SituacaoCliente;
import io.github.pedrohss2.msAvaliadorDeCreditos.repository.CartaoClienteRepository;
import io.github.pedrohss2.msAvaliadorDeCreditos.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private CartaoClienteRepository cartaoClienteRepository;

    public SituacaoCliente consultaSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> dadosClientes = clientesRepository.buscarPorCpf(cpf);
        ResponseEntity<List<CartaoCliente>> cartaoCliente = cartaoClienteRepository.procurarPorCpf(cpf);

        return  SituacaoCliente
                .builder()
                .cliente(dadosClientes.getBody())
                .cartoes(cartaoCliente.getBody())
                .build();
    }

}
