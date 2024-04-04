package almacen.tienda.Dtos.ProductDtos;

import org.springframework.stereotype.Component;

import almacen.tienda.Entities.Product;

@Component
public class ProductoMapper {

    public ProductoMapper(){

    }

    public ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }

    public Product toProductEntity(ProductDto dto){

        Product producto = new Product();
        producto.setName(dto.getName());
        producto.setPrice(dto.getPrice());
        producto.setStock(dto.getStock());

        return producto;
    
    }

    public ProductCreateDto toProductCreate( Product product){
        ProductCreateDto dto = new ProductCreateDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        return dto;
    }

    public Product toProductEntityByDtoCreate(ProductCreateDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return product;
    }
    
}
