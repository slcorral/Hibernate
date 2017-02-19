/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author EMiSaRiO
 */
public class HibernateUtil {
    
    //Nos creamos un objeto SessionFactory, este objeto es estatico y no final, para que no se pueda modificar en toda la aplicacion
    private static final SessionFactory sessionFactory;
    
    static //bloque de inicializacion estatico
    {
        
        try
        {
            //creamos un objeto sessionFactory.
            sessionFactory=new Configuration().configure().buildSessionFactory();
           
        } catch(HibernateException he)
        {
            //Sacamos por la consola de error lo que ocurre.
            System.err.println("Ocurrio un error en la inicializacion de la SessionFactory: "+he);
            throw new ExceptionInInitializerError(he);
            
        }
        
    }
    
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
    
}
