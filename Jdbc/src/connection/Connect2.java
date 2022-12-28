package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import entity.Employee;

public class Connect2 {
	
	public static void updateEverySal(Connection con) throws SQLException
	{
//		con.setAutoCommit(false);
		Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=stmt.executeQuery("select * from emp");
		
		while(rs.next())
		{
			double sal=rs.getDouble(6);
			double per=0;
			
			if(sal<10000)
			{
				per=2;
			}else if(sal<25000)
			{
				per=5;
			}else if(sal<50000)
			{
				per=8;
			}else {
				per=10;
			}
			
			double newSal=sal+(sal*(per)/100);
			rs.updateDouble(6, newSal);
			rs.updateRow();
			
		}
		rs.close();
		stmt.close();
	}
	
	public static void procedure2(Connection con) throws SQLException
	{
		CallableStatement cs= con.prepareCall("{call incSal(?,?)}");
		Scanner sc= new Scanner( System.in);
		System.out.println("Enter empno:");
		int n=sc.nextInt();
		System.out.println("Enter percentage increase:");
		double d=sc.nextDouble();
		
		cs.setInt(1, n);
		cs.setDouble(2, d);
		cs.execute();
		System.out.println("Updated succesfully...");
		
	}
	
	public static void procedure1(Connection con) throws SQLException
	{
		CallableStatement cs= con.prepareCall("{call getEmps()}");
		cs.execute();
//		cs.
//		while (cs.next()) {
//		      System.out.println(
//		        cs.getInt(1) +
//		        " | " +
//		        cs.getString(2) +
//		        " | " +
//		        cs.getDouble(6) +
//		        " | " +
//		        cs.getInt(9)
//		      );
//		      
//		    }
	}
	
	public static void sortBySalary(Connection con, ResultSet rs) throws SQLException {
		String query = "select * from emp";
	    Statement stmt=con.createStatement();
	    rs = stmt.executeQuery(query);
	    Set<Employee> emps = new TreeSet<>(
	    		(Employee e1, Employee e2)->{
	    			double d=e2.getSAL()-e1.getSAL();
	    			if(d==0)
	    				return e1.getEMPNO()-e2.getEMPNO();
	    			return (int)d;
	    		}
	    		);

	    while (rs.next()) {
	      Employee e = new Employee();
	      e.setEMPNO(rs.getInt(1));
	      e.setENAME(rs.getString(2));
	      e.setJOB(rs.getString(3));
	      e.setMGR(rs.getInt(4));
	      e.setHIREDATE(rs.getDate(5));
	      e.setSAL(rs.getDouble(6));
	      e.setCOMM(rs.getDouble(7));
	      e.setNetsal(rs.getInt(8));
	      e.setDEPTNO(rs.getInt(9));

	      emps.add(e);
	    }

	    for (Employee e : emps) {
	      System.out.println(e);
	    }
	    rs.close();
	    
	}
	
//	Sort by Name--->
	public static void sortByName(Connection con, ResultSet rs) throws SQLException {
		String query = "select * from emp";
	    Statement stmt=con.createStatement();
	    rs = stmt.executeQuery(query);
	    Set<Employee> emps = new TreeSet<>(
	    		(Employee e1, Employee e2)->{
	    			int n=e1.getENAME().compareTo(e2.getENAME());
	    			if(n==0)
	    				return e1.getEMPNO()-e2.getEMPNO();
	    			return e1.getENAME().compareTo(e2.getENAME());
	    		}
	    		);

	    while (rs.next()) {
	      Employee e = new Employee();
	      e.setEMPNO(rs.getInt(1));
	      e.setENAME(rs.getString(2));
	      e.setJOB(rs.getString(3));
	      e.setMGR(rs.getInt(4));
	      e.setHIREDATE(rs.getDate(5));
	      e.setSAL(rs.getDouble(6));
	      e.setCOMM(rs.getDouble(7));
	      e.setNetsal(rs.getInt(8));
	      e.setDEPTNO(rs.getInt(9));

	      emps.add(e);
	    }

	    for (Employee e : emps) {
	      System.out.println(e);
	    }
	    rs.close();
	    
	}

//	Storing in HashSet===>
	  public static void getAllEmployees( Connection con, ResultSet rs) throws SQLException {
		    

		  	String query = "select * from emp";
		    Statement stmt=con.createStatement();
		    rs = stmt.executeQuery(query);

		    Set<Employee> emps = new HashSet<>();

		    while (rs.next()) {
		      Employee e = new Employee();
		      e.setEMPNO(rs.getInt(1));
		      e.setENAME(rs.getString(2));
		      e.setJOB(rs.getString(3));
		      e.setMGR(rs.getInt(4));
		      e.setHIREDATE(rs.getDate(5));
		      e.setSAL(rs.getDouble(6));
		      e.setCOMM(rs.getDouble(7));
		      e.setNetsal(rs.getInt(8));
		      e.setDEPTNO(rs.getInt(9));

		      emps.add(e);
		    }

		    for (Employee e : emps) {
		      System.out.println(e);
		    }
		    rs.close();
		  }	
	
  public static void getEmpById(PreparedStatement stmt, Connection con, ResultSet rs)
    throws NumberFormatException, IOException, SQLException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the employee id:");

    int id = Integer.parseInt(bf.readLine());

    stmt = con.prepareStatement("select * from emp where empno=?");
    stmt.setInt(1, id);
    rs = stmt.executeQuery();

    boolean flag = false;
    while (rs.next()) {
      System.out.println(
        rs.getInt(1) +
        " | " +
        rs.getString(2) +
        " | " +
        rs.getDouble(6) +
        " | " +
        rs.getInt(9)
      );
      flag = true;
    }
    if (!flag) {
      System.out.println("Employee doesn't exist..");
    }
    rs.close();
    stmt.close();
  }

  public static void getEmpBetSal(PreparedStatement stmt, Connection con, ResultSet rs)
    throws NumberFormatException, IOException, SQLException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter min & max salary");

    double min = Double.parseDouble(bf.readLine());
    double max = Double.parseDouble(bf.readLine());

    stmt = con.prepareStatement("select * from emp where sal between ? and ?");
    stmt.setDouble(1, min);
    stmt.setDouble(2, max);
    rs = stmt.executeQuery();
    while (rs.next()) {
      System.out.println(
        rs.getInt(1) +
        " | " +
        rs.getString(2) +
        " | " +
        rs.getDouble(6) +
        " | " +
        rs.getInt(9)
      );
    }
    rs.close();
    stmt.close();
  }

  public static void main(String[] args) throws SQLException {
    Connection con = null;
    String jdbcUrl = "jdbc:mysql://localhost:3306/test";
    String userName = "root";
    String password = "root1234";
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      con = DriverManager.getConnection(jdbcUrl, userName, password);

      System.out.println("Connected succesfully...");

//      getEmpById(stmt, con, rs);
//      getEmpBetSal(stmt,con,rs);
//      getAllEmployees(con,rs);
//      sortByName(con,rs);
//      sortBySalary(con,rs);
//      procedure1(con);
//      procedure2(con);
//       updateEverySal(con);
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
//    catch (IOException e) {
//      e.printStackTrace();
//    } 
    finally {
      con.close();
    }
  }
}
