package almacen.tienda.Services.Implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import almacen.tienda.Entities.Pedido;
import almacen.tienda.Repository.PedidoRepository;
import almacen.tienda.Services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {


    private final PedidoRepository pedidoRepo;

    public PedidoServiceImpl(PedidoRepository pedidoRepo){
        this.pedidoRepo=pedidoRepo;
    }

    @Override
    public Optional<Pedido> findById(long id) {
       return  pedidoRepo.findById(id);
    }

    @Override
    public Optional<Pedido> update(long id, Pedido pedido) {
        Optional<Pedido> pedidoDB = pedidoRepo.findById(id);
        Optional<Pedido> pedidoUpdate = pedidoDB.map(oldPedido->{
            Pedido pedidoAux = oldPedido.updateWith(pedido);
            return pedidoRepo.save(pedidoAux);
        });
        return pedidoUpdate;
    }

    @Override
    public void delete(long id) {
       pedidoRepo.deleteById(id);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepo.findAll();
    }

    @Override
    public List<Pedido> findByClienteId(long id) {
        return pedidoRepo.findAllPedidosWithItemsByCustomer(id);
    }

    @Override
    public List<Pedido> findBetweenDate(LocalDate fecha1, LocalDate fecha2) {
        return pedidoRepo.findBetweenDate(fecha1, fecha2);
    }

    @Override
    public Pedido create(Pedido pedido) {
        Pedido pedidoCopy = new Pedido();
        pedidoCopy.setCliente_id(pedido.getCliente_id());
        pedidoCopy.setFechaPedido(pedido.getFechaPedido());
        pedidoCopy.setStatus(pedido.getStatus());
        return pedidoRepo.save(pedidoCopy);
    }

    
}
