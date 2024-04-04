package almacen.tienda.Entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="pagos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column
    private Integer total;

    @Column
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate fechaPago;

    @Column
    private MetodoPagoE metodoPago;

    @OneToOne
    @JoinColumn(name="id_Pedido", referencedColumnName = "id")
    private Pedido pedido;


    @ManyToMany(mappedBy = "pagos")
    private Set<Product> productos;

    public Pago(long id, Pedido pedido, int total, LocalDate fechaPago, MetodoPagoE metodoPago){
        this.id=id;
        this.pedido=pedido;
        this.total=total;
        this.fechaPago=fechaPago;
        this.metodoPago=metodoPago;

    }

    public Pago updateWith(Pago pago){
        return new Pago(this.id, pago.pedido,pago.total,pago.fechaPago,pago.metodoPago);
    }
    
    
}
