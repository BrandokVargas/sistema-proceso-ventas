
package com.Proyecto.FinalSoWeb.Controller;


import com.Proyecto.FinalSoWeb.Models.Cliente;
import com.Proyecto.FinalSoWeb.Services.IClienteServices;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Brandok
 */
@Controller
public class ClientesController {
    
   
    String ruta = "Clientes/";
    
    @Autowired
    private IClienteServices IClienteServices;
    
    
    @GetMapping("/clientes")
    public String Clientes(Model model){
        List<Cliente> clientes = IClienteServices.Listar();
        model.addAttribute("clientes",clientes);       
        return ruta + "ListaClientes";
    }
    
    @GetMapping("/add-cliente")
    public String addCliente(){
        return ruta + "NuevoCliente";
    }
    
    @PostMapping("/add-cliente")
    public String RegistrarCliente(@RequestParam("nom") String nom,
                                   @RequestParam("ape") String ape,
                                   @RequestParam("dni") String dni,
                                   @RequestParam("cel") String cel,
                                   @RequestParam("email") String email,
                                   @RequestParam("dir") String dir,
                                    Model model){
        
        Cliente c = new Cliente();
        c.setNombres(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
        IClienteServices.Guardar(c);
  
        return Clientes(model);
    }

    @GetMapping("/clientes/eliminar")
    public String EliminarCliente(@RequestParam("idCliente") int id,Model model){        
        IClienteServices.Eliminar(id);
        return Clientes(model);   
    }
    
    @GetMapping("/clientes/editar")
    public String EditarCliente(@RequestParam("idCliente") int id,Model model){
        Optional<Cliente> cliente = IClienteServices.ConsultarId(id);
        model.addAttribute("cliente",cliente);  
        return ruta + "EditarCliente";       
    } 
    
    @PostMapping("/cliente/editado")
    public String ClienteActualizado( @RequestParam("idCliente") int id,
                                   @RequestParam("nombres") String nom,
                                   @RequestParam("apellidos") String ape,
                                   @RequestParam("dni") String dni,
                                   @RequestParam("celular") String cel,
                                   @RequestParam("email") String email,
                                   @RequestParam("direccion") String dir,
                                    Model model){
        
        Cliente c = new Cliente();
        c.setIdCliente(id);
        c.setNombres(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
                
        IClienteServices.Guardar(c);
  
        return Clientes(model);
    }
} 
