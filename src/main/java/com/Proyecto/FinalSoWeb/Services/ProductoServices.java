
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Producto;
import com.Proyecto.FinalSoWeb.Repository.IProducto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brandok
 */
@Service
public class ProductoServices implements IProductoServices {

    
    
    @Autowired
    private IProducto data;
    
    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }
    
    @Override
    public Optional<Producto> ConsultarId(int id) {
        return data.findById(id);
    }
 
    @Override
    public void Guardar(Producto p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) { 
        data.deleteById(id);
    }  
}
