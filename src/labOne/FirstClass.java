package labOne;
//todo lab1
public class FirstClass {
    final int count=100;
    public static void main(String[] args){
        FirstClass firstClass=new FirstClass();
         char[] chars=firstClass.getCharArray();
        firstClass.displayChar(chars);
        int[] countNumber=firstClass.computeCharNumber(chars);
        firstClass.displayCountNumber(countNumber);
//        System.out.println("hello world");

    }
    private char[] CreateCharArray(){
        char[] chars;
        if(count>0){
            chars=new char[count];
            return chars;
        }
        return null;

    }
    private char getChar(char low,char high){
        return (char)('a'+Math.random()*(high-low+1));
    }
    private   char getLowerChar(){
        char temp=getChar('a','z');
        return temp;
    }
    public char[] getCharArray(){
       char[] chars= this.CreateCharArray();
       for(int i=0;i<count;i++){
           chars[i]=getChar('a','z');
       }
       return chars;
    }
    public void displayChar(char[] chars){
//        char[] chars=this.getCharArray();
        String str=String.valueOf(chars);
        System.out.println(str);
    }
    public int[] computeCharNumber(char[] chars){
        int[] countNumber=new int[26];
        for (char temp:chars){
            countNumber[temp-'a']++;
        }
        return countNumber;
    }
    public void displayCountNumber(int[] countNumber){
        int p=0;
        for(int temp:countNumber){
            System.out.println((char)('a'+p)+"的出现次数为："+temp);
            p++;
        }
    }

}
