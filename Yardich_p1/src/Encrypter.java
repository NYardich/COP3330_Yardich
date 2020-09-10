public class Encrypter {

    public static int[] strConvert(String num) {
        int[] res = new int[4];

        // Iterate through res
        for(int i = 0; i < 4; i++) {
            // Assign character i in num to digit i in res
            res[i] = Character.getNumericValue(num.charAt(i));
        }
        return res;
    }

    public static String encrypt(String num) {
        int[] res = strConvert(num);
        char[] encrypted = new char[4];

        // Encrypt each digit
        for(int i = 0; i < 4; i++) {
            // Add to character array in correct index
            encrypted[(i + 2) % 4] = numberMix(res[i]);
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