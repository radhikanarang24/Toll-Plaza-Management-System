import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class linking_user extends JFrame implements ActionListener
{

	JLabel bg,genslip,slipval,passval;
	ImageIcon imgbg,imggenslip,imgslipval,imgpassval,imgback;
	JButton gsbutton,svbutton,pvbutton,back;
	
	public linking_user()
	{
		setLayout(null);

	    getContentPane().setBackground(Color.BLACK);
	    Font f=new Font("lato",Font.BOLD,20);
		
		imggenslip=new ImageIcon("gen_slip_button2.jpg");
		gsbutton=new JButton(imggenslip);
		gsbutton.setBounds(195,250,330,250);
		gsbutton.addActionListener(this);
		
		imgslipval=new ImageIcon("slip_val_button2.jpg");
		svbutton=new JButton(imgslipval);
		svbutton.setBounds(535,250,330,250);
		svbutton.addActionListener(this);
		
		imgpassval=new ImageIcon("pass_val_button2.jpg");
		pvbutton=new JButton(imgpassval);
		pvbutton.setBounds(875,250,330,250);
		pvbutton.addActionListener(this);
		
		imgback=new ImageIcon("backarrowred.jpg");
        back=new JButton(imgback);
        back.setBounds(50,600,50,50);
        back.addActionListener(this);
		
		imgbg=new ImageIcon("bg_user.jpg");
        bg=new JLabel(imgbg);
        bg.setBounds(0,15,1400,710);
		
		add(gsbutton);
		add(svbutton);
		add(pvbutton);
		add(back);
		add(bg);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==gsbutton)
		{
			dispose();
		generate_slip gs= new generate_slip(); 
        gs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gs.setVisible(true);
		gs.setSize(1400,730);
		gs.setTitle("Generate Slip");
		}
		else if(ae.getSource()==svbutton)
		{
			dispose();
		slip_validity sv= new slip_validity(); 
        sv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sv.setVisible(true);
		sv.setSize(1400,730);
		sv.setTitle("Slip Validity");
		}
		else if(ae.getSource()==pvbutton)
		{
			dispose();
		pass_validity pv= new pass_validity(); 
        pv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pv.setVisible(true);
		pv.setSize(1400,730);
		pv.setTitle("Pass Validity");
		}
		else if(ae.getSource()==back)
		{	dispose();
			userlogin s4=new userlogin();
            s4.setVisible(true);
            s4.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            s4.setSize(1400,710);
		}
		
	}
	
	public static void main(String args[])
	{
		linking_user lu=new linking_user();
		lu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lu.setVisible(true);
		lu.setSize(1400,730);
		lu.setTitle("Linking User");
		
	}



}
