package almacen.tienda.Dtos.ClienteDtos;

import org.springframework.stereotype.Component;

import almacen.tienda.Entities.Cliente;

@Component
public class ClienteMapper {

    public ClienteMapper(){

    }

    public ClienteDto toDto(Cliente cliente){
        ClienteDto dto = new ClienteDto();
        dto.setName(cliente.getName());
        dto.setEmail(cliente.getEmail());
        dto.setDireccion(cliente.getDireccion());
        return dto;
    }

    public Cliente toClienteEntity(ClienteDto dto){
        Cliente cliente = new Cliente();
        cliente.setName(dto.getName());
        cliente.setEmail(dto.getEmail());
        cliente.setDireccion(dto.getDireccion());
        return cliente;
    }


    public ClienteCreateDto toClienteCreateDto(Cliente cliente){
        ClienteCreateDto dto = new ClienteCreateDto();
        dto.setId(cliente.getId());
        dto.setName(cliente.getName());
        dto.setEmail(cliente.getEmail());
        dto.setDireccion(cliente.getDireccion());

        return dto;
    }

    public Cliente toClienteEntityByDtoCreate(ClienteCreateDto dto){

        Cliente cliente = new Cliente();

        cliente.setId(dto.getId());
        cliente.setName(dto.getName());
        cliente.setEmail(dto.getEmail());
        cliente.setDireccion(dto.getDireccion());

        return cliente;

    }
    
}
