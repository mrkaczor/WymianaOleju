package cars.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;

public class Engine implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int SIGNATURE_MAX_LENGTH = 15;

    /**
     * The type of this engine.
     */
    private EngineType m_Type;
    /**
     * The cubic capacity of this engine (given in ccm).
     */
    private int m_iCubicCapacity;
    /**
     * The power of this engine given in kW.
     */
    private int m_iPower;
    /**
     * The customer-friendly signature of this engine used by it's manufacturer
     * (mostly including it's cubic capacity and type).
     */
    private String m_sSignature;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new engine with empty fields without validation.
     */
    public Engine() {
    }

    /**
     * Creates new engine with given type and cubic capacity.
     *
     * @param type type of created engine
     * @param iCubicCapacity cubic capacity of created engine given in ccm
     * @param iPower power of created engine given in kW
     * @throws ValidationException when any of the parameters is not valid
     */
    public Engine(EngineType type, int iCubicCapacity, int iPower) throws ValidationException {
        m_Type = type;
        validateType();
        m_iCubicCapacity = iCubicCapacity;
        validateCubicCapacity();
        m_iPower = iPower;
        validatePower();
        m_sSignature = buildSignature();
    }

    /**
     * Creates new engine with given type and cubic capacity.
     *
     * @param type type of created engine
     * @param iCubicCapacity cubic capacity of created engine
     * @param iPower power of created engine given in kW
     * @param sSignature customer-friendly signature of created engine used by
     * it's manufacturer
     * @throws ValidationException when any of the parameters is not valid
     */
    public Engine(EngineType type, int iCubicCapacity, int iPower, String sSignature) throws ValidationException {
        m_Type = type;
        validateType();
        m_iCubicCapacity = iCubicCapacity;
        validateCubicCapacity();
        m_iPower = iPower;
        validatePower();
        m_sSignature = sSignature;
        validateSignature();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private String buildSignature() {
        //TODO implement signature bulider method for Engine class
        return null;
    }
  
    private void validateCubicCapacity() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_iCubicCapacity <= 0) {
            ed.addMessage("Pojemność silnika musi być wartością całkowitą dodatnią");
        }
        if(!ed.isEmpty()) {
            throw new ValidationException("Validation of field CUBIC_CAPACITY within ENGINE entity failed", ed);
        }
    }
  
    private void validatePower() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_iPower <= 0) {
            ed.addMessage("Moc silnika musi być wartością całkowitą dodatnią!");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field POWER within ENGINE entity failed", ed);
        }
    }
  
    private void validateSignature() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sSignature == null || !m_sSignature.isEmpty()) {
            ed.addMessage("Oznaczenie silnika jest wymagane");
        } else if (m_sSignature.length() > SIGNATURE_MAX_LENGTH) {
            ed.addMessage("Oznaczenie silnika jest zbyt długie (max. " + SIGNATURE_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field SIGNATURE within ENGINE entity failed", ed);
        }
    }
  
    private void validateType() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Type == null) {
            ed.addMessage("Typ silnika jest wymagany");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field TYPE within ENGINE entity failed", ed);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the cubic capacity of this engine given in ccm.
     *
     * @return cubic capacity of this engine
     */
    public int getCubicCapacity() {
        return m_iCubicCapacity;
    }

    /**
     * Returns the power of this engine given in HP.
     *
     * @return power of this engine
     */
    public int getHorsePower() {
        return (int) Math.ceil(m_iPower * 1.36);
    }

    /**
     * Returns the power of this engine given in kW.
     *
     * @return power of this engine
     */
    public int getPower() {
        return m_iPower;
    }

    /**
     * Returns the customer-friendly signature of this engine used by it's
     * manufacturer (mostly including it's cubic capacity and type).
     *
     * @return
     */
    public String getSignature() {
        return m_sSignature;
    }

    /**
     * Returns the type of this engine.
     *
     * @return type of this engine
     */
    public EngineType getType() {
        return m_Type;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new cubic capacity for this engine.
     *
     * @param iCubicCapacity cubic capacity given in ccm
     * @throws ValidationException when capacity is not a positive number
     */
    public void setCubicCapacity(int iCubicCapacity) throws ValidationException {
        m_iCubicCapacity = iCubicCapacity;
        validateCubicCapacity();
    }
  
    /**
     * Sets new power value for this engine.
     *
     * @param iPower power of this engine given in HP
     * @throws ValidationException when power is not a positive number
     */
    public void setHorsePower(int iPower) throws ValidationException {
        m_iPower = iPower;
        validatePower();
    }
  
    /**
     * Sets new power value for this engine.
     *
     * @param iPower power of this engine given in kW
     * @throws ValidationException when power is not a positive number
     */
    public void setPower(int iPower) throws ValidationException {
        m_iPower = iPower;
        validatePower();
    }

    /**
     * Sets new customer-friendly signature for this engine.
     *
     * @param sSignature signature for this engine
     * @throws ValidationException when signature is too long or null
     */
    public void setSignature(String sSignature) throws ValidationException {
        m_sSignature = sSignature;
        validateSignature();
    }
  
    /**
     * Sets new type for this engine.
     *
     * @param type type of this engine
     * @throws ValidationException when type is null
     */
    public void setType(EngineType type) throws ValidationException {
        m_Type = type;
        validateType();
    }
    // </editor-fold>
  
    @Override
    public void selfValidate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        try {
            validateCubicCapacity();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validatePower();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateType();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateSignature();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Engine entity is not valid", ed);
        }
    }
    
    @Override
    public String toString() {
        return m_sSignature;
    }
    // </editor-fold>

}