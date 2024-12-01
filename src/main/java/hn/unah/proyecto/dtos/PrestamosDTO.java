package hn.unah.proyecto.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamosDTO {
    private int idPrestamo;

    private double monto;
    
    private int plazo;

    private double tasaInteres;

    private double cuota;

    private char estado;

    private char tipoPrestamo;

    private List<Cliente2DTO> listaCliente;

    @JsonIgnore
    private List<TablaAmortizacionDTO> listaAmortizacion;
}
