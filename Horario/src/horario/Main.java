/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import horario.cli.Menu;

/**
 *
 * @author José Andrés Domínguez González
 */
public class Main {
  
  private static Menu menu;
  
  public static void main (String [] args) {
    menu = new Menu();
    int opcion;
    
    do {
      menu.mostrar();
      opcion = menu.leerOpcion();
      menu.realizarOpcion(opcion);
    } while (opcion != 0);
  }
}
