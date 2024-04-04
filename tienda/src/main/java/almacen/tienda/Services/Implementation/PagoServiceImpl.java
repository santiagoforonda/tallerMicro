package almacen.tienda.Services.Implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import almacen.tienda.Entities.Pago;
import almacen.tienda.Repository.PagoRepository;
import almacen.tienda.Services.PagoService;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagorepo;

    public PagoServiceImpl(PagoRepository pagorepo){
        this.pagorepo=pagorepo;
    }


    @Override
    public Optional<Pago> findById(long id) {
        return pagorepo.findById(id);
    }

    @Override
    public List<Pago> findAll() {
        return pagorepo.findAll();
    }

    @Override
    public Optional<Pago> findByOrder(long id) {
        return pagorepo.findByOrderId(id);
    }

    @Override
    public List<Pago> findBetweenDate(LocalDate fecha1, LocalDate fecha2) {
        return pagorepo.findByRangeDate(fecha1, fecha2);
    }

    @Override
    public Pago createPago(Pago pago) {
        Pago pagoCopy = new Pago();
        pagoCopy.setPedido(pago.getPedido());
        pagoCopy.setTotal(pago.getTotal());
        pagoCopy.setFechaPago(pago.getFechaPago());
        pagoCopy.setMetodoPago(pago.getMetodoPago());
        return pagorepo.save(pagoCopy);
    }

    @Override
    public Optional<Pago> updatePago(long id, Pago pago) {
        Optional<Pago> pagoDB = pagorepo.findById(id);
        Optional<Pago> pagoUpdate = pagoDB.map(oldPago->{
            Pago pagoAux = oldPago.updateWith(pago);
            return pagorepo.save(pagoAux);

        });
        return pagoUpdate;
    }

    @Override
    public void deletePago(long id) {
        pagorepo.deleteById(id);
    }
    
}
