/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.cli;

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
  
  /**
   * Lee una cadena del usuario y la devuelve
   * @return Cadena leida del usuario
   */
  public String leerString (){
    return sc.nextLine();
  }
  
  /**
   * Lee un número entero y limpia el buffer del Scanner para poder leer cadenas
   * @return El número entero que se leyó
   */
  public int leerEntero () {
    int entero;
    entero = sc.nextInt();
    sc.nextLine();
    return entero;
  }
  
  /**
   * Lee un número doble y limpia el buffer del Scanner para poder leer cadenas
   * @return El número doble leido del ususario
   */
  public double leerDouble () {
    double doble;
    doble = sc.nextDouble();
    sc.nextLine();
    return doble;
  }
  
  
  
}
