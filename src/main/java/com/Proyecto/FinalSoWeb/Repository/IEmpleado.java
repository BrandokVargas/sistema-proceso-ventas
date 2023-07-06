
package com.Proyecto.FinalSoWeb.Repository;

import com.Proyecto.FinalSoWeb.Models.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Brandok
 */
@Repository
public interface IEmpleado extends CrudRepository<Empleado,Integer>{
    
}
