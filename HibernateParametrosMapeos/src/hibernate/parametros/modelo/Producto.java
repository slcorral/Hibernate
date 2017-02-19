/*
 * Producto.java
 *
 * Creada el 24/05/2010, 11:23:55 PM
 *
 * Clase Java desarrollada por Alex para el blog http://javatutoriales.blogspot.com/ el día 24/05/2010
 *
 * Para informacion sobre el uso de esta clase, asi como bugs, actualizaciones, o mejoras enviar un mail a
 * programadorjavablog@gmail.com
 *
 */
package hibernate.parametros.modelo;

import java.io.Serializable;

/**
 * @author Alex
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 24/05/2010
 */
public class Producto implements Serializable
{
    public static enum Estatus{ACTIVO, INACTIVO};

    private int id;
    private String nombre;
    private String codigoBarras;
    private float precio;
    private Estatus estatus = Estatus.ACTIVO;

    public Producto()
    {
        
        
    }

    public Producto(String nombre, String codigoBarras, float precio)
    {
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
    }

    public String getCodigoBarras()
    {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras)
    {
        this.codigoBarras = codigoBarras;
    }

    public Estatus getEstatus()
    {
        return estatus;
    }

    public void setEstatus(Estatus estatus)
    {
        this.estatus = estatus;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public float getPrecio()
    {
        return precio;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }
    
}
