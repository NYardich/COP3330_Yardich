public class Decrypter {

    public static int[] strConvert(String num) {
        int[] res = new int[4];

        // Iterate through res
        for(int i = 0; i < 4; i++) {
            // Assign character i in num to digit i in res
            res[i] = Character.getNumericValue(num.charAt(i));
        }
        return res;
    }

    public String decrypt(String num) {
        int[] res = strConvert(num);
        char[] decrypted = new char[4];

        // Decrypt each digit
        for(int i = 0; i < 4; i++) {
            // Add to character array in correct index
            decrypted[(i + 2) % 4] = numberUnmix(res[i]);
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
