import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
public class generate_slip extends JFrame implements ActionListener
{
  int i,k;
  ImageIcon imgbg,imgexit,imgback,imggenslip; 
  JLabel backgr,slipno,vehno,vehtype,jourtype,currdate,amt,slipval;
  JTextField txtslipno,txtvehno,txtcurrdate,txtamt,txtslipval;
  JComboBox cmbvehtype,cmbjourtype;
  JButton genslip,exit,back;
  
  String journey[] = {"One Way","Two Way"};

  public generate_slip()
  {
	setLayout(null);

	getContentPane().setBackground(Color.BLACK);
	Font f=new Font("lato",Font.BOLD,20);


	slipno=new JLabel("SLIP NO");
	slipno.setBounds(730,190,200,30);
	slipno.setFont(f); 
	slipno.setForeground(Color.BLACK);

  

  txtslipno=new JTextField(18);
  slipno();
  txtslipno.setBounds(930,190,200,30);
 // txtrunid.setEditable(false);

  vehno=new JLabel("VEHICLE NO.");
  vehno.setBounds(730,240,200,30);
  vehno.setFont(f);
  vehno.setForeground(Color.BLACK);
  

  txtvehno=new JTextField(18);
  txtvehno.setBounds(930,240,200,30);

  vehtype=new JLabel("VEHICLE TYPE");
  vehtype.setBounds(730,290,200,30);
  vehtype.setFont(f);
  vehtype.setForeground(Color.BLACK);

  cmbvehtype=new JComboBox();
  addvehtype();
  cmbvehtype.setBounds(930,290,200,30);
  
  jourtype=new JLabel("JOURNEY TYPE");
  jourtype.setBounds(730,340,200,30);
  jourtype.setFont(f);
  jourtype.setForeground(Color.BLACK);

  cmbjourtype=new JComboBox(journey);
  
  cmbjourtype.setBounds(930,340,200,30);
   
  cmbjourtype.addFocusListener(new FocusAdapter()
  {
  public void focusLost(FocusEvent fe)
         {

             try
             {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
               Statement stmt=con.createStatement();
               String query="Select * from tblrate where vehicletype='"+cmbvehtype.getSelectedItem()+"'";
               ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   
			   if(cmbjourtype.getSelectedItem()=="One Way")
			   {
				  txtamt.setText(""+rs.getInt("onewayrate"));
                  txtslipval.setText("");
			   }
			   else
			   {
				   txtamt.setText(""+rs.getInt("twowayrate"));
				   Calendar cal1=Calendar.getInstance();
				   cal1.add(Calendar.DAY_OF_MONTH,1);
				   SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy HH:mm");
				   String s1=sdf1.format(cal1.getTime());
				   txtslipval.setText(s1);
				   txtslipval.setEditable(false);
				  
			   }
              
  con.close();
  }
  catch(Exception e){
 System.out.println("Exceptionva caught"+e);
  }
		 }});

  currdate=new JLabel("CURRENT DATE/TIME");
  currdate.setBounds(700,390,230,30);
  
  currdate.setFont(f);
  currdate.setForeground(Color.BLACK);

  txtcurrdate=new JTextField(18);
  txtcurrdate.setBounds(930,390,200,30);
  txtcurrdate.setEditable(false);
  
  Calendar cal=Calendar.getInstance();
  cal.add(Calendar.DAY_OF_MONTH,1);
  SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy/HH:mm");
  String s=sdf.format(new Date());
  txtcurrdate.setText(""+s);
  
  amt=new JLabel("AMOUNT");
  amt.setBounds(730,440,200,30);
  amt.setFont(f);
  amt.setForeground(Color.BLACK);

  txtamt=new JTextField(18);
  txtamt.setBounds(930,440,200,30);
  txtamt.setEditable(false);
  
  slipval=new JLabel("SLIP VALIDITY TIME");
  slipval.setBounds(710,490,220,30);
  slipval.setFont(f);
  slipval.setForeground(Color.BLACK);

  txtslipval=new JTextField(18);
  txtslipval.setBounds(930,490,200,30);
  txtslipval.setEditable(false);
  
   imgbg=new ImageIcon("gen_slip.jpg");
 backgr=new JLabel(imgbg);
backgr.setBounds(0,15,1400,710);

imggenslip=new ImageIcon("genslipbutton.jpg");
genslip=new JButton(imggenslip);
genslip.setBounds(750,590,92,50);

genslip.addActionListener(this);

imgexit=new ImageIcon("exitgreen.jpg");
exit=new JButton(imgexit);
exit.setBounds(950,590,92,50);

exit.addActionListener(this);

imgback=new ImageIcon("backarrowgreen.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);

back.addActionListener(this);
  
  add(slipno);
  add(txtslipno);
  add(vehno);
  add(txtvehno);
  add(vehtype);
  add(cmbvehtype);
  add(jourtype);
  add(cmbjourtype);
  add(currdate);
  add(txtcurrdate);
  add(amt);
  add(txtamt);
  add(slipval);
  add(txtslipval);
  add(back);
add(exit);
add(genslip);
add(backgr);
}

public void addvehtype()
{
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
		Statement stmt= con.createStatement();
		String query ="Select vehicletype from tblrate";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
			cmbvehtype.addItem(rs.getString("vehicletype"));
		}
		con.close();
	}
	
	catch(Exception e)
	{
		System.out.println("Exception addvehtype:"+e);
		
	}
}

	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==genslip)
		{
			if(txtvehno.getText().equals("") || txtamt.getText().equals(""))
            {
            JOptionPane.showMessageDialog(null,"FIELDS SHOULD NOT BE EMPTY");
            }
           else
           {
            try
            {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
				Statement stmt=con.createStatement();
				String query="Insert into tblnewslip(slipno,vehicleno,vehicletype,journeytype,amount,currentdate,slipvaltime) values ("+txtslipno.getText()+",'"+txtvehno.getText()+"','"+cmbvehtype.getSelectedItem().toString()+"','"+cmbjourtype.getSelectedItem().toString()+"','"+txtamt.getText()+"','"+txtcurrdate.getText()+"','"+txtslipval.getText()+"')";
				int x=stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"SLIP GENERATED.");
                con.close();
              }
             catch(Exception e){
             System.out.println("Exception Caught"+e);
            }}
		}
		else if(ae.getSource()==back)
		{
			dispose();
		linking_user lu=new linking_user();
		lu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lu.setVisible(true);
		lu.setSize(1400,730);
		lu.setTitle("Linking User");
		}
		else if(ae.getSource()==exit)
		{
			
    System.exit(0);
		}
	}
	
	public void slipno()
	{
		try{
			int a=0;
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
			Statement stmt=con.createStatement();
			String query="Select slipno from tblnewslip";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
				a=rs.getInt("slipno");
			}
			
			if(a==0)
			{
				txtslipno.setText("2001");
				
			}
			else
			{
				txtslipno.setText(String.valueOf(a+1));
				
			}
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception slipno:"+e);
		}
	}
	
	public static void main(String args[])
	{
		generate_slip gs= new generate_slip(); 
        gs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gs.setVisible(true);
		gs.setSize(1400,730);
		gs.setTitle("Generate Slip");
	}
	


	} 


  
  
  
  
  