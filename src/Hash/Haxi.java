package Hash;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Haxi {
    public int times =1;//
    public int length;//用户数据表长度
    public final int size=97;//表长

    public int TotalConflict;
    public Hash Hashtable_phone;//电话号码哈希表
    public Hash Hashtable_name;//姓名哈希表
    public Hash Hashtable_address;//地址哈希表

    public UserData []record;//用户的信息，线性结构存储

    public String Res_phone;//电话号码搜索结果
    public String Res_name;//姓名搜索结果
    public String Res_address;//地址搜索结果

    public int phone_key;//电话号码获得的key
    public int name_key;//姓名获得的key
    public int address_key;//地址获得的key

    //初始的电话簿文件 TeleTable.txt
    public final String TeleTable="D:\\java_test\\HashFrame" +
            "\\src\\Hash\\TeleTable.txt";
    public File file; //文件
    public BufferedReader reader = null;//读取文件

    /**
     * 构造函数
     * 定义及初始化
     * 电话号码哈希表初始化
     * 地址哈希表初始化
     * 姓名哈希表初始化
     */
    public Haxi(){
        file=new File(TeleTable);
        Hashtable_phone = new Hash();
        Hashtable_name =new Hash();
        Hashtable_address = new Hash();

        record = new UserData[size];

        for(int i=0;i<size;i++) {
            record[i]=new UserData();
        }

        Hashtable_phone.data=new UserData[size];

        for(int i=0;i<size;i++) {
            Hashtable_phone.data[i]=new UserData();
        }
        Hashtable_phone.size=size;
        Hashtable_phone.cnt=0;

        Hashtable_address.data=new UserData[size];
        for(int i=0;i<size;i++) {
            Hashtable_address.data[i]=new UserData();
        }
        Hashtable_address.size=size;
        Hashtable_address.cnt=0;

        Hashtable_name.data=new UserData[size];
        for(int i=0;i<size;i++) {
            Hashtable_name.data[i]=new UserData();
        }
        Hashtable_name.size=size;
        Hashtable_name.cnt=0;
        TotalConflict=0;
    }

    /**
     * 输入资料,直接从文件读入
     */
    public void InitialTable() {
        try{
            reader = new BufferedReader(new FileReader(file));
            String temp;
            int i=0,times=1;
            while ((temp=reader.readLine())!=null) {
                if(times%3==1){
                    record[i].phoneNumber=temp;
                }
                else if(times%3==2){
                    record[i].name=temp;
                }
                else{
                    record[i].address=temp;
                    i++;
                }
                times++;
            }
            length =i;
            reader.close();
        }catch (IOException e){}

        /**
         * 创建哈希表
         */
        CreateHashTable_Phone();
        CreateHashTable_Name();
        CreateHashTable_Address();
        /**
         * 输出
         */
        for(int i=0;i<size;i++) {
            System.out.println(i+" "+Hashtable_phone.data[i].phoneNumber+" "
                    +Hashtable_phone.data[i].name+" "+Hashtable_phone.data[i].address);
        }
        System.out.println(TotalConflict);
    }

    public void Insert(UserData Data){
        phone_key=GetHashKey_Phone(Data.phoneNumber);
        if(Hashtable_phone.data[phone_key].name.charAt(0)!='0')
            phone_key= HandleConflict(Hashtable_phone,phone_key);

        Hashtable_phone.data[phone_key].phoneNumber=Data.phoneNumber;
        Hashtable_phone.data[phone_key].name=Data.name;
        Hashtable_phone.data[phone_key].address=Data.address;

        name_key=GetHashKey_Name(Data.name);
        if(Hashtable_name.data[name_key].name.charAt(0)!='0')
            name_key= HandleConflict(Hashtable_name,name_key);

        Hashtable_name.data[name_key].phoneNumber=Data.phoneNumber;
        Hashtable_name.data[name_key].name=Data.name;
        Hashtable_name.data[name_key].address=Data.address;

        address_key = GetHashKey_Address(Data.address);
        if(Hashtable_address.data[address_key].name.charAt(0)!='0')
            address_key= HandleConflict(Hashtable_address,address_key);

        Hashtable_address.data[address_key].phoneNumber=Data.phoneNumber;
        Hashtable_address.data[address_key].name=Data.name;
        Hashtable_address.data[address_key].address=Data.address;

    }
    /**根据电话号码获得key
     * @param phone
     * @return
     */
    public int GetHashKey_Phone(String phone) {
        int len = phone.length();
        int key=0,t1;
        for(int i=7;i<len;i++){
            t1 = Integer.parseInt(String.valueOf(phone.charAt(i)));
            if(i==7){
                key+=t1*1000;
            }
            else if(i==8) {
                key+=t1*100;
            }
            else if(i==9) {
                key+=t1*10;
            }
            else if(i==10) {
                key+=t1;
            }
        }
        return key%size;
    }
    /**根据姓名获得key
     * @param Name
     * @return
     */
    public int GetHashKey_Name(String Name) {
        int len=Name.length();
        int key=0;
        for(int i=0;i<len;i++) {
            int temp=(int)Name.charAt(i);
            key+=temp;
        }
        return key%size;
    }
    /**
     * 根据地址获得key
     * @param Address
     * @return
     */
    public int GetHashKey_Address(String Address) {
        int len=Address.length();
        int key=0;
        for(int i=0;i<len;i++) {
            int temp=(int)Address.charAt(i);
            key+=temp;
        }
        return key%size;
    }

    /**
     *根据电话号码构建哈希表
     */
    public void CreateHashTable_Phone() {
        for(int i = 0; i< length; i++){
            phone_key=GetHashKey_Phone(record[i].phoneNumber);
            if(Hashtable_phone.data[phone_key].name.charAt(0)!='0')
                phone_key= HandleConflict(Hashtable_phone,phone_key);

            Hashtable_phone.data[phone_key].phoneNumber=record[i].phoneNumber;
            Hashtable_phone.data[phone_key].name=record[i].name;
            Hashtable_phone.data[phone_key].address=record[i].address;
        }
    }
    /**
     *根据姓名构建哈希表
     */
    public void CreateHashTable_Name() {
        for(int i = 0; i< length; i++){
            name_key=GetHashKey_Name(record[i].name);
            if(Hashtable_name.data[name_key].name.charAt(0)!='0')
                name_key= HandleConflict(Hashtable_name,name_key);

            Hashtable_name.data[name_key].phoneNumber=record[i].phoneNumber;
            Hashtable_name.data[name_key].name=record[i].name;
            Hashtable_name.data[name_key].address=record[i].address;
        }
    }

    /**
     *根据地址构建哈希表
     */
    public void CreateHashTable_Address() {
        for(int i = 0; i< length; i++){
            address_key = GetHashKey_Address(record[i].address);
            if(Hashtable_address.data[address_key].name.charAt(0)!='0')
                address_key= HandleConflict(Hashtable_address,address_key);

            Hashtable_address.data[address_key].phoneNumber=record[i].phoneNumber;
            Hashtable_address.data[address_key].name=record[i].name;
            Hashtable_address.data[address_key].address=record[i].address;
        }
    }

    /**
     * 冲突处理，二次探测再散列
     * @param HashTable
     * @param key
     * @return
     */
    public int HandleConflict(Hash HashTable, int key) {
        times = 1; //从2,3,4,5,.......
        while(true){
            times++; //从2,3,4,5,.......
            TotalConflict++;
            if(times %2==0) {
                if(HashTable.data[(key+(times /2)*(times /2))%50].name.charAt(0)=='0')
                    return (key+(times /2)*(times /2))%50;
            }
            else if(times %2!=0) {
                if((key-(times /2)*(times /2))<0)
                    continue;//由于是减法，要注意负数不能取模
                if(HashTable.data[(key-(times /2)*(times /2))%50].name.charAt(0)=='0')
                    return (key-(times /2)*(times /2))%50;
            }
        }
    }
    /**
     * 根据电话号码查询
     * @param PhoneNumber
     */
    public boolean SearchKey_Phone(String PhoneNumber) {
        phone_key=GetHashKey_Phone(PhoneNumber);
        if(!Hashtable_phone.data[phone_key].phoneNumber.equals(PhoneNumber)){
            for(times = 1; times < size; times++){
                if(times %2==0) {
                    if(PhoneNumber.equals(Hashtable_phone.data[(phone_key+(times /2)*(times /2))%size].phoneNumber)){
                        phone_key= (phone_key+(times /2)*(times /2))%size;
                        break;
                    }
                }
                else if(times %2!=0) {
                    if((phone_key-(times /2)*(times /2))<0)
                        continue;//由于是减法，要注意负数不能取模
                    if(PhoneNumber.equals(Hashtable_phone.data[(phone_key-(times/2)*(times/2))%size].phoneNumber)){
                        phone_key = (phone_key-(times /2)*(times /2))%size;
                        break;
                    }
                }
            }
        }
        if(Hashtable_phone.data[phone_key].phoneNumber.equals(PhoneNumber)){
            Res_phone=Hashtable_phone.data[phone_key].name+"--"+Hashtable_phone.data[phone_key].phoneNumber
                    +"--"+Hashtable_phone.data[phone_key].address;
            System.out.println(Res_phone);
            return true;
        }
        else{
            Res_phone="该号码不在名单上";
            System.out.println(Res_name);
            return false;
        }
    }

    /**
     * 根据姓名查询
     * @param Name
     * @return
     */
    public boolean SearchKey_Name(String Name)
    {
        name_key=GetHashKey_Name(Name);
        if(!Hashtable_name.data[name_key].name.equals(Name)){
            for(times = 1; times < size; times++){
                if(times %2==0) {
                    if(Name.equals(Hashtable_name.data[(name_key+(times /2)*(times /2))%size].name)){
                        name_key= (name_key+(times /2)*(times /2))%size;
                        break;
                    }
                }
                else if(times %2!=0) {
                    if((name_key-(times /2)*(times /2))<0) continue;//由于是减法，要注意负数不能取模
                    if(Name.equals(Hashtable_name.data[(name_key-(times /2)*(times /2))%size].name)){
                        name_key= (name_key-(times /2)*(times /2))%size;
                        break;
                    }
                }
            }
        }
        if(Hashtable_name.data[name_key].name.equals(Name)){
            Res_name=Hashtable_name.data[name_key].name+"--"+Hashtable_name.data[name_key].phoneNumber
                    +"--"+Hashtable_name.data[name_key].address;
            System.out.println(Res_name);
            return true;
        }
        else{
            Res_name="该名字不在名单上";
            System.out.println(Res_name);
            return false;
        }
    }

    /**
     * 根据地址查询
     * @param Address
     * @return
     */
    public boolean SearchKey_Address(String Address)
    {
        address_key =GetHashKey_Address(Address);
        if(!Hashtable_address.data[address_key].address.equals(Address)){
            for(times =1; times < size; times++){
                if(times %2==0) {
                    if(Address.equals(Hashtable_address.data[(address_key +(times /2)*(times /2))%size].address)){
                        address_key = (address_key +(times /2)*(times /2))%size;
                        break;
                    }
                }
                else if(times %2!=0) {
                    if((address_key -(times /2)*(times /2))<0)
                        continue;//由于是减法，要注意负数不能取模
                    if(Address.equals(Hashtable_address.data[(address_key -(times /2)*
                            (times /2))%size].address)){
                        address_key = (address_key -(times /2)*(times /2))%size;
                        break;
                    }
                }
            }
        }
        if(Hashtable_address.data[address_key].address.equals(Address)){
            Res_address =Hashtable_address.data[address_key].name+"--"+
                    Hashtable_address.data[address_key].phoneNumber
                    +"--"+Hashtable_address.data[address_key].address;
            System.out.println(Res_address);
            return true;
        }
        else{
            Res_address="该地址不在名单上";
            System.out.println(Res_address);
            return false;
        }
    }
}

/**
 * 用户数据，作为一个类
 * 初始时全部作为‘0’
 * @Haxi
 */
class UserData{
    String phoneNumber;
    String name;
    String address;
    UserData(){
        phoneNumber="0";
        name="0";
        address="0";
    }
}

/**哈希表，作为一个线性结构
 * 存储表长
 * @UserData
 * @Haxi
 */
class Hash{
    UserData data[];
    int cnt;
    int size;
}