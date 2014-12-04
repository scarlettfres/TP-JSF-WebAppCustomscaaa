package step4.processing;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.model.RecipeModelBean;
import step4.dao.fabric.DaoFabric;
import step4.dao.instance.RecipesDao;
import step4.model.RecipeListModelBean;
import step4.model.RecipeModelBean;
import step4.model.RecipeSubmissionModelBean;
import step4.model.StatusBean;
import step4.model.UserSubmissionModelBean;

@ManagedBean
@ApplicationScoped

public class RecipeControlerBean 
{
	private RecipesDao recipeDao;
	
	public RecipeControlerBean() 
	{
		this.recipeDao=DaoFabric.getInstance().createRecipesDao();
	}
	
	
	public void loadAllRecipe()
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
		
	}
	
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
}
