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
import horario.entidades.Profesor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Clase contenedora de los métodos ejecutados desde la clas Menu, además contiene métodos privados
 * que se utilizan como utilidad por los demás métodos
 * @author José Andrés Domínguez González
 */
public class Cli {
  private Profesor profesor;
  private Teclado teclado;
  private ExperienciaEducativa experiencia;
  private Aula aula;
  private Connection con;
  private String sQuery = "";
  private Statement s;
  private String op;
  private ResultSet rs;
  
  public Cli () {
    teclado = new Teclado();
  }
  
  /**
   * Crea un nuevo objeto Profesor con datos leidos del usuario y lo agrega a la base de datos
   */
  public void agregarProfe () {
    
    profesor = new Profesor();
    
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
    
    realizaUpdate(sQuery);
    
    System.out.println("El profesor se ha agregado con éxito\n");
  }
  
  /**
   * Crea un nuevo objeto ExperienciaEducativa con datos leidos del usuario y lo agrega a la
   * base de datos
   */
  public void agregarEE () {
      
    experiencia = new ExperienciaEducativa();
    
    int nrc;
        
    System.out.println("Nombre: ");
    experiencia.setNombre(teclado.leerString());
    System.out.println("NRC: ");
    nrc = teclado.leerEntero();
    experiencia.setNrc(nrc);
    System.out.println("Creditos: ");
    experiencia.setCreditos(teclado.leerEntero());
    
    sQuery = "INSERT INTO ExperienciaEducativa (nrc, nombre, creditos) values ("+ 
        experiencia.getNrc() + ", \"" + experiencia.getNombre() + "\", " + 
        experiencia.getCreditos()+");";
      
    realizaUpdate(sQuery);
    
    asignarProfesor(nrc);
    
    asignarAula(nrc);
    
    asignarDiaYHora(nrc);
    
  }
  
  /**
   * 
   * Relaciona el dia y la hora con la EE
   * @param nrc nrc de la EE
   */
  private void asignarDiaYHora (int nrc) {
    
    String horaInicio, horaFin, day;
    int dia;
    
    do{
      System.out.println("Agregue un día(s/n)");
      op = teclado.leerString();
      if (op.equals("s")) {
        mostrarDias();
        System.out.println("Escoja un día");
        dia = teclado.leerEntero();
        
        switch (dia) {
          case 1:
            day = "Lunes";
            break;
          case 2:
            day = "Martes";
            break;
          case 3:
            day = "Miércoles";
            break;
          case 4:
            day = "Jueves";
            break;
          case 5:
            day = "Viernes";
            break;
          default:
            day = null;
            break;
        }
        
        System.out.println("Ingresa la hora de inicio:");
        horaInicio = teclado.leerString();
        System.out.println("Ingresa la hora de salida:");
        horaFin = teclado.leerString();
        
        sQuery = "INSERT INTO Hora (horaInicio, horaFin, dia) values (\"" + horaInicio + "\", \"" +
            horaFin + "\", \"" + day + "\");";
        
        realizaUpdate(sQuery);
        
        sQuery = "SELECT * FROM ExperienciaEducativa_has_Hora where ExperienciaEducativa_nrc = "
            + nrc + ", AND Hora_idHora = " + obteneriDHora(day, horaInicio)+ ";";
        
         try {
           con = new Conexion().connection();
           s = con.createStatement();
           rs = s.executeQuery(sQuery);
           
           if (rs.next()) {
            sQuery = "INSERT INTO ExperienciaEducativa_has_hora (ExperienciaEducativa_nrc, Hora_idHora)"
                + " values ();";
             realizaUpdate(sQuery);
           } else {
             System.out.println("Lo sentimos esa hora ya está ocupada");
           }
         } catch (ClassNotFoundException | SQLException ex) {
          Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    } while (op.equals("s"));
  }
  
  /**
   * 
   * Regresa el id de un elemento guardado en la tabla hora
   * @param dia El nombre del día
   * @param inicio La hora de inicio
   * @return EL id de la hora
   */
  private int obteneriDHora (String dia, String inicio) {
    sQuery ="SELECT idHora FROM Hora where horaInicio = \"" + inicio + "\", AND dia = \"" + dia +"\";";
    
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      rs = s.executeQuery(sQuery);
      
      return rs.getInt("idHora");
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        con.close();
      } catch (SQLException ex) {
        Logger.getLogger(Cli.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return 0;
  }
  
  /**
   * 
   * Guarda la relación profesor-experiencia educativa en la base de datos
   * @param nrc nrc de la EE
   */
  private void asignarProfesor (int nrc) {
    int idProfesor;
    
    System.out.println("¿Desea ver la lista de profesores? (s/n)");
    if (teclado.leerString().equals("s")) {
      mostrarProfesores();
    }
    System.out.println("¿Desea agregar un nuevo profesor? (s/n)");
    if (teclado.leerString().equals("s")) {
      agregarProfe();
    }
    System.out.println("Profesor:");
    idProfesor = obtenerIDProfe(teclado.leerString());
    
    sQuery = "INSERT INTO ExperienciaEducativa_has_Profesor (ExperienciaEducativa_nrc, "
        + "Profesor_idProfesor)values(" + nrc + ", " + idProfesor + ");";
    
    realizaUpdate(sQuery);
  }
  
  /**
   * 
   * Guarda la relación aula-experiencia educativa en la base de datos
   * @param nrc nrc de la EE
   */
  private void asignarAula (int nrc) {
    String idAula;
    System.out.println("¿Desea ver la lista de salones? (s/n)");
    if (teclado.leerString().equals("s")) {
      mostrarAulas();
    }
    System.out.println("¿Desea agregar un nuevo salón? (s/n)");
    if (teclado.leerString().equals("s")) {
      agregarAula();
    }
    System.out.println("Aula: ");
    idAula = obtenerIDAula(teclado.leerString());
    
    sQuery = "INSERT INTO aula_EE (Aula_idAula, ExperienciaEducativa_nrc"
        + " values (" + idAula + ", " + nrc + ");";
    
    realizaUpdate(sQuery);
  }
  
  /**
   * 
   * Imprime los días de la semana 
   */
  private void mostrarDias () {
    System.out.println("1.- Lunes\n2.- Martes\n 3.- Miércoles\n4.- Jueves\n5.- Viernes");
  }
  
  /**
   * Realiza un update en la base de datos con las consulta que se le pasa
   * @param query Consulta en sql
   */
  private void realizaUpdate (String query) {
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      s.executeUpdate(query);
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
    
    System.out.println("Número:");
    aula.setNumero(teclado.leerString());
    
    sQuery = "INSERT INTO Aula (nombre) values(\"" + aula.getNumero() + "\");";
    
    realizaUpdate(sQuery);
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
        if (rs.getString("apellidoPaterno").equals(null)) {
          System.out.println("Nombre: "+ rs.getString("nombre") + " " + 
              rs.getString("apellidoPaterno"));
        }
        else {
          System.out.println("Nombre: "+ rs.getString("nombre") + " " + 
              rs.getString("apellidoPaterno") + " " + rs.getString("apellidoMaterno"));
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
  
  /**
   * 
   * Muestra los números de aulas que hay registrados
   */
  private void mostrarAulas () {
    sQuery = "SELECT nombre FROM Aula;";
    
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      rs = s.executeQuery(sQuery);
      
      while(rs!=null && rs.next())
      {
        System.out.println("Salón " + rs.getString("nombre"));
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
  
  /**
   * 
   * Regresa el id relacionado con el nombre del profesor
   * @param nombre Nombre del profesor
   * @return El id relacionado con el profesor
   */
  private int obtenerIDProfe (String nombre) {
    sQuery = "SELECT idProfesor FROM Profesor where nombre = \"" + nombre + "\";";
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      rs = s.executeQuery(sQuery);
      if (rs.next()) {
        return rs.getInt("idProfesor");
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
    return 0;
  }
  
  /**
   * 
   * Regresa el id relacionado con el nombre del aula
   * @param nombre Nombre del aula
   * @return El id relacionado con el aula
   */
  private String obtenerIDAula (String nombre) {
    sQuery = "SELECT idAula FROM Aula where nombre = \"" + nombre + "\";";
    try {
      con = new Conexion().connection();
      s = con.createStatement();
      rs = s.executeQuery(sQuery);
      if (rs.next()) {
        return rs.getString("idAula");
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
    return null;
  }
}

