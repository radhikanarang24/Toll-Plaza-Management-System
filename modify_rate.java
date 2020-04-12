import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class modify_rate extends JFrame implements ActionListener
{
	JLabel backgr,vehtype,oneway,twoway,pass;
	JTextField txtoneway,txttwoway,txtpass;
	JComboBox cmbvehtype;
	JButton back,exit,update,delete;
	ImageIcon imgbg,imgexit,imgupdate,imgback,imgdelete;
	
	public modify_rate()
	{
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		Font f=new Font("lato",Font.BOLD,20);

		vehtype=new JLabel("VEHICLE TYPE");
		vehtype.setBounds(500,300,200,30);
		vehtype.setFont(f);
		vehtype.setForeground(Color.WHITE);

		cmbvehtype=new JComboBox();
		cmbvehtype.setBounds(700,300,200,30);
		addvehtype();
		cmbvehtype.addFocusListener(new FocusAdapter()
			{
				public void focusLost(FocusEvent fe)
				{
					try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
						Statement stmt=con.createStatement();
						String query="Select * from tblrate where vehicletype='"+cmbvehtype.getSelectedItem()+"'";
						ResultSet rs=stmt.executeQuery(query);
						while(rs.next())
						{
							txtoneway.setText(""+rs.getInt("onewayrate"));
							txttwoway.setText(""+rs.getInt("twowayrate"));
							txtpass.setText(""+rs.getInt("passholder"));
						}
						con.close();
					}
					
					catch(Exception e)
					{
						System.out.println("Exception caught:"+e);
					}
				}
			}
		);
		
		oneway=new JLabel("ONE WAY RATE");
		oneway.setBounds(500,350,200,30);
		oneway.setFont(f);
		oneway.setForeground(Color.WHITE);

		txtoneway=new JTextField(15);
		txtoneway.setBounds(700,350,200,30);
		

		twoway=new JLabel("TWO WAY RATE");
		twoway.setBounds(500,400,200,30);
		twoway.setFont(f);
		twoway.setForeground(Color.WHITE);

		txttwoway=new JTextField(15);
		txttwoway.setBounds(700,400,200,30);
		
		
		pass=new JLabel("MONTH PASS RATE");
		pass.setBounds(500,450,200,30);
		pass.setFont(f);
		pass.setForeground(Color.WHITE);

		txtpass=new JTextField(15);
		txtpass.setBounds(700,450,200,30);
		
		
		imgbg=new ImageIcon("insert_rate.jpg");
		backgr=new JLabel(imgbg);
		backgr.setBounds(0,15,1400,710);

		imgupdate=new ImageIcon("updateblue.jpg");
		update=new JButton(imgupdate);
		update.setBounds(500,600,92,50);
		update.addActionListener(this);
		
		imgdelete=new ImageIcon("deleteblue.jpg");
		delete=new JButton(imgdelete);
		delete.setBounds(650,600,92,50);
		delete.addActionListener(this);

		imgexit=new ImageIcon("exitblue.jpg");
		exit=new JButton(imgexit);
		exit.setBounds(800,600,92,50);
		exit.addActionListener(this);

		imgback=new ImageIcon("backarrowblue.jpg");
		back=new JButton(imgback);
		back.setBounds(50,600,50,50);
		back.addActionListener(this);
		
		add(vehtype);
		add(cmbvehtype);
		add(oneway);
		add(txtoneway);
		add(twoway);
		add(txttwoway);
		add(pass);
		add(txtpass);
		add(update);
		add(delete);
		add(exit);
		add(back);
		add(backgr);


	}
	
	public void addvehtype()
	{
		try{
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
		catch(Exception e)
		{
			System.out.println("Exception caught:"+e);
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==update)
		{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
				Statement stmt=con.createStatement();
				String query="Update tblrate set onewayrate="+txtoneway.getText()+",twowayrate="+txttwoway.getText()+",passholder="+txtpass.getText()+" where vehicletype='"+cmbvehtype.getSelectedItem().toString()+"'";
  
				int x=stmt.executeUpdate(query);
				JOptionPane.showMessageDialog(null,"RATE UPDATED");
				con.close();
			}
			catch(Exception e){
			System.out.println("Exception caught"+e);
				}
			
		}
		else if(ae.getSource()==delete)
		{
			try
			{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
			Statement stmt=con.createStatement();
			String query="Delete from tblrate where vehicletype='"+cmbvehtype.getSelectedItem().toString()+"'";
  
			int x=stmt.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"RATE DELETED");
			txtoneway.setText("");
			txttwoway.setText("");
			txtpass.setText("");
			con.close();
			}
			catch(Exception e){
			System.out.println("Exception caught"+e);
				}
			cmbvehtype.removeAllItems();
			addvehtype();     

			
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
		modify_rate mr=new modify_rate();
		mr.setSize(1400,710);
		mr.setVisible(true);
		mr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
