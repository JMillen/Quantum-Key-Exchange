import java.util.List;

/*
 * Author: Jakob Millen
 * 
 ***** Inspiration Material *****
 * Author: Stack overflow
 * Title: Simply XOR encrypt in javascript and decrypt in java
 * Link: https://stackoverflow.com/questions/33529103/simply-xor-encrypt-in-javascript-and-decrypt-in-java
 */
public class XOR {

    public static String xorEncrypt(String message, List<Integer> key) {
        StringBuilder cipherText = new StringBuilder();
        int keyLength = key.size();
        for (int i = 0; i < message.length(); i++) {
            int messageBit = Character.getNumericValue(message.charAt(i));
            int keyBit = key.get(i % keyLength);
            cipherText.append(messageBit ^ keyBit);
        }
        return cipherText.toString();
    }

    public static String xorDecrypt(String cipherText, List<Integer> key) {
        StringBuilder message = new StringBuilder();
        int keyLength = key.size();
        for (int i = 0; i < cipherText.length(); i++) {
            int messageBit = Character.getNumericValue(cipherText.charAt(i));
            int keyBit = key.get(i % keyLength);
            message.append(messageBit ^ keyBit);
        }
        return message.toString();
    }
}