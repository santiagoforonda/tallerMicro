package almacen.tienda.Controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import almacen.tienda.Dtos.ProductDtos.ProductCreateDto;
import almacen.tienda.Dtos.ProductDtos.ProductDto;
import almacen.tienda.Dtos.ProductDtos.ProductoMapper;
import almacen.tienda.Entities.Product;
import almacen.tienda.Services.ProductoService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductoMapper productoMapper;
    private final ProductoService productoService;

    public ProductController(ProductoMapper productoMapper, ProductoService productoService){
        this.productoMapper=productoMapper;
        this.productoService=productoService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductCreateDto> create(@RequestBody ProductDto productoDto){
        Product newProduct = productoMapper.toProductEntity(productoDto);
        Product productCreate = null;

        productCreate = productoService.create(newProduct);
        ProductCreateDto trueProduct = productoMapper.toProductCreate(productCreate);
        URI locacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trueProduct.getId()).toUri();
        return ResponseEntity.created(locacion).body(trueProduct);
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductCreateDto> update(@PathVariable("id") long id, @RequestBody ProductCreateDto productDto){
        Product productoUpdate = productoMapper.toProductEntityByDtoCreate(productDto);
        return productoService.update(id, productoUpdate).map(productoActualizado -> ResponseEntity.ok().body(productoMapper.toProductCreate(productoUpdate))).orElseGet(()->{
            Product productoCreado = productoService.create(productoUpdate);
            ProductCreateDto trueProducto = productoMapper.toProductCreate(productoUpdate);

            URI locacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(trueProducto.getId()).toUri();
            return ResponseEntity.created(locacion).body(trueProducto);
        });
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductCreateDto>> finAll(){
        List<Product> productos = productoService.findAll();
        List<ProductCreateDto> verdaderosProductos = productos.stream().map(r->productoMapper.toProductCreate(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(verdaderosProductos);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductCreateDto> delete(@PathVariable("id") long id){
        productoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
