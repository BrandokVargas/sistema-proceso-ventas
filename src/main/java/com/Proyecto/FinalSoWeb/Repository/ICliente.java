
package com.Proyecto.FinalSoWeb.Repository;

import com.Proyecto.FinalSoWeb.Models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Brandok
 */
@Repository
public interface ICliente extends CrudRepository<Cliente,Integer> {
    
   
}
