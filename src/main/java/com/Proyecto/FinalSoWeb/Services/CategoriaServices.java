
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Categoria;
import com.Proyecto.FinalSoWeb.Repository.ICategoria;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brandok
 */
@Service
public class CategoriaServices implements ICategoriaServices{
    
    @Autowired
    private ICategoria data;
    
    @Override
    public List<Categoria> Listar() {
        return (List<Categoria>) data.findAll();
    }
    
    @Override
    public Optional<Categoria> ConsultarId(int id) {
        return data.findById(id);
    }
 
    @Override
    public void Guardar(Categoria ca) {
        data.save(ca);
    }

    @Override
    public void Eliminar(int id) { 
        data.deleteById(id);
    }      
}
