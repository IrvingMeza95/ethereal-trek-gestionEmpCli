package com.TeamCode.serviciousuarios.enums;

public enum TipoServicio {
    RESERVACION_DE_HOTEL ("Reservación de hotel"),
    ALQUILER_DE_AUTO("Alquiler de auto"),
    PASAJES_DE_COLECTIVO ("Pasajes de colectivo"),
    PASAJES_DE_AVION ("Pasajes de avión"),
    PASAJES_DE_TREN ("Pasajes de tren"),
    EXCURSIONES ("Excursiones"),
    ENTRADAS_A_EVENTOS ("Entradas a eventos");

    private final String tag;

    TipoServicio(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

}
