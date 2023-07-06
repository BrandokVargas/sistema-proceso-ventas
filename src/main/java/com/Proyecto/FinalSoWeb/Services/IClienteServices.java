
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Cliente;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Brandok
 */
public interface IClienteServices {
    public List<Cliente> Listar();
    public Optional<Cliente> ConsultarId(int id);
    public void Guardar(Cliente c);
    public void Eliminar(int id);
}
