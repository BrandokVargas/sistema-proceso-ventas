
package com.Proyecto.FinalSoWeb.Controller;


import com.Proyecto.FinalSoWeb.Models.Cliente;
import com.Proyecto.FinalSoWeb.Repository.IClientePage;
import com.Proyecto.FinalSoWeb.Services.IClienteServices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
/**
 *
 * @author Brandok
 */
@Controller
public class ClientesController {
    
   
    String ruta = "Clientes/";
    final int cantidadCols = 2;
    final int pages = 0;
    
    String mostrarClientes = "redirect:/clientes";
    
    @Autowired
    private IClienteServices IClienteServices;
    
    @Autowired
    private IClientePage IClientePage;
    
    @GetMapping("/clientes")
    public String Clientes(@PageableDefault(size = cantidadCols, page = pages) Pageable pageable,Model model){
        //List<Cliente> clientes = IClienteServices.Listar();
        
        Page<Cliente> clientes = IClientePage.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        
        model.addAttribute("page",clientes);   
        
        var totalPages = clientes.getTotalPages();
		var currentPage = clientes.getNumber();
		
		var start = Math.max(1, currentPage);
		var end = Math.min(currentPage + 5, totalPages);
		
		if (totalPages > 0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = start; i <= end; i++) {
				pageNumbers.add(i);
			}
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		
		List<Integer> pageSizeOptions = Arrays.asList(10,20, 50, 100);
		model.addAttribute("pageSizeOptions", pageSizeOptions);
                
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
                                   @RequestParam("dir") String dir
                                   ){
        
        Cliente c = new Cliente();
        c.setNombres(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
        IClienteServices.Guardar(c);
  
        return mostrarClientes;
    }

    @GetMapping("/clientes/eliminar")
    public String EliminarCliente(@RequestParam("idCliente") int id){        
        IClienteServices.Eliminar(id);
        return mostrarClientes;
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
                                   @RequestParam("direccion") String dir
                                    ){
        
        Cliente c = new Cliente();
        c.setIdCliente(id);
        c.setNombres(nom);
        c.setApellidos(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        
                
        IClienteServices.Guardar(c);
  
        return mostrarClientes; 
    }
} 
