package almacen.tienda.Controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import almacen.tienda.Dtos.ClienteDtos.ClienteCreateDto;
import almacen.tienda.Dtos.ClienteDtos.ClienteDto;
import almacen.tienda.Dtos.ClienteDtos.ClienteMapper;
import almacen.tienda.Entities.Cliente;
import almacen.tienda.Services.ClienteService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper){
        this.clienteService=clienteService;
        this.clienteMapper=clienteMapper;
    }



    @PostMapping("/cliente")
    public ResponseEntity<ClienteCreateDto> create(@RequestBody ClienteDto clienteDto){
        Cliente newCliente = clienteMapper.toClienteEntity(clienteDto);
        Cliente clienteCreated = null;

        clienteCreated = clienteService.createCliente(newCliente);
        ClienteCreateDto trueCliente = clienteMapper.toClienteCreateDto(clienteCreated);
        URI locacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trueCliente.getId()).toUri();
        return ResponseEntity.created(locacion).body(trueCliente);

    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClienteCreateDto> update(@PathVariable("id") long id, @RequestBody ClienteCreateDto cliente){
        Cliente clienteUpdate = clienteMapper.toClienteEntityByDtoCreate(cliente);
        return clienteService.updateCliente(id, clienteUpdate).map(clienteActualizado ->ResponseEntity.ok().body(clienteMapper.toClienteCreateDto(clienteUpdate))).orElseGet(()->{

            Cliente clienteCreado = clienteService.createCliente(clienteUpdate);
            ClienteCreateDto trueCliente = clienteMapper.toClienteCreateDto(clienteUpdate);

            URI locacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(trueCliente.getId()).toUri();
            return ResponseEntity.created(locacion).body(trueCliente);
        });

    }

    @GetMapping("/allClientes")
    public ResponseEntity<List<ClienteCreateDto>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteCreateDto> verdaderosCliente = clientes.stream().map(r->clienteMapper.toClienteCreateDto(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(verdaderosCliente);
    }


    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<ClienteCreateDto> delete (@PathVariable("id") long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/cliente/{email}")
    public ResponseEntity<ClienteCreateDto> findByEmail(@PathVariable("email") String email){
        Cliente cliente = clienteService.findByEmail(email);
        ClienteCreateDto clienteCreateDto = clienteMapper.toClienteCreateDto(cliente);
        return ResponseEntity.ok().body(clienteCreateDto);

    }

    

}
