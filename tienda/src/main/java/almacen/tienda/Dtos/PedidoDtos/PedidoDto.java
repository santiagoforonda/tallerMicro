package almacen.tienda.Dtos.PedidoDtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import almacen.tienda.Entities.EstadoPedidoE;
import lombok.Data;

@Data
public class PedidoDto {
    
    private long cliente_id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate fechaPedido;
    private EstadoPedidoE status;
}
