/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.parametros;

import hibernate.parametros.dao.AbstractDAO;
import hibernate.parametros.dao.UsuariosDAO;
import hibernate.parametros.modelo.Compra;
import hibernate.parametros.modelo.Direccion;
import hibernate.parametros.modelo.Producto;
import hibernate.parametros.modelo.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Alex
 */
public class Main
{
    public Main() throws IOException
    {
//      creaUsuarios();
//      buscaUsuario();
        creaCompras();
        buscaUsuariosProductosInactivos();
        mostrarCompras();
       //actualizarNombreUsuario(1,"Nuevo");
        //actualizarNombreUsuarioPorIdDireccion(2,"Antonio");
        usuariosComprasParametro(2);
        usuariosComprasProductosParametro(2);
        usuariosComprasProductosNombre("Libro");
        
        System.out.println("Desea almacenar Usuarios");
        System.out.println("Introduzca Si en caso Afirmativo, No en caso negativo");
        
        String resultado=new String();
        UsuariosDAO usuario=new UsuariosDAO();
        
        
        BufferedReader bufferlectura = new BufferedReader(new InputStreamReader(System.in));
        
        //Try de lectura de valores.
        try
        {
            resultado=bufferlectura.readLine();

            if(resultado.compareTo("Si")==0)
            {
                System.out.println("Introduzca el Numero de Usuarios");
            
                String numero=bufferlectura.readLine();
                //convertimos de string a entero
                int valor_numero;
                valor_numero=Integer.parseInt(numero);
                
                usuario=new UsuariosDAO();
                usuario.almacenarUsuarios(valor_numero);
            }
            
            
        }
        catch(IOException exception)
        {
            System.out.println("No se puede leer de consola");
        }
        finally
        {
            //Inicializamos siempre la variable
            resultado="";
        }
        
      boolean salir_compras=false;
     
      
      while(salir_compras==false)
      {
      
            System.out.println("Desea realizar una compra");
            System.out.println("Introduca Si para continuar, introduzca No para salir");
            resultado="";
            resultado=bufferlectura.readLine();

            if(resultado.compareTo("Si")==0)
            {


             try
             {

                 System.out.println("Seleccione un Usuario");
                 System.out.println("Para ello introduzca, el nombre del usuario y el password");
                 System.out.println("Introduzca el nombre de usuario");

                 String nombre_usuario=bufferlectura.readLine();
                 System.out.println("Introduzca el password");
                 String password_usuario=bufferlectura.readLine();

                 /** BUSCAR QUE EL USUARIO SOLO INTRODUZCA LETRAS **/


                 long id=usuario.buscarUsuario(nombre_usuario,password_usuario);


                 System.out.println("ID usuario elegido "+id);

                 System.out.println("Listado de Productos ");

                 List<List> lista_productos=usuario.buscarProductos();

                 Iterator<List> it_lista_productos=null;


                 for(it_lista_productos=lista_productos.iterator();it_lista_productos.hasNext();)
                 {
                     List lista_parametros=it_lista_productos.next();

                     System.out.println("ID "+lista_parametros.get(0)+" Nombre "+lista_parametros.get(1));

                 }


                 System.out.println("Introduce el Identificador de los productos");
                 System.out.println("Introduce Salir para dejar de introducir productos");

                 boolean salir=false;
                 long valor_leido=0;

                 List<Long> lista_id_productos=new ArrayList<Long>();

                 while(salir==false)
                 {
                    resultado=bufferlectura.readLine();

                    if(resultado.compareTo("salir")!=0)
                    {
                       //comprobamos que el valor leido sea un numero.
                      try
                      {
                          valor_leido=Long.parseLong(resultado);
                          lista_id_productos.add(valor_leido);

                      }catch(NumberFormatException excepcion)
                      {
                          System.out.println("El valor introducido no es un numero");


                      }

                    }
                    else
                    {
                        salir=true;
                    }

                 }

                 //Obtenemos la lista de productos.

                 usuario.realizarCompra(id,lista_id_productos);


             }catch(IOException excepcion)
             {
                 System.out.println("No se puede leer de consola para Realizar una compra");

             }finally
             {
                 resultado="";
             }
             
          }else
           {
               //caso en el que no queremos seguir realizando compras.
               
               salir_compras=true;
               
           }
           
        }
      
      /**
      boolean eliminar_compras=false;
      
      while(eliminar_compras==false)
      {
          
          System.out.println("Desea eliminar una compra");
          System.out.println("Introduzca si si desea eliminar una compra, no si desea salir");
          
          String continuar=bufferlectura.readLine();
          
          if(continuar.compareTo("si")==0)
          {
            System.out.println("Introduzca el id de la compra ");

            String lectura=bufferlectura.readLine();
            long valor=Long.parseLong(lectura);

            usuario.eliminarCompra(valor);
                    
           }
           else
          {
              eliminar_compras=true;
          }
          
      }
      * **/
      
        usuario.eliminarCompra(4);
        
        
        System.out.println("Desea Realizar Ejemplos de la clase Query");
        System.out.println("Introduzca si en caso afirmativo, no en caso negativo");
        
         resultado="";
         resultado=bufferlectura.readLine();
         
        
        
        if(resultado.compareTo("si")==0 || resultado.compareTo("Si")==0)
        {
            
            //Que método desea ejecutar.
            
            boolean salir_query=false;
            
            while(salir_query==false)
            {
                
                System.out.println("Introduzca 0, para salir de la aplicacion");
                System.out.println("Introduzca 1, para la recuperacion  Usuarios a partir de un elemento");
                System.out.println("Introduzca 2, para la recuperacion de un Numero Maximo de Usuarios");
                System.out.println("Introduzca 3, para la construccion de una consulta con scroll");
                System.out.println("Introduzca 4, para la construccion de una consulta con paginacion");
                System.out.println("Introduzca 5, para la ejecución de filtros de colecciones");

                resultado="";
                resultado=bufferlectura.readLine();

                int valor=Integer.parseInt(resultado);
                
                switch(valor)
                {
                    case 0:
                    {
                        //salimos de la aplicacion
                        salir_query=true;
                        break;
                    }
                    case 1:
                    {
                        //leemos el primer valor de paginacion                      
                        System.out.println("Introduce el primer valor de recuperacion");
                        resultado=bufferlectura.readLine();
                        int primerElemento=Integer.parseInt(resultado);
                        
                        usuario.primerElemento(primerElemento);
                        break;
                    }
                    case 2:
                    {
                        //leemos el valor maximo de la consulta
                        System.out.println("Introduce el valor maximo de elementos a recuperar");
                        resultado=bufferlectura.readLine();
                        long valorMaximo=Long.parseLong(resultado);
                        
                        usuario.numeroMaximoElementos(valorMaximo);
                        
                        break;
                        
                    }
                        
                    case 3:
                    {
                        System.out.println("Mostramos el numero de Usuarios");
                        usuario.consultaConScroll();
                        
                        break;

                    }
                        
                    case 4:
                    {
                        
                        System.out.println("Mostramos el numero de Usuarios por paginacion");
                        int valorPaginacion=0;
                        System.out.println("Introduce el valor de paginacion");
                        
                        resultado=bufferlectura.readLine();
                        valorPaginacion=Integer.parseInt(resultado);
                        
                        if(valorPaginacion>0)
                        {
                        
                          usuario.consultaConScrollSalto(valorPaginacion);
                        }
                        else
                            System.out.println("El valor de paginacion debe ser mayor que cero");
                        
                        break;
                        
                    }
                        
                    case 5:
                    {
                        System.out.println("Seleccionamos todos las compras con un importe mayor o igual");
                        
                        double valorImporte=0;
                        
                        resultado=bufferlectura.readLine();
                         
                        valorImporte=Double.parseDouble(resultado);
                        
                        
                        if(valorImporte>0)
                        {
                            UsuariosDAO usuario_nuevo=new UsuariosDAO();
                            
                           //usuario_nuevo.filtroComprasPorImporte(valorImporte);
                            usuario_nuevo.filtroComprasPorImporte("caperucita");
                            
                            
                        }
                        else
                        {
                            
                            System.out.println("El importe debe de ser mayor que cero");
                        }
                       
                        break;
                        
                    }
                       
   
                }
                
            }
            
            
        }
        
       
    }
         

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        new Main();
        
    }

    private void creaUsuarios()
    {
        try
        {
            AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 1", "estudioso", "desvelado"));
            AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 2", "caperucita", "loboFeroz"));
            AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 3", "empleado", "infelicidad"));
            AbstractDAO.almacenaEntidad(new Usuario("Usuario de Prueba numero 4", "usuarioComun", "password"));
        }
        catch (HibernateException he)
        {
            System.err.println("Ocurrió un error al agregar los usuarios...");
            he.printStackTrace();
        }
    }

    private void buscaUsuario()
    {
        Usuario usuario = null;
        UsuariosDAO usuariosDAO = new UsuariosDAO();

        try
        {
            usuario = usuariosDAO.getUsuario("caperucita", "loboFeroz");
        }
        catch (HibernateException e)
        {
            System.err.println("Ocurrió un error al recuperar usuario");
            e.printStackTrace();
        }

        if (usuario == null)
        {
            System.out.println("No se encontró al usuario");
        }
        else
        {
            System.out.println("El usuario es: " + usuario.getNombre());
        }
    }

    private void creaCompras()
    {
        
        Producto libros = new Producto("Libro", "ABC123456", 120.0F);
        Producto iPads = new Producto("iPad", "RAF755576", 1315.45F);
        Producto televisor = new Producto("T.V.", "AOF765984", 379.64F);
        Producto postales = new Producto("Postal", "ELF", 15.65F);
        Producto juegos = new Producto("Videojuego", "MEN", 158.24F);

        libros.setEstatus(Producto.Estatus.INACTIVO);
        postales.setEstatus(Producto.Estatus.INACTIVO);

        Usuario usuario1 = new Usuario("Usuario de Prueba numero 1", "estudioso", "desvelado");
        usuario1.setDireccion(new Direccion("calle principal", "12345"));

        Usuario usuario2 = new Usuario("Usuario de Prueba numero 2", "caperucita", "loboFeroz");
        usuario2.setDireccion(new Direccion("primera avenida", "AVR-175"));

        Usuario usuario3 = new Usuario("Usuario de Prueba numero 3", "empleado", "infelicidad");
        usuario3.setDireccion(new Direccion("puesta del sol", "12345"));

        Usuario usuario4 = new Usuario("Usuario de Prueba numero 4", "usuarioComun", "password");
        usuario4.setDireccion(new Direccion("Este 145", null));

        Compra compraUsuario1 = new Compra();
        compraUsuario1.addProducto(libros);
        usuario1.addCompra(compraUsuario1);

        compraUsuario1 = new Compra();
        compraUsuario1.addProducto(televisor);
        compraUsuario1.addProducto(juegos);
        usuario1.addCompra(compraUsuario1);

        Compra compraUsuario2 = new Compra();
        compraUsuario2.addProducto(iPads);
        compraUsuario2.addProducto(televisor);
        compraUsuario2.addProducto(juegos);
        usuario2.addCompra(compraUsuario2);

        Compra compraUsuario3 = new Compra();
        compraUsuario3.addProducto(iPads);
        compraUsuario3.addProducto(televisor);
        usuario3.addCompra(compraUsuario3);

        compraUsuario3 = new Compra();
        compraUsuario3.addProducto(postales);
        compraUsuario3.addProducto(juegos);
        usuario3.addCompra(compraUsuario3);

        Compra compraUsuario4 = new Compra();
        compraUsuario4.addProducto(libros);
        usuario4.addCompra(compraUsuario4);

        try
        {
           
            AbstractDAO.almacenaEntidad(libros);
            AbstractDAO.almacenaEntidad(iPads);
            AbstractDAO.almacenaEntidad(televisor);
            AbstractDAO.almacenaEntidad(postales);
            AbstractDAO.almacenaEntidad(juegos);
            
             
            AbstractDAO.almacenaEntidad(usuario1);
            AbstractDAO.almacenaEntidad(usuario2);
            AbstractDAO.almacenaEntidad(usuario3);
            AbstractDAO.almacenaEntidad(usuario4);
            
        }
        catch (HibernateException he)
        {
            System.err.println("Ocurrió un error al agregar los usuarios...");
            he.printStackTrace();
        }
        
    }

    private void buscaUsuariosProductosInactivos()
    {
        List<Usuario> listaUsuarios = new UsuariosDAO().getUsuariosConComprasInactivas("12345");

        for(int i = 0; i < listaUsuarios.size(); i++)
        {
            Usuario usuarioActual = listaUsuarios.get(i);

            System.out.println("\nUsuario: " + usuarioActual.getNombre());

            List<Compra> listaCompras = usuarioActual.getCompras();

            for (int numeroCompra = 0; numeroCompra < listaCompras.size(); numeroCompra++)
            {
//                En este caso es necesario agregar la condición porque con archivos de mapeo se guarda el orden en el que se
//                se encuentran las compras del usuario. Si una compra no tiene un producto inactivo no se recuperará, pero su
//                orden no se modificará. Prueben imprimir la lista de compras y quitar la condición a ver que pasa.
                if (listaCompras.get(numeroCompra) != null)
                {
                    List<Producto> listaProductos = listaCompras.get(numeroCompra).getProductos();

                    for (int numeroProducto = 0; numeroProducto < listaProductos.size(); numeroProducto++)
                    {
                        Producto producto = listaProductos.get(numeroProducto);

                        System.out.println("\t" + producto.getNombre());
                    }
                }
            }
        }
    }
    
    private void mostrarCompras()
    {
        UsuariosDAO usuario=new UsuariosDAO();
        usuario.mostrarCompras();
    }
    
    /**Actualizamos usuarios  mediante una consulta masiva 
     
     Actualizamos el nombre de todos los usuarios a "Buen Comprador" si es mayor que el importe
     que pasamos por parametros**/
    
    private void actualizarNombreUsuario(long id,String nombre)
    {
        UsuariosDAO usuario=new UsuariosDAO();
        usuario.actualizarUsuarioConsulta(id,nombre);
    }
    
    private void actualizarNombreUsuarioPorIdDireccion(long id,String nombre)
    {
        UsuariosDAO usuario=new UsuariosDAO();
        usuario.actualizarNombreUsuarioPorIdDireccion(id,nombre);
        
    }
    
    private void usuariosComprasParametro(long numCompras)
    {
        UsuariosDAO usuario=new UsuariosDAO();
        usuario.usuariosComprasParametro(numCompras);
    }
    
    private void usuariosComprasProductosParametro(long numCompras)
    {
        UsuariosDAO usuario=new UsuariosDAO();
        usuario.usuariosComprasProductosParametro(numCompras);
    }
    
    private void usuariosComprasProductosNombre(String nombreProduc)
    {
        UsuariosDAO usuario=new UsuariosDAO();
        usuario.usuariosComprasProductosNombre(nombreProduc);
    }
    
   
}
