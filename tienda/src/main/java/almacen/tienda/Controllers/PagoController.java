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

import almacen.tienda.Dtos.PagoDtos.PagoCreateDto;
import almacen.tienda.Dtos.PagoDtos.PagoDto;
import almacen.tienda.Dtos.PagoDtos.PagoMapper;
import almacen.tienda.Entities.Pago;
import almacen.tienda.Services.PagoService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PagoController {
    private final PagoService pagoService;
    private final PagoMapper pagoMapper;

    public PagoController(PagoService pagoService, PagoMapper pagoMapper){
        this.pagoService=pagoService;
        this.pagoMapper=pagoMapper;
    }

    @PostMapping("/pago")
    public ResponseEntity<PagoCreateDto> create(@RequestBody PagoDto pagoDto){
        Pago newPago = pagoMapper.toPagoEntity(pagoDto);
        Pago pagoCreated = null;

        pagoCreated = pagoService.createPago(newPago);
        PagoCreateDto truePago = pagoMapper.toPagoCreateDto(pagoCreated);
        URI locacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(truePago.getId()).toUri();
        return ResponseEntity.created(locacion).body(truePago);
    }

    @PutMapping("/pago/{id}")
    public ResponseEntity<PagoCreateDto> update(@PathVariable("id") long id, @RequestBody PagoCreateDto pagoDto){
        Pago pagoUpdate = pagoMapper.toPagoEntityByDtoCreate(pagoDto);
        return pagoService.updatePago(id, pagoUpdate).map(pagoActualizado -> ResponseEntity.ok().body(pagoMapper.toPagoCreateDto(pagoUpdate))).orElseGet(()->{
            Pago pagoCreado = pagoService.createPago(pagoUpdate);
            PagoCreateDto truePago = pagoMapper.toPagoCreateDto(pagoUpdate);

            URI locacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(truePago.getId()).toUri();
            return ResponseEntity.created(locacion).body(truePago);
        });
    }

    @GetMapping("/allPagos")
    public ResponseEntity<List<PagoCreateDto>> findAll(){
        List<Pago> pagos = pagoService.findAll();
        List<PagoCreateDto> verdaderosPagos = pagos.stream().map(r-> pagoMapper.toPagoCreateDto(r)).collect(Collectors.toList());
        return ResponseEntity.ok().body(verdaderosPagos);
    }

    @DeleteMapping("/pago/{id}")
    public ResponseEntity<PagoCreateDto> delete(@PathVariable("id") long id){
        pagoService.deletePago(id);
        return ResponseEntity.ok().build();
    }
    
}
