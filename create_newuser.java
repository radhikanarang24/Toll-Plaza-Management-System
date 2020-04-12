import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class create_newuser extends JFrame implements ActionListener
{

JLabel backgr;
JLabel fname,lname,userid,pwd,secq,seca,contact;
JTextField txtfname,txtlname,txtuserid,txtseca,txtcontact;
JPasswordField password;
JComboBox cmbsecq;

JButton create,exit,back;
ImageIcon imgcreate,imgexit,imgback;
ImageIcon imgbg;

public create_newuser()
{
setLayout(null);
getContentPane().setBackground(Color.BLACK);
Font f=new Font("lato",Font.BOLD,20);

fname=new JLabel("FIRST NAME");
fname.setBounds(500,250,200,30);
fname.setFont(f);
fname.setForeground(Color.WHITE);

txtfname=new JTextField(15);
txtfname.setBounds(700,250,200,30);

lname=new JLabel("LAST NAME");
lname.setBounds(500,300,200,30);
lname.setFont(f);
lname.setForeground(Color.WHITE);

txtlname=new JTextField(15);
txtlname.setBounds(700,300,200,30);

userid=new JLabel("USER ID");
userid.setBounds(500,350,200,30);
userid.setFont(f);
userid.setForeground(Color.WHITE);

txtuserid=new JTextField(15);
txtuserid.setBounds(700,350,200,30);

pwd=new JLabel("PASSWORD");
pwd.setBounds(500,400,200,30);
pwd.setFont(f);
pwd.setForeground(Color.WHITE);

password=new JPasswordField(10);
password.setBounds(700,400,200,30);

secq=new JLabel("SECURITY QUES");
secq.setBounds(500,450,200,30);
secq.setFont(f);
secq.setForeground(Color.WHITE);

cmbsecq=new JComboBox();
cmbsecq.setBounds(700,450,200,30);
cmbsecq.addItem("Which is your favourite football team?");
cmbsecq.addItem("Which is your favourite book?");
cmbsecq.addItem("Which is your mother's birthplace?");
cmbsecq.addItem("What is your first phone no.?");
cmbsecq.addItem("Which is your favourite cartoon character?");


seca=new JLabel("SECURITY ANS");
seca.setBounds(500,500,200,30);
seca.setFont(f);
seca.setForeground(Color.WHITE);

txtseca=new JTextField(15);
txtseca.setBounds(700,500,200,30);

contact=new JLabel("CONTACT");
contact.setBounds(500,550,200,30);
contact.setFont(f);
contact.setForeground(Color.WHITE);

txtcontact=new JTextField(15);
txtcontact.setBounds(700,550,200,30);

imgbg=new ImageIcon("an.jpg");
backgr=new JLabel(imgbg);
backgr.setBounds(0,15,1400,710);

imgcreate=new ImageIcon("create.jpg");
create=new JButton(imgcreate);
create.setBounds(550,600,92,50);
create.addActionListener(this);

imgexit=new ImageIcon("exit.jpg");
exit=new JButton(imgexit);
exit.setBounds(750,600,92,50);
exit.addActionListener(this);

imgback=new ImageIcon("backarrow.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);
back.addActionListener(this);

add(fname);
add(txtfname);
add(lname);
add(txtlname);
add(userid);
add(txtuserid);
add(pwd);
add(password);
add(secq);
add(cmbsecq);
add(seca);
add(txtseca);
add(contact);
add(txtcontact);
add(create);
add(exit);
add(back);
add(backgr);
}

public void createid()
{
	try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
	Statement stmt=con.createStatement();
	String queryid="Select userid from tbluser";
	ResultSet rs=stmt.executeQuery(queryid);
	int ic=0;
	
	
	while(rs.next())
	{
		ic=rs.getInt("userid");
	}
	if(ic==0)
		ic=1001;
	else
		ic+=1;
	
	txtuserid.setText(""+ic);
	txtuserid.setEditable(false);
	con.close();
	}
	
	catch(Exception e)
	{
		System.out.println("inside createid:"+e);
	}
	
	
}




public void actionPerformed(ActionEvent ae){

String blank="";
/*String s=new String(txtcontact.getText());
int len=s.length();

String s1=new String(password.getText());
int len1=s1.length();

String regex="[a-zA-Z]+";*/

if(ae.getSource()==create)
{
   if(blank.equals(txtfname.getText())||blank.equals(txtlname.getText())||blank.equals(password.getText())||blank.equals(txtseca.getText())||blank.equals(txtcontact.getText()))
    {
    JOptionPane.showMessageDialog(null,"Fill all the details");
     }
	/*else if(len<10||len>10)
	{
		JOptionPane.showMessageDialog(null,"Invalid Mobile Number");
	}
	else if(s.matches("(0|1|2|3|4|5|6).*"))
	{
		JOptionPane.showMessageDialog(null,"Invalid Mobile Number");
	}
	else if(s.matches(regex))
	{
		JOptionPane.showMessageDialog(null,"Invalid Mobile Number");
    
	}
	else if(len1<6)
	{
		JOptionPane.showMessageDialog(null,"Password must be atleast 6 characters long");
	}*/
   else
    {
	//System.out.println("hey");
     try
      {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		System.out.println("drive");
		Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
		Statement stmt=con.createStatement();
		String query="Select * from tbluser where userid='"+txtuserid.getText()+"'";
		ResultSet rs=stmt.executeQuery(query);
		int flag=0;
		while(rs.next())
		{
		flag++;
		}
		
		 if(flag==0)
		{
		try{
		createid();
		/*pwdvalidate();*/
		String query1="Insert into tbluser(firstname,lastname,userid,password,secq,seca,contact)values('"+txtfname.getText()+"','"+txtlname.getText()+"','"+txtuserid.getText()+"','"+password.getText()+"','"+cmbsecq.getSelectedItem().toString()+"','"+txtseca.getText()+"',"+txtcontact.getText()+")";
		int x=stmt.executeUpdate(query1);
		JOptionPane.showMessageDialog(null,"Account created successfully!!");
		con.close();
		}
		catch(Exception e){
			System.out.println("inside inner try:"+e);
		}
		dispose();
		adminscreen s5=new adminscreen();
		s5.setVisible(true);
		s5.setSize(1400,710);
		s5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		}
		else{
			JOptionPane.showMessageDialog(null,"This user already exists");
			txtfname.setText("");
			txtlname.setText("");
			password.setText("");
			txtseca.setText("");
			txtcontact.setText("");
		}

	   }
	   
	   catch(Exception e)
	   {
		   System.out.println("inside outer try:"+e);
	   }


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
create_newuser cn=new create_newuser();
cn.setVisible(true);
cn.setSize(1400,710);
cn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}

