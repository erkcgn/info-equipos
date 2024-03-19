package com.prueba.gestion.equipos.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.prueba.gestion.equipos.exception.ResourceException;

import java.util.HashMap;
import java.util.Map;

public record GenericResponse<T extends Object>(T data, HttpStatus httpStatus) {
    public static <T extends Object> ResponseEntity<GenericResponse<T>> createdResponse(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse<>(data, HttpStatus.CREATED));
    }
    public static <T extends Object> ResponseEntity<GenericResponse<T>> okResponse(T data) {
        return ResponseEntity.ok(new GenericResponse<>(data, HttpStatus.OK));
    }
    public static <T extends Object> ResponseEntity<GenericResponse<T>> exceptionResponse(ResourceException exception) {
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("mensaje", exception.getMensaje());
        errorData.put("codigo", exception.getCodigo());
        return ResponseEntity.status(exception.getCodigo())
                .body((GenericResponse<T>) new GenericResponse<>(errorData, HttpStatus.valueOf(exception.getCodigo())));
    }

}



