package data.small;

import java.util.Scanner;

public class ItemNode implements GenNode {
    private Armor item;
    
    public Armor get(){
        return item;
    }

    @Override
    public GenNode random(Scanner in) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isTC() {
        return false;
    }

    @Override
    public Armor generateBaseItem() {
        return null;
        // TODO Auto-generated method stub    
    }
}
