/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author EMiSaRiO
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
    
    static
    {
        
        try
        {
            sessionFactory=new Configuration().configure().buildSessionFactory();
           
        }catch(HibernateException he)
        {
            System.err.println("Ocurrio un error en la inicializacion del sessionFactory "+he);
            throw he;          
        }
       
    }
    
    public static SessionFactory getSessionFactory()
    {
          return sessionFactory; 
    }
    
}
