/*
 * HibernateUtil.java
 *
 * Creada el 24/07/2010, 01:25:07 PM
 *
 * Clase Java desarrollada por Alex para el blog http://javatutoriales.blogspot.com/ el día 24/07/2010
 *
 * Para informacion sobre el uso de esta clase, asi como bugs, actualizaciones, o mejoras enviar un mail a
 * programadorjavablog@gmail.com
 *
 */

package hibernate.parametros;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Alex
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 24/07/2010
 */
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (HibernateException he)
        {
            System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
            throw new ExceptionInInitializerError(he);
        }
        
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
    
}
