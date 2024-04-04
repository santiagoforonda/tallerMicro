package almacen.tienda.Dtos.PagoDtos;

import org.springframework.stereotype.Component;

import almacen.tienda.Entities.Pago;

@Component
public class PagoMapper {

    public PagoMapper(){

    }

    public PagoDto toDto (Pago pago){
        PagoDto dto = new PagoDto();

        dto.setPedido(pago.getPedido());
        dto.setTotal(pago.getTotal());
        dto.setFechaPago(pago.getFechaPago());
        dto.setMetodoPago(pago.getMetodoPago());

        return dto;
    }


    public Pago toPagoEntity (PagoDto dto){
        Pago pago = new Pago();

        pago.setPedido(dto.getPedido());
        pago.setTotal(dto.getTotal());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());

        return pago;
    }


    public PagoCreateDto toPagoCreateDto(Pago pago){
        PagoCreateDto dto = new PagoCreateDto();

        dto.setId(pago.getId());
        dto.setPedido(pago.getPedido());
        dto.setTotal(pago.getTotal());
        dto.setFechaPago(pago.getFechaPago());
        dto.setMetodoPago(pago.getMetodoPago());

        return dto;
    }


    public Pago toPagoEntityByDtoCreate(PagoCreateDto dto){
        Pago pago = new Pago();

        pago.setId(dto.getId());
        pago.setPedido(dto.getPedido());
        pago.setTotal(dto.getTotal());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());

        return pago;
    }
}
