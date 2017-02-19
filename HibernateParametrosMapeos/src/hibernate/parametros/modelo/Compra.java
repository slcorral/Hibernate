/*
 * Compra.java
 *
 * Creada el 24/05/2010, 11:44:30 PM
 *
 * Clase Java desarrollada por Alex para el blog http://javatutoriales.blogspot.com/ el día 24/05/2010
 *
 * Para informacion sobre el uso de esta clase, asi como bugs, actualizaciones, o mejoras enviar un mail a
 * programadorjavablog@gmail.com
 *
 */
package hibernate.parametros.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 24/05/2010
 */
public class Compra implements Serializable
{
    private long id;
    private List<Producto> productos = new ArrayList<Producto>();
    private double importeTotal;
    
    private Usuario usuario;

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public double getImporteTotal()
    {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal)
    {
        this.importeTotal = importeTotal;
    }

    public List<Producto> getProductos()
    {
        return productos;
    }

    public void setProductos(List<Producto> productos)
    {
        this.productos = productos;
    }

    public void addProducto(Producto producto)
    {
        this.productos.add(producto);
        importeTotal += producto.getPrecio();
    }

    public long getId()
    {
        return id;
    }

    protected void setId(long id)
    {
        this.id = id;
    }
}
