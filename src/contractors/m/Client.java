package contractors.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;
import utils.CommonValidator;

/**
 * Class representing a client, which extends contractor.
 * @author mrkaczor
 */
public class Client extends Contractor implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int ADDRESS_MAX_LENGTH = 100;
    private static final int PHONE_NUMBER_MAX_LENGTH = 12;
    private static final int PHONE_NUMBER_MIN_LENGTH = 9;
    private static final int SURNAME_MAX_LENGTH = 20;
    private static final int VORNAME_MAX_LENGTH = 20;
    
    /**
     * The vorname of this client.
     */
    private String m_sVorname;
    /**
     * The surname of this client.
     */
    private String m_sSurname;
    /**
     * The address of this client.
     */
    private String m_sAddress;
    /**
     * The phone numer of this client.
     */
    private String m_sPhoneNumber;
    /**
     * The tax identification number of this client.
     */
    private String m_sTIN;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new client with empty fields without validation.
     */
    public Client() {
        m_sVorname = null;
        m_sSurname = null;
        m_sAddress = null;
        m_sPhoneNumber = null;
        m_sTIN = null;
    }
    
    /**
     * Creates new client with given short-cut name, vorname and surname.
     *
     * @param sName short-cut name of this client
     * @param sVorname created client's vorname
     * @param sSurname created client's surname
     * @throws ValidationException when any of parameters is not valid
     */
    public Client(String sName, String sVorname, String sSurname) throws ValidationException {
        super(sName);
        m_sVorname = sVorname;
        validateVorname();
        m_sSurname = sSurname;
        validateSurname();
        m_sAddress = null;
        m_sPhoneNumber = null;
        m_sTIN = null;
    }
  
    /**
     * Creates new client with given short-cut name, vorname, surname and TIN.
     *
     * @param sName short-cut name of this client
     * @param sVorname created client's vorname
     * @param sSurname created client's surname
     * @param sTIN created client's TIN
     * @throws ValidationException when any of parameters is not valid
     */
    public Client(String sName, String sVorname, String sSurname, String sTIN) throws ValidationException {
        super(sName);
        m_sVorname = sVorname;
        validateVorname();
        m_sSurname = sSurname;
        validateSurname();
        m_sTIN = sTIN;
        validateTIN();
        m_sAddress = null;
        m_sPhoneNumber = null;
    }
  
    /**
     * Creates new client with given short-cut name, vorname, surname and
     * company.
     *
     * @param sName short-cut name of this client
     * @param sVorname created client's vorname
     * @param sSurname created client's surname
     * @param company created client's company
     * @throws ValidationException when any of parameters is not valid
     */
    public Client(String sName, String sVorname, String sSurname, Company company) throws ValidationException {
        super(sName, company);
        m_sVorname = sVorname;
        validateVorname();
        m_sSurname = sSurname;
        validateSurname();
        m_sAddress = null;
        m_sPhoneNumber = null;
        m_sTIN = null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void validateAddress() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sAddress == null || m_sAddress.isEmpty()) {
            ed.addMessage("Adres jest wymagany!");
        } else if (m_sAddress.length() > ADDRESS_MAX_LENGTH) {
            ed.addMessage("Adres jest zbyt długi (max. " + ADDRESS_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field ADRESS within CLIENT entity failed", ed);
        }
    }
  
    private void validatePhoneNumber() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sPhoneNumber != null) {
            if (m_sPhoneNumber.length() < PHONE_NUMBER_MIN_LENGTH) {
                ed.addMessage("Numer telefonu jest zbyt krótki (min. " + PHONE_NUMBER_MIN_LENGTH + " znaków)");
            } else if (m_sPhoneNumber.length() > PHONE_NUMBER_MAX_LENGTH) {
                ed.addMessage("Numer telefonu jest zbyt długi (max. " + PHONE_NUMBER_MAX_LENGTH + " znaków)");
            } else if (m_sPhoneNumber.charAt(0) != '+' && (m_sPhoneNumber.charAt(0) < 48 || m_sPhoneNumber.charAt(0) > 57)) {
                ed.addMessage("Numer telefonu jest niepoprawny (może się on zaczynać znakiem '+' i musi się składać wyłącznie z cyfer)");
            }
            for (int i = 1; i < m_sPhoneNumber.length(); i++) {
                if (m_sPhoneNumber.charAt(i) < 48 || m_sPhoneNumber.charAt(i) > 57) {
                    ed.addMessage("Numer telefonu jest niepoprawny (może się on zaczynać znakiem '+' i musi się składać wyłącznie z cyfer)");
                    break;
                }
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field PHONE_NUMBER within CLIENT entity failed", ed);
        }
    }
  
    private void validateSurname() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sSurname == null || m_sSurname.isEmpty()) {
            ed.addMessage("Nazwisko jest wymagane!");
        } else if (m_sSurname.length() > SURNAME_MAX_LENGTH) {
            ed.addMessage("Nazwisko jest zbyt długie (max. " + SURNAME_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field SURNAME within CLIENT entity failed", ed);
        }
    }
  
    private void validateTIN() throws ValidationException {
        if (m_sTIN != null) {
            try {
                CommonValidator.validateTIN(m_sTIN);
            } catch (ValidationException ex) {
                throw new ValidationException("Validation of field TIN within CLIENT entity failed", ex);
            }
        }
    }
  
    private void validateVorname() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sVorname == null || m_sVorname.isEmpty()) {
            ed.addMessage("Imię jest wymagane!");
        } else if (m_sVorname.length() > VORNAME_MAX_LENGTH) {
            ed.addMessage("Imię jest zbyt długie (max. " + VORNAME_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field VORNAME within CLIENT entity failed", ed);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the address of this client.
     *
     * @return Address of this client or null
     */
    public String getAddress() {
        return m_sAddress;
    }

    /**
     * Returns the phone number of this client.
     *
     * @return Phone number of this client
     */
    public String getPhoneNumber() {
        return m_sPhoneNumber;
    }

    /**
     * Returns the surname of this client.
     *
     * @return Surname of this client
     */
    public String getSurname() {
        return m_sSurname;
    }

    /**
     * Returns the tax identification number of this client.
     *
     * @return Tax identification number of this client or null
     */
    public String getTIN() {
        return m_sTIN;
    }

    /**
     * Returns the vorname of this client.
     *
     * @return Vorname of this client
     */
    public String getVorname() {
        return m_sVorname;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new address for this client.
     *
     * @param sAddress address of this client
     * @throws ValidationException when given address is too long
     */
    public void setAddress(String sAddress) throws ValidationException {
        m_sAddress = sAddress;
        validateAddress();
    }

    /**
     * Sets new phone number for this client.
     *
     * @param sPhoneNumber phone number of this client
     * @throws ValidationException when given phone number is not valid
     */
    public void setPhoneNumber(String sPhoneNumber) throws ValidationException {
        m_sPhoneNumber = sPhoneNumber;
        validatePhoneNumber();
    }

    /**
     * Sets new surname for this client.
     *
     * @param sSurname surname of this client
     * @throws ValidationException when given surname is too long or null
     */
    public void setSurname(String sSurname) throws ValidationException {
        m_sSurname = sSurname;
        validateSurname();
    }

    /**
     * Sets new tax identification number for this client.
     *
     * @param sTIN tax identification number of this client
     * @throws ValidationException when given TIN is not valid
     */
    public void setTIN(String sTIN) throws ValidationException {
        m_sTIN = sTIN;
        validateTIN();
    }

    /**
     * Sets new vorname for this client.
     *
     * @param sVorname vorname of this client
     * @throws ValidationException when given vorname is too long or null
     */
    public void setVorname(String sVorname) throws ValidationException {
        m_sVorname = sVorname;
        validateVorname();
    }
    // </editor-fold>
  
    @Override
    public void selfValidate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        try {
            super.selfValidate();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateVorname();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateSurname();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateAddress();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validatePhoneNumber();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateTIN();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("CLIENT entity is not valid", ed);
        }
    }
    
    @Override
    public String toString() {
        return m_sName;
    }
    // </editor-fold>

}