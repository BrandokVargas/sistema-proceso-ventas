
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Brandok
 */
public interface IProductoServices {
    public List<Producto> Listar();
    public Optional<Producto> ConsultarId(int id);
    public void Guardar(Producto p);
    public void Eliminar(int id);
}
