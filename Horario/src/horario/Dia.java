/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author andres
 */
public class Dia {
  private StringProperty nombre;
  private ExperienciaEducativa[] experiencias;
  private StringProperty horaInicio;
  private StringProperty horaFin;
  
  public Dia () {}
  
  public Dia (String dia, String horaInicio, String horaFin) {
    this.nombre = new SimpleStringProperty(dia);
    this.horaInicio = new SimpleStringProperty(horaInicio);
    this.horaFin = new SimpleStringProperty(horaFin);
    this.experiencias = new ExperienciaEducativa[7];
  }
  
  public void setNombre (String nombre) {
    this.nombre = new SimpleStringProperty(nombre);
  }
  
  public StringProperty getNombre () {
    return nombre;
  }
  
  public void setExperienciaEducativa (ExperienciaEducativa experiencia, int indice) {
    experiencias [indice] = experiencia;
  }
  
  public ExperienciaEducativa getExperienciaEducativa (int indice) {
    return experiencias[indice];
  }
  
  public void setHoraInicio (String horaInicio) {
    this.horaInicio = new SimpleStringProperty(horaInicio);
  }
  
  public StringProperty getHoraInicio () {
    return horaInicio;
  }
  
  public void sethoraFin (String horaFin) {
    this.horaFin = new SimpleStringProperty(horaFin);
  }
  
  public StringProperty getHoraFin () {
    return horaFin;
  }
}
