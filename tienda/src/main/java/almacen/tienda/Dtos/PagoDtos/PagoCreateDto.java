package almacen.tienda.Dtos.PagoDtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import almacen.tienda.Entities.MetodoPagoE;
import almacen.tienda.Entities.Pedido;
import lombok.Data;

@Data
public class PagoCreateDto {

    private long id;
    private Pedido pedido;

    private int total;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate fechaPago;

    private MetodoPagoE metodoPago;
    
}
