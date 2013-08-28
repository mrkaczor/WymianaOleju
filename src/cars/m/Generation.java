package cars.m;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Generation implements Serializable
{
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
   * Creates new car model generation with given name and production start date.
   * @param sName Name of created car model generation
   * @param productionStartDate Production start date for this generation
   * @throws IllegalArgumentException when any of the parameters is not valid
   */
  public Generation(String sName, Date productionStartDate) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    validateStartDate(productionStartDate);
    m_ProductionStartDate=productionStartDate;
    m_ProductionEndDate=null;
    m_Engines=new ArrayList<>();
  }
  
  /**
   * Creates new car model generation with given name and production start and end date.
   * @param sName Name of created car model generation
   * @param productionStartDate Production start date for this generation
   * @param productionEndDate Production end date for this generation
   * @throws IllegalArgumentException when any of the parameters is not valid
   */
  public Generation(String sName, Date productionStartDate, Date productionEndDate) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    validateStartDate(productionStartDate);
    m_ProductionStartDate=productionStartDate;
    validateEndDate(productionEndDate);
    m_ProductionEndDate=null;
    m_Engines=new ArrayList<>();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  private void validateEndDate(Date date) throws IllegalArgumentException
  {
    if(date!=null && date.before(m_ProductionStartDate))
      throw new IllegalArgumentException("Pole KONIEC PRODUKCJI nie może zawierać daty wcześniejszej niż POCZĄTEK PRODUKCJI!");
  }
  
  private void validateEngine(Engine engine) throws IllegalArgumentException
  {
    if(engine==null)
      throw new IllegalArgumentException("Silnik nie może być null'em!");
  }
  
  private void validateName(String sName) throws IllegalArgumentException
  {
    if(sName==null)
      throw new IllegalArgumentException("Pole NAZWA jest wymagane!");
    if(sName.length()>NAME_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + NAME_MAX_LENGTH);
  }
  
  private void validateStartDate(Date date) throws IllegalArgumentException
  {
    if(date==null)
      throw new IllegalArgumentException("Pole POCZĄTEK PRODUKCJI jest wymagane!");
    if(m_ProductionEndDate!=null && date.after(m_ProductionEndDate))
      throw new IllegalArgumentException("Pole POCZĄTEK PRODUKCJI nie może zawierać daty późniejszej niż KONIEC PRODUKCJI!");
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns list of engines available for this generation.
   * @return list of engines available for this generation
   */
  public List<Engine> getEngines()
  {
    return m_Engines;
  }
  
  /**
   * Returns the name of this generation.
   * @return the name of this generation
   */
  public String getName()
  {
    return m_sName;
  }
  
  /**
   * Returns the production end date of this generation.
   * @return the production end date of this generation or null
   */
  public Date getProductionEndDate()
  {
    return m_ProductionStartDate;
  }
  
  /**
   * Returns the production beginig date of this generation.
   * @return the production beginig date of this generation
   */
  public Date getProductionStartDate()
  {
    return m_ProductionStartDate;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new name for this generation.
   * @param sName Name of this generation
   * @throws IllegalArgumentException when given name is too long or null
   */
  public void setName(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
  }
  
  /**
   * Sets the production end date for this generation.
   * @param date production end date for this generation
   * @throws IllegalArgumentException when this date is before production start date
   */
  public void setProductionEndDate(Date date) throws IllegalArgumentException
  {
    validateEndDate(date);
    m_ProductionEndDate=date;
  }
  
  /**
   * Sets the production start date for this generation.
   * @param date production start date for this generation
   * @throws IllegalArgumentException when date is after production end date or null
   */
  public void setProductionStartDate(Date date) throws IllegalArgumentException
  {
    validateStartDate(date);
    m_ProductionStartDate=date;
  }
  // </editor-fold>
  
  /**
   * Adds new engine available for this generation.
   * @param engine new engine available for this generation
   * @throws IllegalArgumentException when engine is null
   */
  public void addEngine(Engine engine) throws IllegalArgumentException
  {
    validateEngine(engine);
    m_Engines.add(engine);
  }
  // </editor-fold>
}