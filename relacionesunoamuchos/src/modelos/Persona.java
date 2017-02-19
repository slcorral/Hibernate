/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EMiSaRiO
 */
public class Persona {
    
    private long id;
    private String nombre;
    private List<Libro> libros=new ArrayList<Libro>();
    
    public Persona()
    {
        
    }
    
    public long getId()
    {
        return id;
    }
    
    protected void setId(long id)
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
    
    public List getLibros()
    {
        return libros;
    }
    
    public void setLibros(List libros)
    {
        this.libros=libros;
    }
    
    public void addLibro(Libro libro)
    {
        this.libros.add(libro);
    }
    
}
