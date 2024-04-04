package almacen.tienda.Services.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import almacen.tienda.Entities.ItemPedido;
import almacen.tienda.Repository.ItemPedidoRepository;
import almacen.tienda.Services.ItemPedidoService;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepo;

    public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepo){
        this.itemPedidoRepo=itemPedidoRepo;
    }

    @Override
    public Optional<ItemPedido> findById(long id) {
        return itemPedidoRepo.findById(id);
    }

    @Override
    public Optional<ItemPedido> update(long id, ItemPedido itemPedido) {
        Optional<ItemPedido> itemPedidoDB = itemPedidoRepo.findById(id);
        Optional<ItemPedido> itemPedidoUpdate = itemPedidoDB.map(oldItemPedido->{
            ItemPedido itemPedidoAux = oldItemPedido.updateWith(itemPedido);
            return itemPedidoRepo.save(itemPedidoAux);
        });
        return itemPedidoUpdate;
    }

    @Override
    public ItemPedido create(ItemPedido itemPedido) {
        ItemPedido itemPedidoCopy = new ItemPedido();
        itemPedidoCopy.setCantidad(itemPedido.getCantidad());
        itemPedidoCopy.setPrecioUnitario(itemPedido.getPrecioUnitario());
        itemPedidoCopy.setProductoId(itemPedido.getProductoId());
        itemPedidoCopy.setOrderId(itemPedido.getOrderId());
        return itemPedidoRepo.save(itemPedidoCopy);
    }

    @Override
    public List<ItemPedido> findAll() {
        return itemPedidoRepo.findAll();
    }

    @Override
    public void delete(long id) {
       itemPedidoRepo.deleteById(id);
    }

    @Override
    public ItemPedido findByOrderId(long id) {
        return itemPedidoRepo.findByOrderId(id);
    }

    @Override
    public List<ItemPedido> findByProductId(long id) {
        return itemPedidoRepo.findByProductId(id);
    }

    
}
