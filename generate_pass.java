import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
public class generate_pass extends JFrame implements ActionListener
{
  int i,k;
  ImageIcon imgbg,imgexit,imgback,imggenpass;
  JLabel backgr,runid,vehno,vehtype,issuedate,validupto,amt;
  JTextField txtrunid,txtvehno,txtissue,txtvalid,txtamt;
  JComboBox cmbvehtype;
  JButton genpass,exit,back;

  public generate_pass()
  {
	setLayout(null);

	getContentPane().setBackground(Color.BLACK);
	Font f=new Font("lato",Font.BOLD,20);


	runid=new JLabel("RUN ID");
	runid.setBounds(500,300,200,30);
	runid.setFont(f);
	runid.setForeground(Color.WHITE);

  

  txtrunid=new JTextField(18);
  runid();
  //txtrunid.setEditable(false);
  txtrunid.setBounds(700,300,200,30);
 // txtrunid.setEditable(false);

  vehno=new JLabel("VEHICLE NO.");
  vehno.setBounds(500,350,200,30);
  vehno.setFont(f);
  vehno.setForeground(Color.WHITE);

  txtvehno=new JTextField(18);
  txtvehno.setBounds(700,350,200,30);

  vehtype=new JLabel("VEHICLE TYPE");
  vehtype.setBounds(500,400,200,30);
  vehtype.setFont(f);
  vehtype.setForeground(Color.WHITE);

  cmbvehtype=new JComboBox();
  addvehtype();
  cmbvehtype.setBounds(700,400,200,30);
   cmbvehtype.addFocusListener(new FocusAdapter()
       {
         public void focusLost(FocusEvent fe)
         {

             try
             {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
               Statement stmt=con.createStatement();
               String query="Select passholder from tblrate where vehicletype='"+cmbvehtype.getSelectedItem()+"'";
               ResultSet rs=stmt.executeQuery(query);
               while(rs.next())
               {
               txtamt.setText(""+rs.getInt("passholder"));
               }
  con.close();
  }
  catch(Exception e){
 System.out.println("Exception caught"+e);

}}});
  issuedate=new JLabel("ISSUE DATE");
  issuedate.setBounds(500,450,200,30);
  issuedate.setFont(f);
  issuedate.setForeground(Color.WHITE);

  txtissue=new JTextField(18);
  txtissue.setBounds(700,450,200,30);
  txtissue.setEditable(false);

  Calendar cal=Calendar.getInstance();
  cal.add(Calendar.DAY_OF_MONTH,1);
  SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
  String s=sdf.format(new Date());
  txtissue.setText(""+s);

  validupto=new JLabel("VALID UPTO");
  validupto.setBounds(500,500,200,30);
  validupto.setFont(f);
  validupto.setForeground(Color.WHITE);

  txtvalid=new JTextField(18);
  txtvalid.setBounds(700,500,200,30);

  Calendar cal1=Calendar.getInstance();
  cal1.add(Calendar.MONTH,1);
  SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
  String s1=sdf1.format(cal1.getTime());
  txtvalid.setText(s1);
  txtvalid.setEditable(false);

  amt=new JLabel("AMOUNT");
  amt.setBounds(500,550,200,30);
  amt.setFont(f);
  amt.setForeground(Color.WHITE);
  
  txtamt=new JTextField(18);
  txtamt.setBounds(700,550,200,30);
  txtamt.setEditable(false);

  imgbg=new ImageIcon("genpass.jpg");
backgr=new JLabel(imgbg);
backgr.setBounds(0,15,1400,710);

imggenpass=new ImageIcon("genpassbutton.jpg");
genpass=new JButton(imggenpass);
genpass.setBounds(550,600,92,50);
genpass.addActionListener(this);

imgexit=new ImageIcon("exitgreen.jpg");
exit=new JButton(imgexit);
exit.setBounds(750,600,92,50);
exit.addActionListener(this);

imgback=new ImageIcon("backarrowgreen.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);
back.addActionListener(this);


add(runid);
add(txtrunid);
add(vehno);
add(txtvehno);
add(vehtype);
add(cmbvehtype);
add(issuedate);
add(txtissue);
add(validupto);
add(txtvalid);
add(amt);
add(txtamt);
add(back);
add(exit);
add(genpass);
add(backgr);
 }

public void addvehtype()
{
try
  {
  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
  Statement stmt=con.createStatement();
  String query="Select vehicletype from tblrate";
  ResultSet rs=stmt.executeQuery(query);
  while(rs.next())
  {
  cmbvehtype.addItem(rs.getString("vehicletype"));
  }
  con.close();
  }
  catch(Exception e){
 System.out.println("Exception caught"+e);
}}

public void actionPerformed(ActionEvent ae)
{
  if(ae.getSource()==genpass)
    {
     try
        {
           if(txtrunid.getText().equals("") || txtvehno.getText().equals("") || cmbvehtype.getSelectedItem().equals("") || txtvalid.getText().equals("") || txtamt.getText().equals(""))
           {
            JOptionPane.showMessageDialog(null,"FIELDS SHOULD NOT BE EMPTY");
            int flag=1;
           }
           else{
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
           Statement stmt=con.createStatement();
           String query="Insert into tblnewpass(runid,vehicleno,vehicletype,issuedate,validupto,amount) values ('"+txtrunid.getText()+"','"+txtvehno.getText()+"','"+cmbvehtype.getSelectedItem()+"','"+txtissue.getText()+"','"+txtvalid.getText()+"','"+txtamt.getText()+"')";
           int x=stmt.executeUpdate(query);
           JOptionPane.showMessageDialog(null,"Pass Generated");
           con.close();
        }}
        catch(Exception e){
                          System.out.println("Exception Caught"+e);
                          }}
   else if(ae.getSource()==back)
   {
   dispose();
   
	adminscreen s5=new adminscreen();
	s5.setVisible(true);
	s5.setSize(1400,710);
	s5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
   }
   else if(ae.getSource()==exit)
   {
    System.exit(0);
	
	}
    }


public void runid()
{
   try
    {
   int a=0;
  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
  Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
  Statement stmt=con.createStatement();
  String query="Select runid from tblnewpass";
  ResultSet rs=stmt.executeQuery(query);
  while(rs.next())
  {
  a=rs.getInt("runid");
  }
  if(a==0)
  {
   txtrunid.setText("1001");
}
   else
   {
   txtrunid.setText(String.valueOf(a+1));
}
  con.close();
  }
  catch(Exception e){
 System.out.println("Exception caught"+e);
}}

public static void main(String args[])
{
  generate_pass gen=new generate_pass();
  gen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
  gen.setSize(1400,730);
  gen.setVisible(true);
}
}