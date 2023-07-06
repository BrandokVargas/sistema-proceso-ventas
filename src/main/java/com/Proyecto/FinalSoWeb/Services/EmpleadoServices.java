
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Empleado;
import com.Proyecto.FinalSoWeb.Repository.IEmpleado;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brandok
 */
@Service
public class EmpleadoServices implements IEmpleadoServices  {
    
    @Autowired
    private IEmpleado data;
    
    @Override
    public List<Empleado> Listar() {
        return (List<Empleado>) data.findAll();
    }

    @Override
    public Optional<Empleado> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Empleado e) {
        data.save(e);
    }

    @Override
    public void Eliminar(int id) { 
        data.deleteById(id);
    }    
}
