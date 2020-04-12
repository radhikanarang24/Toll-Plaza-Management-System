import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class modify_user extends JFrame implements ActionListener
{

JLabel backgr;
JLabel fname,lname,pwd,userid,contact;
JTextField txtfname,txtlname,txtcontact;
JPasswordField password;
JComboBox cmbuserid;

JButton update,delete,exit,back;
ImageIcon imgupdate,imgdelete,imgexit,imgback;
ImageIcon imgbg;

public modify_user()
{
setLayout(null);
getContentPane().setBackground(Color.BLACK);
Font f=new Font("lato",Font.BOLD,20);

cmbuserid=new JComboBox();
cid();
cmbuserid.setBackground(Color.WHITE);
cmbuserid.setBounds(700,250,200,30);
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
											txtfname.setText(""+rs.getString("firstname"));
											txtlname.setText(""+rs.getString("lastname"));
											password.setText(""+rs.getString("password"));
											txtcontact.setText(""+rs.getString("contact")); 
										}
										con.close();
										}
								catch(Exception e){
								System.out.println("Exception caught"+e);
										}
								}	
							});
										
									

fname=new JLabel("FIRST NAME");
fname.setBounds(500,300,200,30);
fname.setFont(f);
fname.setForeground(Color.WHITE);

txtfname=new JTextField(15);
txtfname.setBounds(700,300,200,30);

lname=new JLabel("LAST NAME");
lname.setBounds(500,350,200,30);
lname.setFont(f);
lname.setForeground(Color.WHITE);

txtlname=new JTextField(15);
txtlname.setBounds(700,350,200,30);

userid=new JLabel("USER ID");
userid.setBounds(500,250,200,30);
userid.setFont(f);
userid.setForeground(Color.WHITE);


pwd=new JLabel("PASSWORD");
pwd.setBounds(500,400,200,30);
pwd.setFont(f);
pwd.setForeground(Color.WHITE);

password=new JPasswordField(10);
password.setBounds(700,400,200,30);

contact=new JLabel("CONTACT");
contact.setBounds(500,450,200,30);
contact.setFont(f);
contact.setForeground(Color.WHITE);

txtcontact=new JTextField(15);
txtcontact.setBounds(700,450,200,30);

imgbg=new ImageIcon("modify_user.jpg");
backgr=new JLabel(imgbg);
backgr.setBounds(0,15,1400,710);

imgupdate=new ImageIcon("update.jpg");
update=new JButton(imgupdate);
update.setBounds(520,600,92,50);
update.addActionListener(this);

imgdelete=new ImageIcon("delete.jpg");
delete=new JButton(imgdelete);
delete.setBounds(660,600,92,50);
delete.addActionListener(this);

imgexit=new ImageIcon("exit.jpg");
exit=new JButton(imgexit);
exit.setBounds(800,600,92,50);
exit.addActionListener(this);

imgback=new ImageIcon("backarrow.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);
back.addActionListener(this);

add(fname);
add(txtfname);
add(lname);
add(txtlname);
add(cmbuserid);
add(userid);
add(pwd);
add(password);
add(contact);
add(txtcontact);
add(update);
add(delete);
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
	if(ae.getSource()==update)
	{
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:tollplazadsn");
		Statement stmt=con.createStatement();
		String queryupdate="Update tbluser set firstname='"+txtfname.getText()+"',lastname='"+txtlname.getText()+"',password='"+password.getText()+"',contact='"+txtcontact.getText().toString()+"' where userid='"+cmbuserid.getSelectedItem().toString()+"'";
		
		int x=stmt.executeUpdate(queryupdate);
		JOptionPane.showMessageDialog(null,"USER UPDATED");
		txtfname.setText("");
		txtlname.setText("");
		txtcontact.setText("");
		password.setText("");
		
		con.close();
		}
		catch(Exception e)
		{
			System.out.println("inside update:"+e);
		}
	}
	else if(ae.getSource()==delete)
	{
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:tollplazadsn");
		Statement stmt=con.createStatement();
		
		String querydel="Delete from tbluser where userid='"+cmbuserid.getSelectedItem().toString()+"'";
		int x=stmt.executeUpdate(querydel);
		JOptionPane.showMessageDialog(null,"USER DELETED");
		
		txtfname.setText("");
		txtlname.setText("");
		txtcontact.setText("");
		password.setText("");
		
		con.close();
		}
		catch(Exception e)
		{
			System.out.println("inside delete:"+e);
		}
		cmbuserid.removeAllItems();
		cid();
		
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
	modify_user mu=new modify_user();
	mu.setVisible(true);
	mu.setSize(1400,710);
	mu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	
}
}