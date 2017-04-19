/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author andres
 */
public class Profesor {
  
  private StringProperty nombre;
  private StringProperty apellidoPaterno;
  private StringProperty apellidoMaterno;
  private StringProperty correoE;
  private IntegerProperty telefono;
  private IntegerProperty numeroDePersonal;
  private ExperienciaEducativa[] experiencias;
  
  public Profesor (){}
  
  public Profesor (String nombre, String apellidoPaterno, String apellidoMaterno, String correoE,
      Integer telefono, Integer numeroDePersonal) {
    this.nombre = new SimpleStringProperty(nombre);
    this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
    this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
    this.correoE = new SimpleStringProperty(correoE);
    this.telefono = new SimpleIntegerProperty (telefono);
    this.numeroDePersonal = new SimpleIntegerProperty(numeroDePersonal);
    this.experiencias = new ExperienciaEducativa[4];
  }
  
  public void setNombre (String nombre) {
    this.nombre = new SimpleStringProperty(nombre);
  }
  
  public StringProperty getNombre () {
    return nombre;
  }
  
  public void setApellidoPaterno (String apellidoPaterno) {
    this.apellidoPaterno = new SimpleStringProperty(apellidoPaterno);
  }
  
  public StringProperty getApellidoPaterno () {
    return apellidoPaterno;
  }
  
  public void setApellidoMaterno (String apellidoMaterno) {
    this.apellidoMaterno = new SimpleStringProperty(apellidoMaterno);
  }
  
  public StringProperty getApellidoMaterno () {
    return apellidoMaterno;
  }
  
  public void setCorreoE (String correoE) {
    this.correoE = new SimpleStringProperty(correoE);
  }
  
  public StringProperty getCorreoE () {
    return correoE;
  }
  
  public void setTelefono (Integer telefono) {
    this.telefono = new SimpleIntegerProperty(telefono);
  }
  
  public Integer getTelefono () {
    return telefono.get();
  }
  
  public void setNumeroDePersonal (Integer numeroDePersonal) {
    this.numeroDePersonal = new SimpleIntegerProperty (numeroDePersonal);
  }
  
  public Integer getNumeroDePersonal () {
    return numeroDePersonal.get();
  }
  
  public ExperienciaEducativa getExperienciasEducativas (int indice) {
    return experiencias [indice];
  }
  
  public void setExperienciasEducativas (ExperienciaEducativa experiencia, int indice) {
    experiencias[indice] =  experiencia;
  }
}
