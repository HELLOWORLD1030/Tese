package lab5;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import static java.io.File.*;

public class FileManage {

    public static void main(String[] args) throws IOException {
        System.out.println("文件管理demo");
        System.out.println("1. 输入1 显示系统当前用户，工作目录");
        System.out.println("2. 输入2 显示指定文本文件的内容");
        Scanner scanner=new Scanner(System.in);
        FileManage fileManage=new FileManage();
        boolean isBreakLoop=false;
        do {
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    System.out.println("当前系统登录的用户为：" + fileManage.ShowCurrentUser());
                    System.out.println("当前用户工作目录为：" + fileManage.ShowCurrentDir());
                    break;
                case 2:
                    System.out.println("请输入要查找的文件名：");
                    String FileName = scanner.nextLine();
                    if (!fileManage.isSetDir(FileName)) {
                        FileName = System.getProperty("user.dir") + File.separator + FileName;
                    }
                    String FileContent = fileManage.listFileContent(FileName);
                    if ("".equals(FileContent)) {
                        System.out.println("文件读取失败");
                    } else {
                        System.out.println(FileContent);
                    }
                    break;
                case 3:
                    System.out.println("请输入要查找的文件名：");
                    String FileName1 = scanner.nextLine();
                    if (!fileManage.isSetDir(FileName1)) {
                        FileName1 = System.getProperty("user.dir") + File.separator + FileName1;
                    }
                    System.out.println(fileManage.listFileInfo(FileName1));
                    break;
                case 4:
                    System.out.println("请输入源文件和目标文件");
                    String FileName2=scanner.nextLine();
                    String FileName3=scanner.nextLine();
                    if (!fileManage.isSetDir(FileName2)) {
                        FileName2 = System.getProperty("user.dir") + File.separator + FileName2;
                    }
                    if (!fileManage.isSetDir(FileName3)) {
                        FileName3 = System.getProperty("user.dir") + File.separator + FileName3;
                    }
                    System.out.println(FileManage.copyFile(FileName2,FileName3));
                    break;
                case 5:
                    boolean fileCreateFlag;
                    do {
                        System.out.println("请输入要创建的文件名：");
                        String FileName4 = scanner.nextLine();
                        if (!fileManage.isSetDir(FileName4)) {
                            FileName1 = System.getProperty("user.dir") + File.separator + FileName4;
                        }
                       fileCreateFlag= fileManage.createFileUsingFileClass(FileName4);
                        if(!fileCreateFlag){
                            System.out.println("文件已存在，请选择是否覆盖，输入1覆盖，2重新输入文件名");
                           int mode=Integer.parseInt(scanner.nextLine());
                           if(mode==1){
                               File file=new File(FileName4);
                               file.delete();
                               fileCreateFlag= fileManage.createFileUsingFileClass(FileName4);
                           }
                        }

                    }while(!fileCreateFlag);
                    break;
                case 6:
                    System.out.println("程序退出");
                    isBreakLoop = true;
                    break;

            }
        } while (!isBreakLoop);
    }

    private static String copyFile(String FileName2, String FileName3) throws IOException {
        File file2= new File(FileName2);
        File file3= new File(FileName3);
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(file2).getChannel();
            outputChannel = new FileOutputStream(file3).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
        return "copy ok";
    }

    private boolean createFileUsingFileClass(String FileName4) throws IOException{
        File file4= new File(FileName4);
        if(file4.exists()){
            System.out.println("File already exists");
            return false;
        }
        file4.createNewFile();
        System.out.println("file create ok");
        return true;

    }

    private String ShowCurrentUser(){
        return System.getProperty("user.name");
    }
    private String ShowCurrentDir(){
        return System.getProperty("user.dir");
    }
    private boolean isSetDir(String FileName){
        return FileName.contains("\\") || FileName.contains("/");
    }
    private String listFileContent(String FilePath){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FilePath))));
            String c;
            StringBuilder stringBuilder=new StringBuilder();
            while(true){
                c=reader.readLine();
                if(c==null){
                    break;
                }
                stringBuilder.append(c).append("\n");

            }
            reader.close();

            return stringBuilder.toString();
        }catch (IOException ioException){
        }
        return "";
    }
    private String listFileInfo(String FileName){

        File file = new File(FileName);
        String name= file.getName();
        String Size=file.length()+"B";
        String Power="";
        if(file.canExecute()){
            Power+="可执行";
        }
        if(file.canRead()){
            Power+="可读";
        }
        if(file.canWrite()){
            Power+="可写";
        }
        return name + " " + Size + " " + Power + " ";

    }
}
