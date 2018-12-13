package Hash;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 继承JPanel作为界面，设置监听
 * @Haxi
 */
class GraPanel extends JPanel implements ActionListener{
    public BufferedImage back;//背景图片
    public JButton b1,b2,b3,b4,b5;//按钮

    public JTextField textField,f1,f2,result;//文本区域
    public JTextField Textname,Textadress,Textnum;//文本区域
    public JTextField Tname,Tadress,Tnum;//文本区域

    public String PhoneNumber;//输入的电话号码
    public String Name;//输入的姓名
    public String Address;//输入的地址
    public Haxi haxi;//总哈希表

    public GraPanel(){
        haxi=new Haxi();
        try{
            back = ImageIO.read(MyTextField.class.getResource("BACK.jpg"));
        }catch (IOException e){}
        setButton();
        setTextField();
        this.add(f1);
        this.add(textField,BorderLayout.NORTH);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(f2);
        this.add(result);
        this.add(b4);
        this.add(b5);
    }

    public void SearchByName(){
        boolean flag=true;
        if(Name.length()>20){
            flag=false;
        }
        if(Name.contains("!") || Name.contains("@")||Name.contains("#")||
                Name.contains("$")||Name.contains("%")){
            flag=false;
        }
        for(int i=0;i<Name.length();i++){
            if(Name.charAt(i)>=0&&Name.charAt(i)<=9){
                flag=false;
                break;
            }
        }
        if(!flag){
            JOptionPane.showMessageDialog(null,
                    "请输入正确的姓名!","WARNING",JOptionPane.WARNING_MESSAGE);
            System.out.println("Error name!");
        }
    }

    public void SearchByNum(){
        boolean flag=true;
        if(PhoneNumber.length()>11){
            flag=false;
        }
        for(int i=0;i<PhoneNumber.length();i++){
            if(PhoneNumber.charAt(i)<'0' || PhoneNumber.charAt(i)>'9'){
                flag=false;
                break;
            }
        }
        if(!flag){
            JOptionPane.showMessageDialog(null,
                    "请输入正确的电话号码!","WARNING",JOptionPane.WARNING_MESSAGE);
            System.out.println("Error telephone number!");
        }
    }

    /**
     * 设置按钮
     */
    public void setButton(){
        Font font1=new Font("黑体",Font.BOLD,28);
        Font font2=new Font("黑体",Font.BOLD,28);
        MatteBorder border2=
                new MatteBorder(3,3,3,3,new Color(63, 55, 224));
        MatteBorder border=
                new MatteBorder(3,3,3,3,new Color(149, 8, 224));
        MatteBorder border3=
                new MatteBorder(3,3,3,3,new Color(101, 25, 224));

        b1=new JButton("按电话号码查询");
        b1.setFont(font1);
        b1.setPreferredSize(new Dimension(500,70));
        b1.setBackground(new Color(55, 201, 245));
        b1.setBorder(border);

        b2=new JButton("按姓名查询");
        b2.setPreferredSize(new Dimension(500,70));
        b2.setFont(font1);
        b2.setBackground(new Color(196, 147, 245));
        b2.setBorder(border2);
        b2.setOpaque(true);

        b3=new JButton("按地址查询");
        b3.setPreferredSize(new Dimension(500,70));
        b3.setFont(font1);
        b3.setBackground(new Color(245, 157, 144));
        b3.setBorder(border2);
        b3.setOpaque(true);

        b4=new JButton("添加");
        b4.setPreferredSize(new Dimension(500,70));
        b4.setFont(font1);
        b4.setBackground(new Color(245, 197, 74));
        b4.setBorder(border2);
        b4.setOpaque(true);

        b5=new JButton("退出");
        b5.setFont(font2);
        b5.setBackground(new Color(245, 244, 145));
        b5.setBorder(border3);
        b5.setPreferredSize(new Dimension(500,70));

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
    }

    /**
     * 设置文本域
     */
    public void setTextField(){
        Font font1=new Font("黑体",Font.BOLD,60);
        Font font2=new Font("黑体",Font.BOLD,35);
        Font font3=new Font("黑体",Font.BOLD,35);
        Font font4=new Font("黑体",Font.BOLD,35);
        MatteBorder border4
                =new MatteBorder(4,4,4,4,new Color(15, 3, 62));
        f1=new JTextField(" 欢迎进入电话查询系统");
        f2=new JTextField("---------查询结果--------");

        textField=new JTextField();
        Textnum=new JTextField();
        Textname=new JTextField();
        Textadress=new JTextField();
        Tadress=new JTextField("请输入地址");
        Tname=new JTextField("请输入姓名");
        Tnum=new JTextField("请输入电话号码");


        result=new JTextField();
        result.setPreferredSize(new Dimension(700,75));
        result.setBackground(new Color(143, 183, 217));
        result.setBorder(border4);
        result.setFont(font4);

        f1.setPreferredSize(new Dimension(700,70));
        f1.setBackground(new Color(76, 182, 194));
        f1.setFont(font1);
        MatteBorder border=
                new MatteBorder(5,5,5,5,new Color(15, 3, 62));
        f1.setBorder(border);
        f1.setEnabled(false);


        f2.setPreferredSize(new Dimension(500,70));
        f2.setFont(font2);
        f2.setBackground(new Color(95, 132, 208));
        f2.setBorder(border);
        f2.setEnabled(false);

        textField.setPreferredSize(new Dimension(700,75));
        textField.setFont(font3);
        textField.setBackground(new Color(87, 158, 217));

        textField.setBorder(border4);
    }

    /**
     * about buttons TODO action of order
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(b1==e.getSource()){
            PhoneNumber = textField.getText();
            SearchByNum();
            System.out.println("给定电话号码为："+PhoneNumber+"\n"
                    +"搜索到的号码数据为：");
            haxi.SerchKey_Phone(PhoneNumber);
            result.setText(haxi.Res_phone);
        }

        if(b2==e.getSource()){
            Name = textField.getText();
            SearchByName();
            System.out.println("给定姓名为："+Name+"\n"
                    +"搜索到的姓名数据为：");
            haxi.SerchKey_Name(Name);
            result.setText(haxi.Res_name);
        }

        if(b3==e.getSource()){
            Address = textField.getText();
            System.out.println("给定地址为："+Address+"\n"
                    +"搜索到的地址数据为：");
            haxi.SerchKey_Address(Address);
            result.setText(haxi.Res_address);
        }

        if(b4==e.getSource()){
            boolean flag=true;
            String name=JOptionPane.showInputDialog("请输入姓名");
            if(haxi.SerchKey_Name(name)){
                JOptionPane.showMessageDialog(null,"该名字已经存在",
                        "注意",JOptionPane.WARNING_MESSAGE);
                flag=false;
            }
            String phone=JOptionPane.showInputDialog("请输入电话号码");
            if(haxi.SerchKey_Phone(phone)){
                JOptionPane.showMessageDialog(null,"该号码已经存在",
                        "注意",JOptionPane.WARNING_MESSAGE);
                flag=false;
            }
            String address=JOptionPane.showInputDialog("请输入地址");
            if(haxi.SerchKey_Address(address)){
                JOptionPane.showMessageDialog(null,"该地址已经存在",
                        "注意",JOptionPane.WARNING_MESSAGE);
                flag=false;
            }
            if(flag)
            {
                UserData Data=new UserData();
                Data.name=name;
                Data.phoneNumber=phone;
                Data.address=address;
                haxi.length++;
                haxi.Insert(Data);/*
                haxi.CreateHashTable_Phone();
                haxi.CreateHashTable_Name();
                haxi.CreateHashTable_Address();*/
            }
        }

        if(b5==e.getSource()){
            System.exit(0);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(back,0,0,back.getWidth(),back.getHeight(),null);
    }
}

/**
 * 总体界面
 */
public class MyTextField{
    public JFrame frame;
    public BufferedImage ICON;

    public MyTextField(){
        try{
            ICON=ImageIO.read(MyTextField.class.getResource("icon.png"));
        }catch (IOException e){}
        frame=new JFrame("电话查询系统");
        frame.setSize(1000,751);
        frame.setIconImage(ICON);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[]args){
        GraPanel graPanel=new GraPanel();
        graPanel.haxi.InitialTable();
        MyTextField textField=new MyTextField();
        textField.frame.add(graPanel);
    }
}