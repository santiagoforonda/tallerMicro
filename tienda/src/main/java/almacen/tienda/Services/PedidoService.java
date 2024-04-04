package almacen.tienda.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import almacen.tienda.Entities.Pedido;

public interface PedidoService {
    
    Optional<Pedido> findById(long id);

    Optional<Pedido> update(long id, Pedido pedido);

    void delete(long id);

    List<Pedido> findAll();

    List<Pedido> findByClienteId(long id);

    List<Pedido> findBetweenDate(LocalDate fecha1, LocalDate fecha2);

    Pedido create(Pedido pedido);
    

    
}
