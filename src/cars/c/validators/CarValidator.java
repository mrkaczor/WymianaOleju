package cars.c.validators;

import cars.c.CarsService;
import cars.m.Car;
import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.EntityValidator;

/**
 *
 * @author mrkaczor
 */
public class CarValidator implements EntityValidator<Car> {

    @Override
    public void validate(Car entity) throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        
        try {
            entity.selfValidate();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if(CarsService.getInstance().getClientsCars().contains(entity)) {
            ed.addMessage("Pojazd o wskazanym numerze rejestracyjnym jest już zarejestrowany w bazie");
        }
        
        if(!ed.isEmpty()) {
            throw new ValidationException("CAR entity is not valid", ed);
        }
    }
    
}
