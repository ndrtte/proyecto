package hn.unah.proyecto.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String dni;
    
    private String nombre;
    
    private String apellido;
    
    private String telefono;
    
    private String correo;

    private double sueldo;

    private DireccionDTO direccion;
    
}
