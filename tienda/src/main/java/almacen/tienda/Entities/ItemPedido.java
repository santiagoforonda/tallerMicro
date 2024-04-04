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
@Table(name="OrderItems")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    

    @Column(unique = true)
    private Long orderId;

    private Long productoId;

    private Integer cantidad;

    private Integer precioUnitario;
    

    @OneToMany
    @JoinColumn(name ="OrderItemsId")
    private Set<DetalleEnvio> detalles;

    @ManyToMany
    @JoinTable(
        name="productosPorPedidos",
        joinColumns=@JoinColumn(name="producto_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name ="pedido_id", referencedColumnName = "id")
    )
    private Set<Pedido>pedidos;

    public ItemPedido(long id, long orderId, long productoId, int cantidad, int precioUnitario){
        this.id=id;
        this.orderId=orderId;
        this.productoId=productoId;
        this.cantidad=cantidad;
        this.precioUnitario=precioUnitario;
    }

    public ItemPedido updateWith(ItemPedido itemPedido){
        return new ItemPedido(this.id, itemPedido.orderId,itemPedido.productoId,itemPedido.cantidad,itemPedido.precioUnitario);
    }
    
}
