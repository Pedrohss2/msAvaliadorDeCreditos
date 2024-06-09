package io.github.pedrohss2.msAvaliadorDeCreditos.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class CustomError {

    private Instant timestamp;
    private Integer status;
    private String error;
}
