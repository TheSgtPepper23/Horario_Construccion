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
public class ExperienciaEducativa {
  private IntegerProperty nrc;
  private StringProperty nombre;
  private IntegerProperty creditos;
  private Aula[] aulas;
  private Dia[] dias;
  private Horario[] horarios;
  private Profesor[] profesores;
  
  public ExperienciaEducativa(){}
  
  public ExperienciaEducativa (Integer nrc, String nombre, Integer creditos) {
    this.nrc = new SimpleIntegerProperty(nrc);
    this.nombre = new SimpleStringProperty(nombre);
    this.creditos = new SimpleIntegerProperty(creditos);
    this.aulas = new Aula[10];
    this.dias = new Dia[5];
    this.horarios = new Horario [50];
    this.profesores = new Profesor[10];
  }
  
  public void setNrc (Integer nrc) {
    this.nrc = new SimpleIntegerProperty(nrc);
  }
  
  public Integer getNrc () {
    return nrc.get();
  }
  
  public void setNombre (String nombre) {
    this.nombre = new SimpleStringProperty(nombre);
  }
  
  public StringProperty getNombre () {
    return nombre;
  }
  
  public void setCreditos (Integer creditos) {
    this.creditos = new SimpleIntegerProperty(creditos);
  }
  
  public Integer getCreditos () {
    return creditos.get();
  }
  
  public void setAulas (Aula aula, int indice) {
    aulas[indice] = aula;
  }
  
  public Aula getAulas (int indice) {
    return aulas[indice];
  }
  
  public void setDias (Dia dia, int indice) {
    dias[indice] = dia;
  }
  
  public Dia getDias (int indice) {
    return dias[indice];
  }
  
  public void setHorarios (Horario horario, int indice) {
    horarios[indice] = horario;
  }
  
  public Horario getHorarios (int indice) {
    return horarios[indice];
  }
  
  public void setProferores (Profesor profesor, int indice) {
    profesores[indice] = profesor;
  }
  
  public Profesor getProfesores (int indice) {
    return profesores [indice];
  }
}
