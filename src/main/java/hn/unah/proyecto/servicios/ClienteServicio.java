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
import hn.unah.proyecto.excepciones.ClienteNoEncontradoException;
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

    public Optional<ClienteDTO> obtenerPorDni(String dni) throws ClienteNoEncontradoException {
        Optional<Cliente> cliente = clienteRepositorio.findById(dni);
    
        if (cliente.isEmpty()) {
            throw new ClienteNoEncontradoException("Cliente con el DNI " + dni + " no encontrado.");
        }

        ClienteDTO clienteDto = this.modelMapper.map(cliente.get(), ClienteDTO.class);
        
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
                    if(nvoPrestamo.getPlazo() >= 12){
                        nvoPrestamo.setEstado('A');
                        nvoPrestamo.setTipoPrestamo(tipo);
                        
                        switch (tipo) {
                            case 'V':
                                nvoPrestamo.setTasaInteres(vehicular);
                                break;
                            case 'P':
                                nvoPrestamo.setTasaInteres(personal);
                                break;
                            case 'H':
                                nvoPrestamo.setTasaInteres(hipotecario);
                                break;
                        }

                        double monto = nvoPrestamo.getMonto();
                        double r = nvoPrestamo.getTasaInteres()/12;
                        double plazo = nvoPrestamo.getPlazo()/12;
                        int n = (int) (plazo*12);
            
                        double cuota = (monto*r*Math.pow(1+r, n))/(Math.pow(1+r, n)-1);
                        nvoPrestamo.setCuota(cuota);

                        listaPrestamos.add(nvoPrestamo);
                    }
                    else{
                        return "La cantidad de prestamos digitada supera su nivel de endeudamiento.";
                    }
            }
            else {
                return "El prestamo con codigo: "+tipo+" no es valido.";
            }
            double tE = 0;
            for (Prestamos prestamos : listaPrestamos) {
                tE+=prestamos.getCuota();
            }
            double nivelEndeudamiento = tE/nvoClienteBd.getSueldo();
            if(nivelEndeudamiento >= 0.4){
                return "El nivel de endeudamiento es mayor al establecido"+"\n"+
                "Nivel endeudamiento :"+nivelEndeudamiento+
                "Limite maximo: 0.4";
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
