package almacen.tienda.Dtos.ItemPedidoDtos;

import lombok.Data;

@Data
public class ItemPedidoCreateDto {


    private long id;
    private long orderId;

    private long productoId;

    private int cantidad;

    private int precioUnitario;
    
}
