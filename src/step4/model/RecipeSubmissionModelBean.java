package step4.model;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step4.processing.RecipeControlerBean;

@ManagedBean
@RequestScoped
public class RecipeSubmissionModelBean extends RecipeModelBean {
	private String errorstatus;
	
	public RecipeSubmissionModelBean() {
		
	}

	public String getErrorstatus() {
		return errorstatus;
	}

	public void setErrorstatus(String errorstatus) {
		this.errorstatus = errorstatus;
	}
}