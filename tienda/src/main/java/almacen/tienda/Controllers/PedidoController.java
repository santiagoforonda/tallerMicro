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
import almacen.tienda.Dtos.PedidoDtos.PedidoCreateDto;
import almacen.tienda.Dtos.PedidoDtos.PedidoDto;
import almacen.tienda.Dtos.PedidoDtos.PedidoMapper;
import almacen.tienda.Entities.Pedido;
import almacen.tienda.Services.PedidoService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    private final PedidoMapper pedidoMapper;
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService, PedidoMapper pedidoMapper){
        this.pedidoMapper=pedidoMapper;
        this.pedidoService=pedidoService;
    }

    @PostMapping("/pedido")
    public ResponseEntity<PedidoCreateDto> create (@RequestBody PedidoDto pedidoDto){
        Pedido newPedido = pedidoMapper.toPedidoEntity(pedidoDto);
        Pedido pedidoCreate = null;

        pedidoCreate=pedidoService.create(newPedido);
        PedidoCreateDto truePedido = pedidoMapper.toPedidoCreate(pedidoCreate);
        URI locacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(truePedido.getId()).toUri();
        return ResponseEntity.created(locacion).body(truePedido);
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<PedidoCreateDto> update (@PathVariable("id") long id, @RequestBody PedidoCreateDto pedidoDto){

        Pedido pedidoUpdate = pedidoMapper.toPedidoEntityByDtoCreate(pedidoDto);
        return pedidoService.update(id, pedidoUpdate).map(pedidoActualizado ->ResponseEntity.ok().body(pedidoMapper.toPedidoCreate(pedidoUpdate))).orElseGet(()->{
            Pedido pedidoCreado = pedidoService.create(pedidoUpdate);
            PedidoCreateDto truePedido = pedidoMapper.toPedidoCreate(pedidoUpdate);

            URI locacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(truePedido.getId()).toUri();
            return ResponseEntity.created(locacion).body(truePedido);
        });
    }


    @GetMapping("/allPedidos")
    public ResponseEntity<List<PedidoCreateDto>> findAll(){
        List<Pedido> pedidos = pedidoService.findAll();
        List<PedidoCreateDto> verdaderosPedidos = pedidos.stream().map(r-> pedidoMapper.toPedidoCreate(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(verdaderosPedidos);
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<PedidoCreateDto> delete(@PathVariable("id") long id){
        pedidoService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
