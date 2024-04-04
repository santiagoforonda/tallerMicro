package almacen.tienda.Dtos.ItemPedidoDtos;

import org.springframework.stereotype.Component;

import almacen.tienda.Entities.ItemPedido;

@Component
public class ItemPedidoMapper {
    

    public ItemPedidoMapper(){

    }

    public ItemPedidoDto toDto(ItemPedido itemPedido){
        ItemPedidoDto dto = new ItemPedidoDto();

        dto.setOrderId(itemPedido.getOrderId());
        dto.setProductoId(itemPedido.getProductoId());
        dto.setCantidad(itemPedido.getCantidad());
        dto.setPrecioUnitario(itemPedido.getPrecioUnitario());

        return dto;
    }

    public ItemPedido toItemPedidoEntity(ItemPedidoDto dto){
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setOrderId(dto.getOrderId());
        itemPedido.setProductoId(dto.getProductoId());
        itemPedido.setCantidad(dto.getCantidad());
        itemPedido.setPrecioUnitario(dto.getPrecioUnitario());

        return itemPedido;
    }

    public ItemPedidoCreateDto toItemPedidoCreate(ItemPedido itemPedido){
        ItemPedidoCreateDto dto = new ItemPedidoCreateDto();

        dto.setId(itemPedido.getId());
        dto.setOrderId(itemPedido.getOrderId());
        dto.setProductoId(itemPedido.getProductoId());
        dto.setPrecioUnitario(itemPedido.getPrecioUnitario());

        return dto;
    }

    public ItemPedido toItemPedidoEntityByDtoCreate(ItemPedidoCreateDto dto){

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setId(dto.getId());
        itemPedido.setOrderId(dto.getOrderId());
        itemPedido.setProductoId(dto.getProductoId());
        itemPedido.setCantidad(dto.getCantidad());
        itemPedido.setPrecioUnitario(dto.getPrecioUnitario());

        return itemPedido;
    }
}
