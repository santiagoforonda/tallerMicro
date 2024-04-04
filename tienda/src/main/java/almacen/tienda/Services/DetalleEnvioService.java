package almacen.tienda.Services;

import java.util.List;
import java.util.Optional;

import almacen.tienda.Entities.DetalleEnvio;

public interface DetalleEnvioService {
    
    Optional<DetalleEnvio> findById(long id);

    List<DetalleEnvio> findAll();

    DetalleEnvio findByIdOrder(long id);

    DetalleEnvio findByTransportadora(String transportadora);

    DetalleEnvio createDetalleEnvio(DetalleEnvio detalleEnvio);

    Optional<DetalleEnvio> update(long id, DetalleEnvio detalle);

    void delete(long id);

    
    
}
