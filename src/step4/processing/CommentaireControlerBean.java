package step4.processing;


import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.dao.fabric.DaoFabric;
import step4.dao.instance.CommentairesDao;
import step4.model.CommentaireBean;
import step4.model.UserBean;

@ManagedBean
@ApplicationScoped

public class CommentaireControlerBean 
{

	private CommentairesDao commentaireDao;
	
	public CommentaireControlerBean() 
	{
		this.commentaireDao=DaoFabric.getInstance().createCommentairesDao();
	}

	public String addCommentaire (CommentaireBean commentaire)
	{
		CommentaireBean com;
		com = new CommentaireBean(
				commentaire.getTitleRecipe(),
				commentaire.getLoginUser(),
				commentaire.getDate(),
				commentaire.getRate(),
				commentaire.getMessage());
		
		System.out.println("Validator Email: "+com.getTitleRecipe());
		this.commentaireDao.addCommentaire(com);
		loadAffichage(commentaire.getTitleRecipe())	;
		return "test.xhtml";
	}

	
	
	public void loadAffichage(String titleRecipe)
	{
		 ArrayList<CommentaireBean> liste_commentaire= commentaireDao.getAllCommentaires(titleRecipe);
		
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		 Map<String, Object> sessionMap = externalContext.getSessionMap();
		 sessionMap.remove("commentaires");// On efface la derniere sessionmap de commentaires pour créer une nouvelle 		
		 sessionMap.put("commentaires", liste_commentaire);
		 for (int i=0;i<liste_commentaire.size();i++)
		 {
			System.out.println("Message =  "+liste_commentaire.get(i).getMessage());
		 }
		
	}
}
