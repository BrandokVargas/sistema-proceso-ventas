
package com.Proyecto.FinalSoWeb.Repository;

import com.Proyecto.FinalSoWeb.Models.DetalleVenta;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Brandok
 */
 
@Repository
public interface IDetalleVenta extends CrudRepository<DetalleVenta,Integer>{
    
    @Query(value="SELECT * FROM detalle_venta WHERE id_venta = ?1",nativeQuery = true)
    public List<DetalleVenta> BuscarIdVenta(int id);   
    
    
    
    //select sum(total) from detalle_venta where date(fecha_registro) = '2023-06-21';
    @Query(value="SELECT SUM(total) FROM detalle_venta WHERE date(fecha_registro) = ?1",nativeQuery = true)
    public String GananciaVenta(String fecha);   
    
    //select sum(total) from detalle_venta where date(fecha_registro) between '2023-06-22' and '2023-06-23'
    @Query(value="SELECT SUM(total) FROM detalle_venta WHERE date(fecha_registro) between ?1 and ?2",nativeQuery = true)
    public String RangoFecha(String desde,String hasta);
     
    
    @Query(value="select dt.id_producto as id ,p.nombre as producto ,sum(dt.cantidad ) as cantidades_vendidas from detalle_venta dt inner join producto p on p.id_producto = dt.id_producto group by p.nombre order by cantidades_vendidas desc limit 1",nativeQuery = true)
    public List<String> ProductoMasVenido(); 
} 
