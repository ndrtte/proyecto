package hn.unah.proyecto.dtos;

import java.time.LocalDate;

import hn.unah.proyecto.modelos.Prestamos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TablaAmortizacionDTO {
    private int id;

    private int numeroCuota;

    private double interes;
    
    private double capital;
    
    private double saldo;
    
    private char estado;
    
    private LocalDate fechaVencimiento;

    private Prestamos prestamo;
}
