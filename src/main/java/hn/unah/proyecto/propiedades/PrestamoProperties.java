package hn.unah.proyecto.propiedades;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrestamoProperties {

    @Value("${prestamo.personal}")
    private double personal;

    @Value("${prestamo.vehicular}")
    private double vehicular;

    @Value("${prestamo.hipotecario}")
    private double hipotecario;


}

