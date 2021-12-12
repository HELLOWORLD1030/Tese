package lab4;
//todo 实验12附加
import java.util.ArrayList;

import java.util.Scanner;

public class Dic {
    private ArrayList<DicItem> list;
    public Dic(){
        list=new ArrayList<>();
    }
    private void Add(String English,String Chinese){
        DicItem dicItem=new DicItem();
        dicItem.setEnglish(English);
        dicItem.setChinese(Chinese);
        list.add(dicItem);
    }
    private String trans(String English){
        for(DicItem dicItem :list){
            if(dicItem.getEnglish().equals(English)){
                return dicItem.getChinese();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Dic dic=new Dic();
        dic.Add("I","我");
        dic.Add("Love","爱");
        dic.Add("You","你");
        dic.Add("Hello","你好");
        dic.Add("World","世界");
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要翻译的英文句子，并以空格隔开。");
        String input= scanner.nextLine();
        String[] inputArr=input.split(" ");
//        System.out.println(Arrays.toString(inputArr));
        StringBuilder stringBuilder=new StringBuilder();
        for(String English:inputArr){
            stringBuilder.append(dic.trans(English));
        }
        System.out.println("翻译的句子为："+stringBuilder);
    }
}
class DicItem{
    private String English;

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getChinese() {
        return Chinese;
    }

    public void setChinese(String chinese) {
        Chinese = chinese;
    }

    private String Chinese;

}
