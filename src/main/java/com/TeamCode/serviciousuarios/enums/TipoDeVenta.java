package com.TeamCode.serviciousuarios.enums;

public enum TipoDeVenta {
    SERVICIO ("Servicio"),
    PAQUETE ("Paquete");

    private final String tag;

    TipoDeVenta(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
