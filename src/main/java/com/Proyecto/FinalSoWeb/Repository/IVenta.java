
package com.Proyecto.FinalSoWeb.Repository;

import com.Proyecto.FinalSoWeb.Models.Venta;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Brandok
 */

@Repository
public interface IVenta extends CrudRepository<Venta,Integer>{
    @Query(value="SELECT id_venta FROM venta ORDER BY id_venta DESC LIMIT 1",nativeQuery = true)
    public int ConsultarIdVenta();
    
    
    @Query(value="SELECT id_venta,id_cliente,id_empleado,metodo_pago,comprobante,fecha_registro,estado FROM venta WHERE estado =  1",nativeQuery = true)
    public List<Venta> VentasActivas();   
    
    @Query(value="UPDATE venta set estado = ?1 where id_venta = ?2",nativeQuery = true)
    @Modifying
    @Transactional
    public void VentasCancel(Boolean estado, int idVenta); 
}
 