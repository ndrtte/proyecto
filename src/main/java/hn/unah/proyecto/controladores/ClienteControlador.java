package hn.unah.proyecto.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dtos.ClienteDTO;
import hn.unah.proyecto.servicios.ClienteServicio;



@RestController
@RequestMapping("api/clientes")
public class ClienteControlador {

      @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/obtener/dni")
    public Optional<ClienteDTO> obtenerPorId(@RequestParam(name="dni") String dni) {
        return this.clienteServicio.obtenerPorDni(dni);
    }

    @GetMapping("/obtener")
    public List<ClienteDTO> obtenerTodos() {
        return this.clienteServicio.obtenerTodos();
    }

    @PostMapping("/crear/nuevo")
    public String crearNuevoCliente(@RequestBody ClienteDTO nvoCliente) {
        return this.clienteServicio.crearCliente(nvoCliente);
    }

    @DeleteMapping("/eliminar/{dni}")
    public String eliminarCliente(@PathVariable(name="dni") String dni){
        return this.clienteServicio.eliminarClientePorId(dni);
    }
    
}
