/*
 * Usuario.java
 *
 * Creada el 24/05/2010, 11:47:00 PM
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
public class Usuario implements Serializable
{
    private long id;
    private String nombre;
    private String username;
    private String password;

    private Direccion direccion;

    private List<Compra> compras = new ArrayList<Compra>();

    public Usuario()
    {
        
    }

    public Usuario(String nombre, String username, String password)
    {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
    }

    public List<Compra> getCompras()
    {
        return compras;
    }

    public void setCompras(List<Compra> compras)
    {
        this.compras = compras;
    }

    public void addCompra(Compra compra)
    {
        this.compras.add(compra);
        compra.setUsuario(this);
    }

    public Direccion getDireccion()
    {
        return direccion;
    }

    public void setDireccion(Direccion direccion)
    {
        this.direccion = direccion;
    }

    public long getId()
    {
        return id;
    }

    protected void setId(long id)
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    
}
