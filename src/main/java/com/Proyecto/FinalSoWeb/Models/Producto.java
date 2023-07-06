
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
@Table(name="producto") 

public class Producto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idProducto;
    private String nombre;
    
    
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idCategoria") 
    private Categoria categoria;
     
    private Double precio;
    private Integer stock; 
    
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}

