package step4.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class UserBean implements Serializable
{
	private String firstname;
	private String lastname;
	private int age;
	private String email; 
	private String login;
	private String pwd;
	
	public UserBean() 
	{
	}
	
	public UserBean(String firstname, String lastname,int age,String email, String login,String pwd) 
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.email=email;
		this.login = login;
		this.pwd = pwd;
	}	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
