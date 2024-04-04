package almacen.tienda.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import almacen.tienda.Entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long> {
    
    

    @Query("SELECT pago FROM Pago pago WHERE pago.fechaPago between ?1 AND ?2")
    List<Pago> findByRangeDate(@DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fecha1, @DateTimeFormat(pattern ="dd-MM-yyyy") LocalDate fehca2);


    @Query("SELECT pago FROM Pago pago WHERE pago.id = ?1 AND pago.metodoPago=?2")
    Pago findByidentifierOrdenAndMethodPayment(Long id, int method);

    @Query("SELECT pago FROM Pago pago WHERE pago.pedido.getId =?1")
    Optional<Pago> findByOrderId(long id);
}
