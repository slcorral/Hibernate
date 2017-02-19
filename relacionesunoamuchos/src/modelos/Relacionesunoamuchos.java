/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import modelos.HibernateUtil;
import modelos.Libro;
import modelos.Persona;
import org.hibernate.Session;

/**
 *
 * @author EMiSaRiO
 */
public class Relacionesunoamuchos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /** Primero creamos una persona y la asociamos con dos libros **/
        
        System.out.println("Proyecto desde pen drive");
        
        Libro libro1=new Libro();
        libro1.setTitulo("2000 leguas de viaje submarino");
        
        Libro libro2=new Libro();
        libro2.setTitulo("La maquina del tiempo");
        
        Persona persona1=new Persona();
        
        persona1.setNombre("Saul");
        persona1.addLibro(libro1);
        persona1.addLibro(libro2);
        
        Libro libro3=new Libro();
        libro3.setTitulo("Los pilares de la tierra");
       
        persona1.addLibro(libro3);
        //creamos la session y almacenamos la persona 1.
        
        //Nos cremaos una segunda persona para luego borrarla.
        Libro lp1=new Libro();
        lp1.setTitulo("Los gitantes de la tierra");
        Libro lp2=new Libro();
        lp2.setTitulo("Los pilares de la tierra dos");
        Persona persona2=new Persona();
        persona2.setNombre("Luis");
        persona2.addLibro(lp1);
        persona2.addLibro(lp2);
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        
        //almacenamos la persona1.
        sesion.persist(persona1);
        sesion.save(persona2);        
        
        sesion.getTransaction().commit();
        sesion.close();
        
        //Abriamos una sesion nueva y borramos a la persona.
        
        sesion=HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        
        sesion.delete(persona2);
        sesion.getTransaction().commit();
        sesion.close();

    }
    
}
