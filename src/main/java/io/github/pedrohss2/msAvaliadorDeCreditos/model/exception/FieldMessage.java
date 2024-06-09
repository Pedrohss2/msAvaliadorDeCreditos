package io.github.pedrohss2.msAvaliadorDeCreditos.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessage {

    private String fieldName;
    private String message;
}
