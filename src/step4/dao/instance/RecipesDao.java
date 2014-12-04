package step4.dao.instance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import step4.model.RecipeModelBean;

public class RecipesDao {

	private Connection connection;
	private String dB_HOST;
	private String dB_PORT;
	private String dB_NAME;
	private String dB_USER;
	private String dB_PWD;

	public RecipesDao(String DB_HOST,String DB_PORT, String DB_NAME,String DB_USER,String DB_PWD) {
		dB_HOST = DB_HOST;
		dB_PORT = DB_PORT;
		dB_NAME = DB_NAME;
		dB_USER = DB_USER;
		dB_PWD = DB_PWD;
	}

	public boolean addRecipe(RecipeModelBean recipe) {
		// Cr�ation de la requ�te
		java.sql.Statement query;
		
		// create connection
		try {
			connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
					+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			String sql = "INSERT INTO `recipes` (`title`, `description`, `expertise`, `duration`, `nbpeople`,`type`) VALUES ('"
					+ recipe.getTitle()
					+ "', '"
					+ recipe.getDescription()
					+ "', '"
					+ recipe.getExpertise()
					+ "', '"
					+ recipe.getDuration()
					+ "', '"
					+ recipe.getNbpeople()
					+ "', '" + recipe.getType() + "');";
			int rs = query.executeUpdate(sql);
			query.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<RecipeModelBean> getAllRecipes() {
		ArrayList<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM recipes;");
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModelBean recipe = new RecipeModelBean(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				// ajout de la recette r�cup�r�e � la liste
				recipeList.add(recipe);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeList;
	}
	
	public RecipeModelBean getRecipe(String Name)
	{
		RecipeModelBean desiredRecipe = new RecipeModelBean();

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();

			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery("SELECT * FROM recipes WHERE title = '"+Name+"';");

				RecipeModelBean recipe = new RecipeModelBean(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return desiredRecipe;
	}
	
	public ArrayList<RecipeModelBean> searchRecipes( RecipeModelBean SearchedBean ) {
		ArrayList<RecipeModelBean> recipeList = new ArrayList<RecipeModelBean>();

		// Cr�ation de la requ�te
		java.sql.Statement query;

		try {
		
		// create connection
		connection = java.sql.DriverManager.getConnection("jdbc:mysql://"
				+ dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);

			// Creation de l'�l�ment de requ�te
			query = connection.createStatement();
			
			// Construction de la requete
			String requete = "SELECT * FROM recipes";
			
			if( SearchedBean.getExpertise() != 0 || SearchedBean.getTitle() != "" || SearchedBean.getDuration() != 0 || SearchedBean.getNbpeople() != 0 || SearchedBean.getType() != "" )
			{
				 requete += " WHERE ";
				 if(SearchedBean.getExpertise() != 0){
					 requete += "expertise = '" + SearchedBean.getExpertise() + "'";
				 }
				 else
				 {
					 requete += " 1=1 ";
				 }
				 if(SearchedBean.getTitle() != ""){
					 requete += " AND title = '" + SearchedBean.getTitle() + "'";
				 }
				 else
				 {
					 requete += " AND 1=1 ";
				 }
				 if(SearchedBean.getDuration() != 0){
					 requete += " AND duration = '" + SearchedBean.getDuration() + "'";
				 }
				 else
				 {
					 requete += " AND 1=1 ";
				 }
				 if(SearchedBean.getNbpeople() != 0){
					 requete += " AND nbpeople = '" + SearchedBean.getNbpeople() + "'";
				 }
				 else
				 {
					 requete += " AND 1=1 ";
				 }
				 if(SearchedBean.getType() != ""){
					 requete += " AND type = '" + SearchedBean.getType() + "'";
				 }
				 else
				 {
					 requete += " AND 1=1 ";
				 }
			}
			requete += ";";
			System.out.println("Requete : " + requete);
			// Executer puis parcourir les r�sultats
			java.sql.ResultSet rs = query
					.executeQuery( requete );
			while (rs.next()) {
				// Cr�ation de  la recette
				RecipeModelBean recipe = new RecipeModelBean(
						rs.getString("title"), rs.getString("description"),
						rs.getInt("expertise"), rs.getInt("duration"),
						rs.getInt("nbpeople"), rs.getString("type"));
				System.out.println("Recipe : " + recipe);

				// ajout de la recette r�cup�r�e � la liste
				recipeList.add(recipe);
			}
			rs.close();
			query.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeList;
	}

}
