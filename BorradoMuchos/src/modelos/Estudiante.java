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
public class Estudiante {
    
    private long id;
    private String nombre;
    private List<Materia> materias=new ArrayList<Materia>();
    
    public Estudiante()
    {
        
    }

    protected void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Materia> getMaterias() {
        return materias;
    }
    
    public void addMateria(Materia materia)
    {
        materias.add(materia);
    }
    
}
