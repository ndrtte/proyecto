package hn.unah.proyecto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTO {
    private int idDireccion;

    private String pais;

    private String departamento;

    private String ciudad;

    private String colonia;

    private String referencia;

}
