
package com.Proyecto.FinalSoWeb.Controller;

import com.Proyecto.FinalSoWeb.Models.Empleado;
import com.Proyecto.FinalSoWeb.Services.IEmpleadoServices;
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
public class EmpleadoController {
    
    
    String ruta = "Empleados/";
    
    @Autowired
    private IEmpleadoServices IEmpleadoServices;
     
    @GetMapping("/empleados")
    public String Empleados(Model model){
        List<Empleado> empleados = IEmpleadoServices.Listar();
        model.addAttribute("empleados",empleados); 
        return ruta + "ListaEmpleado";
    }
    
    @GetMapping("/add-empleado")
    public String addEmpleados(){
        return ruta + "NuevoEmpleado";
    }
     
    
    
    @PostMapping("/add-empleado")
    public String RegistrarEmpleado(@RequestParam("nom") String nom,
                                   @RequestParam("ape") String ape,
                                   @RequestParam("dni") String dni,
                                   @RequestParam("cel") String cel,
                                   @RequestParam("email") String email,
                                   @RequestParam("dir") String dir,
                                   @RequestParam("cargo") String cargo,
                                    Model model){
        
        Empleado e = new Empleado();
        e.setNombres(nom);
        e.setApellidos(ape);
        e.setDni(dni);
        e.setCelular(cel);
        e.setEmail(email);
        e.setDireccion(dir);
        e.setCargo(cargo);
        
        IEmpleadoServices.Guardar(e);
  
        return Empleados(model);
    }
    
    
    @GetMapping("/empleados/eliminar")
    public String EliminarEmpleado(@RequestParam("idEmpleado") int id,Model model){        
        IEmpleadoServices.Eliminar(id);
        return Empleados(model);   
    }
    
    @GetMapping("/empleados/editar")
    public String EditarEmpleado(@RequestParam("idEmpleado") int id,Model model){
        Optional<Empleado> empleado = IEmpleadoServices.ConsultarId(id);
        model.addAttribute("empleado",empleado);  
        return ruta + "EditarEmpleado";       
    } 
    
    @PostMapping("/empleado/editado")
    public String EmpleadoActualizado( @RequestParam("idEmpleado") int id,
                                   @RequestParam("nombres") String nom,
                                   @RequestParam("apellidos") String ape,
                                   @RequestParam("dni") String dni,
                                   @RequestParam("celular") String cel,
                                   @RequestParam("email") String email,
                                   @RequestParam("direccion") String dir,
                                   @RequestParam("cargo") String cargo,
                                    Model model){
        

        Empleado e = new Empleado();
        e.setIdEmpleado(id);
        e.setNombres(nom);
        e.setApellidos(ape);
        e.setDni(dni);
        e.setCelular(cel);
        e.setEmail(email);
        e.setDireccion(dir);
        e.setCargo(cargo);
        
                
        IEmpleadoServices.Guardar(e);
  
        return Empleados(model);
    }
    
    
    
}
