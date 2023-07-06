
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Venta;
import com.Proyecto.FinalSoWeb.Repository.IVenta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brandok
 */
@Service
public class VentaServices implements IVentaServices{
    @Autowired
    private IVenta data;
    
    @Override
    public List<Venta> Listar() {
        return (List<Venta>) data.findAll(); 
    }

    @Override
    public Optional<Venta> ConsultarId(int id) {
        return data.findById(id);
    }
 
    @Override
    public void Guardar(Venta v) {
        data.save(v);
    }

    @Override
    public void Eliminar(int id) { 
        data.deleteById(id);
    }    
    @Override
    public int ConsultarIdVenta(){
        return data.ConsultarIdVenta();
    } 
    @Override
    public List<Venta> VentasActivas(){
        return data.VentasActivas(); 
    }  
    @Override
    public void VentasCancel(Boolean estado, int idVenta){
        data.VentasCancel(estado,idVenta);  
    } 
} 
