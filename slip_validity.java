import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
public class slip_validity extends JFrame implements ActionListener
{
  int i,k;
  ImageIcon imgbg,imgexit,imgback; 
  JLabel backgr,slipno,vehno,vehtype,jourtype,slipdate,amt,slipval;
  JTextField txtvehtype,txtjourtype,txtvehno,txtslipdate,txtamt,txtslipval;
  JComboBox cmbslipno;
  JButton exit,back;
  
  public slip_validity()
  {
	setLayout(null);

	getContentPane().setBackground(Color.BLACK);
	Font f=new Font("lato",Font.BOLD,20);


	slipno=new JLabel("SLIP NO");
	slipno.setBounds(730,190,200,30);
	slipno.setFont(f); 
	slipno.setForeground(Color.BLACK);

  

  cmbslipno=new JComboBox();
  slipno();
  cmbslipno.setBounds(930,190,200,30);
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
  
  jourtype=new JLabel("JOURNEY TYPE");
  jourtype.setBounds(730,340,200,30);
  jourtype.setFont(f);
  jourtype.setForeground(Color.BLACK);

  txtjourtype=new JTextField(10);
  
  txtjourtype.setBounds(930,340,200,30);
  txtjourtype.setEditable(false);
   
  cmbslipno.addFocusListener(new FocusAdapter()
  {
  public void focusLost(FocusEvent fe)
         {

             try
             {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
               Statement stmt=con.createStatement();
               String query="Select * from tblnewslip where slipno='"+cmbslipno.getSelectedItem().toString()+"'";
               ResultSet rs=stmt.executeQuery(query);
			  rs.next();
			  /* while(rs.next())*/
	
				   txtvehno.setText(""+rs.getString("vehicleno"));
				   txtvehtype.setText(""+rs.getString("vehicletype"));
				   txtjourtype.setText(""+rs.getString("journeytype"));
				   txtslipdate.setText(""+rs.getString("currentdate"));
				   txtamt.setText(""+rs.getString("amount"));
				   txtslipval.setText(""+rs.getString("slipvaltime"));
				  
			  
			   con.close();
				}
			catch(Exception e){
					System.out.println("Exceptionva caught"+e);
						}
						
					try
					{	
						
						Date d=new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
						
						Date validuptodate=sdf.parse(txtslipval.getText());
						
						String currdate=sdf.format(new Date());
						
						Date curdt=sdf.parse(currdate);
						
						
						if(curdt.compareTo(validuptodate)>0)
							JOptionPane.showMessageDialog(null,"Invalid Slip");
						else
							JOptionPane.showMessageDialog(null,"Valid Slip");
						
					}
					catch(Exception e)
					{
						System.out.println("yha date "+e);
					}
		 }});

  slipdate=new JLabel("SLIP DATE/TIME");
  slipdate.setBounds(730,390,200,30);
  
  slipdate.setFont(f);
  slipdate.setForeground(Color.BLACK);

  txtslipdate=new JTextField(18);
  txtslipdate.setBounds(930,390,200,30);
  txtslipdate.setEditable(false);
 
 
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
  
   imgbg=new ImageIcon("slip_val.jpg");
 backgr=new JLabel(imgbg);
backgr.setBounds(0,15,1400,710);


imgexit=new ImageIcon("exitred.jpg");
exit=new JButton(imgexit);
exit.setBounds(950,590,92,50);

exit.addActionListener(this);

imgback=new ImageIcon("backarrowred.jpg");
back=new JButton(imgback);
back.setBounds(50,600,50,50);

back.addActionListener(this);
  
  add(slipno);
  add(cmbslipno);
  add(vehno);
  add(txtvehno);
  add(vehtype);
  add(txtvehtype);
  add(jourtype);
  add(txtjourtype);
  add(slipdate);
  add(txtslipdate);
  add(amt);
  add(txtamt);
  add(slipval);
  add(txtslipval);
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
	
	public void slipno()
	{
		try{
			
			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
			Statement stmt=con.createStatement();
			String query="Select slipno from tblnewslip";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next())
			{
			cmbslipno.addItem(rs.getString("slipno"));	
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
		slip_validity sv= new slip_validity(); 
        sv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sv.setVisible(true);
		sv.setSize(1400,730);
		sv.setTitle("Slip Validity");
	}
	


	} 


  
  
  
  
  