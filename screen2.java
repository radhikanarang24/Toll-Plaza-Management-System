import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;

public class screen2 extends JFrame implements ActionListener
{
ImageIcon img1;
JLabel back,labelpass;
JRadioButton rbuser,rbadmin;
JPasswordField adminpass;

public screen2()
{
setLayout(null);
getContentPane().setBackground(Color.BLACK);
Font f=new Font("Special Elite",Font.BOLD,20);
img1=new ImageIcon("TOLL.jpg");
back=new JLabel(img1);
back.setBounds(0,0,1400,710);

rbuser=new JRadioButton("USER");
rbuser.addActionListener(this);
rbuser.setFont(f);
rbuser.setBounds(900,200,90,35);
rbuser.setBackground(Color.BLACK);
rbuser.setForeground(Color.WHITE);

rbadmin=new JRadioButton("ADMIN");
rbadmin.addActionListener(this);
rbadmin.setFont(f);
rbadmin.setBounds(900,270,90,35);
rbadmin.setBackground(Color.BLACK);
rbadmin.setForeground(Color.WHITE);

rbuser.setSelected(true);


ButtonGroup bg=new ButtonGroup();
bg.add(rbuser);
bg.add(rbadmin);

add(rbuser);
add(rbadmin);
add(back);
}

public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==rbadmin)
{
dispose();
adminlogin s3=new adminlogin();
s3.setVisible(true);
s3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
s3.setSize(1400,710);
}
else if(ae.getSource()==rbuser)
{
dispose();
userlogin s4=new userlogin();
s4.setVisible(true);
s4.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
s4.setSize(1400,710);
}


}


public static void main(String args[])
{
screen2 s2=new screen2();
s2.setVisible(true);
s2.setSize(1400,710);
s2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

}
}

