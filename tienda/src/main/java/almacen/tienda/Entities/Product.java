package almacen.tienda.Entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name ="products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer stock;

    
    @ManyToMany
    @JoinTable(
        name="productos pagados",
        joinColumns = @JoinColumn(name="producto_id", referencedColumnName = "id"),
        inverseJoinColumns=@JoinColumn(name="pago_id", referencedColumnName = "id")
    )
    private Set<Pago> pagos;

    public Product(long id, String name, int price, int stock){
        this.id=id;
        this.name=name;
        this.price=price;
        this.stock=stock;
    }

    public Product updateWith(Product producto){
        return new Product(this.id,producto.name,producto.price,producto.stock);
    }
    
}
