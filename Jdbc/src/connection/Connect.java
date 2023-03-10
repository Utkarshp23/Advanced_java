package connection;

import entity.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Connect {

  public static void getAllEmployees(Statement stmt) throws SQLException {
    String query = "select * from emp";
    ResultSet rs = stmt.executeQuery(query);

    List<Employee> emps = new ArrayList<>();

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

  public static void updateSalary(Statement stmt) throws SQLException {
    String query = "update emp set sal=20000 where ename='Utkarsh'";
    int n = stmt.executeUpdate(query);
    System.out.println("Affected rows:" + n);
  }

  public static void getAvgSal(Statement stmt) throws SQLException {
    String query =
      "select d.dname , avg(sal) avgsal from emp e, dept d where e.deptno=d.deptno group by d.dname order by avg(sal) desc";
    ResultSet rs = stmt.executeQuery(query);
    while (rs.next()) {
      System.out.println(rs.getString(1) + "----" + rs.getDouble(2));
    }

    rs.close();
  }

  public static void sortEmps(Statement stmt) throws SQLException {
    String query = "select * from emp";
    ResultSet rs = stmt.executeQuery(query);

    //		Set<Employee> emps=new TreeSet<>((Employee e1, Employee e2)->{
    //			return e1.getSAL()-e2.getSAL();
    //		});

    Set<Employee> emps = new TreeSet<>(
      new Comparator<Employee>() {

        public int compare(Employee e1, Employee e2) {
          double k = e1.getSAL() - e2.getSAL();
          int f = 0;
          if (k == 0) return e1.getEMPNO() - e2.getEMPNO(); else if (k > 0) f =
            1; else f = -1;

          return f;
          //						return e1.getSAL()>e2.getSAL();
        }
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

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    // TODO Auto-generated method stub

    Class.forName("com.mysql.cj.jdbc.Driver");

    String jdbcUrl = "jdbc:mysql://localhost:3306/test";
    String username = "root";
    String password = "root1234";

    //		onsol
    Connection con = DriverManager.getConnection(jdbcUrl, username, password);

    System.out.println("Connected...");

    Statement stmt = con.createStatement();

    //		getAllEmployees(stmt);
    //		updateSalary(stmt);
    //		getAllEmployees(stmt);

    //		sortEmps(stmt);
    getAvgSal(stmt);

    stmt.close();
    con.close();
  }
}
