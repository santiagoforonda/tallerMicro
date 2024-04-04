package almacen.tienda.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import almacen.tienda.Entities.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{
   


    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.fechaPedido between ?1 AND ?2 ")
    List<Pedido> findBetweenDate(@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fecha1,  @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fecha2);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cliente_id =?1 AND pedido.status=?2")
    List<Pedido> findByCustomerAndStatus(Long idCliente, int estado);

    @Query("SELECT pedido FROM Pedido pedido WHERE pedido.cliente_id =?1 ")
    List<Pedido> findAllPedidosWithItemsByCustomer(Long id);

    
}
