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
    
    public String getName() {
        return name;
    }
    
    public int getMinac() {
        return minac;
    }
    
    public int getMaxax() {
        return maxac;
    }

    public int generateBaseStats() {
        Random rand = new Random();
        return rand.nextInt(maxac-minac+1) + minac;
    }
    
    
}
