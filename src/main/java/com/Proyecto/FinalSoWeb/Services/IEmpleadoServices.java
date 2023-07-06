
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Empleado;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Brandok
 */
public interface IEmpleadoServices {
    public List<Empleado> Listar();
    public Optional<Empleado> ConsultarId(int id);
    public void Guardar(Empleado e);
    public void Eliminar(int id);   
}
