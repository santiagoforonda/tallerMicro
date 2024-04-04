package almacen.tienda.Services;

import java.util.List;
import java.util.Optional;

import almacen.tienda.Entities.ItemPedido;

public interface ItemPedidoService {
    
    Optional<ItemPedido> findById(long id);

    Optional<ItemPedido> update(long id, ItemPedido itemPedido);

    ItemPedido create (ItemPedido itemPedido);

    List<ItemPedido> findAll();

    void delete(long id);

    ItemPedido findByOrderId(long id);

    List<ItemPedido> findByProductId(long id);


    

}
