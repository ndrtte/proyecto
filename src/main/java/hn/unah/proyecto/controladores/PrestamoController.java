package hn.unah.proyecto.controladores;

import hn.unah.proyecto.dtos.PrestamosDTO;
import hn.unah.proyecto.servicios.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @PostMapping
    public String crearPrestamo(@RequestBody PrestamosDTO prestamo) {
        return prestamoServicio.crearPrestamo(prestamo);
    }

    @GetMapping("/dni/{dni}")
    public List<PrestamosDTO> buscarPrestamosPorDni(@PathVariable String dni) {
        return prestamoServicio.buscarPrestamosPorDni(dni);
    }

    @GetMapping("/{id}")
    public Optional<PrestamosDTO> buscarPrestamoPorId(@PathVariable int id) {
        return prestamoServicio.buscarPrestamoPorId(id);
    }

    @PutMapping("/{idPrestamo}/{dniCliente}")
    public void asociarPrestamoACliente(@PathVariable int idPrestamo, @PathVariable String dniCliente) {
        prestamoServicio.asociarPrestamoACliente(idPrestamo, dniCliente);
    }

    @GetMapping("/saldo/{idPrestamo}")
    public double obtenerSaldoPendiente(@PathVariable int idPrestamo) {
        return prestamoServicio.obtenerSaldoPendiente(idPrestamo);
    }

    @PutMapping("/pagar/{idPrestamo}")
    public void pagarCuota(@PathVariable int idPrestamo) {
        prestamoServicio.pagarCuota(idPrestamo);
    }
}