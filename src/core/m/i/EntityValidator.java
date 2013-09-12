package core.m.i;

import core.m.exceptions.ValidationException;

/**
 *
 * @author Damian Kaczybura
 */
public interface EntityValidator<T> {

    public void validate(T entity) throws ValidationException;

}
