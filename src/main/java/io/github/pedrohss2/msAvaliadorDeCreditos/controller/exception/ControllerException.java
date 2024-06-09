package io.github.pedrohss2.msAvaliadorDeCreditos.controller.exception;

import io.github.pedrohss2.msAvaliadorDeCreditos.model.exception.CustomError;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.exception.ComunicacaoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.exception.DadosClienteNaoEncontradoException;
import io.github.pedrohss2.msAvaliadorDeCreditos.service.exception.SolicitacaoCartaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerException extends RuntimeException {

    @ExceptionHandler(DadosClienteNaoEncontradoException.class)
    public ResponseEntity<CustomError> DadosClienteNaoEncontrado(DadosClienteNaoEncontradoException erro, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(SolicitacaoCartaoException.class)
    public ResponseEntity<CustomError> SolicitacaoCartao(DadosClienteNaoEncontradoException erro, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(ComunicacaoException.class)
    public ResponseEntity<CustomError>  ComunicacaoException(DadosClienteNaoEncontradoException erro, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

}
