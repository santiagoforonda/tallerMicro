package almacen.tienda.Dtos.DetalleEnvioDtos;

import lombok.Data;

@Data
public class DetalleEnvioDto {
    
    private long orderId;
    private String direccion;
    private String transportadora;
    private int numeroGuia;
}
