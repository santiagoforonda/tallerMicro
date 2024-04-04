package almacen.tienda.Entities;



import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Long cliente_id;

    @Column
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate fechaPedido;

    @Column
    private EstadoPedidoE status;

    @ManyToMany(mappedBy = "pedidos")
    private Set<ItemPedido> itemspedidos;


    public Pedido(long id,long cliente_id, LocalDate fechaPedido, EstadoPedidoE status){
        this.id=id;
        this.cliente_id=cliente_id;
        this.fechaPedido=fechaPedido;
        this.status=status;
    }

    public Pedido updateWith(Pedido pedido){
        return new Pedido(this.id,pedido.cliente_id,pedido.fechaPedido,pedido.status);
    }
   

}
