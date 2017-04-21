/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.entidades;
/**
 *
 * @author José Andrés Domínguez González
 */
public class Profesor {
  
  private String nombre;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private String correoE;
  private int telefono;
  private ExperienciaEducativa[] experiencias;
  
  public Profesor (){}
  
  public Profesor (String nombre, String apellidoPaterno, String apellidoMaterno, String correoE,
      Integer telefono, Integer numeroDePersonal) {
    this.nombre = nombre;
    this.apellidoPaterno = apellidoMaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.correoE = correoE;
    this.telefono = telefono;
    this.experiencias = new ExperienciaEducativa[4];
  }
  
  public void setNombre (String nombre) {
    this.nombre = nombre;
  }
  
  public String getNombre () {
    return nombre;
  }
  
  public void setApellidoPaterno (String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }
  
  public String getApellidoPaterno () {
    return apellidoPaterno;
  }
   /**
    * En caso de que el valor sea igual a "0" no fijará ningún apellido en el objeto
    * @param apellidoMaterno Apellido del profesor o "0" en caso de que no se desee ingresar
    */
  public void setApellidoMaterno (String apellidoMaterno) {
    if (!nombre.equals("0")) {
      this.apellidoMaterno = apellidoMaterno;
    }
    else {
      this.apellidoMaterno = null;
    }
  }
  
  public String getApellidoMaterno () {
    return apellidoMaterno;
  }
  
  public void setCorreoE (String correoE) {
    this.correoE = correoE;
  }
  
  public String getCorreoE () {
    return correoE;
  }
  
  /**
   * En caso de que el valor sea igual a "0" no fijará ningún teléfono en el objeto
   * @param telefono Teléfono del profesor o "0" en caso de que no se desee agregar
   */
  public void setTelefono (int telefono) {
    if (telefono != 0) {
      this.telefono = telefono;
    }
    else {
      this.telefono = 0;
    }
  }
  
  public int getTelefono () {
    return telefono;
  }
  
  public ExperienciaEducativa getExperienciasEducativas (int indice) {
    return experiencias [indice];
  }
  
  public void setExperienciasEducativas (ExperienciaEducativa experiencia, int indice) {
    experiencias[indice] =  experiencia;
  }
}
