package hn.unah.proyecto.enums;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class TipoPrestamo {

    private static final Map<Character, Double> tasas = new HashMap<>();

    @Value("${prestamo.vehicular}")
    private static double vehicular;

    @Value("${prestamo.personal}")
    private static double personal;

    @Value("${prestamo.hipotecario}")
    private static double hipotecario;

    public static void valorTasas() {
        tasas.put(PrestamoEnum.Vehicular.getC(), vehicular);
        tasas.put(PrestamoEnum.Personal.getC(), personal);
        tasas.put(PrestamoEnum.Hipotecario.getC(), hipotecario);
    }

    public static double obtenerTasa(char tipo) {
        return tasas.get(tipo);
    }
}

