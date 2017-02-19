/*
 * Materia.java
 *
 * Creada el 14/08/2009, 11:32:46 PM
 *
 * Clase Java desarrollada por Programador Java para El blog http://javatutoriales.blogspot.com/ el día 14/08/2009
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones, o mejoras envíar un mail  
 * a programadorjavablog@gmail.com
 */


package hibernate.relaciones.muchos.muchos.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Programador Java
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 14/08/2009
 * @proyect Hibernate Relaciones Muchos a Muchos
 */
public class Materia 
{
    private long id;
    private String nombre;

    private List<Estudiante> estudiantes = new ArrayList<Estudiante>();

    public Materia()
    {
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

    public List<Estudiante> getEstudiantes()
    {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes)
    {
        this.estudiantes = estudiantes;
    }

    public void addEstudiante(Estudiante estudiante)
    {
        this.estudiantes.add(estudiante);
        estudiante.addMateria(this);
    }
    
}