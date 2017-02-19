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
public class Presidente implements Serializable {
    
    private int id;
    private String nombre;
    
    private Pais pais;
    
    public Presidente()
    {
        
    }
    
    public int getId()
    {
        return id;
    }
    
    protected void setId(int id)
    {
        this.id=id;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public Pais getPais()
    {
        return pais;
    }
    
    public void setPais(Pais pais)
    {
        this.pais=pais;
    }
    
}
