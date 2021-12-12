package lab4;
//todo 实验11
public class ExTestDrive {
    public static void main(String[] args) {
        String test=args[0];
        try {
            doRisky(test);
//            System.out.println("thaws");
        }catch (MyEx x){
            System.out.println(x.getMessage());
        }
    }
    static void doRisky(String t) throws MyEx{
        if("yes".equals(t)) {
            System.out.println("thaws");
        }else{
            throw new MyEx();
        }
    }
}
class MyEx extends Exception{
    public MyEx()  {
        super("throws");
    }
}
