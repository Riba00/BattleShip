import java.util.Scanner;

public class Barquet {
    static String[][] tableroUsuari = new String[10][10];
    static String[][] tableroSolucio = new String[10][10];

    public static void main(String[] args) {

        crearTablerosEnAigua();
        //mostrarTableroUsuari();
        generarBarcos();
        //mostrarTableroSolucio();
        jugar();
    }

    public static void crearTablerosEnAigua() {

        //TABLERO MOSTRAR A L'USUARI
        for (int i = 0; i < tableroUsuari.length; i++) {
            for (int j = 0; j < tableroUsuari[i].length; j++) {
                tableroUsuari[i][j] = "\uD83C\uDF0A";
            }
        }

        //TABLERO SOLUCIÓ
        for (int i = 0; i < tableroSolucio.length; i++) {
            for (int j = 0; j < tableroSolucio[i].length; j++) {
                tableroSolucio[i][j] = "\uD83C\uDF0A";
            }
        }
    }

    public static void mostrarTableroUsuari() {
        System.out.println("  1  2  3  4  5  6  7  8  9  10");
        char eixY = 'A';
        for (int i = 0; i < tableroUsuari.length; i++) {
            System.out.print(eixY + " ");
            for (int j = 0; j < tableroUsuari[i].length; j++) {
                System.out.print(tableroUsuari[i][j] + " ");
            }
            System.out.println();
            eixY++;
        }
    }

    public static void generarBarcos() {
        generarBarco(6);
        generarBarco(5);
        generarBarco(3);
        generarBarco(3);
        generarBarco(2);
    }

    public static void generarBarco(int midaBarco) {
        boolean loBarcoEntra = false;
        int direccio;
        int capFila;
        int capColumna;

        String barco = "\uD83D\uDEA4";

        do {
            direccio = (int) (Math.random() * 2);

            //if direccio == 1 -> Vertical
            //else -> Horitzontal

            if (direccio == 1) {
                capFila = (int) (Math.random() * ((midaBarco - 1)) + 0);
                capColumna = (int) (Math.random() * (10) + 0);
                //System.out.println(capColumna);
                //System.out.println(capFila);
                //GENERAR BARCO
                for (int i = capFila; i <= capFila + (midaBarco - 1); i++) {
                    if (tableroSolucio[i][capColumna] == barco) {
                        loBarcoEntra = false;
                        break;
                    } else {
                        loBarcoEntra = true;
                    }
                }
            } else {
                capFila = (int) (Math.random() * (10) + 0);
                capColumna = (int) (Math.random() * ((midaBarco - 1)) + 0);

                for (int i = capColumna; i <= capColumna + (midaBarco - 1); i++) {
                    if (tableroSolucio[capFila][i] == barco) {
                        loBarcoEntra = false;
                        break;
                    } else {
                        loBarcoEntra = true;
                    }
                }
            }
        } while (!loBarcoEntra);

        //PINTAR LOS BARCOS
        if (direccio == 1) {
            for (int i = capFila; i <= capFila + (midaBarco - 1); i++) {
                tableroSolucio[i][capColumna] = barco;
            }

        } else {
            for (int i = capColumna; i <= capColumna + (midaBarco - 1); i++) {
                tableroSolucio[capFila][i] = barco;
            }
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

    public static void jugar() {

        Scanner teclat = new Scanner(System.in);

        String coordenadaUsuari;
        String numeroColumnaString;

        String barco = "\uD83D\uDEA4";
        String tocat = "\uD83C\uDFAF";
        String fallat = "\uD83D\uDC4E\uD83C\uDFFC";
        boolean partidaAcabada = false;
        boolean seguirJugant;
        int numeroFila;
        int numeroColumna;
        int intents = 50;
        String respostaSeguirJugant;

        System.out.println();
        //PARTIDA
        do {
            seguirJugant = false;
            do {

                do {
                    mostrarTableroUsuari();
                    System.out.println("Intents restants: " + intents);
                    System.out.print("Introdueix la coordenada a disparar (Ex: D4): ");
                    coordenadaUsuari = teclat.nextLine();
                    intents--;

                    if (!coordenadaCorrecta(coordenadaUsuari)) {
                        System.out.println("Coordenada incorrecta company");
                        System.out.println();
                    }
                    if (intents == 0) {
                        System.out.println("Has esgotat tots els intents");
                        partidaAcabada = true;
                        break;
                    }
                } while (!coordenadaCorrecta(coordenadaUsuari));

                numeroColumnaString = (coordenadaUsuari.substring(1));
                numeroFila = (coordenadaUsuari.toUpperCase().charAt(0) - 65);
                numeroColumna = (Integer.parseInt(numeroColumnaString) - 1);

                if (tableroSolucio[numeroFila][numeroColumna] == barco || tableroSolucio[numeroFila][numeroColumna] == tocat) {
                    tableroUsuari[numeroFila][numeroColumna] = tocat;
                    tableroSolucio[numeroFila][numeroColumna] = tocat;
                } else {
                    tableroUsuari[numeroFila][numeroColumna] = fallat;
                    tableroSolucio[numeroFila][numeroColumna] = fallat;
                }
                if (barcosPetats()) {
                    System.out.println("!!!FELICITATS!!!");
                    System.out.println("HAS ENFONSAT TOTS ELS VAIXELLS!!!");
                    partidaAcabada = true;
                    break;
                }

                // mostrarTableroSolucio();

            } while (!partidaAcabada);

            do {
                System.out.println("Vols seguir jugant? (S) Si   (N) No");
                System.out.print("Resposta: ");
                respostaSeguirJugant = teclat.nextLine();
            } while (!respostaSeguirJugantCorrecta(respostaSeguirJugant));
            if (respostaSeguirJugant.charAt(0) == 'S'  || respostaSeguirJugant.charAt(0) == 's') {
                seguirJugant = true;
                crearTablerosEnAigua();
                generarBarcos();
            }
        } while (seguirJugant);
        System.out.println("Adeu, fins la proxima!!!");
    }

    public static boolean barcosPetats() {
        String barco = "\uD83D\uDEA4";
        for (int i = 0; i < tableroSolucio.length; i++) {
            for (int j = 0; j < tableroSolucio[i].length; j++) {
                if (tableroSolucio[i][j] == barco) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean coordenadaCorrecta(String coordenada) {

        if (coordenada.length() == 3) {
            if (coordenada.toUpperCase().charAt(0) >= 'A' && coordenada.toUpperCase().charAt(0) <= 'J') {
                if (coordenada.charAt(1) >= '1' && coordenada.charAt(1) <= '9')
                    if (coordenada.charAt(1) == '1' && coordenada.charAt(2) == '0') return true;
            }
        }
        if (coordenada.length() == 2) {
            if (coordenada.toUpperCase().charAt(0) >= 'A' && coordenada.toUpperCase().charAt(0) <= 'J') {
                if (coordenada.charAt(1) >= '1' && coordenada.charAt(1) <= '9') return true;
            }
        }
        return false;
    }

    public static boolean respostaSeguirJugantCorrecta(String resposta) {
        char primeraLletra = resposta.toUpperCase().charAt(0);
        if (primeraLletra == 'S' || primeraLletra == 'N') return true;
        return false;
    }
}
