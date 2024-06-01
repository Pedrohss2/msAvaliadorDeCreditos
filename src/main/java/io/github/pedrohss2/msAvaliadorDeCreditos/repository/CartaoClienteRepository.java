package io.github.pedrohss2.msAvaliadorDeCreditos.repository;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoClienteRepository {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> procurarPorCpf(@RequestParam(name = "cpf", defaultValue = "") String cpf);

}
