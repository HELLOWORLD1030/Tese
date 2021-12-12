package lab4;

import java.util.Scanner;
public class ChenTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String NumberS=scanner.nextLine();
        int Number=Integer.parseInt(NumberS);
        data[] dateTemp=new data[Number];
        for(int i=0;i<Number;i++){
            dateTemp[i]=new data();
            String temp=scanner.nextLine();
            String[] tempArr= temp.split(" ");
            dateTemp[i].setName(tempArr[0]);
            dateTemp[i].setRows(Integer.parseInt(tempArr[1]));
            dateTemp[i].setColumn(Integer.parseInt(tempArr[2]));
        }
        for(int i=0;i<Number;i++){
            System.out.println(dateTemp[i]);
        }
    }

}
class data{
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRows() {
        return Rows;
    }

    public void setRows(int rows) {
        Rows = rows;
    }

    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        Column = column;
    }

    private int Rows;
    private int Column;
    @Override
    public String toString(){
        return this.getName()+" "+this.getRows()+" "+this.getColumn();
    }
    public data(){}
}
