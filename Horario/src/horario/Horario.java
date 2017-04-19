/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author andres
 */
public class Horario {
  private StringProperty carrera;
  private IntegerProperty semestre;
  private ExperienciaEducativa[] experiencias;
  
  public Horario() {}
  
  public Horario (String carrera, Integer semestre) {
    this.carrera = new SimpleStringProperty(carrera);
    this.semestre = new SimpleIntegerProperty(semestre);
    this.experiencias = new ExperienciaEducativa[10];
  }
  
  public void setCarrera (String carrera) {
    this.carrera = new SimpleStringProperty(carrera);
  }
  
  public StringProperty getCarrera () {
    return carrera;
  }
  
  public void setSemestre (Integer semestre) {
    this.semestre = new SimpleIntegerProperty(semestre);
  }
  
  public Integer getSemestre () {
    return semestre.get();
  }
  
  public void setExperienciasEducativas (ExperienciaEducativa experiencia, int indice) {
    experiencias [indice] = experiencia;
  }
}
