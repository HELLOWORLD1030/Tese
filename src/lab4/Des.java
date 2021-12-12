package lab4;
public class Des{
    int[] byteKey;
    public Des(int[] byteKey) {
        this.byteKey = byteKey;
    }
    //=====初始置换表=====
    private static final int[] IP = {58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,
            62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,
            57,49,41,33,25,17, 9,1,59,51,43,35,27,19,11,3,
            61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
    //=====终止置换表=====
    private static final int[] IP_1={40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,
            38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,
            36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,
            34,2,42,10,50,18,58,26,33,1,41, 9,49,17,57,25};
    //=====扩展置换表====
    private static final int[] PC_1 = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10,11,
            12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,
            22,23,24,25,24,25,26,27,28,29,28,29,30,31,32, 1};
    //=====直接置换表=====
    private static final int[] PC_2= {16,7,20,21,29,12,28,17, 1,15,23,26, 5,18,31,10,
            2,8,24,14,32,27, 3, 9,19,13,30, 6,22,11, 4,25};
    //=====压缩置换1======
    private static final int[]E= {57,49,41,33,25,17,9,1,58,50,42,34,26,18,
            10,2,59,51,43,35,27,19,11,3,60,52,44,36,
            63,55,47,39,31,23,15,7,62,54,46,38,30,22,
            14,6,61,53,45,37,29,21,13,5,28,20,12,4};
    //=====压缩置换2======
    private static final int[] P= {14,17,11,24, 1, 5, 3,28,15, 6,21,10,
            23,19,12, 4,26, 8,16, 7,27,20,13, 2,
            41,52,31,37,47,55,30,40,51,45,33,48,
            44,49,39,56,34,53,46,42,50,36,29,32};
    private static final int[][][]S_Box = {// s-盒
            {//s_box[1]
                    {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                    {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                    {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                    {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},
            {//s-box[2]
                    {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                    {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                    {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                    {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},
            {//s-box[3]
                    {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                    {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                    {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},
            {//s_box[4]
                    {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                    {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                    {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                    {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},
            {//s_box[5]
                    {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9,
                            14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6,
                            4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14,
                            11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},
            {//s_box[6]
                    {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                    {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                    {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                    {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},
            {//s_box[7]
                    {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                    {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                    {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                    {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},
            {//s_box[8]
                    {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                    {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                    {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                    {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}};
    private static final int[] LeftMove= {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
    public int[] lpReplace(int[] data){
        int[] ipMingWen = new int[64];
        for (int i = 0; i < ipMingWen.length; i++){
            ipMingWen[i] = data[IP[i]-1];
        }
        return ipMingWen;
    }
    public static int[] StringToBits(String data){
        byte[] test = data.getBytes();
        int[] IntVa = new int[64];
        int[] IntDa = new int[8];
        for (int i = 0; i <8; i++){
            IntDa[i]=test[i];
            if (IntDa[i]<0){
                IntDa[i]+= 256;
                IntDa[i]%=256;
            }
        }
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                IntVa[((i*8)+7)- j]=IntDa[i] % 2;
                IntDa[i]=IntDa[i]/ 2;
            }
        }
        return IntVa;
    }
    public int[] leftMove(int[] key,int offset) {
        int[] subKey = new int[56];
        int[] c0 = new int[28];
        int[] D0 = new int[28];
        int[] C1 = new int[28];
        int[] D1 = new int[28];
        for (int i = 0; i <28; i++){
            c0[i] = key[i];
            D0[i]= key[i+28];
        }
        if(offset == 1){
            for(int i = 0; i< 27;i++){
                C1[i]=c0[i+1];
                D1[i]=D0[i+1];
            }
            C1[27] = c0[0];
            D1[27] = D0[0];}
        else if(offset == 2)
        {
            for (int i = 0; i<26; i++){
                C1[i]=c0[i+2];
                D1[i]=D0[i+2];
            }
            C1[26]= c0[0];
            D1[26] = D0[0];
            C1[27]= c0[1];
            D1[27]= D0[1];
        }
        for (int i = 0; i<28; i++){
            subKey[i]= C1[i];
            subKey[i+28]= D1[i];
        }
        return subKey;
    }
    public int[][] SubKeyGenerate(int[] key){
        int[][]subKeyArrayTemp = new int[16][56];
        int[][] subKey = new int[16][48];
        int[] K0 = new int[56];//特别注意: xxx[IP[i]-1]等类似变
//对64位的密钥进行pc-1置换变成56位的密钥
        for (int i = 0; i < 56; i++){
            K0[i]= key[PC_1[i]-1];//密钥进行PC-1变换
        }
//由经过pc-1置换生成的56位密钥经过16轮左移，pc位的子密钥
        for (int i=0; i<16; i++){
            subKeyArrayTemp[i]=leftMove(K0,LeftMove[i]);
            System.arraycopy(subKeyArrayTemp[i],0, K0,0,56);
        }
        for (int i = 0; i< 16; i++){
            for (int j = 0; j< 48; j++){
                subKey[i][i] = subKeyArrayTemp[i][PC_2[j]-1];
            }
        }
        return subKey;
    }
    public int[]EExpend(int[] data){
        int[] dataExpend = new int[48];
        for (int i = 0; i<dataExpend.length; i++){
            dataExpend[i] = data[E[i]-1];
        }
        return dataExpend;
    }
    public int[]XOR(int[] data1,int[] data2){
        int[] XORResult = new int[data1.length];
        for (int i = 0; i < data1.length; i++){
            XORResult[i]= data1[i]+data2[i];
            if(XORResult[i] == 2){
                XORResult[i]=0;
            }
        }
        return XORResult;
    }
    public static int[] SBoxReplace(int[] temp){
        int[] sBoxResult = new int[32];//存储经过s盒什
        int[] pReplaceResult = new int[32];//存储经过
        int[][]tempArray = new int[8][6];
        int[] tempRowTemp = new int[8];
        int roundCount = temp.length/6;
//将生成的8个数(每个都是经过s盒代换后的十进序存储到结果数组中
        for (int i = 0; i< roundCount; i++){
            for (int j = 0; j<6; j++){
                tempArray[i][j]=temp[(i*6)+j];
            }
            tempRowTemp[i]=S_Box[i][(tempArray[i][0]<<1)+(tempArray[i][5])]
                    [(tempArray[i][1]<<3)+(tempArray[i][2]<<2)+(tempArray[i][3]<<1)+(tempArray[i][4])];
            for(int j=0;j<4;j++) {
                sBoxResult[(i*4+3)-j]=tempRowTemp[i]%2;
                tempRowTemp[i]= tempRowTemp[i]/2;
            }
        }
        for (int i =0; i< 32; i++){
            pReplaceResult[i]= sBoxResult[P[i]-1];
        }
        return pReplaceResult;
    }
    public int[] lp1Replace(int[] data){
        int[] ipMingWen = new int[64];
        for (int i = 0; i < ipMingWen.length; i++){
            ipMingWen[i] = data[IP_1[i]-1];
        }
        return ipMingWen;
    }
    public int[] EnAndDecrypt(int[] data, int flag){
        int[] mingwen = lpReplace(data);
        int[] mingwenTemp = new int[64];
        int[] miwen = lpReplace(data);
        int[] miwenTemp = new int[64];
        int[][] keyArray = SubKeyGenerate(byteKey);
        int[] temp = new int[48];
        int[] tempSBox = new int[32];
        int[] L0 = new int[32];
        int[] R0 = new int[32];
        int[] L1 = new int[32];
        int[] R1 = new int[32];
        int[]ER0= new int[48];
        if(1 == flag){
            System.arraycopy(mingwen, 0,L0,0,32);
            System.arraycopy(mingwen, 32,R0,0,32);
            for (int i = 0; i<16; i++){
                System.arraycopy(R0,0,L1,0,32);
                R0 = EExpend(R0);
                temp = XOR(R0,keyArray[i]);
                tempSBox = SBoxReplace(temp);
                R1= XOR(L0,tempSBox);
                System.arraycopy(L1,0, L0,0,32);
                System.arraycopy(R1,0,R0,0,32);
            }
            for (int i = 0; i< 32; i++){
                mingwenTemp[i]=R1[i];
                mingwenTemp[i+32]= L1[i];
            }
            mingwen = lp1Replace(mingwenTemp);}
        else if(0 == flag) {
            System.arraycopy(mingwen,0, L0,0,32);
            System.arraycopy(mingwen, 32,R0,0,32);
            for (int i = 0; i<16; i++){
                System.arraycopy(R0,0,L1,0,32);
                R0 = EExpend(R0);
                temp = XOR(R0,keyArray[15- i]);
                tempSBox = SBoxReplace(temp);
                R1 = XOR(L0,tempSBox);
                System.arraycopy(L1,0,L0,0,32);
                System.arraycopy(R1,0,R0,0,32);
            }
            for (int i = 0; i< 32; i++){
                miwenTemp[i]=R1[i];
                miwenTemp[i+32]=L1[i];
            }
            miwen = lp1Replace(miwenTemp);
        }
        return (flag==1)?mingwen:miwen;
    }
    public static void main(String[] args) {
        String dataString = "10101001";
        System.out.println("明文为: "+dataString);
        int[] data = StringToBits(dataString);
        System.out.println("明文的二进制表示为:");
        for (int i = 0; i< 64; i++){
            System.out.print(data[i]);
        }
        System.out.println();
        String keyString = "10101010";
        System.out.println("密钥为: "+keyString);int[] key = StringToBits(dataString);
        System.out.println("密钥的二进制表示为: ");
        for (int i=0; i< 64; i++){
            System.out.print(key[i]);
        }
        System.out.println();
        Des des = new Des(key);
        int[] result = des.EnAndDecrypt(data,1);
        System.out.print("加密后的密文的二进制为: ");
        for (int i = 0; i< result.length; i++){
            System.out.print(result[i]);
        }
        System.out.println();
        int[] testResult = des.EnAndDecrypt(result,0);
        System.out.print("还原为明文为:");
        for (int i = 0; i < testResult.length; i++) {
            System.out.print(testResult[i]);
        }
    }
}
