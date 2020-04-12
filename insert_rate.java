import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class insert_rate extends JFrame implements ActionListener
{
	JLabel backgr,vehtype,oneway,twoway,pass;
	JTextField txtvehtype,txtoneway,txttwoway,txtpass;
	JButton back,exit,clear,insert;
	ImageIcon imgbg,imgexit,imgclear,imgback,imginsert;
	
	public insert_rate()
	{
		setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		Font f=new Font("lato",Font.BOLD,20);

		vehtype=new JLabel("VEHICLE TYPE");
		vehtype.setBounds(500,300,200,30);
		vehtype.setFont(f);
		vehtype.setForeground(Color.WHITE);

		txtvehtype=new JTextField(15);
		txtvehtype.setBounds(700,300,200,30);
		txtvehtype.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				try{
					char ch=ke.getKeyChar();
			 if((Character.isDigit(ch) || ch=='.'))
			 {
              JOptionPane.showMessageDialog(null,"Please enter a string value");
			 String s=txtvehtype.getText();
			 txtvehtype.setText(s.substring(0,s.length()-1));
			 }
				}
				
				catch(Exception e)
				{
					System.out.println("exception caught:"+e);
				}
			}
		});

		oneway=new JLabel("ONE WAY RATE");
		oneway.setBounds(500,350,200,30);
		oneway.setFont(f);
		oneway.setForeground(Color.WHITE);

		txtoneway=new JTextField(15);
		txtoneway.setBounds(700,350,200,30);
		txtoneway.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				try{
					char ch=ke.getKeyChar();
			 if(!(Character.isDigit(ch) || ch=='.'))
			 {
              JOptionPane.showMessageDialog(null,"Please enter a numeric value");
			 String s=txtoneway.getText();
			 txtoneway.setText(s.substring(0,s.length()-1));
			 }
				}
				
				catch(Exception e)
				{
					System.out.println("exception caught:"+e);
				}
			}
		});


		twoway=new JLabel("TWO WAY RATE");
		twoway.setBounds(500,400,200,30);
		twoway.setFont(f);
		twoway.setForeground(Color.WHITE);

		txttwoway=new JTextField(15);
		txttwoway.setBounds(700,400,200,30);
		txttwoway.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				try{
					char ch=ke.getKeyChar();
			 if(!(Character.isDigit(ch) || ch=='.'))
			 {
              JOptionPane.showMessageDialog(null,"Please enter a numeric value");
			 String s=txttwoway.getText();
			 txttwoway.setText(s.substring(0,s.length()-1));
			 }
				}
				
				catch(Exception e)
				{
					System.out.println("exception caught:"+e);
				}
			}
		});

		
		
		pass=new JLabel("MONTH PASS RATE");
		pass.setBounds(500,450,200,30);
		pass.setFont(f);
		pass.setForeground(Color.WHITE);

		txtpass=new JTextField(15);
		txtpass.setBounds(700,450,200,30);
		txtpass.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke)
			{
				try{
					char ch=ke.getKeyChar();
			 if(!(Character.isDigit(ch) || ch=='.'))
			 {
              JOptionPane.showMessageDialog(null,"Please enter a numeric value");
			 String s=txtpass.getText();
			 txtpass.setText(s.substring(0,s.length()-1));
			 }
				}
				
				catch(Exception e)
				{
					System.out.println("exception caught:"+e);
				}
			}
		});

		
		imgbg=new ImageIcon("insert_rate.jpg");
		backgr=new JLabel(imgbg);
		backgr.setBounds(0,15,1400,710);

		imginsert=new ImageIcon("insert.jpg");
		insert=new JButton(imginsert);
		insert.setBounds(500,600,92,50);
		insert.addActionListener(this);
		
		imgclear=new ImageIcon("clear.jpg");
		clear=new JButton(imgclear);
		clear.setBounds(650,600,92,50);
		clear.addActionListener(this);

		imgexit=new ImageIcon("exitblue.jpg");
		exit=new JButton(imgexit);
		exit.setBounds(800,600,92,50);
		exit.addActionListener(this);

		imgback=new ImageIcon("backarrowblue.jpg");
		back=new JButton(imgback);
		back.setBounds(50,600,50,50);
		back.addActionListener(this);
		
		add(vehtype);
		add(txtvehtype);
		add(oneway);
		add(txtoneway);
		add(twoway);
		add(txttwoway);
		add(pass);
		add(txtpass);
		add(insert);
		add(clear);
		add(exit);
		add(back);
		add(backgr);


	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==clear)
		{
			txtvehtype.setText("");
			txtoneway.setText("");
			txttwoway.setText("");
			txtpass.setText("");
			
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
		else if(ae.getSource()==insert)
		{
			try{
				
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
			Statement stmt=con.createStatement();
			String query="Insert into tblrate(vehicletype,onewayrate,twowayrate,passholder) values ('"+txtvehtype.getText()+"','"+txtoneway.getText()+"','"+txttwoway.getText()+"','"+txtpass.getText()+"')";
			int x=stmt.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Rate Inserted");
			con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception caught:"+e);
			}
		}
		}
		
		public static void main(String args[])
		{
			insert_rate ir=new insert_rate();
			ir.setVisible(true);
			ir.setSize(1400,710);
			ir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
		
}
