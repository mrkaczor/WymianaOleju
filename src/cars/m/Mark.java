package cars.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mark implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int NAME_MAX_LENGTH = 20;

    /**
     * The name of this car mark.
     */
    private String m_sName;
    /**
     * Models of cars available for this mark.
     */
    private List<Model> m_Models;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new Mark with empty fields without validation.
     */
    public Mark() {
        
    }
    
    /**
     * Creates new car mark with given name.
     *
     * @param sName Name of created car mark
     * @throws ValidationException when name is not valid
     */
    public Mark(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
        m_Models = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void validateModels() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Models != null && !m_Models.isEmpty()) {
            for(Model m : m_Models) {
                try{
                    m.selfValidate();
                } catch(ValidationException ex) {
                    ed.addMessage("Model o nazwie " + m.getName() + " jest niepoprawny (" + ex.getErrorMessages().getMergeMessages() + ")");
                }
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field MODELS within MARK entity failed", ed);
        }
    }
    
    private void validateName() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sName == null) {
            ed.addMessage("Nazwa jest wymagana");
        } else if (m_sName.length() > NAME_MAX_LENGTH) {
            ed.addMessage("Nazwa jest zbyt duga (max. " + NAME_MAX_LENGTH + " znakw)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field NAME within MARK entity failed", ed);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the name of this mark.
     *
     * @return the name of this mark
     */
    public String getName() {
        return m_sName;
    }

    /**
     * Returns the list of models available for this mark.
     *
     * @return The list of models available for this mark
     */
    public List<Model> getModels() {
        return m_Models;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new name for this mark.
     *
     * @param sName Name of this mark
     * @throws ValidationException when given name is too long or null
     */
    public void setName(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
    }
    // </editor-fold>
  
    /**
     * Adds new model to this mark.
     *
     * @param model new model available for this mark
     * @throws IllegalArgumentException when model is null
     */
    public void addModel(Model model) throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Model pojazdu nie może być null'em!");
        }
        m_Models.add(model);
    }

    @Override
    public void selfValidate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        try {
            validateName();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateModels();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if(!ed.isEmpty()) {
            throw new ValidationException("MARK entity is not valid", ed);
        }
    }
    
    @Override
    public String toString() {
        return m_sName;
    }
  // </editor-fold>
}