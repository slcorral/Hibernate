/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relacionesmuchosamuchos;

import modelos.Estudiante;
import modelos.Materia;
import org.hibernate.Session;
import modelos.HibernateUtil;

/**
 *
 * @author EMiSaRiO
 */
public class Relacionesmuchosamuchos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Estudiante estudiante1=new Estudiante();
        estudiante1.setNombre("Saul");
        
        Materia materia1=new Materia();
        materia1.setNombre("Matematicas");
        Materia materia2=new Materia();
        materia2.setNombre("Fisica");
        Materia materia3=new Materia();
        materia3.setNombre("Dibujo");
        
        Materia materiaborrada=new Materia();
        materiaborrada.setNombre("Religion");
        
        estudiante1.addMateria(materia1);
        estudiante1.addMateria(materia2);
        estudiante1.addMateria(materia3);
        
        //Nos creamos un segundo estudiante.
        
        
        Estudiante estudiante2=new Estudiante();
        estudiante2.setNombre("Luis");
        
        
        //Insertamos un estudiante unico.
        Estudiante estudianteunico=new Estudiante();
        estudianteunico.setNombre("David");
        
        //Insertamos una materia unica.
        Materia materiaunica=new Materia();
        materiaunica.setNombre("Historia");
        
        /**Tercer estudiante para borrarlo **/
        
        Estudiante estudianteborrado=new Estudiante();
        estudianteborrado.setNombre("Antonio");
        
        estudianteborrado.addMateria(materiaborrada);
     
        
        /** 
         * 
         * Materias unicas para el.
        Materia materia4=new Materia();
        materia4.setNombre("materia4");
        
        Materia materia5=new Materia();
        materia5.setNombre("materia5");
        
        Materia materia6=new Materia();
        materia6.setNombre("materia6");
        
        estudiante2.addMateria(materia4);
        estudiante2.addMateria(materia5);
        estudiante2.addMateria(materia6);
      
        estudiante2.addMateria(materia4);
        estudiante2.addMateria(materia5);
        estudiante2.addMateria(materia6);
        * 
        * 
       **/
       
        estudiante2.addMateria(materia1);
        estudiante2.addMateria(materia2);

        //Abrimos la session.
        
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        
        sesion.save(estudiante1);
        sesion.save(estudiante2);
        sesion.save(estudianteborrado);
        
        
       sesion.getTransaction().commit();
       sesion.close();
       
       /** añadimos materias unicas y estudiantes unicos **/
        
       sesion=HibernateUtil.getSessionFactory().openSession();
       sesion.beginTransaction();
        
       sesion.save(estudianteunico);
       sesion.save(materiaunica);
       
       sesion.getTransaction().commit();
       sesion.close();
       
       /**Borramos el estudiante "estudianteborrado" **/
       
       sesion=HibernateUtil.getSessionFactory().openSession();
       sesion.beginTransaction();
       
       sesion.delete(estudianteborrado);
       
       sesion.getTransaction().commit();
       sesion.close();
     
    }
    
}
