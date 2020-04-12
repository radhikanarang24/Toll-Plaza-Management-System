import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class change_password extends JFrame implements ActionListener
{

JLabel backgr;
JLabel userid,pwd,newpwd,confirmpwd;
JPasswordField password,pwd_new,pwd_confirm;
JComboBox cmbuserid;

JButton changepwd,exit,back;
ImageIcon imgchangepwd,imgexit,imgback;
ImageIcon imgbg;

public change_password()
{
setLayout(null);
getContentPane().setBackground(Color.BLACK);
Font f=new Font("lato",Font.BOLD,20);


userid=new JLabel("USER ID");
userid.setBounds(450,300,250,30);
userid.setFont(f);
userid.setForeground(Color.WHITE);

cmbuserid=new JComboBox();
cmbuserid.setBounds(700,300,200,30);
cid();
cmbuserid.addFocusListener(new FocusAdapter()
                            {
								public void focusLost(FocusEvent fe)
								{
									try{
										Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
										Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
										Statement stmt=con.createStatement();
										String queryid="Select * from tbluser where userid='"+cmbuserid.getSelectedItem()+"'";
										ResultSet rs=stmt.executeQuery(queryid);
										while(rs.next())
										{
											password.setText(""+rs.getString("password"));
											 
										}
										con.close();
										}
								catch(Exception e){
								System.out.println("Exception caught"+e);
										}
								}	
							});



pwd=new JLabel("CURRENT PASSWORD");
pwd.setBounds(450,350,250,30);
pwd.setFont(f);
pwd.setForeground(Color.WHITE);

password=new JPasswordField(10);
password.setBounds(700,350,200,30);

confirmpwd=new JLabel("CONFIRM PASSWORD");
confirmpwd.setBounds(450,450,250,30);
confirmpwd.setFont(f);
confirmpwd.setForeground(Color.WHITE);

pwd_confirm=new JPasswordField(10);
pwd_confirm.setBounds(700,450,200,30);

newpwd=new JLabel("NEW PASSWORD");
newpwd.setBounds(450,400,250,30);
newpwd.setFont(f);
newpwd.setForeground(Color.WHITE);

pwd_new=new JPasswordField(10);
pwd_new.setBounds(700,400,200,30);

imgbg=new ImageIcon("change_pwd.jpg");
backgr=new JLabel(imgbg);
backgr.setBounds(0,15,1400,710);

imgchangepwd=new ImageIcon("change.jpg");
changepwd=new JButton(imgchangepwd);
changepwd.setBounds(550,600,92,50);
changepwd.addActionListener(this);

imgexit=new ImageIcon("exit.jpg");
exit=new JButton(imgexit);
exit.setBounds(750,600,92,50);
exit.addActionListener(this);

imgback=new ImageIcon("backarrow.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);
back.addActionListener(this);

add(userid);
add(cmbuserid);
add(pwd);
add(password);
add(confirmpwd);
add(pwd_confirm);
add(newpwd);
add(pwd_new);
add(changepwd);
add(exit);
add(back);
add(backgr);
}

public void cid()
{
	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
		Statement stmt=con.createStatement();
		String querycid="Select userid from tbluser";
		ResultSet rs=stmt.executeQuery(querycid);
		while(rs.next())
		{
				cmbuserid.addItem(rs.getString("userid"));
		}
		con.close();
		
	}
	
	catch(Exception e)
	{
		System.out.println("inside cid:"+e);
	}
	
}

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource()==changepwd)
	{
		if(pwd_new.getText().equals(pwd_confirm.getText()))
		{
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:tollplazadsn");
		Statement stmt=con.createStatement();
		String queryupdate="Update tbluser set password='"+pwd_new.getText()+"'where userid='"+cmbuserid.getSelectedItem().toString()+"'";
		
		int x=stmt.executeUpdate(queryupdate);
		JOptionPane.showMessageDialog(null,"PASSWORD UPDATED");
		password.setText("");
		pwd_confirm.setText("");
		pwd_new.setText("");
		con.close();
		}
		catch(Exception e)
		{
			System.out.println("inside change_password:"+e);
		}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"PASSWORDS DO NOT MATCH!");
		}

	
	}
	else if(ae.getSource()==exit)
	{
		System.exit(0);
	}
	else if(ae.getSource()==back)
	{
	dispose();
	adminscreen s5=new adminscreen();
	s5.setVisible(true);
	s5.setSize(1400,710);
	s5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

public static void main(String args[])
{
	change_password cp=new change_password();
	cp.setVisible(true);
	cp.setSize(1400,710);
	cp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

}
