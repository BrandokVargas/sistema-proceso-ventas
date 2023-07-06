
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.DetalleVenta;
import com.Proyecto.FinalSoWeb.Repository.IDetalleVenta;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brandok 
 */
@Service
public class DetalleVentaServices implements IDetalleVentaServices{
    @Autowired
    private IDetalleVenta data;
    
    @Override
    public List<DetalleVenta> Listar() {
        return (List<DetalleVenta>) data.findAll(); 
    }

    @Override
    public Optional<DetalleVenta> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(DetalleVenta dtv) {
        data.save(dtv);
    }

    @Override
    public void Eliminar(int id) { 
        data.deleteById(id);
    }     
    @Override
    public List<DetalleVenta> BuscarIdVenta(int id){
        return data.BuscarIdVenta(id); 
    } 
    @Override
    public String GananciaVenta(String fecha){
        return data.GananciaVenta(fecha);
    }
    @Override
    public String RangoFecha(String desde,String hasta){
        return data.RangoFecha(desde, hasta);
    }  
    
    @Override
    public List<String> ProductoMasVenido(){
        return data.ProductoMasVenido(); 
    }   
     
   
    
}
