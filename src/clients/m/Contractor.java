package clients.m;

import java.io.Serializable;

/**
 * Abstract class representing an contractor item.
 * @author MRKACZOR
 */
public abstract class Contractor implements Serializable
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private static final int ANNOTATIONS_MAX_LENGTH = 200;
  private static final int NAME_MAX_LENGTH = 20;
  
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
   * Creates new contractor with given name and company.
   * @param sName name of created contractor
   * @throws IllegalArgumentException when any of parameters is not valid
   */
  public Contractor(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    m_Company=null;
    m_sAnnotations=null;
  }
  
  /**
   * Creates new contractor with given name and company.
   * @param sName name of created contractor
   * @param company copmany that is represented by created contractor
   * @throws IllegalArgumentException when any of parameters is not valid
   */
  public Contractor(String sName, Company company) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    validateCompany(company);
    m_Company=company;
    m_sAnnotations=null;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  protected void validateAnnotations(String sAnnotations) throws IllegalArgumentException
  {
    if(sAnnotations!=null && sAnnotations.length()>ANNOTATIONS_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + ANNOTATIONS_MAX_LENGTH);
  }
  
  protected void validateCompany(Company company) throws IllegalArgumentException
  {
    //No special validation needed for custom contractor.
  }
  
  protected void validateName(String sName) throws IllegalArgumentException
  {
    if(sName==null || sName.isEmpty())
      throw new IllegalArgumentException("Pole NAZWA jest wymagane!");
    if(sName.length()>NAME_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + NAME_MAX_LENGTH);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the annotations for this contractor.
   * @return Annotations for this contractor
   */
  public String getAnnotations()
  {
    return m_sAnnotations;
  }
  
  /**
   * Returns the company represented by this contractor.
   * @return Company represented by this contractor
   */
  public Company getCompany()
  {
    return m_Company;
  }
  
  /**
   * Returns the name of this part.
   * @return Name of this part
   */
  public String getName()
  {
    return m_sName;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new annotations for this contractor.
   * @param sAnnotations new contractor annotations
   * @throws IllegalArgumentException when given annotations are too long
   */
  public void setAnnotations(String sAnnotations) throws IllegalArgumentException
  {
    validateAnnotations(sAnnotations);
    m_sAnnotations=sAnnotations;
  }
  
  /**
   * Sets new company for this contractor.
   * @param company company represented by this contractor
   * @throws IllegalArgumentException when company is not valid
   */
  public void setCompany(Company company) throws IllegalArgumentException
  {
    validateCompany(company);
    m_Company=company;
  }
  
  /**
   * Sets new name for this contractor.
   * @param sName new contractor name
   * @throws IllegalArgumentException when given name is too long or null
   */
  public void setName(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
  }
  // </editor-fold>
  
  // </editor-fold>
}
