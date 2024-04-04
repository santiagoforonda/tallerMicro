package almacen.tienda.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import almacen.tienda.Entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    

    @Query("SELECT cliente FROM Cliente cliente WHERE cliente.email = ?1")
    Cliente findByEmail(String email);

    @Query("SELECT cliente FROM Cliente cliente WHERE cliente.direccion=?1")
    Cliente findByDireccion(String direccion);

    List<Cliente> findByName(String name);
    
}
