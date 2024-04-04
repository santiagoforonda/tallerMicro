package almacen.tienda.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import almacen.tienda.Entities.ItemPedido;
import almacen.tienda.Entities.Product;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {
    
    @Query("SELECT itemPedido FROM ItemPedido itemPedido WHERE itemPedido.orderId =?1")
    ItemPedido findByOrderId(long id);

    @Query("SELECT itemPedido FROM ItemPedido itemPedido WHERE itemPedido.id=?1 ")
    List<Product> ItemsByPedido(Long id);

    @Query("SELECT itemPedido FROM ItemPedido itemPedido WHERE itemPedido.productoId")
    List<ItemPedido> findByProductId(long id);


    
}
