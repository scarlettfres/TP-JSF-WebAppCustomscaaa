package step1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import step1.model.UserModel;

public class DB {
	private String DB_HOST="localhost";
	private String DB_PORT="3306";
	private String DB_NAME="step";
	private String DB_USER="root";
	private String DB_PWD="";
	private Connection connection;
	
	public DB() {
		try {
			// Chargement du Driver, puis �tablissement de la connexion
			Class.forName("com.mysql.jdbc.Driver");
		
			//create connection
			 connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/step","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserModel> getData(){
		
		//return value
		ArrayList<UserModel> userList=new ArrayList<UserModel>();
		
		// Cr�ation de la requ�te
		java.sql.Statement query;
		try {
			//Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query.executeQuery( "SELECT * FROM UserTestTP");
			while( rs.next() )
			{
				//Cr�ation de l'utilisateur
				UserModel user=new UserModel(rs.getString( "lastname" ), rs.getString("surname"), rs.getInt("age"), rs.getString("login"), rs.getString("pwd"));
				System.out.println("User : " + user );
				
				//ajout de l'utilisateur r�cup�r� � la liste
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

	public void addUser(UserModel user) {
		
		// Cr�ation de la requ�te
		java.sql.Statement query;
		try {
			//Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Executer puis parcourir les r�sultats
			String sql ="INSERT INTO `users` (`lastname`, `surname`, `age`, `login`, `pwd`) VALUES ('"+user.getLastname()+"', '"+user.getSurname() +"', '"+user.getAge()+"', '"+user.getLogin()+"', '"+user.getPwd()+"')";
			int rs = query.executeUpdate(sql);
			
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
