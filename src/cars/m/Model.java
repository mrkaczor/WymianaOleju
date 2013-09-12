package cars.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int NAME_MAX_LENGTH = 20;

    /**
     * The name of this car model.
     */
    private String m_sName;
    /**
     * List of generetions available for this mark.
     */
    private List<Generation> m_Generations;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new Model with empty fields without validation.
     */
    public Model() {
        
    }
    
    /**
     * Creates new car model with given name.
     *
     * @param sName name of created car model
     * @throws ValidationException when name is not valid
     */
    public Model(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
        m_Generations = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void validateGenerations() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Generations != null && !m_Generations.isEmpty()) {
            for(Generation g : m_Generations) {
                try{
                    g.selfValidate();
                } catch(ValidationException ex) {
                    ed.addMessage("Generacja o oznaczeniu " + g.getName() + " jest niepoprawna (" + ex.getErrorMessages().getMergeMessages() + ")");
                }
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field GENERATIONS within MODEL entity failed", ed);
        }
    }
    
    private void validateName() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sName == null) {
            ed.addMessage("Pole NAZWA jest wymagane!");
        } else if (m_sName.length() > NAME_MAX_LENGTH) {
            ed.addMessage("Nazwa jest zbyt długa (max. " + NAME_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field NAME within MODEL entity failed", ed);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the list of generations available for this model.
     *
     * @return The list of generations available for this model
     */
    public List<Generation> getGenerations() {
        return m_Generations;
    }

    /**
     * Returns the name of this model.
     *
     * @return the name of this model
     */
    public String getName() {
        return m_sName;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new name for this model.
     *
     * @param sName name of this model
     * @throws ValidationException when given name is too long or null
     */
    public void setName(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
    }
    // </editor-fold>
  
    /**
     * Adds new generation to this model.
     *
     * @param generation new generation available for this mark
     * @throws IllegalArgumentException when generation is null
     */
    public void addGeneration(Generation generation) throws IllegalArgumentException {
        if (generation == null) {
            throw new IllegalArgumentException("Generacja pojazdu nie może być null'em!");
        }
        m_Generations.add(generation);
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
            validateGenerations();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if(!ed.isEmpty()) {
            throw new ValidationException("MODEL entity is not valid", ed);
        }
    }

    @Override
    public String toString() {
        return m_sName;
    }
    // </editor-fold>
}