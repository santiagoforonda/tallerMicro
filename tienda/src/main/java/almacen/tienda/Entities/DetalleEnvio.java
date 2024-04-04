package almacen.tienda.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="detalles por envio")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DetalleEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(unique = true)
    private Long pedidoId;

    @Column
    private String direccionEnvio;

    @Column
    private String transportadora;

    @Column(unique = true)
    private Integer numeroGuia;


    @OneToOne
    @JoinColumn(name="pago_id", referencedColumnName = "id")
    private Pago pago;

    public DetalleEnvio(long id, long pedidoId, String direccionEnvio, String transportadora, int numeroGuia){
        this.id=id;
        this.pedidoId=pedidoId;
        this.direccionEnvio=direccionEnvio;
        this.transportadora=transportadora;
        this.numeroGuia=numeroGuia;
    }

    public DetalleEnvio updateWith(DetalleEnvio detalleEnvio){
        return new DetalleEnvio(this.id,detalleEnvio.pedidoId,detalleEnvio.direccionEnvio,detalleEnvio.transportadora,detalleEnvio.numeroGuia);
    }

    
}
