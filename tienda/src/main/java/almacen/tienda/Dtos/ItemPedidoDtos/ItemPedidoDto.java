package almacen.tienda.Dtos.ItemPedidoDtos;

import lombok.Data;

@Data
public class ItemPedidoDto {

    private long orderId;

    private long productoId;

    private int cantidad;

    private int precioUnitario;
    
}
