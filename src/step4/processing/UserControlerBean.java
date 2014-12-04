package step4.processing;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step4.model.LoginBean;
import step4.model.UserBean;
import step4.model.UserModelBean;
import step4.dao.fabric.DaoFabric;
import step4.dao.instance.UserDao;
import step4.model.UserSubmissionModelBean;

@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entré unique à l'ensemble des clients
public class UserControlerBean 
{
	private UserDao userDao;
	
	public UserControlerBean() 
	{
		this.userDao=DaoFabric.getInstance().createUserDao();
	}
	
	public String checkUser(LoginBean loginBean)
	{
		UserBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
		if( user!=null) 
		{
			System.out.println("user.getLogin()" );
			//récupère l'espace de mémoire de JSF
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			//place l'utilisateur dans l'espace de mémoire de JSF
			sessionMap.put("loggedUser", user);
			
			//redirect the current page
			return "main.xhtml";
		}
		else
		{
			System.out.println("erreur login ");
			return "main.xhtml";
		}
	}
	
	public String deconectUser()
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.remove("loggedUser");
		return "main.xhtml";
	}
	
	
	public String inscriptionUser (UserBean userBean)
	{
		UserBean user = this.userDao.checkExistingUser(userBean.getLogin(), userBean.getEmail());
		if (user == null)// ie utilisateur n'existe pas deja 
		{
			this.userDao.addUser(userBean);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			//place l'utilisateur dans l'espace de mémoire de JSF
			sessionMap.put("loggedUser", userBean);
			// .useHttpSession session = request.getSession(true);
		}
		else
		{
			
			System.out.println("EXISTE DEJA ");
		}
			
		return "main.xhtml";
	}
	public String loadInscription()
	{
		return "inscription.xhtml";
	}
	
	
	
	

}
