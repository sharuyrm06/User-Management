package com.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.usermangement.bean.User;

public class UserDao {
	static String url="jdbc:mysql://localhost:3306/usermanagement";
	static String user="root";
	static String pass="admin";
	static Statement s = null;
	static PreparedStatement ps =null;
	static Connection con = null;
	static ResultSet rs = null;
	static String checkQuery="select * from admin";
	private static String insert="insert into user(name,email,city)values(?,?,?)";
	private static String delete="delete from user where id=?";
	private static String selectUser="select * from user where id=?"; 
	private static String updateUser="update user set name=?,email=?,city=? where id=?";
	private static String display="select * from user";
	
	
	public static void delete(int id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			ps = con.prepareStatement(delete);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static User selectUser(int id1)
	{
		User u = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			ps = con.prepareStatement(selectUser);
			ps.setInt(1,id1);
			rs = ps.executeQuery();
			rs.next();
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String city = rs.getString("city");
				u = new User(id, name, email, city);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	
	public static boolean checkLogin(String username,String password)
	{
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			s = con.createStatement();
			rs = s.executeQuery(checkQuery);
			while(rs.next())
			{
				String name = rs.getString("username");
				String pass1 = rs.getString("password");
				if(name.equals(username) && pass1.equals(password))
				{
					return true;
				}
;			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				s.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}
	public static void insertUserDetails(User u)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			ps = con.prepareStatement(insert);
			ps.setString(1,u.getName());
			ps.setString(2,u.getEmail());
			ps.setString(3,u.getCity());
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		public static ArrayList<User> display() {
			ArrayList<User> al = new ArrayList<User>();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, pass);
				s = con.createStatement();
				rs = s.executeQuery(display);
				while(rs.next())
				{
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String city = rs.getString("city");
					User u = new User(id, name, email, city);
					al.add(u);
				
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
		       try {
				   rs.close();
				   s.close();
				   con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			return al;	
		}
		 public static void updateUser(User u)
		 {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url, user, pass);
				ps = con.prepareStatement(updateUser);
				ps.setString(1,u.getName());
				ps.setString(2, u.getEmail());
				ps.setString(3,u.getCity());
				ps.setInt(4,u.getId());
				ps.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			 {
				 try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 
		 }
		 
	}
		
	
		