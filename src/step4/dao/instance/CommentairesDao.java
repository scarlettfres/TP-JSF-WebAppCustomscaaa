package step4.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step4.model.CommentaireBean;
import step4.model.UserBean;
import step4.processing.CommentaireControlerBean;

public class CommentairesDao {
	
	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public CommentairesDao(String DB_HOST, String DB_PORT, String DB_NAME,
			String DB_USER, String DB_PWD) 
	{
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public void addCommentaire(CommentaireBean com) 
	{
		// Création de la requête
		
		
		System.out.println("DAOO: "+com.getTitleRecipe());
		
		java.sql.Statement query;
		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'élément de requète
			query = connection.createStatement();

			// Executer puis parcourir les résultats
			String sql = "INSERT INTO `step`.`commentaires` (`titleRecipe`, `loginUser`, `date`, `rate`,`message`) VALUES ('"
					+ com.getTitleRecipe()
					+ "', '"
					+ com.getLoginUser()
					+ "', '"
					+ com.getDate()
					+ "', '"
					+ com.getRate()
					+ "', '"
					+ com.getMessage() + "');";
			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CommentaireBean> getAllCommentaires(String titleRecipe) 
	{
		// return value
		ArrayList<CommentaireBean> comList = new ArrayList<CommentaireBean>();

		try {
			// create connection
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Création de la requête
			java.sql.Statement query;

			// Creation de l'élément de requète
			query = connection.createStatement();

			// Executer puis parcourir les résultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM `commentaires` WHERE `titleRecipe`='"+titleRecipe+"' ");
			while (rs.next()) 
			{
				// Création de l'utilisateur
				CommentaireBean com = new CommentaireBean(
						rs.getString("titleRecipe"), rs.getString("loginUser"),
						 rs.getString("date"),
						rs.getInt("rate"),rs.getString("message"));
				// ajout de l'utilisateur récupéré à la liste
				comList.add(com);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comList;
	}
}
