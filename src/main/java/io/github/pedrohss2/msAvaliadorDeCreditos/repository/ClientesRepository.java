package io.github.pedrohss2.msAvaliadorDeCreditos.repository;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClientesRepository {

    @GetMapping
    ResponseEntity<DadosCliente> buscarPorCpf(@RequestParam(name = "cpf", defaultValue = "") String cpf);

}
