package step4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RecipeListModelBean implements Serializable {
	private List<RecipeModelBean> recipeList;
	
	public RecipeListModelBean() {
		recipeList=new ArrayList<RecipeModelBean>();
	}
	
	public void addRecipeList(RecipeModelBean recipe){
		this.recipeList.add(recipe);
	}
	
	public List<RecipeModelBean> getRecipeList() {
		return recipeList;
	}

}
