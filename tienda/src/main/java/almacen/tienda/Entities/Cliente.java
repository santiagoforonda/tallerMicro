package almacen.tienda.Entities;

import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clientes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column(unique=true)
    private String email;

    @Column
    private String direccion;

    @OneToMany
    @JoinColumn(name="cliente_id")
    private Set<Pedido> pedidos;

    public Cliente(long id,String name, String email, String direccion){
        this.id=id;
        this.name=name;
        this.email=email;
        this.direccion=direccion;
    }

    public Cliente updateWith(Cliente cliente){
        return new Cliente(this.id,cliente.name,cliente.email,cliente.direccion);
    }
    
}
