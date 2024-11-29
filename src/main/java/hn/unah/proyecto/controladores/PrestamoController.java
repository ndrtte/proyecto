package hn.unah.proyecto.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dtos.PrestamosDTO;
import hn.unah.proyecto.servicios.PrestamoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoServicio prestamoServicio;
    
    @PostMapping("/crear/nuevo")
    public String crearPrestamos(@RequestBody PrestamosDTO nvoPrestamo ) {
        return prestamoServicio.crearPrestamos(nvoPrestamo);
    }
    

}
