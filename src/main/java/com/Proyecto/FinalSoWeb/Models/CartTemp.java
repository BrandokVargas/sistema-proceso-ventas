
package com.Proyecto.FinalSoWeb.Models;

import lombok.Data;

/**
 *
 * @author Brandok
 */
@Data
public class CartTemp {
    private int idCart;
    private String nombreProducto;
    private int cantidad;
    private Double precio;
    private Double total;  
}
