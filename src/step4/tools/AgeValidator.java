package step4.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "AgeValidator")
public class AgeValidator implements Validator
{
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{
		int name =(int)value;
    	
        if(name>100)	
        {
        	 System.out.println("Validator Age: false");
        	 throw new ValidatorException(new FacesMessage("'"+value+"' is not a correct age  "));
        }
	    else
	    {
	    	System.out.println("Validator Age: true");
	    }
	    	
	 }

}

