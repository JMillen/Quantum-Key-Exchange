import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Author: Jakob Millen
 */

public class QKE {
    
    // Initialize Variables
    private final List<Qubit> transmittedQubits;
    private final List<Integer> transmittedPolarisations;
    private final List<Integer> receiverPolarisations;
    private final List<Integer> receiverMeasurements;

    public static void main(String[] args){
        QKE qke = new QKE();
        qke.simulateQKE(16);
    }

    public void simulateQKE(int numQubits){
        if (numQubits <= 0) {
            System.out.println("Number of qubits must be greater than 0");
            return;
        }

        transmitQubits(numQubits);
        if (transmittedQubits.isEmpty()){
            System.out.println("Transmitted qubits are empty");
            return;
        }

        receiveQubits();
        List<Integer> key = extractSecretKey();
        if ( key == null || key.isEmpty()){
            System.out.println("Shared secret key is null or empty");
            return;
        }
        System.out.println("Shared Secret Key: " + key);

        // Message to encrypt
        String message = "0100110101100101";
        System.out.println("Original Message: " + message);

        // Encrypted Message
        String encryptedMessage = XOR.xorEncrypt(message, key);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypted Message
        String decryptedMessage = XOR.xorDecrypt(encryptedMessage, key);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }


    public QKE(){
        transmittedQubits = new ArrayList<>();
        transmittedPolarisations = new ArrayList<>();
        receiverPolarisations = new ArrayList<>();
        receiverMeasurements = new ArrayList<>();
    }

    public void transmitQubits(int numQubits){
        Random rand = new Random();
        for (int i = 0; i < numQubits; i++){
            // 0 or 1
            int value = rand.nextInt(2);
            // 0 for circular and 1 for linear
            int polarisation = rand.nextInt(2);
            transmittedQubits.add(new Qubit(value, polarisation));
            transmittedPolarisations.add(polarisation);
        }
    }

    public void receiveQubits(){
        Random rand = new Random();
        for (Qubit qubit : transmittedQubits){
            // 0 for circular and 1 for linear
            int polarisation = rand.nextInt(2);
            receiverPolarisations.add(polarisation);
            int measuredValue = qubit.measure(polarisation);
            receiverMeasurements.add(measuredValue);
        }
    }

    public List<Integer> extractSecretKey(){
        if (transmittedPolarisations.isEmpty() || receiverPolarisations.isEmpty()){
            return null;
        }

        List<Integer> secretKey = new ArrayList<>();
        for (int i = 0; i < transmittedPolarisations.size(); i++){
            if (transmittedPolarisations.get(i).equals(receiverPolarisations.get(i))){
                secretKey.add(receiverMeasurements.get(i));
            } 
        }
        return secretKey;
    }
}
