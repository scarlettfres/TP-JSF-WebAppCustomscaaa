package step4.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step4.model.UserBean;
import step4.model.UserModelBean;

public class UserDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public UserDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addUser(UserBean user) {
		// Cr�ation de la requ�te
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			String sql = "INSERT INTO `step`.`users` (`firstname`, `lastname`, `age`, `email`,`login`, `pwd`) VALUES ('"
					+ user.getFirstname()
					+ "', '"
					+ user.getLastname()
					+ "', '"
					+ user.getAge()
					+ "', '"
					+ user.getEmail()
					+ "', '"
					+ user.getLogin()
					+ "', '"
					+ user.getPwd() + "');";
			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UserBean> getAllUser() 
	{
		// return value
		ArrayList<UserBean> userList = new ArrayList<UserBean>();

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Cr�ation de la requ�te
			java.sql.Statement query;

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				// Cr�ation de l'utilisateur
				UserBean user = new UserBean(
						rs.getString("firstname"), rs.getString("lastname"),
						rs.getInt("age"), rs.getString("email"),
						rs.getString("login"),rs.getString("pwd"));
				System.out.println("User : " + user);

				// ajout de l'utilisateur r�cup�r� � la liste
				userList.add(user);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public UserBean checkUser(String login, String pwd) // pour connection 
	{
		try {
				// create connection
				connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
						+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
	
				// Cr�ation de la requ�te
				java.sql.Statement query;
	
				// Creation de l'�l�ment de requ�te
				query = connection.createStatement();
	
				// Executer puis parcourir les r�sultats
				java.sql.ResultSet rs = query
						.executeQuery("SELECT * FROM users where login='"
								+ login + "' and pwd='" + pwd + "';");
	
				if (!rs.next()) 	
				{
					return null;
				}
				else 
				{
					// Cr�ation de l'utilisateur
					UserBean user = new UserBean(
					rs.getString("firstname"), rs.getString("lastname"),
					rs.getInt("age"), rs.getString("email"),
					rs.getString("login"), rs.getString("pwd"));
					System.out.println("User Dao Login : " + user.getLogin() +user.getEmail() );
					return user;
				}
			} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}

	}
	
	public UserBean checkExistingUser(String login, String email) // pour connection 
	{
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Cr�ation de la requ�te
			java.sql.Statement query;

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM users where login='"
							+ login + "' and email='" + email + "';");

			if (!rs.next()) 	// si utilisateur existe deja
			{
				return null;
			}
			else 
			{
				// Cr�ation de l'utilisateur
				UserBean user = new UserBean(
				rs.getString("firstname"), rs.getString("lastname"),
				rs.getInt("age"), rs.getString("email"),
				rs.getString("login"), rs.getString("pwd"));
				System.out.println("User Login : " + user );
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	

}
