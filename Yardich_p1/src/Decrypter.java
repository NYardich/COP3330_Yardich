import java.util.Scanner;

public class Decrypter {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(decrypt(getInput()));
    }

    public static String getInput() {
        System.out.print("Number to be decrypted: ");
        return in.next();
    }

    public static String decrypt(String num) {
        char[] decrypted = new char[4];

        // Decrypt each digit
        for(int i = 0; i < 4; i++) {
            // Add to character array in correct index
            decrypted[(i + 2) % 4] = numberUnmix(Character.getNumericValue(num.charAt(i)));
        }

        // Return string version of character array
        return String.valueOf(decrypted);
    }

    public static char numberUnmix(int currNum) {
        // Adding 3 and modding by 10 reverses the encryption on the number
        currNum = (currNum + 3) % 10;

        // Return number as char
        return (char) (currNum + '0');
    }
}
