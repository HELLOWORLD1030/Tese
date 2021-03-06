package lab4;

import java.util.Scanner;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import java.util.*;
import java.awt.*;
import java.io.*;
import java.math.*;

public class DES_N {
    String password="95880228";
    String algorithm="DES";
    SecretKey DESkey;
    public byte[] decryptFun(byte[] cipertext){
        try{

            //use the ciper text in a byte type from the father function
            if(!(new File("DESkey.dat")).exists()){
                System.out.println("can not find the DES key!");
                return null;
            }
            else{
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("DESkey.dat"));
                DESkey = (SecretKey)in.readObject();
                in.close();
            }
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, DESkey);
            //System.out.println("decrypt string:"+cipertext.toString());
            return  cipher.doFinal(cipertext);
        }catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    public byte[] encrypt(String input)
    {
        try{
            BufferedReader in_clear = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
            StringBuffer content = new StringBuffer();
            String s="";
            while((s=in_clear.readLine())!=null){
                content.append(s);
            }
            in_clear.close();

            String cleartext=content.toString();
            if(!(new File("DESkey.dat")).exists()){
                System.out.println("creating DES key");
                KeyGenerator keygen=KeyGenerator.getInstance(algorithm);
                DESkey = keygen.generateKey();

                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("DESkey.dat"));
                outputStream.writeObject(DESkey);
                outputStream.close();
            }
            else{
                System.out.println("read DES key");
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("DESkey.dat"));
                DESkey = (SecretKey)in.readObject();
                in.close();
            }
            Cipher c1=Cipher.getInstance(algorithm);
            c1.init(Cipher.ENCRYPT_MODE, DESkey);

            return c1.doFinal(cleartext.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public byte[] encryptFun(String inputfile) {
        try{
            BufferedReader in_clear = new BufferedReader(new InputStreamReader(new FileInputStream(inputfile)));
            StringBuffer content = new StringBuffer();
            String s="";
            while((s=in_clear.readLine())!=null){
                content.append(s);
            }
            in_clear.close();
            String cleartext=content.toString();
            //get the content from a document

            SecureRandom random = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(password.getBytes());

            //creat a key factory
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secrekey=keyFactory.generateSecret(deskey);

            //use ciper object to encrypt
            Cipher cipher=Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secrekey,random);

            return cipher.doFinal(cleartext.getBytes());

        }catch (Exception e) {
            //
            e.printStackTrace();
        }
        return null;
    }

    public byte[] readInbyte(String inputname)
    {
        try{
            File file = new File(inputname);
            FileInputStream in = new FileInputStream(file);
            long filesize = file.length();
            byte[] readin = new byte[(int)filesize];

            int offset=0;
            int numRead=0;

            while(offset<readin.length&&(numRead = in.read(readin, offset, readin.length-offset))>=0){
                offset+=numRead;
            }
            if(offset!=readin.length){
                throw new IOException("can not read completely of file :"+file.getName());
            }
            in.close();
            return readin;
        }catch (Exception e) {
            //
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Please select the running mode:");
        System.out.println("1.encrypt");
        System.out.println("2.decrypt");
        DES_N des=new DES_N();
        Scanner scanner = new Scanner(System.in);
        int mode;
        mode = scanner.nextInt();
        switch (mode) {
            case 1:{
                System.out.println("Input the encrypt file name");
                scanner.nextLine();
                String input=scanner.nextLine();
                System.out.println("Input the ciper file name");
                String cipername=scanner.nextLine();
                byte[] ciper = des.encrypt(input);
                //System.out.println(ciper.toString());
                System.out.println(ciper);

                //write the byte from the ciper into a document
                try{
                    FileOutputStream out = new FileOutputStream(new File(cipername));
                    out.write(ciper);
                    out.close();
                }catch (Exception e) {
                    //
                    e.printStackTrace();
                }
                System.out.println("des decrypt"+new String(des.decryptFun(ciper)));
                break;

            }
            case 2:{
                System.out.println("Input the decrypt file");
                scanner.nextLine();
                String input = scanner.nextLine();
                System.out.println("Input the plain text name");
                String outname = scanner.nextLine();

                byte[] clear = des.readInbyte(input);
                clear = des.decryptFun(clear);
                System.out.println("decrypt content:  "+new String(clear));
                try{
                    FileOutputStream outputStream = new FileOutputStream(new File(outname));
                    outputStream.write(clear);
                    outputStream.close();
                }catch (Exception e) {
                    //
                    e.printStackTrace();
                }
                break;
            }
            default:
                break;
        }
    }
}