package Paquetes;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        boolean estado = true;
        int opcion = 0;
        while (estado) {
            try {
                System.out.println("Elija una opcion");
                System.out.println("Que desea hacer: ");
                System.out.println("1. Agregar texto a un archivo");
                System.out.println("2. leer un atchivo");
                System.out.println("3. Salir");

                Scanner teclado = new Scanner(System.in);
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("El caracter ingresado no es numerico, intentelo nuevamente.");
            }
            switch (opcion) {
                case 1:
                    System.out.println(agregarTexto(recibirPalabra(), seleccionarArchivo(), recibirRuta()));
                    break;
                case 2:
                    System.out.println(leerArchivo(seleccionarArchivo()));
                    break;
                case 3:
                    estado = false;
                    break;

            }
        }
    }

    public static String agregarTexto(String texto, String ruta, String newruta) {//se ingre el texto a agregar, la ruta del archivo a editar
                                                                                  //y la ruta donde se va a crear el archivo nuevo
        try {
            copiarArchivo(ruta, newruta);

            FileWriter arch = new FileWriter(newruta, true);
            PrintWriter escribir = new PrintWriter(arch);
            escribir.println();
            escribir.print(texto);
            escribir.close();
        } catch (IOException e) {
            System.out.println("Error al agregar texto al archivo");
        }

        return leerArchivo(newruta);
    }

    public static String leerArchivo(String ruta) {//lee el archivo de la ruta especificada

        Path archivo = Paths.get(ruta);
        String texto = "";
        try {
            texto = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
        return texto;
    }

    public static void copiarArchivo(String ruta, String newruta) { //copia un archivo

        Path archivo = Paths.get(ruta);

        Path newArchivo = Paths.get(newruta);
        try {
            Files.copy(archivo, newArchivo, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser copiado");
        }
    }

    public static String seleccionarArchivo() { //selecciona un archivo
        String ruta = "";
        try {
            JFileChooser j = new JFileChooser();
            j.showOpenDialog(j);
            ruta = j.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Error al selecionar archivo");
        }
        return ruta;
    }

    public static String recibirPalabra() { // recibe un texto y lo valida
        String num = " ";
        boolean estado = true;

        while (estado) {

            try {
                System.out.println("Ingrese el texto que desea agregar");
                Scanner sc = new Scanner(System.in);

                num = sc.nextLine();
                estado = false;

            } catch (InputMismatchException e) {
                System.out.println("El caracter ingresado no es una palabra, intentelo nuevamente.");
                estado = true;
            }

        }
        return num;
    }

    public static String recibirRuta() { //recibe una ruta

        String ruta = JOptionPane.showInputDialog("Escriba la ruta del archivo");

        return ruta;
    }
}
