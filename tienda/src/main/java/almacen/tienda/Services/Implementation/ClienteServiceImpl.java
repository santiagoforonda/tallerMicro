package almacen.tienda.Services.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import almacen.tienda.Entities.Cliente;
import almacen.tienda.Repository.ClienteRepository;
import almacen.tienda.Services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepo;


    public ClienteServiceImpl(ClienteRepository clienteRepo){
        this.clienteRepo=clienteRepo;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepo.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepo.findById(id);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        Cliente clienteCopy = new Cliente();

        clienteCopy.setName(cliente.getName());
        clienteCopy.setEmail(cliente.getEmail());
        clienteCopy.setDireccion(cliente.getDireccion());
        return clienteRepo.save(clienteCopy);
    }

    @Override
    public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
       
        Optional<Cliente> clienteDB = clienteRepo.findById(id);

        Optional<Cliente> clienteUpdate = clienteDB.map(oldCliente ->{
            Cliente clienteAux = oldCliente.updateWith(cliente);
            return clienteRepo.save(clienteAux);
        });
        return clienteUpdate;
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteRepo.findByEmail(email);
    }
    
}
