package data.small;

import java.util.ArrayList;
import java.util.List;

public class MonsterMap {
    private List<Triple> data;

    public MonsterMap() {
        data = new ArrayList<Triple>(); 
    }

    private static class Triple {
        private int index;
        private String monster;
        private String TC;

        public Triple(int index, String monster, String TC) {
            this.index = index;
            this.monster = monster;
            this.TC = TC;
        }
        
        public int getIndex() {
            return index;
        }

        public String getMonster() {
            return monster;
        }

        public String getTC() {
            return TC;
        }
    }

    public void put(int index, String monster, String TC) {
        data.add(new Triple(index, monster, TC));

    }

    public String getMonster(int index) {
        for (Triple p : data) {
            if (p.getIndex() == index) {
                return p.getMonster();
            }
        }
        throw new IllegalArgumentException();
    }

    
    public String getTC(String monster) {
        for (Triple p : data) {
            if (p.getMonster().equals(monster)) {
                return p.getTC();
            }
        }
        throw new IllegalArgumentException();
    }
}
