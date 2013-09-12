package cars.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author mrkaczor
 */
public class Generation implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int NAME_MAX_LENGTH = 10;
    
    /**
     * The name of this generation.
     */
    private String m_sName;
    /**
     * The production begining date of this generation.
     */
    private Date m_ProductionStartDate;
    /**
     * The production end date of this generation.
     */
    private Date m_ProductionEndDate;
    /**
     * List of engines available for this generation.
     */
    private List<Engine> m_Engines;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new Generation with empty fields without validation.
     */
    public Generation() {
        
    }
    
    /**
     * Creates new car model generation with given name and production start
     * date.
     *
     * @param sName Name of created car model generation
     * @param productionStartDate Production start date for this generation
     * @throws ValidationException when any of the parameters is not valid
     */
    public Generation(String sName, Date productionStartDate) throws ValidationException {
        m_sName = sName;
        validateName();
        m_ProductionStartDate = productionStartDate;
        validateStartDate();
        m_ProductionEndDate = null;
        m_Engines = new ArrayList<>();
    }
  
    /**
     * Creates new car model generation with given name and production start and
     * end date.
     *
     * @param sName Name of created car model generation
     * @param productionStartDate Production start date for this generation
     * @param productionEndDate Production end date for this generation
     * @throws ValidationException when any of the parameters is not valid
     */
    public Generation(String sName, Date productionStartDate, Date productionEndDate) throws ValidationException {
        m_sName = sName;
        validateName();
        m_ProductionStartDate = productionStartDate;
        validateStartDate();
        m_ProductionEndDate = null;
        validateEndDate();
        m_Engines = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void validateEndDate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_ProductionEndDate != null && m_ProductionEndDate.before(m_ProductionStartDate)) {
            ed.addMessage("Pole KONIEC PRODUKCJI nie może zawierać daty wcześniejszej niż POCZĄTEK PRODUKCJI!");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field PRODUCTION_END_DATE within GENERATION entity failed", ed);
        }
    }
  
    private void validateEngines() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Engines == null || m_Engines.isEmpty()) {
            ed.addMessage("Generacja musi mieć określony conajmniej jeden silnik");
        } else {
            for(Engine e : m_Engines) {
                try{
                    e.selfValidate();
                } catch(ValidationException ex) {
                    ed.addMessage("Silnik o oznaczeniu " + e.getSignature() + " jest niepoprawny");
                }
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field ENGINES within GENERATION entity failed", ed);
        }
    }
  
    private void validateName() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sName == null || m_sName.equals("")) {
            ed.addMessage("Nazwa jest wymagana");
        }
        if (m_sName.length() > NAME_MAX_LENGTH) {
            ed.addMessage("Nazwa jest zbyt długa (max. " + NAME_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field NAME within GENERATION entity failed", ed);
        }
    }

    private void validateStartDate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_ProductionStartDate == null) {
            ed.addMessage("Data początku produkcji jest wymagana");
        } else if (m_ProductionEndDate != null && m_ProductionStartDate.after(m_ProductionEndDate)) {
            ed.addMessage("Pole POCZĄTEK PRODUKCJI nie może zawierać daty późniejszej niż KONIEC PRODUKCJI!");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field PRODUCTION_START_DATE within GENERATION entity failed", ed);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns list of engines available for this generation.
     *
     * @return list of engines available for this generation
     */
    public List<Engine> getEngines() {
        return m_Engines;
    }

    /**
     * Returns the name of this generation.
     *
     * @return the name of this generation
     */
    public String getName() {
        return m_sName;
    }

    /**
     * Returns the production end date of this generation.
     *
     * @return the production end date of this generation or null
     */
    public Date getProductionEndDate() {
        return m_ProductionStartDate;
    }

    /**
     * Returns the production beginig date of this generation.
     *
     * @return the production beginig date of this generation
     */
    public Date getProductionStartDate() {
        return m_ProductionStartDate;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new name for this generation.
     *
     * @param sName Name of this generation
     * @throws ValidationException when given name is too long or null
     */
    public void setName(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
    }
  
    /**
     * Sets the production end date for this generation.
     *
     * @param date production end date for this generation
     * @throws ValidationException when this date is before production start
     * date
     */
    public void setProductionEndDate(Date date) throws ValidationException {
        m_ProductionEndDate = date;
        validateEndDate();
    }
  
    /**
     * Sets the production start date for this generation.
     *
     * @param date production start date for this generation
     * @throws ValidationException when date is after production end date or
     * null
     */
    public void setProductionStartDate(Date date) throws ValidationException {
        m_ProductionStartDate = date;
        validateStartDate();
    }
    // </editor-fold>
  
    /**
     * Adds new engine available for this generation.
     *
     * @param engine new engine available for this generation
     * @throws ValidationException when engine is null
     */
    public void addEngine(Engine engine) throws ValidationException {
        m_Engines.add(engine);
        engine.selfValidate();
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
            validateStartDate();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateEndDate();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateEngines();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("GENERATION entity is not valid", ed);
        }
    }
    
    @Override
    public String toString() {
        return m_sName;
    }
    // </editor-fold>

}