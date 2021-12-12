package lab4;

import java.util.Scanner;
public class Fractal {
    static int len=0,n;
    public static void main(String[] args) {
        int x,i=0;
        int a[]=new int[1000];
        Scanner scan=new Scanner(System.in);
        x=scan.nextInt();
        while(x!=-1)
        {
            a[i]=x;
            i++;
            x=scan.nextInt();
            len=i;
        }
        for(n=0;n<len;n++);
        {
            fractal(a,n);
        }
		/*System.out.printf("输出：\n");
		for(int j=0;j<len;j++)
		{
			System.out.println(a[j]);
		}*/
    }
    public static void fractal(int A[],int n) {
        if(A[n]==1)//图形1
        {
            System.out.printf("X");
        }
        if(A[n]>1)
        {
            int num1=2*A[n]-3;
            int aa=A[n];
            A[n]=A[n]-1;
            //第一行
            fractal(A,n);
            for(int l=0;l<num1;l++)
            {
                System.out.printf(" ");
            }
            fractal(A,n);
            System.out.printf("\n");
            //第二行
            int num2;
            if(aa==2)
                num2=1;
            else
            {
                num2=3;
                for(int p=0;p<A[n];p++)
                {
                    num2=num2*3;
                }
            }
            for(int k=0;k<num2;k++)
            {
                System.out.println(" ");
            }
            fractal(A,n);
            System.out.printf("\n");
            //第三行
            fractal(A,n);
            for(int l=0;l<num1;l++)
            {
                System.out.printf(" ");
            }
            fractal(A,n);
        }
        System.out.printf("\n");
        System.out.println("-");
    }
}