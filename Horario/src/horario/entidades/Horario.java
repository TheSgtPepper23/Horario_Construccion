/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.entidades;

/**
 *
 * @author andres
 */
public class Horario {
  private String carrera;
  private int semestre;
  private ExperienciaEducativa[] experiencias;
  
  public Horario() {}
  
  public Horario (String carrera, int semestre) {
    this.carrera = carrera;
    this.semestre = semestre;
    this.experiencias = new ExperienciaEducativa[10];
  }
  
  public void setCarrera (String carrera) {
    this.carrera = carrera;
  }
  
  public String getCarrera () {
    return carrera;
  }
  
  public void setSemestre (int semestre) {
    this.semestre = semestre;
  }
  
  public int getSemestre () {
    return semestre;
  }
  
  public void setExperienciasEducativas (ExperienciaEducativa experiencia, int indice) {
    experiencias [indice] = experiencia;
  }
}
