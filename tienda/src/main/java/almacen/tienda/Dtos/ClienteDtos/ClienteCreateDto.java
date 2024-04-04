package almacen.tienda.Dtos.ClienteDtos;

import lombok.Data;

@Data
public class ClienteCreateDto {

    private long id;
    private String name;
    private String email;
    private String direccion;
    
}
