import java.util.Scanner;

public class Encrypter {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(encrypt(strConvert(getInput())));
    }

    public static String getInput() {
        System.out.print("Number to be encrypted: ");
        return in.next();
    }

    public static int[] strConvert(String num) {
        int[] res = new int[4];

        // Iterate through res
        for(int i = 0; i < 4; i++) {
            // Assign character i in num to digit i in res
            res[i] = Character.getNumericValue(num.charAt(i));
        }
        return res;
    }

    public static String encrypt(int[] num) {
        char[] encrypted = new char[4];

        // Encrypt each digit
        for(int i = 0; i < 4; i++) {
            // Add to character array in correct index
            encrypted[(i + 2) % 4] = numberMix(num[i]);
        }
        // Return string version of character array
        return String.valueOf(encrypted);
    }

    public static char numberMix(int currNum) {
        // Perform operations necessary
        currNum = (currNum + 7) % 10;

        // Return number as char
        return (char) (currNum + '0');
    }
}