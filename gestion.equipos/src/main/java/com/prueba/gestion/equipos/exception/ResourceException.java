package com.prueba.gestion.equipos.exception;

public class ResourceException extends RuntimeException {
    private final String mensaje;
    private final int codigo;

    public ResourceException(String mensaje, int codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getCodigo() {
        return codigo;
    }
}


