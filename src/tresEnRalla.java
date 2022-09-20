import java.util.Scanner;

public class tresEnRalla {
    static String[][] tablero = new String[3][3];

    public static void main(String[] args) {
        crearTablero();
        //jugar();
        jugar();
    }

    public static void crearTablero() {

        //TABLERO
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "-";
            }
        }
    }

    public static void mostrarTablero() {
        System.out.println("  1  2  3");
        char eixY = 'A';
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(eixY + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println();
            eixY++;
        }
    }

    public static void jugarPreguntantCadaJugador() {

        Scanner teclat = new Scanner(System.in);

        String jugador1Coordenada;
        String jugador2Coordenada;

        String casellaJugador1 = "X";
        String casellaJugador2 = "O";

        int numeroFila = 0;
        int numeroColumna = 0;

        boolean partidaAcabada = false;

        int saberTurno = 0;
        //Si es 2,4,6 -> Jugador 1
        //Si es 1,3,5 -> Jugador 2

        do {
            //JUGADOR 1
            if (saberTurno % 2 == 0) {
                System.out.print("Jugador 1 : ");
                jugador1Coordenada = teclat.nextLine();

                try {
                    numeroFila = (jugador1Coordenada.toUpperCase().charAt(0) - 65);
                    numeroColumna = (Integer.parseInt(String.valueOf(jugador1Coordenada.charAt(1))) - 1);

                    if (tablero[numeroFila][numeroColumna] == "-") {
                        throw new buidaException();
                    }
                    if (tablero[numeroFila][numeroColumna] == casellaJugador2 || tablero[numeroFila][numeroColumna] == casellaJugador1) {
                        throw new ocupadaException();
                    }
                    if (mirarGuanyador() == 1) {
                        throw new guanyaJugador1Exception();
                    }
                    if (mirarGuanyador() == 2) {
                        throw new guanyaJugador2Exception();
                    }
                    if (mirarGuanyador() == 0) {
                        throw new empatException();
                    }
                } catch (buidaException e) {
                    System.out.println("La casella és buida");
                    System.out.println();
                    pintarCasella(1, numeroFila, numeroColumna);
                    saberTurno++;
                } catch (ocupadaException e) {
                    System.out.println("La casella està ocupada");
                    System.out.println();
                } catch (guanyaJugador1Exception e) {
                    System.out.println("Jugador 1 és el guanyador");
                    partidaAcabada = true;
                } catch (guanyaJugador2Exception e) {
                    System.out.println("Jugador 2 és el guanyador");
                    partidaAcabada = true;
                } catch (empatException e) {
                    System.out.println("La partida ha acabat en empat");
                    System.out.println("Vols fer una altra partida? (S) Si   (N) No");
                    String respostaSeguirJugant = teclat.nextLine();
                }
            } else {
                System.out.print("Jugador 2 : ");
                jugador2Coordenada = teclat.nextLine();

                try {
                    numeroFila = (jugador2Coordenada.toUpperCase().charAt(0) - 65);
                    numeroColumna = (Integer.parseInt(String.valueOf(jugador2Coordenada.charAt(1))) - 1);

                    if (tablero[numeroFila][numeroColumna] == "-") {
                        throw new buidaException();
                    }
                    if (tablero[numeroFila][numeroColumna] == casellaJugador2 || tablero[numeroFila][numeroColumna] == casellaJugador1) {
                        throw new ocupadaException();
                    }
                    switch (mirarGuanyador()) {
                        case 0 -> throw new empatException();
                        case 1 -> throw new guanyaJugador1Exception();
                        case 2 -> throw new guanyaJugador2Exception();
                    }
                } catch (buidaException e) {
                    System.out.println("La casella és buida");
                    System.out.println();
                    pintarCasella(2, numeroFila, numeroColumna);
                    saberTurno++;
                } catch (ocupadaException e) {
                    System.out.println("La casella està ocupada");
                    System.out.println();
                } catch (guanyaJugador1Exception e) {
                    System.out.println("Jugador 1 és el guanyador");
                    partidaAcabada = true;
                } catch (guanyaJugador2Exception e) {
                    System.out.println("Jugador 2 és el guanyador");
                } catch (empatException e) {
                    System.out.println("La partida ha acabat en empat");
                    System.out.println("Vols fer una altra partida? (S) Si   (N) No");
                    String respostaSeguirJugant = teclat.nextLine();
                }
            }
            mostrarTablero();
            mirarGuanyador();
        } while (!partidaAcabada);
    }

    public static void jugar() {

        Scanner teclat = new Scanner(System.in);

        int saberTurno = 0;
        int turnoJugador = 0;
        int numeroFila = 0;
        int numeroColumna = 0;
        String coordenada;

        boolean partidaAcabada = false;

        do {
            mostrarTablero();
            if (saberTurno % 2 == 0) {
                System.out.print("Jugador 1: ");
                coordenada = teclat.nextLine();
                turnoJugador = 1;
            } else {
                System.out.print("Jugador 2: ");
                coordenada = teclat.nextLine();
                turnoJugador = 2;
            }

            try {
                numeroFila = (coordenada.toUpperCase().charAt(0) - 65);
                numeroColumna = (Integer.parseInt(String.valueOf(coordenada.charAt(1))) - 1);

                if (tablero[numeroFila][numeroColumna] == "-") {
                    throw new buidaException();
                }
                if (tablero[numeroFila][numeroColumna] == "X" || tablero[numeroFila][numeroColumna] == "O") {
                    throw new ocupadaException();
                }

                switch (mirarGuanyador()){
                    case 0 -> throw new empatException();
                    case 1 -> throw new guanyaJugador1Exception();
                    case 2 -> throw new guanyaJugador2Exception();
                }
            }catch (buidaException e){
                System.out.println("La casella és buida");
                System.out.println();
                pintarCasella(turnoJugador, numeroFila, numeroColumna);
                saberTurno++;
            }catch (ocupadaException e){
                System.out.println("La casella està ocupada");
                System.out.println();
            } catch (empatException e){
                System.out.println("La partida ha acabat en empat");
                partidaAcabada = true;
            }catch (guanyaJugador1Exception e){
                System.out.println("Jugador 1 és el guanyador");
                partidaAcabada = true;
            }catch (guanyaJugador2Exception e){
                System.out.println("Jugador 2 és el guanyador");
                partidaAcabada = true;
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Coordenada incorrecta");
            }
        } while (!partidaAcabada);
    }

    public static void pintarCasella(int jugador, int fila, int columna) {
        if (jugador == 1) {
            tablero[fila][columna] = "X";
        }
        if (jugador == 2) {
            tablero[fila][columna] = "O";
        }
    }

    public static int mirarGuanyador() {
        String casellaJugador1 = "X";
        String casellaJugador2 = "O";
        /*
            resultat = 0 -> EMPATE
            resultat = 1 -> JUGADOR 1
            resultat = 2 -> JUGADOR 2
            resultat = 3 -> SEGUIR JUGANT
         */

        //BUSCAR DIAGONAL DESCENDENT
        if (tablero[0][0] == casellaJugador1 && tablero[1][1] == casellaJugador1 && tablero[2][2] == casellaJugador1)
            return 1;
        if (tablero[0][0] == casellaJugador2 && tablero[1][1] == casellaJugador2 && tablero[2][2] == casellaJugador2)
            return 2;

        //BUSCAR DIAGONAL ASCENDENT
        if (tablero[2][1] == casellaJugador1 && tablero[1][1] == casellaJugador1 && tablero[0][2] == casellaJugador1)
            return 1;
        if (tablero[2][1] == casellaJugador2 && tablero[1][1] == casellaJugador2 && tablero[0][2] == casellaJugador2)
            return 2;


        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                //BUSCAR HORITZONTAL
                if (tablero[i][j] == casellaJugador1 && tablero[i][j + 1] == casellaJugador1 && tablero[i][j + 2] == casellaJugador1)
                    return 1;
                if (tablero[i][j] == casellaJugador2 && tablero[i][j + 1] == casellaJugador2 && tablero[i][j + 2] == casellaJugador2)
                    return 2;

                //BUSCAR VERTICAL
                if (tablero[i][j] == casellaJugador1 && tablero[i + 1][j] == casellaJugador1 && tablero[i + 2][j] == casellaJugador1)
                    return 1;
                if (tablero[i][j] == casellaJugador2 && tablero[i + 1][j] == casellaJugador2 && tablero[i + 2][j] == casellaJugador2)
                    return 2;

                if (tablero[i][j] != "-") {
                    return 0;
                }
            }
        }
        return 3;
    }
}
