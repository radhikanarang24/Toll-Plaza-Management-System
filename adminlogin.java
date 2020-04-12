import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class adminlogin extends JFrame implements ActionListener
{
ImageIcon img,loginimg,imgback;
JLabel labelpass,bg;
JPasswordField adminpass;
JButton submit,back;

public adminlogin()
{
setLayout(null);
getContentPane().setBackground(Color.BLACK);

Font f=new Font("Graduate",Font.BOLD,30);
img=new ImageIcon("TOLL1.jpg");
bg=new JLabel(img);
bg.setBounds(0,0,1400,710);

labelpass=new JLabel("ENTER PASSWORD");
labelpass.setFont(f);
labelpass.setBounds(440,200,350,70);

adminpass=new JPasswordField(10);
adminpass.setBounds(770,200,120,50);

loginimg=new ImageIcon("LOGIN.jpg");
submit=new JButton(loginimg);
submit.setBounds(630,600,92,50);
submit.addActionListener(this);

imgback=new ImageIcon("backarrowblue.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);
back.addActionListener(this);

add(submit);
add(labelpass);
add(adminpass);
add(back);
add(bg);
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==submit)
{
if((adminpass.getText().equals("admin")))
{
dispose();
adminscreen s5=new adminscreen();
s5.setVisible(true);
s5.setSize(1400,710);
s5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
else
{
JOptionPane.showMessageDialog(null,"Invalid Password");
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
adminlogin s3=new adminlogin();
s3.setVisible(true);
s3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
s3.setSize(1400,710);


}

}
