package almacen.tienda.Dtos.DetalleEnvioDtos;

import java.util.Optional;

import org.springframework.stereotype.Component;

import almacen.tienda.Entities.DetalleEnvio;

@Component
public class DetalleEnvioMapper {

    

    public DetalleEnvioMapper(){

    }


    public DetalleEnvioDto toDto(DetalleEnvio detalleEnvio){
        DetalleEnvioDto dto = new DetalleEnvioDto();

        dto.setOrderId(detalleEnvio.getPedidoId());
        dto.setDireccion(detalleEnvio.getDireccionEnvio());
        dto.setTransportadora(detalleEnvio.getTransportadora());
        dto.setNumeroGuia(detalleEnvio.getNumeroGuia());

        return dto;
    }

    public DetalleEnvio toDetalleEnvioEntity(DetalleEnvioDto dto){
        DetalleEnvio detalleEnvio = new DetalleEnvio();

        detalleEnvio.setPedidoId(dto.getOrderId());
        detalleEnvio.setDireccionEnvio(dto.getDireccion());
        detalleEnvio.setTransportadora(dto.getTransportadora());
        detalleEnvio.setNumeroGuia(dto.getNumeroGuia());

        return detalleEnvio;
    }

    public DetalleEnvioCreteDto toDetalleEnvioCreate( DetalleEnvio detalleEnvio){
        DetalleEnvioCreteDto dto = new DetalleEnvioCreteDto();

        dto.setId(detalleEnvio.getId());
        dto.setOrderId(detalleEnvio.getPedidoId());
        dto.setDireccion(detalleEnvio.getDireccionEnvio());
        dto.setTransportadora(detalleEnvio.getTransportadora());
        dto.setNumeroGuia(detalleEnvio.getNumeroGuia());

        return dto;
    }

    public DetalleEnvio toEntityByDtoCreate(DetalleEnvioCreteDto dto){
        DetalleEnvio detalleEnvio = new DetalleEnvio();

        detalleEnvio.setId(dto.getId());
        detalleEnvio.setPedidoId(dto.getOrderId());
        detalleEnvio.setDireccionEnvio(dto.getDireccion());
        detalleEnvio.setTransportadora(dto.getTransportadora());
        detalleEnvio.setNumeroGuia(dto.getNumeroGuia());

        return detalleEnvio;
    }


    
}
