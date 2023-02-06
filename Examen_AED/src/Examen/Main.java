package Examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //step1 load the driver class
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //step2 create  the connection object
        Connection con=DriverManager.getConnection(
                //"jdbc:oracle:thin:@192.168.192.75:1521:XE","MIXE","aed");  //192.168.192.75 XE , MIXE , aed
            "jdbc:oracle:thin:@localhost:1521:system","system","system");
        //step3 create the statement object
        Statement stmt=con.createStatement();

        //step4 execute query
        ResultSet rs=stmt.executeQuery("select * from alumnos");
        while(rs.next())
            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

        //step5 close the connection object
        con.close();

    }

}
