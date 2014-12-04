package step4.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "NameValidator")
public class NameValidator implements Validator{
	
	@Override
	 public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	       
	    	String name = (String) value;
	    	String ePattern = "^[a-zA-Z0-9]+$";
	        Pattern p = Pattern.compile(ePattern);
	        Matcher m = p.matcher(name);
	        System.out.println("Validator Name: "+m.matches());
	      //  return m.matches();
	    	
	        if (m.matches() == false ) {
	        	
	            throw new ValidatorException(new FacesMessage("'"+value+"' is not a correct Name "));
	        }
	    }

}

