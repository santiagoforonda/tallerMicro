package almacen.tienda.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import almacen.tienda.Entities.Product;


@Repository
public interface ProductoRepository extends JpaRepository<Product, Long>{

    Product findByName(String name);
    List<Product> findAll();
   
    
    @Query("SELECT product FROM Product product WHERE product.stock > 0")
    List<Product> findByStock();

    @Query("SELECT product FROM Product product WHERE product.stock < ?1 AND product.price < ?2")
    List<Product> findByPriceAndStock( int stock, int price);


    
}
