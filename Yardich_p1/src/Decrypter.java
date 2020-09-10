public class Decrypter {

    public String decrypt(String num) {
        int[] numIntArr = convertStringToIntArr(num);
        char[] decrypted = new char[4];

        for(int i = 0; i < 4; i++) {
            // Add decrypted char in correct index
            decrypted[(i + 2) % 4] = numberUnmix(numIntArr[i]);
        }

        return String.valueOf(decrypted);
    }

    public static int[] convertStringToIntArr(String num) {
        int[] numIntArr = new int[4];

        for(int i = 0; i < 4; i++) {
            numIntArr[i] = Character.getNumericValue(num.charAt(i));
        }
        return numIntArr;
    }

    public static char numberUnmix(int currNum) {
        // Adding 3 and modding by 10 reverses the encryption on the number
        currNum = (currNum + 3) % 10;

        return (char) (currNum + '0');
    }
}
