package step4.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.model.RecipeModelBean;
import step4.dao.fabric.DaoFabric;
import step4.dao.instance.RecipesDao;
import step4.model.RecipeSubmissionModelBean;
import step4.model.StatusBean;

@ManagedBean
@ApplicationScoped

public class RecipeControlerBean
{
	private RecipesDao recipeDao;
	private ArrayList<RecipeModelBean> recipeListModel;
	private ArrayList<RecipeModelBean> searchedList;
	private RecipeModelBean desiredRecipe;


	public RecipeControlerBean() 
	{
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
	}
	
	
	
/*	public void loadAllRecipe()
	{
		
		ArrayList<RecipeModelBean> list = this.recipeDao.getAllRecipes();
		
		RecipeListModelBean recipeList=new RecipeListModelBean();
		
		for(RecipeModelBean recipe:list)
		{
			recipeList.addRecipeList(recipe);
		}
		
		//r�cup�re l'espace de m�moire de JSF
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		//place la liste de recette dans l'espace de m�moire de JSF
		sessionMap.put("recipeList", recipeList);
		
		
		
	}*/
	
	public String checkAndAddRecipe(RecipeSubmissionModelBean recipeSubmitted)
	{
		
		String returnPage = "recipes";
		
		//V�rifier les propri�t�s de la recette
		//TODO
		
		StatusBean statusBean = new StatusBean();
		
		//ajout de l'utilisateur � la base de donn�es
		
		if( this.recipeDao.addRecipe(recipeSubmitted) )
		{
			statusBean.setSuccess(true);
			statusBean.setMessage("Recipe added!");
		}
		else
		{
			statusBean.setSuccess(false);
			statusBean.setMessage("Adding failed!");
			returnPage = "add-recipe";
		}
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getRequestMap();
		sessionMap.put("statusBean", statusBean);
		return returnPage;
	}
	
	public RecipeModelBean getDesiredRecipe() {
		return desiredRecipe;
	}



	public void setDesiredRecipe(RecipeModelBean desiredRecipe) {
		this.desiredRecipe = desiredRecipe;
	}



	public String showDetailedRecipe()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
	    Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		
		String recipeName = params.get("recipeName");
		
		String returnPage = "recipe-details";

		desiredRecipe = this.recipeDao.getRecipe(recipeName);
		CommentaireControlerBean Commentaires = new CommentaireControlerBean();
		Commentaires.loadAffichage(recipeName);
				
		return returnPage;
	}



	public ArrayList<RecipeModelBean> getRecipeListModel() {
		recipeListModel = this.recipeDao.getAllRecipes();
		System.out.println("bouse");
		return recipeListModel;
	}



	public void setRecipeListModel(ArrayList<RecipeModelBean> recipeListModel) {
		this.recipeListModel = recipeListModel;
	}
	
	public ArrayList<RecipeModelBean> getSearchedList( RecipeModelBean searchedBean) {
		searchedList = this.recipeDao.searchRecipes( searchedBean );
		return searchedList;
	}



	public void setSearchedList(ArrayList<RecipeModelBean> searchedList) {
		this.searchedList = searchedList;
	}

}
