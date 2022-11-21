import java.util.Random;
import java.util.Scanner;
public class MagicWord {
    public static final String[] palabraInicial = {"pikachu", "charmander", "bulbasur", "squirtle"};
    public static boolean[] indices = new boolean[palabraInicial.length]; //evitar repeticiones
    public static final int maxIntentos = 5;

    public static String nuevaPalabra() {
        int i = 0;
        do {
           i = new Random().nextInt(palabraInicial.length);
        } while (indices[i] == true);
        indices[i] = true;
        return palabraInicial[i];
    }
    public static char leerLetra(Scanner in) {
        char l = ' ';
        do {
            System.out.print("Introduzca una letra: ");
            l = in.nextLine().toLowerCase().charAt(0);
        } while (!Character.isLetter(l));
        System.out.println();
        return l;
    }
    public static void mostrarEstadoPartida(char[] palabra, char[] fallos) {
        System.out.println("-- WORD GUESS --");

        System.out.print("Palabra: ");
        for (int i = 0; i < palabra.length; i++) {
            if ((int) palabra[i] == 0) {
                System.out.print("_ ");
            } else {
                System.out.print(palabra[i] + " ");
            }
        }
        System.out.print("\nFallos: ");
        for (int i = 0; i < fallos.length; i++) {
            if ((int) fallos[i] == 0) {
                System.out.print("_ ");
            } else {
                System.out.print(fallos[i] + " ");
            }
        }
        System.out.println();
    }
    public static boolean partida(Scanner in) {
        final String palabraSecreta = nuevaPalabra();         //palabra a adivinar
        char[] palabra = new char[palabraSecreta.length()]; //palabra que se muestra
        char[] letrasIntroducidas = new char[maxIntentos];
        int fallos = 0;
        char letra;
        boolean fin = false, letraCorrecta;

        do {
            letraCorrecta = false;
            mostrarEstadoPartida(palabra, letrasIntroducidas);
            System.out.println("Intentos: " + (maxIntentos - fallos));
            letra = leerLetra(in);
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra && palabra[i] != letra) {
                    palabra[i] = letra;
                    letraCorrecta = true; //cuenta como acierto
                }
            }
            if (letraCorrecta) {
                fin = palabraSecreta.equals(String.copyValueOf(palabra));
            } else {
                letrasIntroducidas[fallos] = letra;
                fallos++;
            }
        } while (!fin && fallos < maxIntentos);
        mostrarEstadoPartida(palabra, letrasIntroducidas);
        if (fin) {
            System.out.println("Acertaste!");
        } else {
            System.out.println("Has perdido!");
        }
        System.out.println(" La palabra secreta es: " + palabraSecreta + "\n");
        return fin;
    }
}
