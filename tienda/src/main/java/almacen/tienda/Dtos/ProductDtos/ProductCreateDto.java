package almacen.tienda.Dtos.ProductDtos;

import lombok.Data;

@Data
public class ProductCreateDto {
    
    private long id;
    private String name;
    private int price;
    private int stock;

}
