
package com.Proyecto.FinalSoWeb.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author Brandok
 */
@Data
@Entity
@Table(name="empleado")
public class Empleado {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int idEmpleado;
   private String nombres; 
   private String apellidos;
   private String dni;
   private String celular;
   private String email;
   private String direccion;
   private String cargo;
   private LocalDateTime fechaRegistro = LocalDateTime.now();
}
 