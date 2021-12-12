package lab4;
import static java.lang.System.out;
import java.io.*;
import java.util.*;
//todo 实验9
public class FullMoons {
    static int DAY_TM=60*60*24*1000;
    public static void main(String[] args) {
        Calendar c=Calendar.getInstance();
        out.println(Calendar.DECEMBER);
       c.set(2004,Calendar.JANUARY,7,15,40);
       long dayl=c.getTimeInMillis();
        out.println(dayl);
       for(int x=0;x<60;x++){
//           out.println(c;
           out.println(String.format("full moon on %tc",c));
           dayl+=(DAY_TM*29.52);
           c.setTimeInMillis(dayl);
       }
    }
}
