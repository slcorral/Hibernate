/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterelaciones;


import modelo.Direccion;
import modelo.HibernateUtil;
import modelo.Pais;
import modelo.Persona;
import modelo.Presidente;
import org.hibernate.Session;

/**
 *
 * @author EMiSaRiO
 */
public class Hibernaterelaciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Persona persona1=new Persona();
        persona1.setNombre("Saul");
        
        //creamos la direccion
        Direccion direccion1=new Direccion();
        direccion1.setCalle("C Sierra de Gredos");
        direccion1.setCodigoPostal("04009");
        
        persona1.setDireccion(direccion1);
        
        Persona persona2=new Persona();
        persona2.setNombre("Luis");
        
        Direccion direccion2=new Direccion();
        direccion2.setCalle("C Sierra Morena");
        direccion2.setCodigoPostal("04008");
        
        persona2.setDireccion(direccion2);
        
        Direccion direccionnueva=new Direccion();
        
        direccionnueva.setCalle("C Sierra de Maria");
        direccionnueva.setCodigoPostal("04007");
       
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        //Iniciamos la transaccion
        sesion.beginTransaction();
        
        /** Funcional
         * 
        sesion.persist(persona1);
        sesion.persist(persona2);
        sesion.persist(direccionnueva);
        * 
        * **/
        sesion.save(persona1);
        sesion.save(persona2);
        sesion.save(direccionnueva);
        
        sesion.getTransaction().commit();
        sesion.close();
        
        /**Parte relacionada con la relacion One-to-One bidireccional.
         * 
         * **/
        
        Pais pais1=new Pais();
        pais1.setNombre("China");
        
        Pais pais2=new Pais();
        pais2.setNombre("Corea");
        
        Presidente presidente1=new Presidente();
        
        presidente1.setNombre("Jiang Zemin");
         
        Presidente presidente2=new Presidente();
         
        presidente2.setNombre("Kim Dae-Jung");
         
        pais1.setPresidente(presidente1);
        pais2.setPresidente(presidente2);
         
        presidente1.setPais(pais1);
        presidente2.setPais(pais2);
         
        sesion=HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
         
         
         /**Agregamos  este pais para comprobar que los presidentes tomen el msmo
          * identificador que los paises **/
        Pais paissolo=new Pais();
        paissolo.setNombre("Chipre");
         
        sesion.persist(paissolo);
        sesion.persist(pais1);
        sesion.persist(pais2);
         
        sesion.getTransaction().commit();
         
        sesion.close();
        
        // TODO code application logic here
    }
    
}
