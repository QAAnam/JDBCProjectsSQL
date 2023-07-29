package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class JDBCSetUp
{
	@Test
	public void jdbcSetup() throws SQLException
	{
		
		String id= new Faker().number().digits(3);
		String fname = new Faker().name().firstName();
		String lname = new Faker().name().lastName();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","root");
		Statement query = connect.createStatement();
		String qur="insert into actor(actor_id,first_name,last_name,last_update) values(\'"+id+"\',\'"+fname+"\',\'"+lname+"\' ,\'"+time+"\');";
		query.execute(qur);
		String qur1= "select * from actor where actor_id ="+ id;
		ResultSet result = query.executeQuery(qur1);
		while (result.next()) 
		{
			System.out.println(result.getString(1)+" | "+result.getString(2)+" | "+result.getString(3)+" | "+result.getString(4));
			
		}
		connect.close();
		
	}

}
