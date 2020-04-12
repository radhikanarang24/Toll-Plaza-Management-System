import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class adminscreen extends JFrame implements ActionListener
{
JMenuBar mbar;
JMenu muser,mreport,mquit,mpass,mrate;
JMenuItem create_user,modify_user,change_pwd,update_rate,insert_rate,create_pass,rep_user,rep_rate,rep_pass,rep_veh,quit;
JPanel panel1,panel2;
JLabel bg;
ImageIcon imgbg;
/*int i;
imgs[5]={"slide1.jpg","slide2.jpg","slide3.jpg","slide4.jpg","slide5.jpg"};*/

public adminscreen()
{
setLayout(new BorderLayout());
getContentPane().setBackground(Color.BLACK);

mbar=new JMenuBar();
setJMenuBar(mbar);

muser=new JMenu("User");
mbar.add(muser);
 
	create_user=new JMenuItem("Create User",new ImageIcon("createuser.png"));
	muser.add(create_user);
	create_user.addActionListener(this);

	modify_user=new JMenuItem("Modify User",new ImageIcon("modifyuser.png"));
	modify_user.addActionListener(this);
	muser.add(modify_user);

	change_pwd=new JMenuItem("Change Password",new ImageIcon("changepwd.png"));
	change_pwd.addActionListener(this);
	muser.add(change_pwd);

	
mrate=new JMenu("RateList");
mbar.add(mrate);
	
	update_rate=new JMenuItem("Modify",new ImageIcon("modifyrate.png"));
	update_rate.addActionListener(this);
	mrate.add(update_rate); 

	insert_rate=new JMenuItem("Insert",new ImageIcon("insertrate.png"));
	insert_rate.addActionListener(this);
	mrate.add(insert_rate);

mpass=new JMenu("Pass");
mbar.add(mpass);

	create_pass=new JMenuItem("Generate Pass",new ImageIcon("genpass.png"));
	create_pass.addActionListener(this);
	mpass.add(create_pass); 

	
mreport=new JMenu("Report");
mbar.add(mreport);

	rep_user=new JMenuItem("User",new ImageIcon("reportuser.png"));
	rep_user.addActionListener(this);
	mreport.add(rep_user);

	rep_rate=new JMenuItem("Rate",new ImageIcon("reportrate.png"));
	rep_rate.addActionListener(this);
	mreport.add(rep_rate);
 
	rep_pass=new JMenuItem("PassHolders",new ImageIcon("reportpass.png"));
	rep_pass.addActionListener(this);
	mreport.add(rep_pass);

	rep_veh=new JMenuItem("Vehicles",new ImageIcon("reportvehicle.jpg"));
	rep_veh.addActionListener(this);
	mreport.add(rep_veh);

mquit=new JMenu("Quit");
mbar.add(mquit);
	
	quit=new JMenuItem("Quit",new ImageIcon("exit.png"));
	quit.addActionListener(this);
	mquit.add(quit);
	
	imgbg=new ImageIcon("bg_menu.jpg");
        bg=new JLabel(imgbg);
        bg.setBounds(0,15,1400,710);
		add(bg);
	/*slideshow=new JLabel(ic);
	slideshow.setBounds(150,200,400,300);
	add(slideshow);
	Thread t=new Thread(this);
	t.start();*/

}

/*
public void run()
{
	while(true)
	{
		i=(int)(Math.random()*10);
		if(i>=0 && i<=4)
		{
			ic=new ImageIcon(imgs[i]);
		}
		slideshow.setIcon(ic);
		try
		{
			Thread.sleep(1000);
			
		}
		catch(Exception e)
		{}
		
	}
	
	
}*/
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==create_user)
{
	dispose();
	create_newuser cn=new create_newuser();
	cn.setVisible(true);
	cn.setSize(1400,710);
	cn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}
else if(ae.getSource()==modify_user)
{
	dispose();
	modify_user mu=new modify_user();
	mu.setVisible(true);
	mu.setSize(1400,710);
	mu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
}
else if(ae.getSource()==change_pwd)
{
	dispose();
	change_password cp=new change_password();
	cp.setVisible(true);
	cp.setSize(1400,710);
	cp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}
else if(ae.getSource()==update_rate)
{
		dispose();
		modify_rate mr=new modify_rate();
		mr.setSize(1400,710);
		mr.setVisible(true);
		mr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}
else if(ae.getSource()==insert_rate)
{
			dispose();
			insert_rate ir=new insert_rate();
			ir.setVisible(true);
			ir.setSize(1400,710);
			ir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
else if(ae.getSource()==create_pass)
{
	  dispose();
	  generate_pass gen=new generate_pass();
	  gen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  gen.setSize(1400,730);
	  gen.setVisible(true);

}
else if(ae.getSource()==rep_user)
{
	dispose();
	report_user log=new report_user();
    log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    log.setVisible(true);
    log.setSize(1400,730);}
else if(ae.getSource()==rep_rate)
{
	dispose();
	report_rate log2=new report_rate();
    log2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    log2.setVisible(true);
    log2.setSize(1400,730);
}
else if(ae.getSource()==rep_pass)
{
	dispose();
	report_pass log3=new report_pass();
    log3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    log3.setVisible(true);
    log3.setSize(1400,730);
	
	
}
else if(ae.getSource()==rep_veh)
{
	dispose();
	report_veh log4=new report_veh();
    log4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    log4.setVisible(true);
    log4.setSize(1400,730);
	
	
}
else if(ae.getSource()==quit)
{
	System.exit(0);
}
}

public static void main(String args[])
{
adminscreen s5=new adminscreen();
s5.setVisible(true);
s5.setSize(1400,710);
s5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}




	