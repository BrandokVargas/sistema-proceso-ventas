
package com.Proyecto.FinalSoWeb.Services;

import com.Proyecto.FinalSoWeb.Models.Cliente;
import com.Proyecto.FinalSoWeb.Repository.ICliente;
import com.Proyecto.FinalSoWeb.Repository.IClientePage;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Service;

/**
 *
 * @author Brandok
 */

@Service
public class ClienteServices implements IClienteServices{

    @Autowired
    private ICliente data;
    
    
    @Override
    public List<Cliente> Listar() {
        return (List<Cliente>) data.findAll();
    }

    @Override
    public Optional<Cliente> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Cliente c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) { 
        data.deleteById(id);
    }
    
}
