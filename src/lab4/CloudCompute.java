package lab4;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
public class CloudCompute {
    private static final String filePath=System.getProperty("user.dir")+"/file/";
    public static void main(String[] args) {
        for(FileSize fileSize:FileSize.values()) {
            System.out.println("开始创建" + fileSize.getText() + "文件");
            File file = CreateDirectoryAndFile(fileSize);
            System.out.println("文件创建完成");
            long len = Long.parseLong(fileSize.getByteNumber());
            System.out.println("正在写入随机字母");
            WriteRandomCharacter(file, len);
            try {
                String str = ReadCharFromFile(file);
                System.out.println("排序开始：");
                long startTime = System.currentTimeMillis();
                char[] sorted = DictionarySort(str);
                long endTime = System.currentTimeMillis();
                System.out.println("排序结束！排序耗时：" + (endTime - startTime) + "ms");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            file.delete();//删除文件
        }
    }

    /***
     *
     * @param fileSize，传入FileSize枚举
     * @return
     * 此方法创建相应大小的文件，并返回File文件句柄
     */
    public static File CreateDirectoryAndFile(FileSize fileSize){
        String FileName=fileSize.getText()+".txt";
        System.out.println(filePath);
        File dir=new File(filePath);
        if(!dir.exists()){//创建文件夹
            boolean su= dir.mkdir();
        }
        File file=new File(filePath,FileName);
        System.out.println(file.getAbsolutePath());
        long len=Long.parseLong(fileSize.getByteNumber()) ;
        if(!file.exists()){//创建文件1
            try {
                createFixLengthFile(file, len);
            }catch (IOException ioException){
                System.out.println(ioException.getMessage());
            }

        }
        return file;

    }

    /**
     *
     * @param file 想要读取的文件的文件句柄
     * @return 包含文件中随机字母的字符串
     * @throws IOException
     * 此方法从文件中读取之前生成的随机字母，拼接成字符串返回
     */
    public static String ReadCharFromFile(File file) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder stringBuilder=new StringBuilder();
        while(bufferedReader.read()!=-1){
            stringBuilder.append(bufferedReader.readLine());
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    /***
     *
     * @param sourceStr
     * @return
     * 排序
     */
    public static char[] DictionarySort(String sourceStr){
    char[] temp= sourceStr.toCharArray();
        Arrays.sort(temp);
      return temp;

    }

    /***
     *
     * @param file
     * @param len
     * 往文件中写入随机字母
     */
   public static void WriteRandomCharacter(File file,long len){
       try(FileWriter fileWriter=new FileWriter(file)) {
           StringBuilder stringBuilder=new StringBuilder();
           for(int i=0;i<len;i++){
               stringBuilder.append(getChar('a','z'));
               if(i%11==0&&i!=0){
                   stringBuilder.append("\n");
                   fileWriter.write(stringBuilder.toString());
                   stringBuilder.delete(0,stringBuilder.length());
               }
           }
       } catch (IOException ioException) {
           ioException.printStackTrace();
       }
   }

    /***
     *
     * @param file
     * @param length
     * @throws IOException
     * 创建文件
     */
    public static void createFixLengthFile(File file, long length) throws IOException {
        FileOutputStream fos = null;
        FileChannel output = null;
        try {
            fos = new FileOutputStream(file);
            output = fos.getChannel();
            output.write(ByteBuffer.allocate(1), length-1);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static char  getChar(char low,char high){
        return (char)('a'+Math.random()*(high-low+1));
    }
}
enum FileSize{
    MB_one("1mb","1000000"),
    MB_ten("10mb","10000000"),
    GB_one("1gb","1000000000");
    private String text;
    private String byteNumber;
    FileSize(String text, String byteNum){
        this.byteNumber=byteNum;
        this.text=text;
    }
    public String getByteNumber(){
        return this.byteNumber;
    }
    public String getText(){
        return this.text;
    }

}
