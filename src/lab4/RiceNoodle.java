package lab4;

public class RiceNoodle {
    private String name;
    private int Quantity;
    private boolean withSoup;
    public RiceNoodle(String name,int quantity,boolean withSoup){
        this.name=name;
        this.Quantity=quantity;
        this.withSoup=withSoup;
    }
    public RiceNoodle(String name,int quantity){
        this.name=name;
        this.Quantity=quantity;
    }
    public void PrintProperties(){
        System.out.println("name:"+this.name+"quan:"+this.Quantity+"with :"+this.withSoup);
    }

    public static void main(String[] args) {
        RiceNoodle n1=new RiceNoodle("bear",3,true);
        RiceNoodle n2=new RiceNoodle("bear",2);
        n1.PrintProperties();
        n2.PrintProperties();
    }
}
