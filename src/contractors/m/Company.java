package contractors.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;
import utils.CommonValidator;

/**
 * Class representing contractor's company.
 *
 * @author mrkaczor
 */
public class Company implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int ADDRESS_MAX_LENGTH = 100;
    private static final int NAME_MAX_LENGTH = 60;
    
    /**
     * The name of this company.
     */
    private String m_sName;
    /**
     * The address of this company premises.
     */
    private String m_sAddress;
    /**
     * The tax identification number of this company.
     */
    private String m_sTIN;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    
    /**
     *  Creates new company with empty fields without validation.
     */
    public Company() {
        m_sName = null;
        m_sAddress = null;
        m_sTIN = null;
    }
    
    /**
     * Creates new company with given name, address and TIN.
     *
     * @param sName name of created company
     * @param sAddress address of created company premises
     * @param sTIN tax identification number of created company
     * @throws ValidationException when any of the parameters is not valid
     */
    public Company(String sName, String sAddress, String sTIN) throws ValidationException {
        m_sName = sName;
        validateName();
        m_sAddress = sAddress;
        validateAddress();
        m_sTIN = sTIN;
        validateTIN();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void validateAddress() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sAddress == null || m_sAddress.isEmpty()) {
            ed.addMessage("Adres jest wymagany");
        } else if (m_sAddress.length() > ADDRESS_MAX_LENGTH) {
            ed.addMessage("Adres jest zbyt długi (max. " + ADDRESS_MAX_LENGTH + " znaków)");
        }
        if(!ed.isEmpty()) {
            throw new ValidationException("Validation of field ADDRESS within COMPANY entity failed", ed);
        }
    }
  
    private void validateName() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sName == null || m_sName.isEmpty()) {
            ed.addMessage("Nazwa jest wymagana");
        } else if (m_sName.length() > NAME_MAX_LENGTH) {
            ed.addMessage("Nazwa jest zbyt długa (max. " + NAME_MAX_LENGTH + " znaków)");
        }
        if(!ed.isEmpty()) {
            throw new ValidationException("Validation of field NAME within COMPANY entity failed", ed);
        }
    }
  
    private void validateTIN() throws ValidationException {
        try {
            CommonValidator.validateTIN(m_sTIN);
        } catch (ValidationException ex) {
            throw new ValidationException("Validation of field TIN within COMPANY entity failed", ex);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the address of this comapny.
     *
     * @return address of this comapny
     */
    public String getAddress() {
        return m_sAddress;
    }

    /**
     * Returns the name of this company.
     *
     * @return name of this company
     */
    public String getName() {
        return m_sName;
    }

    /**
     * Returns the tax identification number of this company.
     *
     * @return tax identification number of this company
     */
    public String getTIN() {
        return m_sTIN;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new address for this company.
     *
     * @param sAddress address of this company
     * @throws ValidationException when given address is too long or null
     */
    public void setAddress(String sAddress) throws ValidationException {
        m_sAddress = sAddress;
        validateAddress();
    }
  
    /**
     * Sets new name for this company.
     *
     * @param sName name of this company
     * @throws ValidationException when given name is too long or null
     */
    public void setName(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
    }
  
    /**
     * Sets new tax identification number for this company.
     *
     * @param sTIN tax identification number of this company
     * @throws ValidationException when given TIN is null or not valid
     */
    public void setTIN(String sTIN) throws ValidationException {
        m_sTIN = sTIN;
        validateTIN();
    }
    // </editor-fold>
  
    @Override
    public void selfValidate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        try {
            validateName();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateAddress();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateTIN();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Company entity is not valid", ed);
        }
    }
    // </editor-fold>

}