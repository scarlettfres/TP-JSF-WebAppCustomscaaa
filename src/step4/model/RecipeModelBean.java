package step4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.RecipesDao;

@ManagedBean
@SessionScoped
public class RecipeModelBean implements Serializable {
	private String title;
	private String description;
	private int expertise;
	private int nbpeople;
	private int duration;
	private String type;
	private RecipesDao mydao;
	private ArrayList<RecipeModelBean> recipeList;
	public RecipeModelBean(){this.mydao = DaoFabric.getInstance().createRecipesDao();}
	
	public RecipeModelBean(String title,String description,int expertise,int duration,int nbpeople,String type) {
		this.title = title;
		this.description = description;
		this.expertise = expertise;
		this.duration = duration;
		this.nbpeople = nbpeople;
		this.type = type;
		this.mydao = DaoFabric.getInstance().createRecipesDao();
		
	}
	
	
	public String Search()
	{
		this.setRecipeList(mydao.searchRecipes(this));
		return "affichage";
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExpertise() {
		return expertise;
	}
	public void setExpertise(int expertise) {
		this.expertise = expertise;
	}
	public int getNbpeople() {
		return nbpeople;
	}
	public void setNbpeople(int nbpeople) {
		this.nbpeople = nbpeople;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<RecipeModelBean> getRecipeList() {
		return recipeList;
	}

	public void setRecipeList(ArrayList<RecipeModelBean> recipeList) {
		this.recipeList = recipeList;
	}
}
