/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author EMiSaRiO
 */
public class Persona implements Serializable {
   
    private long id;
    private String nombre;
    
    private Direccion direccion;
    
    public Persona()
    {
        
    }
    
    public Direccion getDireccion()
    {
        return direccion;
    }
    
    public void setDireccion(Direccion direccion)
    {
        this.direccion=direccion;
    }
    
    public void setId(long id)
    {
        this.id=id;
    }
    public long getId()
    {
        return this.id;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String nombre)
    {
        
        this.nombre=nombre;
    }
    
}
