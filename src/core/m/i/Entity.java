package core.m.i;

import core.m.exceptions.ValidationException;

/**
 *
 * @author Damian Kaczybura
 */
public interface Entity {
    
    public void selfValidate() throws ValidationException;

}
