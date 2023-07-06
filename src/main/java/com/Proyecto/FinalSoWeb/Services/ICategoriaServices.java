
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Categoria;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Brandok
 */
public interface ICategoriaServices {
    public List<Categoria> Listar();
    public Optional<Categoria> ConsultarId(int id);
    public void Guardar(Categoria ca);
    public void Eliminar(int id);
}
