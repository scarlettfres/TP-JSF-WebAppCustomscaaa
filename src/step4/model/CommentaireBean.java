package step4.model;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class CommentaireBean {



	private String loginUser;
	private String date;
	private int rate;
	private String message;
	private String titleRecipe;
	
	public CommentaireBean() 
	{
	}
	
	public CommentaireBean(String titleRecipe, String loginUser,String date,int rate, String message) 
	{
		this.titleRecipe = titleRecipe;
		this.loginUser = loginUser;
		this.date = date;
		this.rate=rate;
		this.message = message;
		
	}	
	
	
	public String getTitleRecipe() {
		return titleRecipe;
	}
	public void setTitleRecipe(String tittleRecipe) {
		this.titleRecipe = tittleRecipe;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
