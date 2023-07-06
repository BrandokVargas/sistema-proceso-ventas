
package com.Proyecto.FinalSoWeb.Controller;

import com.Proyecto.FinalSoWeb.Models.Categoria;
import com.Proyecto.FinalSoWeb.Models.Producto;
import com.Proyecto.FinalSoWeb.Services.ICategoriaServices;
import com.Proyecto.FinalSoWeb.Services.IDetalleVentaServices;
import com.Proyecto.FinalSoWeb.Services.IProductoServices;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class ProductoController {
 
    String ruta = "Productos/";
    
    @Autowired 
    private IProductoServices IProductoServices;
    
    @Autowired 
    private ICategoriaServices ICategoriaServices;
    

    
    @GetMapping("/productos")
    public String Productos(Model model){
        List<Producto> productos = IProductoServices.Listar();
        model.addAttribute("productos",productos);
        return ruta + "ListaProducto";
    }
    
    
    @GetMapping("/add-producto")
    public String addProductos(Model model){
        
        List<Categoria> categoriap = ICategoriaServices.Listar();
        model.addAttribute("categoriap",categoriap);
        
   
        return ruta + "NuevoProducto";
    }
    
    
    @PostMapping("/add-producto")
    public String RegistrarProducto(@RequestParam("prodname") String nom,
                                  @RequestParam("categoria") Categoria cat, 
                                  @RequestParam("prodpre") Double pre,
                                  @RequestParam("stock") Integer stock,
                                  Model model){
        
        Producto p = new Producto();
        p.setNombre(nom);
        p.setCategoria(cat);
        p.setPrecio(pre);
        p.setStock(stock);
        
 
        IProductoServices.Guardar(p);
  
        return Productos(model);
    }
    
    @GetMapping("/producto/eliminar")
    public String EliminarProducto(@RequestParam("idProducto") int id,Model model){        
           IProductoServices.Eliminar(id);
           return Productos(model);
        
    } 
     
    @GetMapping("/producto/editar")
    public String EditarProducto(@RequestParam("idProducto") int id,Model model){
        Optional<Producto> producto = IProductoServices.ConsultarId(id);
        model.addAttribute("producto",producto);  
        return ruta + "EditarProducto";       
    } 
    
    @PostMapping("/producto/editado")
    public String ProductoActualizado( @RequestParam("idProducto") int id,
                                  @RequestParam("nombre") String nom,
                                  @RequestParam("categoria") Categoria cat,
                                  @RequestParam("precio") Double pre,
                                  @RequestParam("stock") Integer stock,
                                  Model model){
        
        Producto p = new Producto(); 
        p.setIdProducto(id);
        p.setNombre(nom);
        p.setCategoria(cat);
        p.setPrecio(pre);
        p.setStock(stock);
                
        IProductoServices.Guardar(p);
   
        return Productos(model); 
    }
    

    
}
