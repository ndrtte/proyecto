package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.modelos.Cliente;
import hn.unah.proyecto.modelos.Direccion;
import hn.unah.proyecto.modelos.Prestamos;
import hn.unah.proyecto.ModelMapper.ModelMapperSingleton;
import hn.unah.proyecto.dtos.ClienteDTO;
import hn.unah.proyecto.dtos.DireccionDTO;
import hn.unah.proyecto.dtos.PrestamosDTO;
import hn.unah.proyecto.enums.PrestamoEnum;
import hn.unah.proyecto.repositorios.ClienteRepositorio;

@Service
@Component
@Configuration
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Value("${prestamo.vehicular}")
    private double vehicular;

    @Value("${prestamo.personal}")
    private double personal;

    @Value("${prestamo.hipotecario}")
    private double hipotecario;
    
    private ModelMapper modelMapper = ModelMapperSingleton.getInstancia();
    
    public List<ClienteDTO> obtenerTodos(){
        List<Cliente> listaCliente = clienteRepositorio.findAll();
        List<ClienteDTO> listaClienteDTO = new ArrayList<>();
        ClienteDTO clienteDTO;
        for (Cliente cliente : listaCliente) {
            clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
            listaClienteDTO.add(clienteDTO);
        }
        return listaClienteDTO;
    }

    public Optional<ClienteDTO> obtenerPorDni(String dni){
        Optional<Cliente> cliente = clienteRepositorio.findById(dni);
        ClienteDTO clienteDto =  this.modelMapper.map(cliente, ClienteDTO.class);
        return Optional.ofNullable(clienteDto);
    }


    public String crearCliente(ClienteDTO nvoCliente) {
        if (this.clienteRepositorio.existsById(nvoCliente.getDni())) {
            return "Ya existe el cliente";
        }

        Cliente nvoClienteBd = modelMapper.map(nvoCliente, Cliente.class);

        List<DireccionDTO> direccionDTOs = nvoCliente.getListaDireccion();
        List<Direccion> listaDirecciones = new ArrayList<>(); 

        if(direccionDTOs.size()<=2){
            for (DireccionDTO d : direccionDTOs) {
                Direccion direccion = modelMapper.map(d,Direccion.class);
                direccion.setCliente(nvoClienteBd);
                listaDirecciones.add(direccion);
            } 
        }
        else{
            return "Un cliente no puede tener mas de 2 direcciones";
        }

        List<PrestamosDTO> prestamosDTOs = nvoCliente.getListaPrestamos();
        List<Prestamos> listaPrestamos = new ArrayList<>();

        for (PrestamosDTO p : prestamosDTOs) {
            Prestamos nvoPrestamo = modelMapper.map(p, Prestamos.class);
            char tipo = Character.toUpperCase(nvoPrestamo.getTipoPrestamo());
            if (tipo == PrestamoEnum.Hipotecario.getC() ||
                tipo == PrestamoEnum.Personal.getC() ||
                tipo == PrestamoEnum.Vehicular.getC()) {
                nvoPrestamo.setEstado('A');
                nvoPrestamo.setTipoPrestamo(tipo);

                switch (p.getTipoPrestamo()) {
                    case 'V':
                        nvoPrestamo.setTasaInteres(vehicular);
                        break;
                    case 'P':
                        nvoPrestamo.setTasaInteres(personal);
                        break;
                    case 'H':
                        nvoPrestamo.setTasaInteres(hipotecario);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de préstamo no válido");
                }

                listaPrestamos.add(nvoPrestamo);
            }
        }

        nvoClienteBd.setListaDireccion(listaDirecciones);
        nvoClienteBd.setListaPrestamos(listaPrestamos);

        clienteRepositorio.save(nvoClienteBd);

        return "Cliente creado exitosamente";
    }

    
    

    public String eliminarClientePorId(String id){
        if(!this.clienteRepositorio.existsById(id)){
            return "No existe el cliente";
        }
        this.clienteRepositorio.deleteById(id);
        return "Cliente eliminado satisfactoriamente";
    }
    
}
