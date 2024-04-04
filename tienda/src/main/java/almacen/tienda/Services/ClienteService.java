package almacen.tienda.Services;

import java.util.List;
import java.util.Optional;

import almacen.tienda.Entities.Cliente;

public interface ClienteService {


    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Cliente createCliente(Cliente cliente);

    Optional<Cliente> updateCliente(Long id,Cliente cliente);

    void deleteCliente(Long id);

    Cliente findByEmail(String email);
    
    
}
