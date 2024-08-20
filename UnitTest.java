
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class UnitTest {

    private QKE qke;

    @Before
    public void setup() {
        qke = new QKE();
    }

    @Test
    public void Qubit_Matching_Polarisation_Measurement() {
        Qubit qubit = new Qubit(1, 1);
        assertEquals(1, qubit.measure(1));
    }

    @Test
    public void Qubit_NonMatching_Polarisation_Measurement() {
        Qubit qubit = new Qubit(1, 1);
        int result = qubit.measure(0);
        assertTrue(result == 0 || result == 1);
    }

    @Test
    public void xor_Test_Encryption_Decryption() {
        List<Integer> key = Arrays.asList(1, 0, 1);
        String message = "010011";
        String encryptedMessage = XOR.xorEncrypt(message, key);
        String decryptMessage = XOR.xorDecrypt(encryptedMessage, key);
        assertEquals(message, decryptMessage);
    }

    @Test
    public void xor_test_empty_message_and_key() {
        String message = "";
        List<Integer> key = new ArrayList<>();

        String encryptedMessage = XOR.xorEncrypt(message, key);

        assertEquals("", encryptedMessage);
    }

    @Test
    public void test_emptySharedSecretKeyForNullInput() {
        qke.simulateQKE(0);
        List<Integer> key = qke.extractSecretKey();
        assertTrue(key.isEmpty());
    }

    @Test
    public void test_QKESimulation_With_0Qubits() {
        simulateAndAssertQKE(0);
    }

    @Test
    public void test_QKESimulation_With_16Qubits() {
        simulateAndAssertQKE(16);
    }

    @Test
    public void test_QKESimulation_With_256Qubits() {
        simulateAndAssertQKE(256);
    }

    @Test
    public void test_QKESimulation_With_1024Qubits() {
        simulateAndAssertQKE(1024);
    }

    private void simulateAndAssertQKE(int numQubits) {
        // Run QKE sim
        qke.simulateQKE(numQubits);

        // Extract the shared key and verify its length
        List<Integer> key = qke.extractSecretKey();
        if (numQubits > 0 ){
            assertNotNull(key);
            assertTrue(!key.isEmpty());
        } else {
            assertNull(key);
        }

        // Set message
        String message = "0100110101100101";

        if (key != null && !key.isEmpty()){
                    // Encrypt the message
        String encryptedMessage = XOR.xorEncrypt(message, key);
        assertNotNull(encryptedMessage);
        assertEquals(message.length(), encryptedMessage.length());

        // Decrypt Message
        String decryptedMessage = XOR.xorDecrypt(encryptedMessage, key);
        assertNotNull(decryptedMessage);
        assertEquals(message, decryptedMessage);
        }
    }
}
