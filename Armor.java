package data.small;

import java.util.Random;

public class Armor {
    
    private String name;
    private int minac;
    private int maxac;
    
    public Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }
    
    /* @return the name of the Armor, a String */
    public String getName() {
        return name;
    }
  
    /* @return the minac, an int */
    public int getMinac() {
        return minac;
    }
    
    /* @return the maxac, an int */
    public int getMaxax() {
        return maxac;
    }

    /* @returns the base statistics for this armor, an int */
    public int generateBaseStats() {
        Random rand = new Random();
        return rand.nextInt(maxac-minac+1) + minac;
    }
    
    
}
