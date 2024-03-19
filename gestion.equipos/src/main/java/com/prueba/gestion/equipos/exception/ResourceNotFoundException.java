package com.prueba.gestion.equipos.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ChangeSetPersister.NotFoundException {
    private final String code;
    private final String field;
    private final HttpStatus httpStatus;

    public ResourceNotFoundException(String code, String field) {
        this.code = code;
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        String message = "";

        if (code.equals("noExistDB")) {
            message = "El recurso '" + field + "' no existe en la base de datos.";
        }
        if (code.equals("noDataFound")) {
            message = "No se encontraron resultados.";
        }

        return message;
    }
}


