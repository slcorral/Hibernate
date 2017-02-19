/*
 * UsuariosDAO.java
 *
 * Creada el 24/07/2010, 01:22:04 PM
 *
 * Clase Java desarrollada por Alex para el blog http://javatutoriales.blogspot.com/ el día 24/07/2010
 *
 * Para informacion sobre el uso de esta clase, asi como bugs, actualizaciones, o mejoras enviar un mail a
 * programadorjavablog@gmail.com
 *
 */
package hibernate.parametros.dao;

import hibernate.parametros.HibernateUtil;
import hibernate.parametros.modelo.Compra;
import hibernate.parametros.modelo.Producto;
import hibernate.parametros.modelo.Usuario;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;



public class UsuariosDAO extends AbstractDAO
{
    public Usuario getUsuario(String username, String password) throws HibernateException
    {
        Usuario usuario = null;

        try
        {
            iniciaOperacion();
            Query query = getSession().createQuery("FROM Usuario u WHERE u. username = :nombreUsuario AND u. password = :password");
            query.setParameter("nombreUsuario", username);
            query.setParameter("password", password);

            usuario = (Usuario)query.uniqueResult();
        }
        catch (HibernateException he)
        {
            manejaExcepcion(he);
        }
        finally
        {
            terminaOperacion();
        }


        return usuario;
    }

    public List<Usuario> getUsuariosConComprasInactivas(String codigoPostal) throws HibernateException
    {
        List<Usuario> listaUsuarios = null;

        try
        {
            iniciaOperacion();

            Query query = getSession().createQuery("SELECT u FROM Usuario u JOIN FETCH u.compras c JOIN c.productos p WHERE p.estatus = ? AND u.direccion.codigoPostal = ?");
            query.setParameter(0, Producto.Estatus.INACTIVO);
            query.setParameter(1, codigoPostal);
            
            listaUsuarios = query.list();

        }catch(HibernateException he)
        {
            he.printStackTrace();
            manejaExcepcion(he);
        }finally
        {
            terminaOperacion();
        }

        return listaUsuarios;
    }
    
    public void mostrarCompras()
    {
        //recuperamos los objetos.
        
        List<Usuario> listaUsuarios = null;
        
        try
        {
            iniciaOperacion();
            Query consulta=getSession().createQuery("FROM Usuario as u  inner join fetch u.compras");
            listaUsuarios=consulta.list();
               
        }catch(HibernateException he)
        {
            he.printStackTrace();
            manejaExcepcion(he);
        
        }finally
        {
            terminaOperacion();
        }
        
        //Mostramos el bucle.
        Iterator<Usuario> it;
        Compra compra;
        
        Iterator<Compra> it_compra;

        for(it=listaUsuarios.iterator();it.hasNext();)
        {
            Usuario usuario=it.next();
            
            List<Compra> list=usuario.getCompras();
            
            if(list.size()!=0)
            {
                System.out.println("Tiene elementos");
                
                for(it_compra=list.iterator();it_compra.hasNext();)
                {
                    compra=it_compra.next();
                    System.out.println("Nombre: "+usuario.getNombre()+" "+"Compra "+compra.getId());

                }
                
            }
            else
            {
                System.out.println("No tiene elementos");
            }
            
        }
        
    }
    
    public void actualizarUsuarioConsulta(long idparametro,String nombre)
    {
       
        //creamos el session
        try
        {
            iniciaOperacion();
            Query query=getSession().createQuery("update Usuario u set u.nombre=:Nombre where u.id=:idparametro");
            query.setParameter("Nombre",nombre);
            query.setParameter("idparametro",idparametro);
            query.executeUpdate();
            
        }catch(HibernateException he)
        {
            manejaExcepcion(he);
  
        }finally
        {
            terminaOperacion();   
        }
        
    }
            
    public  void actualizarNombreUsuarioPorIdDireccion(long idDireccion,String nombre)
    {
        
        try
        {
         
            iniciaOperacion();
            
            Query query=getSession().createQuery("update Usuario as c set c.nombre=:Nombre where"
                    + " c.id in ( select d.id from Direccion as d  where d.id>=:idDireccion)");
           
            query.setParameter("Nombre",nombre);
            query.setParameter("idDireccion",idDireccion);
            query.executeUpdate();
            
            
           /**
           Query query=getSession().createQuery("select c.nombre from Usuario as c where c.id in (select u.id from Usuario as u where u.direccion.id>=:idDireccion)");
           query.setParameter("idDireccion",idDireccion);
 
           List<String> listanombres=query.list();
           
           Iterator<String> it;
           
           for(it=listanombres.iterator();it.hasNext();)
           {
               String n;
               n=it.next();
               System.out.println("Nombre: "+n);
           }
          
           
           /**
           List<Long> listanombres=query.list();
           
           System.out.println("Tamañoooooooo "+listanombres.size());
           
           Iterator<Long> it;
           
           for(it=listanombres.iterator();it.hasNext();)
           {
               Long l=it.next();
               System.out.println("IDS: "+l);
               
 
           }
           **/
           
 
        }catch(HibernateException he)
        {
            manejaExcepcion(he);
           
        }finally
        {
            terminaOperacion();
        }
        
        
    } 
    
    public void usuariosComprasParametro(long numCompras)
    {
        
        try
        {
            iniciaOperacion();
            /**
            Query query=getSession().createQuery("select c.nombre FROM Usuario as c where c.id"
                    + " in (select u.id FROM Usuario as u inner join fetch Compra as comp GROUP BY(u.id) HAVING COUNT(comp.importeTotal)>=:numeroCompras)");
                    * **/
            //Query query=getSession().createQuery("select u.id FROM Usuario as u inner join fetch u.compras  GROUP BY u.id HAVING COUNT u.compras.importeTotal>=:numeroCompras");
            
             //Query query=getSession().createQuery("select comp.id FROM Compra as comp GROUP BY comp.id");
              Query query=getSession().createQuery("select new list(u.id,u.nombre) from Usuario u inner join  u.compras comp group by u.id having count(comp.importeTotal)>=:numCompras");
             
              query.setParameter("numCompras",numCompras);
              
              List<List> listaid=query.list();
              System.out.println("Nombre "+listaid.size());

              Iterator<List> it;
              
              System.out.println("\n\n");
              System.out.println("Usuarios con compras superiores a : "+numCompras);
              
              for(it=listaid.iterator();it.hasNext();)
              {
                  List l=it.next();
                  System.out.println("ID: "+l.get(0)+ " Nombre: "+l.get(1));
              }
              

        }catch(HibernateException he)
        {
             manejaExcepcion(he);
                     
        }finally
        {
           terminaOperacion();
        }
        

    }

    
    /**
     * Metodo:
     * 
     * Consulta: Usuarios con compras que tengan mas de  un numero determinado de productos
     * 
     */
    public void usuariosComprasProductosParametro(long numProductos)
    {
        
        try
        {
            
            iniciaOperacion();
        
            Query query=getSession().createQuery("select new list(u.id,u.nombre) from Usuario u inner join u.compras as ucomp where ucomp.id in "
                    + " (select comp.id FROM Compra as comp inner join comp.productos as produc "
                    + "group by comp.id having count(produc.id)=:numProductos)");

            
            query.setParameter("numProductos",numProductos);
            List<List> listaid=query.list();
            
            System.out.println("\n\n");
            
            System.out.println("Usuarios con compras realizadsa con numero igual de productos a "+numProductos);
            
            Iterator<List> it;
            System.out.println("HAY QUE APLICARLE EL DISTINCT");
            
            for(it=listaid.iterator();it.hasNext();)
            {
                List l=it.next();
                System.out.println("ID: "+l.get(0)+" Nombre: "+l.get(1));
                
            }
          
        }catch(HibernateException he)
        {
            manejaExcepcion(he);
                
        }finally
        {
            terminaOperacion();
            
        }
        
    }
    
    
    public void usuariosComprasProductosNombre(String nombreProduc)
    {
        
        try
        {
            iniciaOperacion();
            Query query=getSession().createQuery("select comp.id from Compra as comp inner join "
                    + " comp.productos cproduc where cproduc.nombre=:nombreProduc");
            
            query.setParameter("nombreProduc",nombreProduc);
            
            List<Long> listaid;
            listaid=query.list();
            
            Iterator<Long> it;
            
            System.out.println("\n\n");
            System.out.append("Usuarios que han realizado compras que contienen el producto "+nombreProduc);
            
            for(it=listaid.iterator();it.hasNext();)
            {
                Long l=it.next();
                System.out.println("ID: "+l);
            }
            
            
        }catch(HibernateException he)
        {
            manejaExcepcion(he);
            
        }finally
        {
            terminaOperacion();
            
        }
        
    }
    
    
    /**Metodo que almacena en la base de datos un num Usuarios **/
    
    public void almacenarUsuarios(int numeroUsuarios)
    {
        //comprobamos numeroUsuarios sea >0
        if(numeroUsuarios>0)
        {
            Long ultimo_id=ultimoID();

            try
            {
                //Abrimos la session y iniciamos transaccion
                 sesion=HibernateUtil.getSessionFactory().openSession();
                 sesion.getTransaction().begin();
                 
                 //Extraemos el ultimo ID de la tabla para concatenarlo con el nombre.
                 
                 StringBuilder nombre=new StringBuilder("");
                 StringBuilder nombreUsuario=new StringBuilder("");
                 StringBuilder passwordUsuario=new StringBuilder("");

 
                 for(int i=1;i<=numeroUsuarios;i++)
                 {

                     nombre.append("UsuarioMetodo ");
                     nombre.append(ultimo_id+i);
                     nombreUsuario.append("Usuario ");
                     long var=ultimo_id+i;
                     nombreUsuario.append(var);
                     passwordUsuario.append("12345");
                     
                     Usuario usuario=new Usuario(nombre.toString(),nombreUsuario.toString(),passwordUsuario.toString());
 
                     sesion.save(usuario);  
                     nombre.delete(0,nombre.length());
                     nombreUsuario.delete(0,nombreUsuario.length());
                     passwordUsuario.delete(0,passwordUsuario.length());
                     
                 }
                 
                 
            }catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                throw he;
                
            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();
            }
            
        }
        else
        {
            System.out.println("El numero de Usuarios tiene que ser mayor que cero");
            
        }  
        
    }
    
    /**Metodo que extrae el ultimo ID de la tabla de la base de datos **/
    
    public Long ultimoID()
    {
        Long enterogrande=null;
        
        try
        {
            sesion=HibernateUtil.getSessionFactory().openSession();
            sesion.getTransaction().begin();
            
            Query query=sesion.createQuery("select max(u.id) from Usuario as u");
           
            //consulta para extraer el ultimo id de la tabla de la base de datos.
             enterogrande=(Long)query.uniqueResult();
             System.out.println(enterogrande);
            
        }catch(HibernateException he)
        {
                sesion.getTransaction().rollback();
                throw he;
                
         }finally
         {
                sesion.getTransaction().commit();
                sesion.close();
         }
        
       return enterogrande;
        
    }
    
    /**Metodo que extrae recorre una consulta con iterator,
     Extraemos todos los usuarios escritos en la tabla. **/
    
    public void queryIterator()
    {
        try
        {
            //abrimos una sesion
            sesion=HibernateUtil.getSessionFactory().openSession();
            sesion.getTransaction().begin();
            
 
        }catch(HibernateException he)
        {
            
            
        }finally
        {
            
        }
  
    }
  
    
    
    /*
     * Método que se encarga de realizar una compra.
     * 
     * Return:
     * Devuelve null en caso de que login=null o password=null o los parametros sean igual a ""
     * en caso tambien de no existir el Usuario.
     */
    public Usuario buscarUsuarioPorLoggin(String login,String password)
    {
       Usuario usuario=null;
        
       if(login!=null && password!=null)
       {
        
            try
            { 
                //Buscamos el usuario por loggin.
                //abrimos sesion.
                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();
                
                Query query=sesion.createQuery("select distinct u from Usuario as  u inner join fetch u.compras as comp where u.password like :paramPassword and u.username  like :paramLogin");
                
                
                query.setParameter("paramLogin",login);
                query.setParameter("paramPassword",password);
               
                /**
                List<Usuario> lista=query.list();
                System.out.println("Ejemplo Nuevoooo "+lista.size());
                * **/
                
                //usuario=(Usuario)query.uniqueResult();
                List<Usuario> list=query.list();
                usuario=list.get(0);
                

            }catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                throw he;
   
            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();
                
            }
            
       }
       
       return usuario;
        
    }
    
    
    /**
    
    /**
     * 
     * Metodo que se encarga de obtener un listado detodos los productos pertenecientes en la base de datos
     * 
     * Return:
     * List<Producto> en el caso de ser null no existen productos en la base de datos
     * 
     
    
    public List<Producto> buscarProductos()
    {
        List<Producto> lista=null;
        
        try
        {
            
            sesion=HibernateUtil.getSessionFactory().openSession();
            sesion.getTransaction().begin();
            
            Query query=sesion.createQuery("select distinct p from Producto as p ");
            
            lista=query.list();

        }
        catch(HibernateException he)
        {
            sesion.getTransaction().rollback();
            throw he;
        
        }finally
        {
            sesion.getTransaction().commit();
            sesion.close();
        }
  
        return lista;
    }
    
    
    /**
     * 
     * Método que se encarga de realizar una compra para el usuario
     * 
     * 
     * 
     * @param usuario para realizar la compra
     * @param listaProductos lista de productos a comprar.
    
    public void usuarioCompraUsuario(Usuario usuario,List<Producto> listaProductos)
    {
        
        if(usuario!=null && listaProductos!=null)
        {
            
            //Extraemos
            
            try
            {
                
                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();
                //Creamos el objeto Compra.
                Compra compra=new Compra();
                
                /**
                compra.setProductos(listaProductos);
                * **/
                //insertamos todos los productos
                
                /**ANTIGUO Funcional 
                Iterator<Producto> it;
                
                for(it=listaProductos.iterator();it.hasNext();)
                {
                    compra.addProducto(it.next());
                    
                }
                * 
                * **/
                
                //realizamos un merge de los productos.
                
                /**
                Iterator<Producto> lista_productos;
                
                for(lista_productos=listaProductos.iterator();lista_productos.hasNext();)
                {
                    Producto producto=lista_productos.next();
                    
                    sesion.merge(producto);
                    
                }
                
                
                
                
                usuario.addCompra(compra);
                
                //compra.setUsuario(usuario);
                
                
                List<Compra> lista_compras;
                
                lista_compras=usuario.getCompras();
                
                Iterator<Compra> iterador;
                
                for(iterador=lista_compras.iterator();iterador.hasNext();)
                {
                    Compra compra_aux=iterador.next();
                    //iteramos sobre los productos de la compra.
                    Iterator<Producto> iterador_producto;
                    
                    //extraigo la lista de productos de la compra
                    for(iterador_producto=compra_aux.getProductos().iterator();iterador_producto.hasNext();)
                    {
                        Producto producto=iterador_producto.next();
                        
                        sesion.merge((Producto)producto);
                        
                        long id=producto.getId();
                        
                        Iterator<Producto> it_lista_productos;
                        
                        for(it_lista_productos=listaProductos.iterator();it_lista_productos.hasNext();)
                        {
                           Producto producto_aux=it_lista_productos.next();
                           
                           if(id==producto_aux.getId())
                           {
                               sesion.merge(producto_aux);
                               compra.addProducto((Producto)sesion.merge(producto_aux));
                           }
                            
                        }
                        //buscamos que coincida con alguno de los ids de lista productos
                        
                        
                    }
                    
                }
                
                
         
                
                int valor=0;
                
                
                /**
                 * Codigo antiguo para la explicación de la Compra de Usuario 
                 * En este caso es donde nos introduce un null  en el orden de la compra 
                compra.setUsuario(usuario);
                * sesion.save(compra)
                * 
                * **/

                //sesion.save(usuario);
                
                /**
                 * PARTE FUNCIONAL CON EL ID DE PRODUCTO 1 **/
                /**
                 * sesion.saveOrUpdate(usuario)
                 * sesion.flush()
                 */
                
                  //sesion.saveOrUpdate(compra);
                  //sesion.save(compra);
                
                //sesion.saveOrUpdate(usuario);
                 //sesion.persist(usuario);
                // sesion.saveOrUpdate(usuario);
                //sesion.merge(usuario);
                //sesion.persist(compra);
                
                
                /**
                 * FUNCIONAL DEL TODOOOOOOOOOOOO BIEN 
                
                    sesion.saveOrUpdate(compra);
                    sesion.saveOrUpdate(usuario);
                    sesion.flush();
                 
                
                
                 //sesion.save(compra);
                // sesion.save(usuario);
                //sesion.save(usuario);
                 //sesion.flush();
                //sesion.persist(usuario);
                // sesion.update(usuario);
             
                //sesion.save(compra);
               
                
            }catch(HibernateException he)
            {
                System.err.println(he.getLocalizedMessage());
                System.err.println("Mensaje");
                System.err.println(he.getMessage());
                
                sesion.getTransaction().rollback();
                throw he;
                
            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();

            }
            
        
            try
            {
                
                
                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();
                
                sesion.update(usuario);
                
                sesion.flush();
                
                
            }catch(HibernateException he)
            {
                System.err.println(he.getLocalizedMessage());
                System.err.println("Mensaje");
                System.err.println(he.getMessage());
                
                sesion.getTransaction().rollback();
                throw he;
            
            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();
            }
            * 

        }
        
    }
    
    
    
    /**
     * 
     * Método que se encarga de obtener todos los productos 
     * 
     * @param 
     * 
     */
    
    /**
    public List<Producto> obtenerProductosPorID(List<Long> lista_id_productos)
    {
        List<Producto> lista_productos_obtenidos=null;
        
        if(lista_id_productos!=null)
        {
            
            //copiamos en un string todos los id de productos para prepararlos en la consulta.
            StringBuilder conjunto_productos=new StringBuilder();
            conjunto_productos.append("(");
            
            Iterator<Long> it_productos;
            
            for(it_productos=lista_id_productos.iterator();it_productos.hasNext();)
            {
                Long aux=it_productos.next();
                
                conjunto_productos.append(aux);
                
                if(it_productos.hasNext()==true)
                  conjunto_productos.append(",");
 
            }
            
            conjunto_productos.append(")");
            
            System.out.println(conjunto_productos.toString());
        
            try
            {
                
                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();

                Query query=sesion.createQuery("select p from Producto as p where p.id in "+conjunto_productos.toString());
                
                lista_productos_obtenidos=query.list();
    
            }catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                throw he;

            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();

            }

        }
        
        return lista_productos_obtenidos;
   
    }
    * 
    * **/
   
    
    /**
     * 
     * Método que se encarga devolvernos el ID y el nombre del producto en una lista.
     * 
     * Primer elemento de la lista ID tipo long
     * Segundo elemento de la lista Nombre tipo string.
     * 
     * @return 
     * List<Object[]> 
     */
    public List<List> buscarProductos()
    {
        
        List<List> tuplas=null;
        
        try
        {
            sesion=HibernateUtil.getSessionFactory().openSession();
            sesion.getTransaction().begin();
            
            //realizamos la consulta.
            Query query=sesion.createQuery("select new list(p.id,p.nombre) from Producto as p");
            
            tuplas=query.list();
            
 
        }catch(HibernateException he)
        {
            sesion.getTransaction().rollback();
            System.err.println(he.getMessage());
            throw he;
            
            
        }finally
        {
            sesion.getTransaction().commit();
            sesion.close();
            
        }
        
        return  tuplas;
           
        
    }

    //PROBLEMAS METERLO TODO EN UM MÉTODO.
    
    /**
     * 
     * Método que se encarga de devolvernos el identificador del usuario
     * mediante su username y password.
     * 
     * @return
     * Long id.
     * En el caso de error devuelve -1
     */
    
    public long buscarUsuario(String username,String password)
    {
        Long id=new Long(-1);
        
        if(username!=null && password!=null && username.compareTo("")!=0 && password.compareTo("")!=0)
        {
            
            //Buscamos el usuario
            try
            {
                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();

                Query query=sesion.createQuery("select distinct u.id from Usuario as u where u.username like :parametroUserName and u.password like :parametroPassword ");

                query.setParameter("parametroUserName",username);
                query.setParameter("parametroPassword",password);
                
                Long id_resultado=null;
                
                /** 
                 * 
                 * EN EL CASO DE NO ENCONTRAR NINGUN OBJETO EN LA BASE DE DATOS
                 * El resultado del objeto devuelto por uniqueResult es NULL
                 * por lo tanto tenemos que comprobar si devuelve resultados.
                 * 
                 * **/
                id_resultado=(Long)query.uniqueResult();
                
                if(id_resultado!=null)
                {
                    id=id_resultado;
                }
                
            }
            
           
           catch(HibernateException he)
           {
                 sesion.getTransaction().rollback();
                 System.err.println(he.getMessage());
                 throw he;
           
           }finally
           {
               sesion.getTransaction().commit();
               sesion.close();
              
           }
                
        }
        
        
        return id.longValue();
 
    }
    
    /**
     * 
     * Método que se de realizar una compra para un usuario y almacenarla en la base de datos.
     * 
     * parametros:
     * 
     * long id_user identificador del usuario
     * 
     * List<long> lista_productos que contiene los identificadores de los productos
     * 
     * return:
     * 
     */
    
    public void realizarCompra(long id_user,List<Long> lista_productos)
    {
        
        if(id_user>=0 && lista_productos!=null && lista_productos.size()>0)
        {
            
            try
            {
                
                //abrimos la sesion
                sesion=HibernateUtil.getSessionFactory().openSession();
                //iniciamos la transaccion
                sesion.getTransaction().begin();
                
                //Recogemos el usuario.
                //ESTUDIAR que vamos a necesitar de usuario, la lista_compra
                Query query=sesion.createQuery("select distinct u from Usuario as u inner join fetch u.compras where u.id=:parametroID");
                
                query.setParameter("parametroID",id_user);
                
                //recogemos el resultado de la base de datos.
                
                Usuario usuario_elegido=(Usuario)query.uniqueResult();
                
                //Caso en el que hemos recibido un usuario.
                
                if(usuario_elegido!=null)
                {
                    //buscamos todos los productos que se encuentra en la lista de identificadores de productos.
                    
                    StringBuilder conjunto_productos=new StringBuilder();
                    
                    Iterator<Long> iterador;
                    
                    conjunto_productos.append("(");
                    
                    for(iterador=lista_productos.iterator();iterador.hasNext();)
                    {
                        conjunto_productos.append(iterador.next());
                        
                        if(iterador.hasNext()==true)
                            conjunto_productos.append(",");
                        
                    }
                    
                    conjunto_productos.append(")");
                    
                    System.out.println(conjunto_productos);
                    
                    Query query_productos=sesion.createQuery("select distinct p from Producto as p where p.id in "+conjunto_productos);
                    
                    
                    List<Producto> lista_query_productos=null;
                    
                    lista_query_productos=query_productos.list();
                    
                    //Comprobamos que el resultado de la consulta contenga productos
                    if(lista_query_productos.size()>0)
                    {
                        //creamos una compra y insertamos todos los productos.
                        Compra compra=new Compra();
                        
                        Iterator<Producto> iterador_productos;
                        
                        for(iterador_productos=lista_query_productos.iterator();iterador_productos.hasNext();)
                        {
                            Producto p=iterador_productos.next();

                            compra.addProducto(p);
                             
                        }
                        
                        //añadimos la compra que acabamos de crear al usuario_elegido
                        usuario_elegido.addCompra(compra);
                        
                        //almacenamos el usuario.
                        
                        /**NO SERIA NECESARIO YA QUE EL OBJETO USUARIO, es un objeto persistente.
                         * Ya esta almacenado en la base de datos
                         * Al encontrarse dentro de una sesión hibernate podemos operar sobre él
                         * **/
                        //sesion.saveOrUpdate(usuario_elegido);

                    }
                    else
                        System.out.println("No contiene productos");
                    
                }
                else
                    System.out.println("No existe dicho Usuario ");
                
             
                
            }catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                System.err.println();
             
                        
            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();
            }
            

        }
  
    }
    
    
    /**
     * 
     * 
     * Método que elimina una compra.
     */
    
    /**
     * 
     */
    
    public void eliminarCompra(long id_compra)
    {
        
        try
        {
            
            sesion=HibernateUtil.getSessionFactory().openSession();
            sesion.getTransaction().begin();
            
            /**Con delete da fallos de clave foranea
             
            Query query=sesion.createQuery("delete from Compra as comp where comp.id=:parametroCompra");
            
            query.setParameter("parametroCompra",id_compra);
            
            query.executeUpdate();
            ***/
            
            Query query=sesion.createQuery("select comp from Compra as comp inner join  comp.productos where comp.id=:parametroCompra ");
            query.setParameter("parametroCompra",id_compra);
            
            Compra compra=(Compra)query.uniqueResult();
            
            System.out.println("Tamaño lista "+compra.getProductos().size());
            
            sesion.delete(compra);
            

        }catch(HibernateException he)
        {
            sesion.getTransaction().rollback();
            System.err.append(he.getMessage());
  
        }finally
        {
            sesion.getTransaction().commit();
            sesion.close();
  
        }
 
        
    }
   
    
    /**
     * Grupo de métodos para el estudio del objeto Query.
     */
    
    /**
     * Metodo que recupera  indica cual es el primer elemento a recuperar del conjunto de resultados.
     * 
     */
    public void primerElemento(int primerElemento)
    {
        
        if(primerElemento >=0) //comprobamos que sea mayor que cero
        {
            //iniciamos la sesion-
            
            try
            {
                sesion=HibernateUtil.getSessionFactory().openSession();
                //Iniciamos la transaccion
                sesion.beginTransaction();
                
                //Creamos la consulta
                Query query=sesion.createQuery("from Usuario");
                query.setFirstResult(primerElemento);
                List<Usuario> lista=query.list();
                
                System.out.println("Numero de elementos "+lista.size());
                Iterator iterador=lista.iterator();
                
                System.out.println("Mostramos todos los usuarios obtenidos");
                
                for(iterador=lista.iterator();iterador.hasNext();)
                {
                    Usuario usuario=(Usuario)iterador.next();
                    System.out.println("ID del usuario "+usuario.getId()+" "+"Nombre "+usuario.getNombre());

                }
                

            }
            catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                System.err.println(he.getMessage());
  
            }finally
            {
                sesion.getTransaction().commit();
                sesion.close();
            }
           
        }
        
        
        
    }
            
    
    /**
     * Método que se encarga de recuperar un numero máximo de elementos.
     * 
     * Parametros:
     * long numMaximoElementos, usamos el tipo long por que el ID de la clase es Long
     * entonces es el tipo de variable que lo representa correctamente
     * 
     */
    
    public void numeroMaximoElementos(long numMaximoElementos)
    {
        //comparamos que el numeroMaximoElementos sea mayor que cero
        if(numMaximoElementos>=0)
        {
           
            //abrimos la session
            try
            {
                    sesion=HibernateUtil.getSessionFactory().openSession();
                    //iniciamos una transacción
                    sesion.getTransaction().begin();

                    //consultamos el numero máximo de resultados.
                    Query query=sesion.createQuery("from Usuario");

                    query.setMaxResults((int)numMaximoElementos);

                    List<Usuario> lista=query.list();

                    System.out.println("Numero de elementos de la lista "+lista.size());

                    System.out.println("Numero de elementos maximos "+numMaximoElementos);

                    Iterator it;

                    for(it=lista.iterator();it.hasNext();)
                    {
                        Usuario usuario=(Usuario)it.next();
                        System.out.println("ID "+usuario.getId()+" Nombre "+usuario.getNombre());

                    }
                    
                    
            }
            catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                System.err.println(he.getMessage());
                
            }
            finally
            {
                sesion.getTransaction().commit();
                sesion.close();
                
            }
            

        }
        

    }
    
    /*
     * 
     * 
     * 
     * 
     * 
     * 
     */
    
    
    /**
     * Método que se encarga de usar el Objeto scroll.
     * 
     * 
     */
    
    public void consultaConScroll()
    {
        
        //extraemos todos los usuarios.
        
        try
        {
            
            sesion=HibernateUtil.getSessionFactory().openSession();
            sesion.getTransaction().begin();
            
            //seleccionamos todos los usuarios.
            
            Query query=sesion.createQuery("from Usuario");
            
            
            
            ScrollableResults scroll=query.scroll();
            
            
            //recorremos todos los elementos.
  
            while(scroll.next())
            {
                
                //extraemos elementos.
                
                //se accede con el get.
                
                /** Usamos el método con get(i) para extraer de la tupla.
                Object[] objeto=scroll.get();
                
                Usuario usuario=(Usuario)objeto[0];
                
                * **/
                
                Usuario usuario=(Usuario)scroll.get(0);

               //Usuario usuario=(Usuario)scroll.get();
               System.out.println("Metodo consultaScroll "+ " ID "+usuario.getId()+" Nombre "+usuario.getNombre());
               

            }
            
  
        }
        catch(HibernateException he)
        {
            
            sesion.getTransaction().rollback();
            System.err.println(he.getMessage());
            
        }
        finally
        {
            sesion.getTransaction().commit();
            sesion.close();
            
        }
        
        
        
    }
           
    
    /**
     * Metodo que usa el query.scroll(i) para saltarse elementos.
     * 
     */
    
    public void  consultaConScrollSalto(int numSalto)
    {
        
        //comprobacion de parametros
       if(numSalto>0)
       {
           
           try
           {
               
                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();

               //creamos la consulta.

                Query query=sesion.createQuery("from Usuario");

                ScrollableResults scroll=query.scroll();


                //el cursor viene situado en beforeFirst

               while(scroll.next())
               {
                   
                   //como es un solo objeto usamos get.
                   
                   Usuario usuario=(Usuario)scroll.get(0);
                   System.out.println("consultaConScrollSalto "+" ID "+usuario.getId()+" Nombre "+usuario.getNombre());
                   
                   scroll.scroll(numSalto);
                   System.out.println("Saltamos numero "+numSalto);
                   
               }
               
               
           
           }
           catch(HibernateException he)
           {
               sesion.getTransaction().rollback();
               System.err.println(he.getMessage());
               
           }
           finally
           {
               sesion.getTransaction().commit();
               sesion.close();
               
           }
           

       }
        
        
        
    }
   
    
    /*
     * Metodo que filtra una colección de compras mediante el importe total.
     * 
     * 
     */
    
    public void filtroComprasPorImporte(String parametroUsername)
    {
        //if(importeTotal>0)
        //{
        
            try
            {

                sesion=HibernateUtil.getSessionFactory().openSession();
                sesion.getTransaction().begin();
                
                
                //recuperamos todos los usuarios con sus correspondientes compras.
                
                Query query=sesion.createQuery("from Usuario");
                
                List<Usuario> lista_usuarios=null;
                
                lista_usuarios=query.list();
                
                //A lo mejor hay que realizarla directamente sobre las compras
               
                Usuario primero=lista_usuarios.get(0);
               
                int valor=0;
 
                /**
                 * 
                 * Lista de compras ordenadas por orden ascendente.
                 * 
                Query consulta_compras=sesion.createFilter(primero.getCompras(),"order by this.importeTotal desc");
                
                
                 List<Compra> lista_compras=consulta_compras.list();
                 * 
                 * **/
                
                /*
                 * Obtenemos las compras con importe mayor a 120
                 * 
                 */
                
                Query consulta_compras=sesion.createFilter(primero.getCompras(),"where importeTotal>120");
                
                List<Compra> compras=consulta_compras.list();
                
                /**
                 * 
                 * Listado de productos cuya compra supere importeTotal>120
                 * 
                 */
                
                
                
                
                /**
                consulta_usuarios.setParameter("parametroNombre",parametroUsername);
                
                List<Usuario> lista_usuarios_nuevos=null;
                
                lista_usuarios_nuevos=consulta_usuarios.list();
                
                //mostramos la lista de usuaarios con id>=valorImporte.
                
                Iterator it;
                
                
                for(it=lista_usuarios_nuevos.iterator();it.hasNext();)
                {
                    Usuario aux=(Usuario)it.next();
                    
                    System.out.println("Metodo Filtro "+aux.getId()+" "+aux.getNombre());
                      
                }
                * **/
               
                
   
            }
            catch(HibernateException he)
            {
                sesion.getTransaction().rollback();
                System.err.println(he.getMessage());
                
            }
            finally
            {
                sesion.getTransaction().commit();
                sesion.close();
                
            }
            

       //}
        
        
   }
 
    
    /**
     *
     * 
     * 
     */
    
    
    
    
}
