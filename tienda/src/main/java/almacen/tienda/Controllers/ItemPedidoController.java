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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import almacen.tienda.Dtos.ItemPedidoDtos.ItemPedidoCreateDto;
import almacen.tienda.Dtos.ItemPedidoDtos.ItemPedidoDto;
import almacen.tienda.Dtos.ItemPedidoDtos.ItemPedidoMapper;
import almacen.tienda.Entities.ItemPedido;
import almacen.tienda.Services.ItemPedidoService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;
    private final ItemPedidoMapper itemPedidoMapper;

    public ItemPedidoController(ItemPedidoService itemPedidoService, ItemPedidoMapper itemPedidoMapper){
        this.itemPedidoMapper=itemPedidoMapper;
        this.itemPedidoService=itemPedidoService;
    }

    @PostMapping("/order-item")
    public ResponseEntity<ItemPedidoCreateDto> create(@RequestBody ItemPedidoDto itemPedidoDto){
        ItemPedido newItemPedido = itemPedidoMapper.toItemPedidoEntity(itemPedidoDto);
        ItemPedido itemPedidoCreate = null;

        itemPedidoCreate = itemPedidoService.create(newItemPedido);
        ItemPedidoCreateDto trueItemPedido = itemPedidoMapper.toItemPedidoCreate(itemPedidoCreate);
        URI locacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trueItemPedido.getId()).toUri();
        return ResponseEntity.created(locacion).body(trueItemPedido);

    }

    public ResponseEntity<ItemPedidoCreateDto> update(@PathVariable("id") long id, @RequestBody ItemPedidoCreateDto itemPedidoDto){

        ItemPedido itemPedidoUpdate = itemPedidoMapper.toItemPedidoEntityByDtoCreate(itemPedidoDto);
        return itemPedidoService.update(id,itemPedidoUpdate).map(itemPedidoActualizado -> ResponseEntity.ok().body(itemPedidoMapper.toItemPedidoCreate(itemPedidoUpdate))).orElseGet(()->{
            ItemPedido itemPedidoCreado = itemPedidoService.create(itemPedidoUpdate);
            ItemPedidoCreateDto trueItemPedido = itemPedidoMapper.toItemPedidoCreate(itemPedidoUpdate);

            URI locacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{i}")
            .buildAndExpand(trueItemPedido.getId()).toUri();
            return ResponseEntity.created(locacion).body(trueItemPedido);
        });

    }

    @GetMapping("/allItemsPedidos")
    public ResponseEntity<List<ItemPedidoCreateDto>> findAll(){
        List<ItemPedido> itemsPedidos = itemPedidoService.findAll();
        List<ItemPedidoCreateDto> verdaderosItemsPedidos = itemsPedidos.stream().map(r->itemPedidoMapper.toItemPedidoCreate(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(verdaderosItemsPedidos);
    }

    @DeleteMapping("/itempedido/{id}")
    public ResponseEntity<ItemPedidoCreateDto> delete(@PathVariable("id") long id){
        itemPedidoService.delete(id);
        return ResponseEntity.ok().build();
    }



    
}
