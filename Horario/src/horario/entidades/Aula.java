/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.entidades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author andres
 */
public class Aula {
  
  private int numero;
  private ExperienciaEducativa [] experiencias;
  
  public Aula () {}
  
  public Aula (int numero) {
    this.numero = numero;
    experiencias = new ExperienciaEducativa[20];
  }
  
  public void setNumero (int numero) {
    this.numero = numero;
  }
  
  public int getNumero () {
    return numero;
  }
  
  public ExperienciaEducativa getExperienciasEducativas (int indice) {
    return experiencias [indice];
  }
  
  public void setExperienciasEducativas (ExperienciaEducativa experiencia, int indice) {
    experiencias[indice] =  experiencia;
  }
}
