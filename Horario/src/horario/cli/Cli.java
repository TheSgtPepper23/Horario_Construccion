/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.cli;

import java.sql.Connection;
import horario.baseDatos.Conexion;
import horario.entidades.Aula;
import horario.entidades.ExperienciaEducativa;
import horario.entidades.Horario;
import horario.entidades.Profesor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Andrés Domínguez González
 */
public class Cli {
  private Profesor profesor;
  private Horario horario;
  private Teclado teclado;
  private ExperienciaEducativa experiencia;
  private Aula aula;
  private Connection con;
  private String sQuery = "";
  private Statement s;
  private ResultSet rs;
  
  /**
   * Crea un nuevo objeto Profesor con datos leidos del usuario y lo agrega a la base de datos
   */
  public void agregarProfe () {
    
    profesor = new Profesor();
    teclado = new Teclado();
    
    System.out.println("Nombre:");
    profesor.setNombre(teclado.leerString());
    System.out.println("Apellido paterno:");
    profesor.setApellidoPaterno(teclado.leerString());
    System.out.println("Apellido materno: \n\"En caso de que no exista escribir 0\"");
    profesor.setApellidoMaterno(teclado.leerString());
    System.out.println("Correo electrónico:q");
    profesor.setCorreoE(teclado.leerString());
    System.out.println("Teléfono: \n\"En caso de que no exista escribir 0\"");
    profesor.setTelefono(teclado.leerEntero());
    
    sQuery = "INSERT INTO Profesor (nombre, apellidoPaterno, apellidoMaterno, correoE, telefono)"+
      "values (\""+ profesor.getNombre() +"\", \"" + profesor.getApellidoPaterno() +"\", \"" + 
      profesor.getApellidoMaterno() + "\", \"" + profesor.getCorreoE() + "\", " + 
      profesor.getTelefono()+");";
    
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      s.executeUpdate(sQuery);
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    System.out.println("El profesor se ha agregado con éxito\n");
  }
  
  /**
   * Crea un nuevo objeto ExperienciaEducativa con datos leidos del usuario y lo agrega a la
   * base de datos
   */
  public void agregarEE () {
      
    experiencia = new ExperienciaEducativa();
    teclado = new Teclado();
    int idProfesor;
        
    System.out.println("Nombre: ");
    experiencia.setNombre(teclado.leerString());
    System.out.println("NRC: ");
    experiencia.setNrc(teclado.leerEntero());
    System.out.println("Creditos: ");
    experiencia.setCreditos(teclado.leerEntero());
    System.out.println("Desea ver la lista de profesores (s/n)");
    if (teclado.leerString().equals("s")) {
      mostrarProfesores();
    }
    System.out.println("Desea agregar un nuevo profesor (s/n)");
    if (teclado.leerString().equals("s")) {
      agregarProfe();
    }
    System.out.println("Profesor:");
    sQuery = "SELECT idProfesor FROM Profesor where nombre = \"" + teclado.leerString() + "\";";
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      rs = s.executeQuery(sQuery);
      if (rs.next()) {
        idProfesor = rs.getInt("idProfesor");
      }
    } catch (SQLException | ClassNotFoundException e) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      try {
        con.close();
      } catch (SQLException ex1) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
      
    sQuery = "INSERT INTO ExperienciaEducativa (nrc, nombre, creditos) values ("+ 
        experiencia.getNrc() + ", \"" + experiencia.getNombre() + "\", " + 
        experiencia.getCreditos()+");";
      
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      s.executeUpdate(sQuery);
    } catch (SQLException | ClassNotFoundException e) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      try {
        con.close();
      } catch (SQLException ex1) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
  }
  
  /**
   * Metodo que agrega un objeto aula con datos obtenidos del usuario y lo guarda en la 
   * base de datos
   */
  public void agregarAula () {
    try {
      con = new Conexion().connection();
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    aula = new Aula();
    teclado = new Teclado();
    
    System.out.println("Número:");
    aula.setNumero(teclado.leerEntero());
    
    sQuery = "INSERT INTO Aula (numero) values(" + aula.getNumero() + ");";
    
    try {
      s = con.createStatement();
      s.executeUpdate(sQuery);
    } catch (SQLException e) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      try {
        con.close();
      } catch (SQLException ex1) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
  }
  
  /**
   * Agrega un objeto del tipo Horario con datos del usuario y lo guarda en la base de datos
   */
  public void agregarHorario () {
    
    horario = new Horario();
    teclado = new Teclado();
    
    System.out.println("Semestre:");
    horario.setSemestre(teclado.leerEntero());
    System.out.println("Carrera");
    teclado.leerString();
    horario.setCarrera(teclado.leerString());  
    
    sQuery = "INSERT INTO Horario (semestre, carrera ) values (" + horario.getSemestre() + ", \"" + 
        horario.getCarrera() + "\");";
    
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      s.executeUpdate(sQuery);
    } catch (SQLException | ClassNotFoundException e) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      try {
        con.close();
      } catch (SQLException ex1) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
  }
  
  /**
   * Hace una consulta con la base de datos para mostrar todos los profesores, además le da un 
   * formato
   */
  public void mostrarProfesores () {
    
    sQuery = "SELECT nombre, apellidoPaterno, apellidoMaterno, correoE, telefono FROM Profesor;";
    
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      rs = s.executeQuery(sQuery);
      
      while(rs!=null && rs.next())
      {
        System.out.println("Nombre: "+ rs.getString("nombre"));
        System.out.println("Apellido paterno: " + rs.getString("apellidoPaterno"));
        if (rs.getString("apellidoPaterno").equals(null)) {
          System.out.println("Apellido materno: No existe");
        }
        else {
          System.out.println("Apellido materno: " + rs.getString("apellidoMaterno"));
        }
        System.out.println("Correo Electrónico: " + rs.getString("correoE"));
        if (rs.getInt("telefono") == 0) {
          System.out.println("Teléfono: No existe");
        }
        else {
          System.out.println("Teléfono: " + rs.getInt("telefono"));
        }
        System.out.println("____________________________________________________");
      }
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
}

