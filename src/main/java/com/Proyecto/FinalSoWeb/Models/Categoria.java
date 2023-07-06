
package com.Proyecto.FinalSoWeb.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Brandok
 */
@Data
@Entity
@Table(name="categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCategoria;
    private String nombre;
    private String descripcion;
}
