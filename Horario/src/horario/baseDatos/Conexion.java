/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author andres
 */
public class Conexion {
    private Connection conn;
    public String username;
    public String password;
    private static Conexion connect;
           
    public Conexion(String username, String password) throws ClassNotFoundException, SQLException {
      Class.forName("com.mysql.jdbc.Driver");
      this.username = username;
      this.password = password;
      conn = DriverManager.getConnection ("jdbc:mysql://localhost/Horario",username,password);
      connect = this;
    } 
    
    public Conexion () throws ClassNotFoundException, SQLException {
      Class.forName("com.mysql.jdbc.Driver");
      username = "root";
      password = "An6248322";
      conn = DriverManager.getConnection("jdbc:mysql://localhost/Horario",username,password);
      connect = this;
    }
    
    public String getUsername() {
      return username;
    }
    
    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }
    
    public void setPassword(String password) {
      this.password = password;
    }
    
    public Connection connection() {
      try {    	       
        return conn;
      }
      finally {}
    }
    
    public void close() {
      try {
        conn.close();
      }
      catch (SQLException e)  {
        System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
      }    	   
    }

	public static Conexion getConnect() {
      return connect;
	}

	public static void setConnect(Conexion connect) {
      Conexion.connect = connect;
	}
}
