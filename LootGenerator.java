package data.small;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

    public static void fillMonsterMap(MonsterMap monsters) throws FileNotFoundException {
        File file = new File(
                "/home/jassohig17/workspace/" + "HW 7 LootGenerator/src/data/large/monstats.txt");
        Scanner stream = new Scanner(file);
        int index = 0;
        while (stream.hasNextLine()) {
            String line = stream.nextLine();
            String[] parts = line.split("\t");
            monsters.put(index, parts[0], parts[parts.length - 1]);
            index++;
        }
        stream.close();
    }

    public static void fillTCHashMap(HashMap<String, String[]> hmap) throws FileNotFoundException {
        File file = new File("/home/jassohig17/workspace/"
                + "HW 7 LootGenerator/src/data/large/TreasureClassEx.txt");
        Scanner stream = new Scanner(file);
        while (stream.hasNextLine()) {
            String line = stream.nextLine();
            String[] parts = line.split("\t");
            hmap.put(parts[0], new String[] { parts[1], parts[2], parts[3] });
        }
        stream.close();
    }

    public static void fillArmorList(ArrayList<Armor> armors) throws FileNotFoundException {
        File file = new File(
                "/home/jassohig17/workspace/" + "HW 7 LootGenerator/src/data/large/armor.txt");
        Scanner stream = new Scanner(file);
        while (stream.hasNextLine()) {
            String line = stream.nextLine();
            String[] parts = line.split("\t");
            int parts1 = Integer.parseInt(parts[1]);
            int parts2 = Integer.parseInt(parts[2]);

            Armor newArmor = new Armor(parts[0], parts1, parts2);
            armors.add(newArmor);
        }
        stream.close();
    }

    public static void fillPrefixHmap(HashMap<Integer, Prefix> prefixes)
            throws FileNotFoundException {
        File file = new File("/home/jassohig17/workspace/"
                + "HW 7 LootGenerator/src/data/large/MagicPrefix.txt");

        Scanner stream = new Scanner(file);
        int index = 0;

        while (stream.hasNextLine()) {

            String line = stream.nextLine();
            String[] parts = line.split("\t");
            int parts2 = Integer.parseInt(parts[2]);
            int parts3 = Integer.parseInt(parts[3]);

            Prefix newPrefix = new Prefix(parts[0], parts[1], parts2, parts3);
            prefixes.put(index, newPrefix);
            index += 2;
        }
        stream.close();
    }

    public static void fillSuffixHmap(HashMap<Integer, Suffix> suffixes)
            throws FileNotFoundException {
        File file = new File("/home/jassohig17/workspace/"
                + "HW 7 LootGenerator/src/data/large/MagicSuffix.txt");

        Scanner stream = new Scanner(file);
        int index = 0;

        while (stream.hasNextLine()) {

            String line = stream.nextLine();
            String[] parts = line.split("\t");
            int parts2 = Integer.parseInt(parts[2]);
            int parts3 = Integer.parseInt(parts[3]);

            Suffix newSuffix = new Suffix(parts[0], parts[1], parts2, parts3);
            suffixes.put(index, newSuffix);
            index += 2;
        }
        stream.close();
    }

    public static String pickMonster(MonsterMap monsters) {
        Random rand = new Random();
        return monsters.getMonster(rand.nextInt(monsters.size()));
    }

    public static String fetchTreasureClass(MonsterMap monsters, String monster) {
        return monsters.getTC(monster);
    }

    public static String generateBaseItem(String[] items, HashMap<String, String[]> hmap) {
        Random rand = new Random();
        int num = rand.nextInt(2);

        if (hmap.containsKey(items[num])) {
            return generateBaseItem(hmap.get(items[num]), hmap);
        }
        return items[num];
    }

    public static int generateBaseStats(String armor, ArrayList<Armor> armors) {

        Iterator<Armor> iter = armors.iterator();

        while (iter.hasNext()) {
            Armor cur = iter.next();
            if (cur.getName().equals(armor)) {
                return cur.generateBaseStats();
            }
        }

        return 0;
    }

    public static String generateAffix(HashMap<Integer, Prefix> prefixes,
            HashMap<Integer, Suffix> suffixes, String armor, int defense) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        int num1 = rand1.nextInt(2 * prefixes.size() + 1);
        int num2 = rand2.nextInt(2 * suffixes.size() + 1);
        if (prefixes.containsKey(num1) && suffixes.containsKey(num2)) {
            return prefixes.get(num1).getPrefix() + " " + armor + " "
                    + suffixes.get(num2).getSuffix() + "\n" + "Defense: " + defense + "\n"
                    + prefixes.get(num1).generateStatValue() + " " + prefixes.get(num1).getStat()
                    + "\n" + suffixes.get(num2).generateStatValue() + prefixes.get(num1).getStat()
                    + "\n";
        } else if (prefixes.containsKey(num1)) {
            return prefixes.get(num1).getPrefix() + " " + armor + "\n" + "Defense: " + defense
                    + "\n" + prefixes.get(num1).generateStatValue() + " "
                    + prefixes.get(num1).getStat() + "\n";
        } else if (suffixes.containsKey(num2)) {
            return armor + " " + suffixes.get(num2).getSuffix() + "\n" + "Defense: " + defense
                    + "\n" + suffixes.get(num2).generateStatValue() + " "
                    + suffixes.get(num2).getStat() + "\n";
        } else {
            return armor + "\n" + "Defense: " + defense + "\n";
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        // fill the monsters and create hashmap
        MonsterMap monsters = new MonsterMap();
        fillMonsterMap(monsters);

        HashMap<String, String[]> hmap = new HashMap<String, String[]>();
        fillTCHashMap(hmap);

        // fill the armors
        ArrayList<Armor> armors = new ArrayList<Armor>();
        fillArmorList(armors);

        // Generate the Affix hashMaps
        HashMap<Integer, Prefix> prefixHmap = new HashMap<Integer, Prefix>();
        fillPrefixHmap(prefixHmap);

        HashMap<Integer, Suffix> suffixHmap = new HashMap<Integer, Suffix>();
        fillSuffixHmap(suffixHmap);

        boolean proceed = true;

        while (proceed) {
            // pick a random moster and fetch its TC
            String monster = pickMonster(monsters);
            String TC = fetchTreasureClass(monsters, monster);
            String armor = generateBaseItem(hmap.get(TC), hmap);
            int defense = generateBaseStats(armor, armors);
            String dropBox = generateAffix(prefixHmap, suffixHmap, armor, defense);

            System.out.printf("Fighting %s... \n", monster);
            System.out.printf("You have slained %s! \n", monster);
            System.out.printf("%s dopped: \n\n", monster);
            System.out.println(dropBox);

            boolean invalid = true;

            while (invalid) {
                System.out.println("Do you want to continue? [Y/N]");

                Scanner scan = new Scanner(System.in);
                String response = scan.next();

                if (response.equalsIgnoreCase("y")) {
                    proceed = true;
                    invalid = false;
                } else if (response.equalsIgnoreCase("n")) {
                    
                    System.out.println("Thanks for playing, Exiting the game...");
                    proceed = false;
                    invalid = false;
                }
                scan.close();
            }

        }
    }
}