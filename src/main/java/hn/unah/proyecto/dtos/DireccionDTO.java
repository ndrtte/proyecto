package hn.unah.proyecto.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private ClienteDTO listaCliente;
}
