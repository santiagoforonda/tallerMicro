package almacen.tienda.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import almacen.tienda.Entities.Pago;

public interface PagoService {
    
    Optional<Pago> findById(long id);

    List<Pago> findAll();

    Optional<Pago> findByOrder(long id);

    List<Pago> findBetweenDate(LocalDate fecha1, LocalDate fecha2);

    Pago createPago(Pago pago);

    Optional<Pago> updatePago(long id, Pago pago);

    void deletePago(long id);
}
