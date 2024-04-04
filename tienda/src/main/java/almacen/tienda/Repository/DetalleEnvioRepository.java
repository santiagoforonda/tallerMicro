package almacen.tienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import almacen.tienda.Entities.DetalleEnvio;


@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio,Long> {
    
   
    @Query("SELECT detalleEnvio FROM DetalleEnvio detalleEnvio WHERE detalleEnvio.pedidoId=?1")
    DetalleEnvio findByPedidoId(Long id);

    @Query("SELECT detalleEnvio FROM DetalleEnvio detalleEnvio WHERE detalleEnvio.transportadora=?1")
    DetalleEnvio findByTransportadora(String transportadora);

    



}
