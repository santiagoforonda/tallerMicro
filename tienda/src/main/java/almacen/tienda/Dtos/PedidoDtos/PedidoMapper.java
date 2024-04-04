package almacen.tienda.Dtos.PedidoDtos;

import org.springframework.stereotype.Component;

import almacen.tienda.Entities.Pedido;

@Component
public class PedidoMapper {

    public PedidoMapper(){

    }

    public PedidoDto tDto(Pedido pedido){
        PedidoDto dto = new PedidoDto();

        dto.setCliente_id(pedido.getCliente_id());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setStatus(pedido.getStatus());

        return dto;
    }


    public Pedido toPedidoEntity(PedidoDto dto){
        Pedido pedido = new Pedido();

        pedido.setCliente_id(dto.getCliente_id());
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setStatus(dto.getStatus());

        return pedido;
    }

    public PedidoCreateDto toPedidoCreate(Pedido pedido){
        PedidoCreateDto dto = new PedidoCreateDto();

        dto.setId(pedido.getId());
        dto.setCliente_id(pedido.getCliente_id());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setStatus(pedido.getStatus());

        return dto;
    }

    public Pedido toPedidoEntityByDtoCreate( PedidoCreateDto dto){
        Pedido pedido = new Pedido();

        pedido.setId(dto.getId());
        pedido.setCliente_id(dto.getCliente_id());
        pedido.setFechaPedido(dto.getFechaPedido());
        pedido.setStatus(dto.getStatus());

        return pedido;
    }
    
}
