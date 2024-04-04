package almacen.tienda.Services.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import almacen.tienda.Entities.DetalleEnvio;
import almacen.tienda.Repository.DetalleEnvioRepository;
import almacen.tienda.Services.DetalleEnvioService;

@Service
public class DetalleEnvioServiceImpl implements DetalleEnvioService {

    private final DetalleEnvioRepository detalleEnvioRepo;


    public DetalleEnvioServiceImpl(DetalleEnvioRepository detalleEnvioRepo){
        this.detalleEnvioRepo=detalleEnvioRepo;
    }

    @Override
    public Optional<DetalleEnvio> findById(long id) {
        return detalleEnvioRepo.findById(id);
    }

    @Override
    public List<DetalleEnvio> findAll() {
        return detalleEnvioRepo.findAll();
    }

    @Override
    public DetalleEnvio findByIdOrder(long id) {
       return detalleEnvioRepo.findByPedidoId(id);

    }

    @Override
    public DetalleEnvio findByTransportadora(String transportadora) {
       return detalleEnvioRepo.findByTransportadora(transportadora);
    }

    @Override
    public DetalleEnvio createDetalleEnvio(DetalleEnvio detalleEnvio) {
        DetalleEnvio detalleCopy = new DetalleEnvio();
        detalleCopy.setDireccionEnvio(detalleEnvio.getDireccionEnvio());
        detalleCopy.setNumeroGuia(detalleEnvio.getNumeroGuia());
        detalleCopy.setPago(detalleEnvio.getPago());
        detalleCopy.setTransportadora(detalleEnvio.getTransportadora());
        detalleCopy.setPedidoId(detalleEnvio.getPedidoId());
        return detalleEnvioRepo.save(detalleCopy);
    }

    @Override
    public Optional<DetalleEnvio> update(long id, DetalleEnvio detalle) {
        Optional<DetalleEnvio> detalleEnvioDB = detalleEnvioRepo.findById(id);
        Optional<DetalleEnvio> detalleUpdate = detalleEnvioDB.map(oldDetalle->{
            DetalleEnvio detalleAux = oldDetalle.updateWith(detalle);
            return detalleEnvioRepo.save(detalleAux);

        });
        return detalleUpdate;
    }

    @Override
    public void delete(long id) {
       detalleEnvioRepo.deleteById(id);
    }

    
}
