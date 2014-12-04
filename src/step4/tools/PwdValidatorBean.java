package step4.tools;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
/*Request scoped managed bean*/
@ManagedBean
@RequestScoped

public class PwdValidatorBean {
  private String input1;
  private String input2;
  private boolean input1Set;

  public void validateField(FacesContext context, UIComponent component,
      Object value) {
	  if (input1Set) 
	  {
	      input2 = (String) value;
	      if (input1 == null || (!input1.equals(input2))) 
	      {
	       // ((EditableValueHolder) component).setValid(false);
	        
	        throw new ValidatorException(new FacesMessage("'"+value+"' both fields unidentical "));
	      }
	      else  System.out.println("Validator Pwd: true");
	  } 
	  else 
	  {
	      input1Set = true;
	      input1 = (String) value;
	  }
  }
}