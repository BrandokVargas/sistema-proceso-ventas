
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Venta;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Brandok
 */
public interface IVentaServices {
    public List<Venta> Listar();
    public Optional<Venta> ConsultarId(int id);
    public void Guardar(Venta v);
    public void Eliminar(int id);   
    public int ConsultarIdVenta();
    public List<Venta> VentasActivas(); 
    public void VentasCancel(Boolean estado, int idVenta);  
}
