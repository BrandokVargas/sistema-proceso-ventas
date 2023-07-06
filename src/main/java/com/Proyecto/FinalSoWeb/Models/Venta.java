
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
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Brandok
 */
@Data
@Entity
@Table(name="venta")
public class Venta {   
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idVenta;
    
    private String metodoPago;
    private String comprobante;
    
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idCliente") 
    private Cliente cliente;
    ////////////////////////////////////////
    
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idEmpleado") 
    private Empleado empleado;
    
    
    
    
    private LocalDateTime fechaRegistro = LocalDateTime.now();
     
    private Boolean estado; 
    //0: Venta anulada
    //1: Venta no anulada
}


