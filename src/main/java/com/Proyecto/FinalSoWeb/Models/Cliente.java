
package com.Proyecto.FinalSoWeb.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Brandok
 */

@Data
@Entity
@Table(name="cliente")
public class Cliente {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int idCliente;
   private String nombres;
   private String apellidos;
   private String dni;
   private String celular;
   private String email;
   private String direccion;
     
   private LocalDateTime fechaRegistro = LocalDateTime.now();
}
