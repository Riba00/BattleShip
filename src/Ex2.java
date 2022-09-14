import java.util.Scanner;

public class Ex2 {

    public static void main(String[] args) {

        Scanner teclat = new Scanner(System.in);

        System.out.print("Numero de items: ");
        String numeroItems = teclat.nextLine();
        String [] llista = new String[Integer.parseInt(numeroItems)];

        for (int i = 0; i < llista.length; i++) {
            System.out.print("Item "+ (i+1) +" de "+numeroItems+": ");
            llista[i] = teclat.nextLine();
        }
        for (int i = 0; i < llista.length; i++) {
            System.out.println(llista[i]);
        }






    }

}
