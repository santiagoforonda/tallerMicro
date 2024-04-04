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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import almacen.tienda.Dtos.DetalleEnvioDtos.DetalleEnvioCreteDto;
import almacen.tienda.Dtos.DetalleEnvioDtos.DetalleEnvioDto;
import almacen.tienda.Dtos.DetalleEnvioDtos.DetalleEnvioMapper;
import almacen.tienda.Entities.DetalleEnvio;
import almacen.tienda.Services.DetalleEnvioService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class DetalleEnvioController {

    private final DetalleEnvioService detalleService;
    private final DetalleEnvioMapper detalleMapper;


    public DetalleEnvioController(DetalleEnvioService detalleService, DetalleEnvioMapper detalleMapper){
        this.detalleService=detalleService;
        this.detalleMapper=detalleMapper;
    }

    @PostMapping("/detalle")
    public ResponseEntity<DetalleEnvioCreteDto> create(@RequestBody DetalleEnvioDto detalleDto){
        DetalleEnvio newDetalleEnvio=detalleMapper.toDetalleEnvioEntity(detalleDto);
        DetalleEnvio detalleCreated = null;

        detalleCreated = detalleService.createDetalleEnvio(newDetalleEnvio);
        DetalleEnvioCreteDto trueDetalle = detalleMapper.toDetalleEnvioCreate(detalleCreated);
        URI locacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trueDetalle.getId()).toUri();
        return ResponseEntity.created(locacion).body(trueDetalle);
    }


    @PutMapping("/detalle/{id}")
    public ResponseEntity<DetalleEnvioCreteDto> update (@PathVariable("id") long id, @RequestBody DetalleEnvioCreteDto detalleDto){

        DetalleEnvio detalleUpdate = detalleMapper.toEntityByDtoCreate(detalleDto);
        return detalleService.update(id, detalleUpdate).map(detalleActualizado ->ResponseEntity.ok().body(detalleMapper.toDetalleEnvioCreate(detalleUpdate))).orElseGet(()->{
            DetalleEnvio detalleCreado = detalleService.createDetalleEnvio(detalleUpdate);
            DetalleEnvioCreteDto trueDetalle = detalleMapper.toDetalleEnvioCreate(detalleUpdate);

            URI locacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(trueDetalle.getId()).toUri();
            return ResponseEntity.created(locacion).body(trueDetalle);
        });
    }

    @GetMapping("/allDetalles")
    public ResponseEntity<List<DetalleEnvioCreteDto>> findAll(){
        List<DetalleEnvio> detalles = detalleService.findAll();
        List<DetalleEnvioCreteDto> verdaderosDetalles = detalles.stream().map(r-> detalleMapper.toDetalleEnvioCreate(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(verdaderosDetalles);
    }

    @DeleteMapping("/detalle/{id}")
    public ResponseEntity<DetalleEnvioCreteDto> delete(@PathVariable("id") long id){
        detalleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/detalle/{orderId}")
    public ResponseEntity<DetalleEnvioCreteDto> findByorderId(@PathVariable("orderId") long orderId){
            DetalleEnvio detalle = detalleService.findByIdOrder(orderId);
            DetalleEnvioCreteDto detalleCreateDto = detalleMapper.toDetalleEnvioCreate(detalle);
            return ResponseEntity.ok().body(detalleCreateDto);

    }


    @GetMapping("/detalle")
    public ResponseEntity<DetalleEnvioCreteDto> finByCarrierName(@RequestParam("transportadora") String transportadora){
        DetalleEnvio detalle = detalleService.findByTransportadora(transportadora);
        DetalleEnvioCreteDto trueDetalle = detalleMapper.toDetalleEnvioCreate(detalle);
        return ResponseEntity.ok().body(trueDetalle);
    }




    
}
