
package com.Proyecto.FinalSoWeb.Repository;

import com.Proyecto.FinalSoWeb.Models.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Brandok
 */

@Repository
public interface ICategoria extends CrudRepository<Categoria,Integer>{
    
   
    
    
}
