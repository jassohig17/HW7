package data.small;

public class TreasureClass {
    private String TC;
    private String item1;
    private String item2;
    private String item3;
    
    public TreasureClass(String TC, String item1, String item2, String item3) {
        this.TC = TC;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
    
    public String getTC() {
        return TC;
    }
    
    public String getItem1() {
        return item1;
    }
    
    public String getItem2() {
        return item2;
    }
    public String getItem3() {
        return item3;
    }
}
