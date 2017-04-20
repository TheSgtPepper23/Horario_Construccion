/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.cli;

/**
 *
 * @author andres
 */
public class Menu {
  private Teclado teclado;
  private Cli cli;
  
  public Menu () {
    teclado  = new Teclado();
    cli = new Cli();
  }
  
  public void mostrar () {
    System.out.println("\t\tBienvenido a su horario\n");
    System.out.println("OPCIONES\n");
    System.out.println("1.- Mostrar horario\n2.- Agregar horario\n3.- Agregar profesor" +
        "\n4.- Agregar experiencia educativa\n5.- Mostrar materias\n6.- Mostrar profesores" +
        "\n0.- Salir");
  }
  
  public int leerOpcion () {
    System.out.println("¿Qué acción desea realizar?");
    return teclado.leerEntero();
  }
  
  public int realizarOpcion (int opcion) {
    teclado.leerString();
    switch (opcion) {
      case 1 :
        break;
      case 2:
        break;
      case 3:
        cli.agregarProfe();
        break;
      case 4:
        cli.agregarEE();
        break;
      case 5:
        break;
      case 6:
        break;
      case 0:
        System.out.println("Gracias por utilizar este horario");
        break;
    }
    return opcion;
  }
}
