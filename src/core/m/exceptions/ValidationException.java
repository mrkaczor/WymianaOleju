package core.m.exceptions;

/**
 *
 * @author Damian Kaczybura
 */
public class ValidationException extends Exception {
    
    private ExceptionDictionary m_ErrorMessages;
    
    public ValidationException(String sMsg) {
        super(sMsg);
    }
    
    public ValidationException(String sMsg, ExceptionDictionary ed) {
        super(sMsg);
        m_ErrorMessages = ed;
    }
    
    public ValidationException(String sMsg, Exception ex) {
        super(sMsg, ex);
        if(ex instanceof ValidationException) {
            m_ErrorMessages = ((ValidationException)ex).getErrorMessages();
        }
    }

    public ExceptionDictionary getErrorMessages() {
        return m_ErrorMessages;
    }

}
