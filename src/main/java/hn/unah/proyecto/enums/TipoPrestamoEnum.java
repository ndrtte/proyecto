package hn.unah.proyecto.enums;

import hn.unah.proyecto.properties.PrestamoProperties;
import lombok.Getter;

@Getter
public enum TipoPrestamoEnum {
    Vehicular('V'),Personal('P'),Hipotecario('H');

    private char c;

    private TipoPrestamoEnum(char c) {
        this.c = c;
    }

    public static double obtenerTasaInteres(TipoPrestamoEnum tipo, PrestamoProperties properties) {
        double tasaInteres = 0;
        if (tipo == TipoPrestamoEnum.Vehicular) {
            tasaInteres = properties.getVehicular();
        } else if (tipo == TipoPrestamoEnum.Personal) {
            tasaInteres = properties.getPersonal();
        } else if (tipo == TipoPrestamoEnum.Hipotecario) {
            tasaInteres = properties.getHipotecario();
        }
        return tasaInteres;
    }
    
}
