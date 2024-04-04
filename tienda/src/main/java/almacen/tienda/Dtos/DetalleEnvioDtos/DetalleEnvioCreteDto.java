package almacen.tienda.Dtos.DetalleEnvioDtos;

import lombok.Data;

@Data
public class DetalleEnvioCreteDto {

    private long id;
    private long orderId;
    private String direccion;
    private String transportadora;
    private int numeroGuia;
    
}
