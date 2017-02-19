/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author EMiSaRiO
 */
public class Libro {
    
    private long id;
    private String titulo;
    
    public Libro()
    {
        
    }
    
    public long getId()
    {
       return this.id;        
    }
    
    protected void setId(long id)
    {
        this.id=id;
    }
    
    public String getTitulo()
    {
        
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo=titulo;
    }
    
}
