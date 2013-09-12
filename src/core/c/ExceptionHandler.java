package core.c;

/**
 *
 * @author Damian Kaczybura
 */
public class ExceptionHandler {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static ExceptionHandler getInstance() {
        return ExceptionHandler.InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {
        private static final ExceptionHandler p_instance = new ExceptionHandler();
    }
    // </editor-fold>

    private ExceptionHandler() {
        
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    
    // </editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    
    // </editor-fold>

}
