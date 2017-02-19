/*
 * AbstractDAO.java
 *
 * Creada el 24/07/2010, 01:22:33 PM
 *
 * Clase Java desarrollada por Alex para el blog http://javatutoriales.blogspot.com/ el día 24/07/2010
 *
 * Para informacion sobre el uso de esta clase, asi como bugs, actualizaciones, o mejoras enviar un mail a
 * programadorjavablog@gmail.com
 *
 */

package hibernate.parametros.dao;

import hibernate.parametros.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * @author Alex
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 24/07/2010
 */
public abstract class AbstractDAO
{
   //Antiguo private Session sesion;
    public Session sesion;

    protected void iniciaOperacion()
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }

    protected void terminaOperacion()
    {
        sesion.getTransaction().commit();
        sesion.close();
    }

    protected void manejaExcepcion(HibernateException he) throws HibernateException
    {
        sesion.getTransaction().rollback();
        throw he;
    }

    protected Session getSession()
    {
        return sesion;
    }

    public static void almacenaEntidad(Object entidad) throws HibernateException
    {
        AbstractDAO dummy = new AbstractDAO(){};

        try
        {
            dummy.iniciaOperacion();
            dummy.getSession().saveOrUpdate(entidad);
            dummy.getSession().flush();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }
    }

    public static <T> T getEntidad(Serializable id, Class<T> claseEntidad) throws HibernateException
    {
        AbstractDAO dummy = new AbstractDAO(){};

        T objetoRecuperado = null;

        try
        {
            dummy.iniciaOperacion();
            objetoRecuperado = (T) dummy.getSession().get(claseEntidad, id);
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return objetoRecuperado;
    }

    public static <T> List<T> getListaEntidades(Class<T> claseEntidad) throws HibernateException
    {
        AbstractDAO dummy = new AbstractDAO(){};

        List<T> listaResultado = null;

        try
        {
            dummy.iniciaOperacion();
            listaResultado = dummy.getSession().createQuery("FROM " + claseEntidad.getSimpleName()).list();
        }
        catch (HibernateException he)
        {
            dummy.manejaExcepcion(he);
        }
        finally
        {
            dummy.terminaOperacion();
        }

        return listaResultado;
    }
}
