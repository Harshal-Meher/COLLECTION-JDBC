import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;
class Student
{
	int roll;
	String name;
	String add;
	public Student(int roll, String name, String add) {
		this.roll = roll;
		this.name = name;
		this.add = add;
	}
	public int getRoll() {
		return roll;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAdd() {
		return add;
	}
	
	public String toString()
	{
		return roll+","+name+","+add;
	}
}
public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/swing3", uname = "root", pass = "abc123";

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,pass);
	
	
	List<Student>c=new ArrayList<>();
	Scanner sc=new Scanner(System.in);
	boolean loop=true;
	while(loop)
	{
	System.out.println("1.Insert");
	System.out.println("2.Show");
	System.out.println("3.Search");
	System.out.println("4.Update ");
	System.out.println("5.Delete");
	
	System.out.println("0.Exit");
	System.out.println("Enter your Choice : ");
	int ch=Integer.parseInt(sc.nextLine());
	
	switch(ch)
	{
	case 1:
		System.out.println("Enter your Roll No : ");
		int roll=Integer.parseInt(sc.nextLine());
		System.out.println("Enter your Name : ");
		String name=sc.nextLine();
		System.out.println("Enter your Address : ");
		String add=sc.nextLine();
		
		c.add(new Student(roll, name, add));
System.out.println("-------------------------------------------------------------");	
PreparedStatement ps=con.prepareStatement("insert into sw3 values(?,?,?)");
ps.setInt(1, roll);
ps.setString(2,name);
ps.setString(3,add);
int a=ps.executeUpdate();
if(a>0)
{
	System.out.println("Data Insert");
}
else
{
	System.out.println("Data Not Inserted");
}


		break;
	case 2:
	Iterator<Student> i=c.iterator();
	while(i.hasNext())
	{
		Student st=i.next();
		System.out.println(st);
	}
System.out.println("-------------------------------------------------------------");	
PreparedStatement ps1=con.prepareStatement("Select * from sw3");
ResultSet rs=ps1.executeQuery();
while(rs.next())
{
	System.out.println("Roll :"+rs.getInt(1)+"Name :"+rs.getString(2)+"Address :"+rs.getString(3));
}

		break;
	case 3:
		System.out.println("Enter Roll No : ");
		int r1=Integer.parseInt(sc.nextLine());
		i=c.iterator();
		while(i.hasNext())
		{
			Student st=i.next();
			if(st.roll==r1)
			{
				System.out.println(st);
			}
			
		}
		PreparedStatement ps3=con.prepareStatement("Select * from sw3 where Roll="+r1+"");
		rs=ps3.executeQuery();
		while(rs.next())
		{
			System.out.println("Roll :"+rs.getInt(1)+"Name :"+rs.getString(2)+"Address :"+rs.getString(3));
		}

		break;
	case 4:
		System.out.println("Enter Roll No : ");
		int r2=Integer.parseInt(sc.nextLine());
//		ListIterator<Student>li=c.listIterator();
//		while(li.hasNext())
//		{
//			Student st=li.next();
//			if(st.roll==r2)
//			{
//			System.out.println("Enter New Name : ");
//		    name=sc.nextLine();
//			System.out.println("Enter New Addresss : ");
//		    add=sc.nextLine();
		
         System.out.println("--------------------------------------------------------------------");
		break;
	case 5:
		System.out.println("Enter Roll No : ");
		int r3=Integer.parseInt(sc.nextLine());
		i=c.iterator();
		while(i.hasNext())
		{
			Student st=i.next();
			if(st.roll==r3)
			{
			i.remove();
			}
		}
		break;
	case 0:
		break;
		default:
			System.out.println("Default value");
		
	}
	}
	}
	}

