
package com.Proyecto.FinalSoWeb.Controller;

import com.Proyecto.FinalSoWeb.Models.Categoria;
import com.Proyecto.FinalSoWeb.Services.ICategoriaServices;
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
public class CategoriaController {
    
    String ruta = "Categoria/";
    
    @Autowired 
    private ICategoriaServices ICategoriaServices;
    
    @GetMapping("/categorias")
    public String Categorias(Model model){
        List<Categoria> categorias = ICategoriaServices.Listar();
        model.addAttribute("categorias",categorias);
        return ruta + "ListaCategoria";
    }
    
    @GetMapping("/add-categoria")
    public String addCategoria(){
        return ruta + "NuevaCategoria";
    }
    
    @PostMapping("/add-categoria")
    public String RegistrarCategoria(@RequestParam("nom") String nom,
                                  @RequestParam("des") String des, 
                                  Model model){
        
        Categoria cat = new Categoria();
        cat.setNombre(nom);
        cat.setDescripcion(des);
        
 
        ICategoriaServices.Guardar(cat);
  
        return Categorias(model);
    }
    
    
    @GetMapping("/categoria/eliminar")
    public String EliminarCategoria(@RequestParam("idCategoria") int id,Model model){        
           ICategoriaServices.Eliminar(id);
           return Categorias(model);    
    }  
    
    @GetMapping("/categoria/editar")
    public String EditarCategoria(@RequestParam("idCategoria") int id,Model model){
        Optional<Categoria> categoria = ICategoriaServices.ConsultarId(id);
        model.addAttribute("categoria",categoria);  
        return ruta + "EditarCategoria";       
    } 
    
    @PostMapping("/categoria/editado")
    public String CategoriaActualizado( @RequestParam("idCategoria") int id,
                                  @RequestParam("nombre") String nom,
                                  @RequestParam("descripcion") String des,
                                  Model model){
        
        Categoria cat = new Categoria();
        cat.setIdCategoria(id);
        cat.setNombre(nom);
        cat.setDescripcion(des);
                
        ICategoriaServices.Guardar(cat);
   
        return Categorias(model); 
    }    
    
    
}
