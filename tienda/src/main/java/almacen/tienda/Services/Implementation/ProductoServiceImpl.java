package almacen.tienda.Services.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import almacen.tienda.Entities.Product;
import almacen.tienda.Repository.ProductoRepository;
import almacen.tienda.Services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {


    private final ProductoRepository productoRepo;

    public ProductoServiceImpl(ProductoRepository productoRepo){
        this.productoRepo=productoRepo;
    }

    @Override
    public Optional<Product> findByid(long id) {
        return productoRepo.findById(id);
    }

    @Override
    public Optional<Product> update(long id, Product product) {
        Optional<Product> productoDB = productoRepo.findById(id);
        Optional<Product> productoUpdate = productoDB.map(oldProducto->{
            Product productoAux = oldProducto.updateWith(product);
            return productoRepo.save(productoAux);
        });
        return productoUpdate;
    }

    @Override
    public List<Product> findAll() {
        return productoRepo.findAll();
    }

    @Override
    public Product create(Product product) {
        Product productoCopy = new Product();
        productoCopy.setName(product.getName());
        productoCopy.setPrice(product.getPrice());
        productoCopy.setStock(product.getStock());
        return productoRepo.save(productoCopy);
    }

    @Override
    public void delete(long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
       return productoRepo.findByName(name);
    }

    @Override
    public List<Product> findByStock() {
        return productoRepo.findByStock();
    }

    
    
}
