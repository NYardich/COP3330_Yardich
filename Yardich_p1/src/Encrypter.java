public class Encrypter {

    public String encrypt(String num) {
        int[] numIntArr = convertStringToIntArr(num);
        char[] encrypted = new char[4];

        for(int i = 0; i < 4; i++) {
            // Add encrypted char in correct index
            encrypted[(i + 2) % 4] = numberMix(numIntArr[i]);
        }

        return String.valueOf(encrypted);
    }

    public static int[] convertStringToIntArr(String num) {
        int[] numIntArr = new int[4];

        for(int i = 0; i < 4; i++) {
            numIntArr[i] = Character.getNumericValue(num.charAt(i));
        }
        return numIntArr;
    }

    public static char numberMix(int currNum) {
        // Perform encryption operations
        currNum = (currNum + 7) % 10;

        return (char) (currNum + '0');
    }
}