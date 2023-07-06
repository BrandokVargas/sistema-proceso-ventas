package com.Proyecto.FinalSoWeb.Controller;

import com.Proyecto.FinalSoWeb.Models.Venta;
import com.Proyecto.FinalSoWeb.Services.IDetalleVentaServices;
import com.Proyecto.FinalSoWeb.Services.IVentaServices;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
public class Inicio {
    
    String ruta = "Detalles/";
    
    @Autowired
    private IDetalleVentaServices IDetalleVentaServices;
    @Autowired
    private IVentaServices IVentaServices;
    
    
    @GetMapping("/")
    public String Inicio(){
        return "Index"; 
    }
    
    
    //Ganancias por fechas
    @GetMapping("/ganancias-dia") 
    public String GananciasDiarias(){    
        return "Detalles/VentasFechas"; 
    }
    
   
    
    @PostMapping("/ganancias-dia") 
    public String GananciasDiariasAdd(@RequestParam("fecha_registro") String fecha,Model model){     
        String[] cut = fecha.split("T");
        String cut1= cut[0];
        String ganancias = IDetalleVentaServices.GananciaVenta(cut[0]);
         
        model.addAttribute("ganancias",ganancias);
        return  ruta + "VentasFechas"; 
    }
    
    @PostMapping("/ganancias-rango-fecha") 
    public String GananciasRangoFechas(@RequestParam("desde") String desde,@RequestParam("hasta") String hasta,Model model){     
        String[] fechaDesdeFormat = desde.split("T");
        String fechaDesde = fechaDesdeFormat[0];
        
        String[] fechaHastaFormat = hasta.split("T");
        String fechaHasta = fechaHastaFormat[0];
        
        
        String rangoganancias = IDetalleVentaServices.RangoFecha(fechaDesde, fechaHasta);
         
        model.addAttribute("rangoganancias",rangoganancias);
        return ruta + "/VentasFechas"; 
    }
    
    //Reportes
    @GetMapping("/reportes") 
    public String Reportes(){    
        return "Detalles/Reportes"; 
    }
    
    
    public ByteArrayInputStream ProductoMasVendidoReporte() throws IOException{
        
        String cols[] = {"ID","Producto","Cantidad Vendidas"};
        
         
        Workbook workbook = new HSSFWorkbook();  
         
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = (Sheet) workbook.createSheet("Producto mas vendido");
        
        Row row = sheet.createRow(0);
        
        for (int i = 0; i < cols.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(cols[i]);
        } 
        
        List<String> productoMasVendido = IDetalleVentaServices.ProductoMasVenido();
        int iRow = 1; 
        for(String prodMas : productoMasVendido){
            String[] data = prodMas.split(",");
            row = sheet.createRow(iRow);
   
            row.createCell(0).setCellValue(data[0]); 
            row.createCell(1).setCellValue(data[1]); 
            row.createCell(2).setCellValue(data[2]); 
            iRow++;
        }
        

        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
    
    
    @GetMapping("/producto-mas-vendido")
    public ResponseEntity<InputStreamResource> generaReporteProd ()throws Exception{
        ByteArrayInputStream stream = ProductoMasVendidoReporte(); 
        HttpHeaders headers = new HttpHeaders();
         
        headers.add("Content-Disposition","attachment; filename=productomasvendido.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    } 
    
     
    public ByteArrayInputStream ReporteVentas() throws IOException{
        
        String cols[] = {"ID","Cliente","Empleado","Metodo pago","Comprobante","Fecha de venta"};
        
         
        Workbook workbook = new HSSFWorkbook();  
         
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = (Sheet) workbook.createSheet("Mis ventas");
        
        Row row = sheet.createRow(0);
        
        for (int i = 0; i < cols.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(cols[i]);
        } 
        
        List<Venta> misVentas = IVentaServices.VentasActivas();
        int iRow = 1; 
        for(Venta v : misVentas){
            
            row = sheet.createRow(iRow);
   
            row.createCell(0).setCellValue(v.getIdVenta()); 
            row.createCell(1).setCellValue((v.getCliente().getNombres()));           
            row.createCell(2).setCellValue(v.getEmpleado().getNombres()); 
            row.createCell(3).setCellValue(v.getMetodoPago()); 
            row.createCell(4).setCellValue(v.getComprobante()); 
            row.createCell(5).setCellValue(v.getFechaRegistro().format(DateTimeFormatter.ISO_DATE)); 
            iRow++;
        }
        

        workbook.write(stream);
        workbook.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
    
    
    @GetMapping("/mis-ventas")
    public ResponseEntity<InputStreamResource> generaReporteVenta ()throws Exception{
        ByteArrayInputStream stream = ReporteVentas(); 
        HttpHeaders headers = new HttpHeaders();
         
        headers.add("Content-Disposition","attachment; filename=misventas.xls");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }     
    
}
