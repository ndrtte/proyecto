package hn.unah.proyecto.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "prestamo")
@Configuration
public class PrestamoProperties {
    private double personal;    
    private double vehicular;  
    private double hipotecario;
}
