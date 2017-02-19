/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relacionesmuchosamuchos;

import java.util.Iterator;
import java.util.List;
import modelos.Estudiante;
import modelos.HibernateUtil;
import modelos.Materia;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Portatil
 */
public class RecuperacionMuchosaMuchos {
    
    public static void main(String[] args){
        
        System.out.println("Ejemplo de programa");
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
       
        try{
            
            Query query = session.createQuery("from Estudiante");
            List list = query.list();
            Iterator it;
            it=list.iterator();
            Estudiante seleccionado=null;
            while(it.hasNext()){
               Estudiante estudiante=(Estudiante)it.next();
               
               if(estudiante.getNombre().compareTo("Saul")==0){
                 seleccionado=estudiante;
                 System.out.println("Seleccionamos estudiante");
               }
               
               System.out.println("Id"+ estudiante.getId());
               System.out.println("Nombre " + estudiante.getNombre());
               System.out.println("Materias Estudiante "+estudiante.getMaterias().size());
               
            }
            
            if(seleccionado!=null){
              seleccionado.setNombre("Saul");
              System.out.println(seleccionado.getMaterias().get(0).getNombre());
              seleccionado.getMaterias().remove(0);
            }
            
            t.commit();
        }catch(Exception e){
            System.out.println("Error "+e.getMessage());
            t.rollback();
        }
        
    }
    
}
