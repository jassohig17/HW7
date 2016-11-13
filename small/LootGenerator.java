package data.small;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    
    public static void fillMonsterMap(MonsterMap monsters) throws FileNotFoundException {
        File file = new File ("/home/jassohig17/workspace/"
                + "HW 7 LootGenerator/src/data/small/monstats.txt");
        Scanner stream = new Scanner(file);
        int index = 0;
        while (stream.hasNextLine()) {
            String line = stream.nextLine();
            String[] parts = line.split("\t");
            monsters.put(index, parts[0], parts[parts.length-1]);
            index++;
        }
        stream.close();
    }

    public static String pickMonster(MonsterMap monsters) {
        //Random rand = new Random();
        return monsters.getMonster(0);
    }
    
    public static String fetchTreasureClass(MonsterMap monsters, String monster) {
        return monsters.getTC(monster);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        MonsterMap monsters = new MonsterMap();
        fillMonsterMap(monsters);
        String monster = pickMonster(monsters);
        String TC = fetchTreasureClass(monsters, monster);
        System.out.printf("Fighting %s... \n", monster);
        System.out.printf("You have slained %s! \n", monster);
        System.out.printf("%s dopped: \n\n", monster);
        
        
    }
}
