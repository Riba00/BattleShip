import java.util.Scanner;

public class Barquet {

    static String[][] tablero = new String[10][10];
    static String[][] tableroSolucio = new String[10][10];

    public static void main(String[] args) {

        crearTablerosEnAigua();
        mostrarTableroUsuari();
        mostrarTableroSolucio();
        generarBarcos();
        jugar();

    }

    public static void crearTablerosEnAigua() {

        //TABLERO MOSTRAR A L'USUARI
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "\uD83C\uDF0A";
            }
        }

        //TABLERO SOLUCIÓ
        for (int i = 0; i < tableroSolucio.length; i++) {
            for (int j = 0; j < tableroSolucio[i].length; j++) {
                tableroSolucio[i][j] = "\uD83C\uDF0A";
            }
        }
    }

    //CREAR BARCOS
    public static void generarBarcos() {
        generarBarco(6);
        generarBarco(5);
        generarBarco(3);
        generarBarco(3);
        generarBarco(2);
    }

    public static void generarBarco(int midaBarco) {
        //GENERAR UN RANDOM PA SABER COM VA
        boolean loBarcoEntra = false;
        int direccio;
        int capFila;
        int capColumna;
        do {
            direccio = (int) (Math.random() * 2);
            //System.out.println(direccio);

            //if direccio == 1 -> Vertical
            //else -> Horitzontal

            if (direccio == 1) {
                capFila = (int) (Math.random() * ((midaBarco - 1) - 0) + 0);
                capColumna = (int) (Math.random() * (10 - 0) + 0);
                //System.out.println(capColumna);
                //System.out.println(capFila);
                //GENERAR BARCO
                for (int i = capFila; i <= capFila + (midaBarco - 1); i++) {
                    if (tablero[i][capColumna] == "\uD83D\uDEA4") {
                        loBarcoEntra = false;
                        break;
                    } else {
                        loBarcoEntra = true;
                        //   tablero[i][capColumna] = "\uD83D\uDEA4";
                    }
                }
            } else {
                capFila = (int) (Math.random() * (10 - 0) + 0);
                capColumna = (int) (Math.random() * ((midaBarco - 1) - 0) + 0);

                for (int i = capColumna; i <= capColumna + (midaBarco - 1); i++) {
                    if (tablero[capFila][i] == "\uD83D\uDEA4") {
                        loBarcoEntra = false;
                        break;
                    } else {
                        loBarcoEntra = true;
                        // tablero[capFila][i] = "\uD83D\uDEA4";
                    }
                }
            }
        } while (!loBarcoEntra);
        //PINTAR LOS BARCOS
        if (direccio == 1) {
            for (int i = capFila; i <= capFila + (midaBarco - 1); i++) {
                tablero[i][capColumna] = "\uD83D\uDEA4";
            }

        } else {
            for (int i = capColumna; i <= capColumna + (midaBarco - 1); i++) {
                tablero[capFila][i] = "\uD83D\uDEA4";
            }
        }

    }

    public static void jugar() {

        Scanner teclat = new Scanner(System.in);

        String casellaUsuari;
        String numeroColumnaString;
        boolean partidaAcabada = false;
        int numeroFila;
        int numeroColumna;
        System.out.println();
        do {
            System.out.print("Introdueix la coordenada a disparar (Ex: D4): ");
            casellaUsuari = teclat.nextLine();
            numeroFila = ((int) casellaUsuari.charAt(0) - 65);
            numeroColumnaString = casellaUsuari.substring(1);
            numeroColumna = (Integer.valueOf(numeroColumnaString) - 1);

            System.out.println(numeroFila);
            System.out.println(numeroColumna);

            //MIRAR SI HI HA UN BARCO

            //PINTAR QUE HA TOCAT EL BARCO
            //RESTAR MISSILS


        } while (!partidaAcabada);

    }


    public static void mostrarTableroUsuari() {
        System.out.println("  1  2  3  4  5  6  7  8  9  10");
        char eixY = 'A';
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(eixY + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
            eixY++;
        }
    }

    public static void mostrarTableroSolucio() {
        System.out.println("  1  2  3  4  5  6  7  8  9  10");
        char eixY = 'A';
        for (int i = 0; i < tableroSolucio.length; i++) {
            System.out.print(eixY + " ");
            for (int j = 0; j < tableroSolucio[i].length; j++) {
                System.out.print(tableroSolucio[i][j] + " ");
            }
            System.out.println();
            eixY++;
        }
    }



    //JUGAR PARTIDA

    //CONTINUAR O PARAR


}
