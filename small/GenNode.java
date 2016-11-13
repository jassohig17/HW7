package data.small;

import java.util.Scanner;

public interface GenNode {
    public GenNode random(Scanner in);
    public boolean isTC();
    public Armor generateBaseItem();
    public Armor get();
}
