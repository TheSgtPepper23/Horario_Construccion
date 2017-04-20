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
    try {
      con = new Conexion().connection();
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    }
    
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
      s = con.createStatement();
      s.executeUpdate(sQuery);
    } catch (SQLException ex) {
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
    try {
      con = new Conexion().connection();
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    experiencia = new ExperienciaEducativa();
    teclado = new Teclado();
    
    System.out.println("Nombre: ");
    experiencia.setNombre(teclado.leerString());
    System.out.println("NRC: ");
    experiencia.setNrc(teclado.leerEntero());
    System.out.println("Creditos: ");
    experiencia.setCreditos(teclado.leerEntero());
      
    sQuery = "INSERT INTO ExperienciaEducativa (nrc, nombre, creditos) values ("+ 
        experiencia.getNrc() + ", \"" + experiencia.getNombre() + "\", " + 
        experiencia.getCreditos()+");";
      
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
  
  public void agregarAula () {
    try {
      con = new Conexion().connection();
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    aula = new Aula();
  }
}

