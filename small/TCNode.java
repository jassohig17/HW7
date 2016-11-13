package data.small;

import java.util.Random;
import java.util.Scanner;

public class TCNode implements GenNode {
    public String TC;
    public GenNode item1;
    public GenNode item2;
    public GenNode item3;
    
    public TCNode(String TC, GenNode item1, GenNode item2, GenNode item3) {
        this.TC = TC;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public Armor generateBaseItem() {
        Random rand = new Random();
        int i = rand.nextInt(3);

        GenNode[] arr = new GenNode[] { item1, item2, item3 };

        if (arr[i].isTC()) {
            arr[i].generateBaseItem();
        }
        return arr[i].get();

    }
    
    public Armor get(){
        return null; 
        
    }

    @Override
    public GenNode random(Scanner in) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isTC() {
        return true;
    }
}
