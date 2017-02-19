/*
 * Main.java
 *
 * Creada el 14/08/2009, 11:02:04 PM
 *
 * Clase Java desarrollada por Programador Java para El blog http://javatutoriales.blogspot.com/ el día 14/08/2009
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones, o mejoras envíar un mail  
 * a programadorjavablog@gmail.com
 */

package hibernate.relaciones.muchos.util;

import hibernate.relaciones.muchos.muchos.modelo.Estudiante;
import hibernate.relaciones.muchos.muchos.modelo.Materia;
import org.hibernate.Session;

/**
 *
 * @author Programador Java
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 14/08/2009
 * @proyect Hibernate Relaciones Muchos a Muchos
 */
public class Main 
{
    /**
     * @param args los argumentos recibidos desde la linea de comandos
     */
    public static void main(String[] args) 
    {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombre("estudiante1");

        Materia materia1 = new Materia();
        materia1.setNombre("materia1");
        Materia materia2 = new Materia();
        materia2.setNombre("materia2");
        Materia materia3 = new Materia();
        materia3.setNombre("materia3");

        materia1.addEstudiante(estudiante1);
        materia2.addEstudiante(estudiante1);
        materia3.addEstudiante(estudiante1);

        
        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombre("estudiante2");

        Materia materia4 = new Materia();
        materia4.setNombre("materia4");
        Materia materia5 = new Materia();
        materia5.setNombre("materia5");
        Materia materia6 = new Materia();
        materia6.setNombre("materia6");

        materia4.addEstudiante(estudiante2);
        materia5.addEstudiante(estudiante2);
        materia6.addEstudiante(estudiante2);

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.save(estudiante1);
        sesion.save(estudiante2);
        sesion.getTransaction().commit();
        sesion.close();

        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.delete(estudiante1);
        sesion.getTransaction().commit();
        sesion.close();
    }

}
