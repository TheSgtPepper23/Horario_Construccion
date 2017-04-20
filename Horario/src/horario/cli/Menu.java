/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario.cli;

/**
 *
 * Clase que se encarga de la interacción básica con el usuario, contiene métodos para mostrar un 
 * menú y leer la opción que elijan
 * @author José Andrés Domínguez González
 */
public class Menu {
  private Teclado teclado;
  private Cli cli;
  
  public Menu () {
    teclado  = new Teclado();
    cli = new Cli();
  }
  
  /**
   * 
   * Muestra todas las opciones disponibles para el usuario 
   */
  public void mostrar () {
    System.out.println("\t\tBienvenido a su horario\n");
    System.out.println("OPCIONES\n");
    System.out.println("1.- Mostrar horario\n2.- Agregar horario\n3.- Agregar profesor" +
        "\n4.- Agregar experiencia educativa\n5.- Agregar aula\n6.- Mostrar experiencias educativas"
        +"\n7.- Mostrar profesores\n0.- Salir");
  }
  
  /**
   * Muestra un prompt y lee la opción del usuario
   * @return El número que haya seleccionado el usuario correspondiente al menú que se muestra en el
   * método mostrar()
   */
  public int leerOpcion () {
    System.out.println("¿Qué acción desea realizar?");
    return teclado.leerEntero();
  }
  
  /**
   * Recibe un parámetro opción y ejecuta la claúsula correspondiente en un switch
   * @param opcion valor introducido por el usuario que corresponde con un menú visual
   * @return El mismo número que se ingresó, se utiliza para poder "ciclar" el menú
   */
  public int realizarOpcion (int opcion) {
    switch (opcion) {
      case 1 :
        break;
      case 2:
        cli.agregarHorario();
        break;
      case 3:
        cli.agregarProfe();
        break;
      case 4:
        cli.agregarEE();
        break;
      case 5:
        cli.agregarAula();
        break;
      case 6:
        break;
      case 7:
        cli.mostrarProfesores();
        break;
      case 0:
        System.out.println("Gracias por utilizar este horario");
        break;
    }
    return opcion;
  }
}
