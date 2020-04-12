import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class report_pass extends JFrame implements ActionListener
{
	JLabel heading;
	JPanel jp,jp1,jp2;
	JScrollPane jsp1;
	JButton b1;
	JTable jt1;
	int i,j;
	DefaultTableModel tm=new DefaultTableModel(1,6);
	String head[]={"runid","vehicleno","vehicletype","issuedate","validupto","amount"};
	
	public report_pass()
	{
		jp=new JPanel();
		jp.setLayout(new BorderLayout());
		
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout());
		
		jt1=new JTable();
		jt1.setModel(tm);
		
		for(j=0;j<6;j++)
		{
			tm.setValueAt(head[j],0,j);	
		}
		
		
	 jp1.add(jt1);

      jp.add(jp1,BorderLayout.CENTER);

    jp2=new JPanel();
    jp2.setLayout(new FlowLayout());
    heading=new JLabel("PASS HOLDERS");
    heading.setFont(new Font("lato",Font.BOLD,20));
    jp2.add(heading);
    jp.add(jp2,BorderLayout.NORTH); 
 
     b1=new JButton("Exit");
     b1.addActionListener(this);
     jp.add(b1,BorderLayout.SOUTH);
 
int v,h;
v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
jsp1=new JScrollPane(jp,v,h);
Container c=getContentPane();
c.add(jsp1);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
System.exit(0);
}});
getFields();
}
public void getFields()
{
  try
   {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con=DriverManager.getConnection("Jdbc:Odbc:tollplazadsn");
    Statement stmt=con.createStatement();
    String query="select * from tblnewpass";
    ResultSet rs=stmt.executeQuery(query);

    while(rs.next())
    {
    String[] r={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
    tm.addRow(r);
    }
    con.close();
   }
   catch(Exception e)
   {
   System.out.println("Error:"+e);
   }
}
public void actionPerformed(ActionEvent ae)
{
System.exit(0);
}
public static void main(String args[])
{
report_pass log3=new report_pass();
log3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
log3.setVisible(true);
log3.setSize(1400,730);
}
}
    