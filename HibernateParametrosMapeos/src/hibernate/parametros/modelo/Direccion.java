/*
 * Direccion.java
 *
 * Creada el 24/05/2010, 11:12:29 PM
 *
 * Clase Java desarrollada por Alex para el blog http://javatutoriales.blogspot.com/ el día 24/05/2010
 *
 * Para informacion sobre el uso de esta clase, asi como bugs, actualizaciones, o mejoras enviar un mail a
 * programadorjavablog@gmail.com
 *
 */

package hibernate.parametros.modelo;

import java.io.Serializable;

/**
 * @author Alex
 * @version 1.0
 * @author-mail programadorjavablog@gmail.com
 * @date 24/05/2010
 */
public class Direccion implements Serializable
{
    private long id;
    private String calle;
    private String codigoPostal;

    public Direccion()
    {
    }

    public Direccion(String calle, String codigoPostal)
    {
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    

    public String getCalle()
    {
        return calle;
    }

    public void setCalle(String calle)
    {
        this.calle = calle;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public long getId()
    {
        return id;
    }

    private void setId(long id)
    {
        this.id = id;
    }
}
