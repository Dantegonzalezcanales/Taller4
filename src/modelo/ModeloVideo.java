package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import sql.Conexion;

public class ModeloVideo extends Conexion {
    
    public ModeloVideo(){
        
    }
    
    //Método para agregar el dato
    public boolean agregaDato(int recibocodigo,String recibonombre,String recibocategoria,String reciboformato,int reciboprecio){
     //Se arma la consulta
    String q=" INSERT INTO videobuster.pelicula(codigo,nombre,categoria,formato,precio) " 
            + "VALUES ( '" + recibocodigo + "','" + recibonombre + "','" + recibocategoria + "','" + reciboformato + "','" + reciboprecio + "') ";
    //se ejecuta la consulta
    try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
    }
    //Metodo para listar producto
    public DefaultTableModel ListadoProducto(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Código","Nombre","Categoria","Formato","Precio"};
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM pelicula");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][5];
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM pelicula");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "categoria" );
                data[i][3] = res.getString( "formato" );
                data[i][4] = res.getString( "precio" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
     //Modificar producto seleccionado
    public boolean modificaProducto( int codigo,String nombre, String categoria , String formato, int precio ){
        String q= "UPDATE videobuster.pelicula SET nombre='"+nombre+"', categoria='"+categoria+"' , formato='"+formato+"', precio='"+precio+"' "
                + " WHERE codigo='"+codigo+"' ";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return false;
    }
    
     //Elimina producto
    public boolean eliminarProducto(int codigo){
        boolean res=false;
        String q = " DELETE FROM videobuster.pelicula WHERE codigo=" + codigo + " " ;
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }

}
