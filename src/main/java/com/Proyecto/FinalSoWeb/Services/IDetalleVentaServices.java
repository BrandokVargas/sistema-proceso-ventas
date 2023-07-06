
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.DetalleVenta;


import java.util.List;
import java.util.Optional;

/**
 *
 * @author Brandok
 */
public interface IDetalleVentaServices {
    public List<DetalleVenta> Listar();
    public Optional<DetalleVenta> ConsultarId(int id);
    public void Guardar(DetalleVenta vdt);
    public void Eliminar(int id);   
    public List<DetalleVenta> BuscarIdVenta(int id);
    public String GananciaVenta(String fecha);  
    public String RangoFecha(String desde,String hasta); 
    public List<String> ProductoMasVenido();  
}
