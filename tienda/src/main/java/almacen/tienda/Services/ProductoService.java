package almacen.tienda.Services;

import java.util.List;
import java.util.Optional;

import almacen.tienda.Entities.Product;

public interface ProductoService {
    
    Optional<Product> findByid(long id);

    Optional<Product> update(long id, Product product);

    List<Product> findAll();

    Product create(Product product);

    void delete(long id);

    Product findByName(String name);

    List<Product> findByStock();


    


}
