package labOne;
//todo lab2
public class SortJavaCode {
    public static void main(String[] args) {
    DrumKit d=new DrumKit();
    if(d.snare){
        d.playSnare();
    }
    d.snare=false;
    d.playTopHat();
    }
}
class DrumKit{
    boolean snare=true;
    boolean topHat=true;
    void playSnare(){
        System.out.println("bang bang ba-bang");
    }
    void playTopHat(){
        System.out.println("ding ding da-ding");
    }
}