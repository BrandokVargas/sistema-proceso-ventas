
package com.Proyecto.FinalSoWeb.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Brandok
 */
@Data
@Entity
@Table(name="detalleVenta")

public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idDetalleVenta;
    
    
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idVenta") 
    private Venta venta;
    ////////////////////////////////////////
     

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idProducto") 
    private Producto producto;    
    
    private int cantidad;
    private Double precio;
      
    private Double total;
    
    private LocalDateTime fechaRegistro = LocalDateTime.now();   
    
}
