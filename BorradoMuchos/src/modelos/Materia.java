/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author EMiSaRiO
 */
public class Materia {
    
    private long id;
    private String nombre;
    
    public Materia()
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
     
     public String getNombre()
     {
         return nombre;
     }
     
     public void setNombre(String nombre)
     {
         this.nombre=nombre;
     }
    
}
