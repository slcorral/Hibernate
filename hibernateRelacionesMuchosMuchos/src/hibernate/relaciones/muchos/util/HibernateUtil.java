/*
 * HibernateUtil.java
 *
 * Creada el 19/08/2009, 10:56:56 PM
 *
 * Clase Java desarrollada por Programador Java para El blog http://javatutoriales.blogspot.com/ el d�a 19/08/2009
 *
 * Para informaci�n sobre el uso de esta clase, as� como bugs, actualizaciones, o mejoras env�ar un mail  
 * a programadorjavablog@gmail.com
 */
package hibernate.relaciones.muchos.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Programador Java
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 19/08/2009
 * @proyect Hibernate Relaciones Muchos a Muchos
 */
public class HibernateUtil
{

    private static final SessionFactory sessionFactory;


    static
    {
        try
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException he)
        {
            System.err.println("Ocurri� un error en la inicializaci�n de la SessionFactory: " + he);
            throw new ExceptionInInitializerError(he);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}