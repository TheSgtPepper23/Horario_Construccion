/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import java.util.Scanner;

/**
 * Clase encargada de las entradas de texto
 * @author José Andrés Domínguez González
 */
public class Teclado {
  private Scanner sc;

  public Teclado() {
    sc = new Scanner(System.in);
  }
  
  public String leerString (){
    return sc.nextLine();
  }
  
  public int leerEntero () {
    return sc.nextInt();
  }
  
  public double leerDouble () {
    return sc.nextDouble();
  }
  
  
  
}
