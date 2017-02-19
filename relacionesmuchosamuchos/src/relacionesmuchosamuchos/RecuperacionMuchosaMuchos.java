/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relacionesmuchosamuchos;

import modelos.HibernateUtil;
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
            
            
            
        }catch(Exception e){
            t.rollback();
        }
        session.close();
        
    }
    
}
