package cars.m;

import java.io.Serializable;

public class Engine implements Serializable
{
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
   * Creates new engine with given type and cubic capacity.
   * @param type type of created engine
   * @param iCubicCapacity cubic capacity of created engine given in ccm
   * @param iPower power of created engine given in kW
   * @throws IllegalArgumentException when any of the parameters is not valid
   */
  public Engine(EngineType type, int iCubicCapacity, int iPower) throws IllegalArgumentException
  {
    validateType(type);
    m_Type=type;
    validateCubicCapacity(iCubicCapacity);
    m_iCubicCapacity=iCubicCapacity;
    validatePower(iPower);
    m_iPower=iPower;
    m_sSignature=buildSignature();
  }
  
  /**
   * Creates new engine with given type and cubic capacity.
   * @param type type of created engine
   * @param iCubicCapacity cubic capacity of created engine
   * @param iPower power of created engine given in kW
   * @param sSignature customer-friendly signature of created engine used by it's
   * manufacturer
   * @throws IllegalArgumentException when any of the parameters is not valid
   */
  public Engine(EngineType type, int iCubicCapacity, int iPower, String sSignature) throws IllegalArgumentException
  {
    validateType(type);
    m_Type=type;
    validateCubicCapacity(iCubicCapacity);
    m_iCubicCapacity=iCubicCapacity;
    validatePower(iPower);
    m_iPower=iPower;
    validateSignature(sSignature);
    m_sSignature=sSignature;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  private String buildSignature()
  {
    //TODO implement signature bulider method for Engine class
    return null;
  }
  
  private void validateCubicCapacity(int iCubicCapacity) throws IllegalArgumentException
  {
    if(iCubicCapacity<=0)
      throw new IllegalArgumentException("Pojemność silnika musi być wartością dodatnią!");
  }
  
  private void validatePower(int iPower) throws IllegalArgumentException
  {
    if(iPower<=0)
      throw new IllegalArgumentException("Moc silnika musi być liczbą dodatnią!");
  }
  
  private void validateSignature(String sSignature) throws IllegalArgumentException
  {
    if(sSignature==null || !sSignature.isEmpty())
      throw new IllegalArgumentException("Pole OZNACZENIE SILNIKA nie może być puste!");
    if(sSignature.length()>SIGNATURE_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + SIGNATURE_MAX_LENGTH);
  }
  
  private void validateType(EngineType type) throws IllegalArgumentException
  {
    if(type==null)
      throw new IllegalArgumentException("Pole TYP SILNIKA jest wymagane!");
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the cubic capacity of this engine given in ccm.
   * @return cubic capacity of this engine
   */
  public int getCubicCapacity()
  {
    return m_iCubicCapacity;
  }
  
  /**
   * Returns the power of this engine given in HP.
   * @return power of this engine
   */
  public int getHorsePower()
  {
    return (int)Math.ceil(m_iPower*1.36);
  }
  
  /**
   * Returns the power of this engine given in kW.
   * @return power of this engine
   */
  public int getPower()
  {
    return m_iPower;
  }
  
  /**
   * Returns the customer-friendly signature of this engine used by it's
   * manufacturer (mostly including it's cubic capacity and type).
   * @return 
   */
  public String getSignature()
  {
    return m_sSignature;
  }
  
  /**
   * Returns the type of this engine.
   * @return type of this engine
   */
  public EngineType getType()
  {
    return m_Type;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new cubic capacity for this engine.
   * @param iCubicCapacity cubic capacity given in ccm
   * @throws IllegalArgumentException when capacity is not a positive number
   */
  public void setCubicCapacity(int iCubicCapacity) throws IllegalArgumentException
  {
    validateCubicCapacity(iCubicCapacity);
    m_iCubicCapacity=iCubicCapacity;
  }
  
  /**
   * Sets new power value for this engine.
   * @param iPower power of this engine given in HP
   * @throws IllegalArgumentException when power is not a positive number
   */
  public void setHorsePower(int iPower) throws IllegalArgumentException
  {
    validatePower(iPower);
    m_iPower=iPower;
  }
  
  /**
   * Sets new power value for this engine.
   * @param iPower power of this engine given in kW
   * @throws IllegalArgumentException when power is not a positive number
   */
  public void setPower(int iPower) throws IllegalArgumentException
  {
    validatePower(iPower);
    m_iPower=iPower;
  }
  
  /**
   * Sets new customer-friendly signature for this engine.
   * @param sSignature signature for this engine
   * @throws IllegalArgumentException when signature is too long or null
   */
  public void setSignature(String sSignature) throws IllegalArgumentException
  {
    validateSignature(sSignature);
    m_sSignature=sSignature;
  }
  
  /**
   * Sets new type for this engine.
   * @param type type of this engine
   * @throws IllegalArgumentException when type is null
   */
  public void setType(EngineType type) throws IllegalArgumentException
  {
    validateType(type);
    m_Type=type;
  }
  // </editor-fold>
  
  // </editor-fold>
}