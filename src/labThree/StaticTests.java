package labThree;
//todo 实验8
public class StaticTests extends staticSuper {
    static int rand;
    static {
        rand=(int)(Math.random()*6);
        System.out.println("static block"+rand);

    }
    StaticTests(){
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("in main");
        StaticTests st=new StaticTests();
    }
}
class staticSuper{
    static{
        System.out.println("super static block");

    }
    staticSuper(){
        System.out.println("super construtor");
    }
}