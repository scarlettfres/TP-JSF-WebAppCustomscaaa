package step2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import step1.model.UserModel;
import step2.model.UserModelBean;

public class DB {
//	private String DB_HOST="db-tp.cpe.fr";
//	private String DB_PORT="3306";
//	private String DB_NAME="binome32";
//	private String DB_USER="binome32";
//	private String DB_PWD="binome32";
	
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
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME , DB_USER, DB_PWD);
			 //connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/step","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public void addUser(UserModelBean user) {
		
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
