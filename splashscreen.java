import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.Color;


public class splashscreen extends JFrame
{
JLabel backgr;
ImageIcon img;
JProgressBar prog;
int i;

/*public void paint(Graphics g)
{
g.setBackgroundColor(Color.black);
}*/


public splashscreen()
{
setLayout(null);
/*Container c=JFrame.getContentPane();*/
getContentPane().setBackground(Color.BLACK);

img=new ImageIcon("TLOC1.jpg");
backgr=new JLabel(img);
backgr.setBounds(0,0,1400,710);


prog=new JProgressBar(0,2000);
prog.setBounds(0,700,1400,5);
prog.setValue(0);
prog.setStringPainted(false);
prog.setForeground(Color.black);


add(prog);


add(backgr);
}

public void iterate()
{
while(i<=2000)
{
prog.setValue(i);
i=i+20;
try
{
Thread.sleep(150);
}
catch(Exception e)
{}
}

}

public static void main(String args[])
{
splashscreen s1=new splashscreen();
s1.setSize(1400,710);
s1.setVisible(true);
s1.iterate();
s1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

screen2 s2=new screen2();
s2.setVisible(true);
s2.setSize(1400,710);
s2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

s1.dispose();

}
}

