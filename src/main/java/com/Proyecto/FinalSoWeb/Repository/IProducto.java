
package com.Proyecto.FinalSoWeb.Repository;

import com.Proyecto.FinalSoWeb.Models.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author USUARIO
 */
@Repository
public interface IProducto extends CrudRepository<Producto,Integer>{
    

}
