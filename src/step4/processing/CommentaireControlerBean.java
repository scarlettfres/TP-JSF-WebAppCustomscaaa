package step4.processing;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;








import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		UserBean user= new UserBean();
		user= (UserBean) sessionMap.get("loggedUser");
		if (user==null)
		{
			 return "test.xhtml";
		}
		else
		{
		 String format = "dd/MM/yy H:mm:ss";
		 java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
		 java.util.Date date = new java.util.Date();
		 String strdate = (String)formater.format( date );
		 com = new CommentaireBean(
				 commentaire.getTitleRecipe(),
				 user.getLogin(),
				 strdate,
				 commentaire.getRate(),
				 commentaire.getMessage());

		 System.out.println("Validator Email: "+com.getTitleRecipe());
		 this.commentaireDao.addCommentaire(com);
		 loadAffichage(commentaire.getTitleRecipe())	;
		 return "test.xhtml";
		}

	}

	
	
	public void loadAffichage(String titleRecipe)
	{
		 ArrayList<CommentaireBean> liste_commentaire= commentaireDao.getAllCommentaires(titleRecipe);
		
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		 Map<String, Object> sessionMap = externalContext.getSessionMap();
		 sessionMap.remove("commentaires");// On efface la derniere sessionmap de commentaires pour créer une nouvelle 		
		 sessionMap.put("commentaires", liste_commentaire);
		/* for (int i=0;i<liste_commentaire.size();i++)
		 {
			System.out.println("Message =  "+liste_commentaire.get(i).getMessage());
		 }*/
		
	}
}
