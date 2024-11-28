package hn.unah.proyecto.enums;

import lombok.Getter;

@Getter
public enum TipoPrestamoEnum {
    Vehicular('V'),Personal('P'),Hipotecario('H');

    private char c;

    private TipoPrestamoEnum(char c) {
        this.c = c;
    }
}
