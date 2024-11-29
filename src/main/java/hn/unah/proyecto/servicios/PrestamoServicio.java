package hn.unah.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dtos.PrestamosDTO;
import hn.unah.proyecto.repositorios.PrestamosRepositorio;

@Service
public class PrestamoServicio {

    @Autowired
    private PrestamosRepositorio prestamosRepositorio;
    
    public String crearPrestamos(PrestamosDTO prestamo ) {
        return "hola";
    }
}
