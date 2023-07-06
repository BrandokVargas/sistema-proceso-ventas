
package com.Proyecto.FinalSoWeb.Controller;

import com.Proyecto.FinalSoWeb.Models.CartTemp;
import com.Proyecto.FinalSoWeb.Models.Cliente;
import com.Proyecto.FinalSoWeb.Models.DetalleVenta;
import com.Proyecto.FinalSoWeb.Models.Empleado;
import com.Proyecto.FinalSoWeb.Models.Producto;
import com.Proyecto.FinalSoWeb.Models.Venta;
import com.Proyecto.FinalSoWeb.Services.IClienteServices;
import com.Proyecto.FinalSoWeb.Services.IDetalleVentaServices;
import com.Proyecto.FinalSoWeb.Services.IEmpleadoServices;
import com.Proyecto.FinalSoWeb.Services.IProductoServices;
import com.Proyecto.FinalSoWeb.Services.IVentaServices;
import java.util.ArrayList;
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
public class VentaController {
    
    String ruta = "Venta/";
    

    ArrayList<CartTemp> cartList = new ArrayList();
    
    
    @Autowired
    private IVentaServices IVentaServices;
    
    @Autowired
    private IProductoServices IProductoServices;
 
    @Autowired
    private IClienteServices IClienteServices;
    
    @Autowired
    private IEmpleadoServices IEmpleadoServices;
    
    @Autowired
    private IDetalleVentaServices IDetalleVentaServices;
    /*
    @GetMapping("/ventas") 
    public String VerVentas(Model model){
        List<Venta> ventas = IVentaServices.Listar();
        model.addAttribute("ventas",ventas);
        return ruta + "ListaVentas";
    } 
     */
    
    @GetMapping("/ventas") 
    public String VerVentas(Model model){
        List<Venta> ventas = IVentaServices.VentasActivas();
        model.addAttribute("ventas",ventas);
        return ruta + "ListaVentas";
    } 
    
    @GetMapping("/venta-anulada")  
    public String AnularVenta(@RequestParam("idVenta") int id,
                               Model model)
    {
        Boolean estado = false;
        IVentaServices.VentasCancel(estado, id);
        return VerVentas(model);  
    }
    
    
    public List<String> MetodoPago(){
        return List.of("Efectivo","Debito","Credito");
    }
    
    public List<String> TipoComprobante(){
        return List.of("Boleta","Factura");
    }
    
    @PostMapping("/add-cart")
    public String RegistrarVenta(@RequestParam("producto") int idPro,
                                  @RequestParam("cantidad") Integer cantidad,
                                  Model model){
        
        Optional<Producto> producto = IProductoServices.ConsultarId(idPro);
  
        String nombre = producto.get().getNombre();
        Double precio = producto.get().getPrecio();
        
        Double total = precio * cantidad; //100 * 2 = 200  //150 * 1
        
        
        
        CartTemp card = new CartTemp();;
        card.setIdCart(idPro);
        card.setNombreProducto(nombre);
        card.setPrecio(precio);
        card.setCantidad(cantidad);
        card.setTotal(total);
        cartList.add(card); //200 - 150
      
     
        Double sumaTotal = 0.0;
        
        for(int i=0;i<cartList.size();i++){ 
            sumaTotal += cartList.get(i).getTotal();//200 
        }   
        model.addAttribute("sumaTotal",sumaTotal);
          
        return formVenta(model);  
    }  
     
     
    
    @GetMapping("/add-venta")
    public String formVenta(Model model){
        List<Cliente> clientev = IClienteServices.Listar();
        model.addAttribute("clientev",clientev);
        
        List<Empleado> empleadov = IEmpleadoServices.Listar();
        model.addAttribute("empleadov",empleadov);
        
        List<Producto> productov = IProductoServices.Listar();
        model.addAttribute("productov",productov);
        
        
         
        List<String> metodopago = MetodoPago();
        model.addAttribute("metodopago",metodopago);
        
        List<String> tipocomprobante = TipoComprobante();
        model.addAttribute("tipocomprobante",tipocomprobante);
        
        model.addAttribute("cartList",cartList);
        return ruta + "NuevaVenta";
    }
     
    @GetMapping("/add-cart")  
    public String EliminarCarrito(@RequestParam("idCart") int id,
                                  Model model)
    {
        cartList.remove(id-1); 
        
        Double sumaTotal = 0.0;
        for(int i=0;i<cartList.size();i++){ 
             sumaTotal -= cartList.get(i).getTotal();//200 
        }    
        
        model.addAttribute("sumaTotal",Math.abs(sumaTotal));
        return formVenta(model); 
    }
    
    @PostMapping("/add-venta")
    public String FinalizarVenta(@RequestParam("cliente") Cliente cliente,
                                @RequestParam("empleado") Empleado empleado,
                                @RequestParam("metodopago") String metodoPago,
                                @RequestParam("tipocomprobante") String tipocomprobante,
                                Model model){
                                 
       
       

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setMetodoPago(metodoPago);
        venta.setComprobante(tipocomprobante);
        
        //Cuando realice una venta 
        //True quiere decir que ese campo está activo
        venta.setEstado(true);

        
        IVentaServices.Guardar(venta);
        
         
        int idVenta = IVentaServices.ConsultarIdVenta();
        Venta v = new Venta();
        v.setIdVenta(idVenta);
        
         
        for(int i=0;i<cartList.size();i++){
            int idProd = cartList.get(i).getIdCart();
            Producto p = new Producto();
            p.setIdProducto(idProd);
            
            int cant = cartList.get(i).getCantidad();
            Double precio = cartList.get(i).getPrecio();
            Double total = cartList.get(i).getTotal();
            
            DetalleVenta dtv = new DetalleVenta();
            dtv.setVenta(v);
            dtv.setProducto(p);
            dtv.setCantidad(cant);
            dtv.setPrecio(precio);
            dtv.setTotal(total);
            
            IDetalleVentaServices.Guardar(dtv);
        }
        
        cartList.clear();
        
        return VerVentas(model);
    }

    
    
    
}
