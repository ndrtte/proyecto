package hn.unah.proyecto.dtos;

import java.util.List;

import hn.unah.proyecto.modelos.Cliente;
import hn.unah.proyecto.modelos.TablaAmortizacion;
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

    private List<Cliente> listaCliente;

    private List<TablaAmortizacion> listaAmortizacion;
}
