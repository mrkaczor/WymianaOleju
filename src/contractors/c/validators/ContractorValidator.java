package contractors.c.validators;

import contractors.m.Client;
import contractors.m.Contractor;
import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.EntityValidator;

/**
 *
 * @author mrkaczor
 */
public class ContractorValidator implements EntityValidator<Contractor> {

    @Override
    public void validate(Contractor entity) throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        
        try {
            entity.selfValidate();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if(entity.getCompany() != null) {
            try {
                entity.getCompany().selfValidate();
            } catch (ValidationException ex) {
                ed.addMessages(ex.getErrorMessages().getMessages());
            }
        } else {
            if(entity instanceof Client && ((Client)entity).getVorname()!=null) {
                ed.addMessage("Konieczne jest określenie danych klienta i/lub firmy");
            } else {
                ed.addMessage("Dane firmy są wymagane");
            }
        }
        
        if(!ed.isEmpty()) {
            throw new ValidationException("Contractor entity is not valid", ed);
        }
    }
    
}
