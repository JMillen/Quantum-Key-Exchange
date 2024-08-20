/**
 *
 * Author: Jakob Millen
 */
public class Qubit{
    
    private int value;
    private int polarisation; // 0 for circular, 1 for linear

    /*
     * Constructor for Qubit
     */
    public Qubit(int value, int polarisation){
        this.value = value;
        this.polarisation = polarisation;
    }

    /*
     * Set the value and polarisation of the Qubit
     */
    public void set(int value, int polarisation){
        this.value = value;
        this.polarisation = polarisation;
    }

    /*
     * Measure the Qubit in the given polarisation
     */
    public int measure(int polarisation){
        // Returns value if polarisation matches
        if(this.polarisation == polarisation){
            return this.value;
        } else {
            // Randomly returns 0 or 1 if polarisation does not match
            this.polarisation = polarisation;
            if(Math.random() < 0.5){
                this.value = 0;
                return this.value;
            } else {
                this.value = 1;
                return this.value;
            }
        }
    }
}