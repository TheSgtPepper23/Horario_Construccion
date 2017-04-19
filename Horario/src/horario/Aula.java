/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author andres
 */
public class Aula {
  
  private IntegerProperty numero;
  private ExperienciaEducativa [] experiencias;
  
  public Aula () {}
  
  public Aula (Integer numero) {
    this.numero = new SimpleIntegerProperty(numero);
    experiencias = new ExperienciaEducativa[20];
  }
  
  public void setNumero (Integer numero) {
    this.numero = new SimpleIntegerProperty(numero);
  }
  
  public Integer getNumero () {
    return numero.get();
  }
  
  public ExperienciaEducativa getExperienciasEducativas (int indice) {
    return experiencias [indice];
  }
  
  public void setExperienciasEducativas (ExperienciaEducativa experiencia, int indice) {
    experiencias[indice] =  experiencia;
  }
}
