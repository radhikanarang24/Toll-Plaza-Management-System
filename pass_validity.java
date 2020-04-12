import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
public class pass_validity extends JFrame implements ActionListener
{
  int i,k;
  ImageIcon imgbg,imgexit,imgback; 
  JLabel backgr,passid,vehno,vehtype,currdate,issuedate,valdate,amt;
  JTextField txtvehtype,txtcurrdate,txtvehno,txtissuedate,txtvaldate,txtamt;
  JComboBox cmbpassid;
  JButton exit,back;
  
  public pass_validity()
  {
	setLayout(null);

	getContentPane().setBackground(Color.BLACK);
	Font f=new Font("lato",Font.BOLD,20);


	passid=new JLabel("SLIP NO");
	passid.setBounds(730,190,200,30);
	passid.setFont(f); 
	passid.setForeground(Color.BLACK);

  

  cmbpassid=new JComboBox();
  passid();
  cmbpassid.setBounds(930,190,200,30);
 // txtrunid.setEditable(false);

  vehno=new JLabel("VEHICLE NO.");
  vehno.setBounds(730,240,200,30);
  vehno.setFont(f);
  vehno.setForeground(Color.BLACK);
  
  

  txtvehno=new JTextField(18);
  txtvehno.setBounds(930,240,200,30);
  txtvehno.setEditable(false);

  vehtype=new JLabel("VEHICLE TYPE");
  vehtype.setBounds(730,290,200,30);
  vehtype.setFont(f);
  vehtype.setForeground(Color.BLACK);

  txtvehtype=new JTextField(10);
  
  txtvehtype.setBounds(930,290,200,30);
  txtvehtype.setEditable(false);
  
  currdate=new JLabel("CURRENT DATE");
  currdate.setBounds(730,340,200,30);
  currdate.setFont(f);
  currdate.setForeground(Color.BLACK);

  txtcurrdate=new JTextField(18);
  
  txtcurrdate.setBounds(930,340,200,30);
  txtcurrdate.setEditable(false);
   
  cmbpassid.addFocusListener(new FocusAdapter()
  {
  public void focusLost(FocusEvent fe)
         {

             try
             {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
               Statement stmt=con.createStatement();
               String query="Select * from tblnewpass where runid='"+cmbpassid.getSelectedItem().toString()+"'";
               ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   
			   
				   txtvehno.setText(""+rs.getString("vehicleno"));
				   txtvehtype.setText(""+rs.getString("vehicletype"));
				  // txtcurrdate.setText(""+rs.getString("type"));
				   txtissuedate.setText(""+rs.getString("issuedate"));
				   txtamt.setText(""+rs.getString("amount"));
				   txtvaldate.setText(""+rs.getString("validupto"));
				   
			   
			   con.close();
				}
			catch(Exception e){
					System.out.println("Exceptionva caught"+e);
						}
						
					try
					{	
					
						Date d=new Date();
						
						SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
						
						Date validuptodate=sdf.parse(txtvaldate.getText());
						
						String currdate=sdf.format(new Date());
						txtcurrdate.setText(""+currdate);
						
						Date curdt=sdf.parse(currdate);
						
						
						if(curdt.compareTo(validuptodate)>0)
							JOptionPane.showMessageDialog(null,"Invalid Pass");
						else
							JOptionPane.showMessageDialog(null,"Valid Pass");
						
					}
					catch(Exception e)
					{
						System.out.println("yha date "+e);
					}
		 }});

  issuedate=new JLabel("ISSUE DATE");
  issuedate.setBounds(730,390,200,30);
  
  issuedate.setFont(f);
  issuedate.setForeground(Color.BLACK);
  
  txtissuedate=new JTextField(18);
  txtissuedate.setBounds(930,390,200,30);
  txtissuedate.setEditable(false);
 
 
  valdate=new JLabel("VALID UPTO");
  valdate.setBounds(730,440,200,30);
  valdate.setFont(f);
  valdate.setForeground(Color.BLACK);

  txtvaldate=new JTextField(18);
  txtvaldate.setBounds(930,440,200,30);
  txtvaldate.setEditable(false);
  
  amt=new JLabel("AMOUNT");
  amt.setBounds(730,490,200,30);
  amt.setFont(f);
  amt.setForeground(Color.BLACK);

  txtamt=new JTextField(18);
  txtamt.setBounds(930,490,200,30);
  txtamt.setEditable(false);
  
 
   imgbg=new ImageIcon("pass_val.jpg");
   backgr=new JLabel(imgbg);
   backgr.setBounds(0,15,1400,710);


imgexit=new ImageIcon("exitblue.jpg");
exit=new JButton(imgexit);
exit.setBounds(950,590,92,50);

exit.addActionListener(this);

imgback=new ImageIcon("backarrowblue.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);

back.addActionListener(this);
  
  add(passid);
  add(cmbpassid);
  add(vehno);
  add(txtvehno);
  add(vehtype);
  add(txtvehtype);
  add(currdate);
  add(txtcurrdate);
  add(issuedate);
  add(txtissuedate);
  add(amt);
  add(txtamt);
 
  add(valdate);
  add(txtvaldate);
  add(back);
add(exit);

add(backgr);
}


	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==back)
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
	
	public void passid()
	{
		try{
			
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
			Statement stmt=con.createStatement();
			String query="Select runid from tblnewpass";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
			cmbpassid.addItem(rs.getString("runid"));	
			}
	
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception runid:"+e);
		}
	}
	
	public static void main(String args[])
	{
		pass_validity pv= new pass_validity(); 
        pv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pv.setVisible(true);
		pv.setSize(1400,730);
		pv.setTitle("Pass Validity");
	}
	


	} 


  
  
  
  
  