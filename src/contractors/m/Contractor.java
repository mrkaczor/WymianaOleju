package contractors.m;

import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;

/**
 * Abstract class representing an contractor item.
 * @author mrkaczor
 */
public abstract class Contractor implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private static final int ANNOTATIONS_MAX_LENGTH = 200;
    private static final int NAME_MAX_LENGTH = 20;
    private static final int NAME_MIN_LENGTH = 3;
    
    /**
     * The short-cut name of this contractor.
     */
    protected String m_sName;
    /**
     * The company represented by this contractor.
     */
    protected Company m_Company;
    /**
     * The annotations about this contractor.
     */
    protected String m_sAnnotations;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new contractor with empty fields without validation.
     */
    public Contractor() {
        m_sName = null;
        m_Company = null;
        m_sAnnotations = null;
    }
    
    /**
     * Creates new contractor with given name and company.
     *
     * @param sName name of created contractor
     * @throws ValidationException when any of parameters is not valid
     */
    public Contractor(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
        m_Company = null;
        m_sAnnotations = null;
    }
  
    /**
     * Creates new contractor with given name and company.
     *
     * @param sName name of created contractor
     * @param company copmany that is represented by created contractor
     * @throws ValidationException when any of parameters is not valid
     */
    public Contractor(String sName, Company company) throws ValidationException {
        m_sName = sName;
        validateName();
        m_Company = company;
        validateCompany();
        m_sAnnotations = null;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    protected void validateAnnotations() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sAnnotations != null && m_sAnnotations.length() > ANNOTATIONS_MAX_LENGTH) {
            ed.addMessage("Uwagi są zbyt długie (max. " + ANNOTATIONS_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field ANNOTATIONS within CONTRACTOR entity failed", ed);
        }
    }
  
    protected void validateCompany() throws ValidationException {
        //No special validation needed for custom contractor.
    }
  
    protected void validateName() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sName == null || m_sName.isEmpty()) {
            ed.addMessage("Nazwa jest wymagana");
        } else if (m_sName.length() > NAME_MAX_LENGTH) {
            ed.addMessage("Nazwa jest zbyt krótka (min. " + NAME_MIN_LENGTH + " znaków)");
        } else if (m_sName.length() > NAME_MAX_LENGTH) {
            ed.addMessage("Nazwa jest zbyt długa (max. " + NAME_MAX_LENGTH + " znaków)");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field NAME within CONTRACTOR entity failed", ed);
        }
    }
  // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the annotations for this contractor.
     *
     * @return Annotations for this contractor
     */
    public String getAnnotations() {
        return m_sAnnotations;
    }
  
    /**
     * Returns the company represented by this contractor.
     *
     * @return Company represented by this contractor
     */
    public Company getCompany() {
        return m_Company;
    }
  
    /**
     * Returns the name of this part.
     *
     * @return Name of this part
     */
    public String getName() {
        return m_sName;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Sets new annotations for this contractor.
     *
     * @param sAnnotations new contractor annotations
     * @throws ValidationException when given annotations are too long
     */
    public void setAnnotations(String sAnnotations) throws ValidationException {
        m_sAnnotations = sAnnotations;
        validateAnnotations();
    }
  
    /**
     * Sets new company for this contractor.
     *
     * @param company company represented by this contractor
     * @throws ValidationException when company is not valid
     */
    public void setCompany(Company company) throws ValidationException {
        m_Company = company;
        validateCompany();
    }
  
    /**
     * Sets new name for this contractor.
     *
     * @param sName new contractor name
     * @throws ValidationException when given name is too long or null
     */
    public void setName(String sName) throws ValidationException {
        m_sName = sName;
        validateName();
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
            validateCompany();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateAnnotations();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Contractor entity is not valid", ed);
        }
    }
    // </editor-fold>

}
