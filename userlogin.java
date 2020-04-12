import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class userlogin extends JFrame implements ActionListener
{
ImageIcon img,loginimg,imgback;
JLabel backgr,username,password;
JPasswordField userpassword;
JButton login,back;
JTextField userfield;

public userlogin()
{
setLayout(null);
getContentPane().setBackground(Color.BLACK);

Color customcolor=new Color(58,151,212);
Font f=new Font("Special Elite",Font.BOLD,30);
img=new ImageIcon("TOLL2.jpg");
backgr=new JLabel(img);
backgr.setBounds(0,0,1400,700);

username=new JLabel("USERNAME");
username.setFont(f);
username.setForeground(customcolor);
username.setBounds(500,420,200,50);
add(username);

userfield=new JTextField(10);
userfield.setBounds(700,420,200,50);
add(userfield);

password=new JLabel("PASSWORD");
password.setFont(f);
password.setBounds(500,520,200,50);
password.setForeground(customcolor);
add(password);

userpassword=new JPasswordField(10);
userpassword.setFont(f);
userpassword.setBounds(700,520,200,50);
add(userpassword);

loginimg=new ImageIcon("LOGIN1.jpg");
login=new JButton(loginimg);
login.setBounds(650,620,92,50);
login.addActionListener(this);

imgback=new ImageIcon("backarrowblue.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);
back.addActionListener(this);

add(back);

add(login);

add(backgr);
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==login)
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
Statement stmt=con.createStatement();
String query="Select * from tbluser where userid='"+userfield.getText()+"'and password='"+userpassword.getText()+"'";
ResultSet rs=stmt.executeQuery(query);

int flag=0;
while(rs.next())
{
	flag=1;
}
if(flag==0)
{	
JOptionPane.showMessageDialog(null,"Incorrect Username or Password!");
userfield.setText("");
userpassword.setText("");
}
else
{
 JOptionPane.showMessageDialog(null,"Login Successful");
 dispose();
 linking_user lu=new linking_user();
 lu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 lu.setVisible(true);
 lu.setSize(1400,730);
 lu.setTitle("Linking User");
		
                              	
	
}
}
catch(Exception e)
{
	System.out.println(""+e);
}
}
else if(ae.getSource()==back)
{
dispose();
screen2 s2=new screen2();
s2.setVisible(true);
s2.setSize(1400,710);
s2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}

public static void main(String args[])
{
userlogin s4=new userlogin();
s4.setVisible(true);
s4.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
s4.setSize(1400,710);
}

}