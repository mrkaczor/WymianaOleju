package clients.m;

import java.io.Serializable;

/**
 * Class representing contractor's company.
 * @author MRKACZOR
 */
public class Company implements Serializable
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private static final int ADDRESS_MAX_LENGTH = 100;
  private static final int NAME_MAX_LENGTH = 60;
  private static final int TIN_LENGTH = 10;
  
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
   * Creates new company with given name, address and TIN.
   * @param sName name of created company
   * @param sAddress address of created company premises
   * @param sTIN tax identification number of created company
   * @throws IllegalArgumentException when any of the parameters is not valid
   */
  public Company(String sName, String sAddress, String sTIN) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    validateAddress(sAddress);
    m_sAddress=sAddress;
    validateTIN(sTIN);
    m_sTIN=sTIN;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  private void validateAddress(String sAddress) throws IllegalArgumentException
  {
    if(sAddress==null || sAddress.isEmpty())
      throw new IllegalArgumentException("Pole ADRES jest wymagane!");
    if(sAddress.length()>ADDRESS_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + ADDRESS_MAX_LENGTH);
  }
  
  private void validateName(String sName) throws IllegalArgumentException
  {
    if(sName==null || sName.isEmpty())
      throw new IllegalArgumentException("Pole NAZWA jest wymagane!");
    if(sName.length()>NAME_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + NAME_MAX_LENGTH);
  }
  
  private void validateTIN(String sTIN) throws IllegalArgumentException
  {
    if(sTIN==null || sTIN.isEmpty())
      throw new IllegalArgumentException("Pole NIP jest wymagane!");
    if(sTIN.length()!=TIN_LENGTH)
      throw new IllegalArgumentException("Numer NIP jest niepoprawny!");
    for(int i=0;i<sTIN.length();i++)
      if(sTIN.charAt(i)<48 || sTIN.charAt(i)>57)
        throw new IllegalArgumentException("Numer NIP jest niepoprawny!");
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the address of this comapny.
   * @return address of this comapny
   */
  public String getAddress()
  {
    return m_sAddress;
  }
  
  /**
   * Returns the name of this company.
   * @return name of this company
   */
  public String getName()
  {
    return m_sName;
  }
  
  /**
   * Returns the tax identification number of this company.
   * @return tax identification number of this company
   */
  public String getTIN()
  {
    return m_sTIN;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new address for this company.
   * @param sAddress address of this company
   * @throws IllegalArgumentException when given address is too long or null
   */
  public void setAddress(String sAddress) throws IllegalArgumentException
  {
    validateAddress(sAddress);
    m_sAddress=sAddress;
  }
  
  /**
   * Sets new name for this company.
   * @param sName name of this company
   * @throws IllegalArgumentException when given name is too long or null
   */
  public void setName(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
  }
  
  /**
   * Sets new tax identification number for this company.
   * @param sTIN tax identification number of this company
   * @throws IllegalArgumentException when given TIN is null or not valid
   */
  public void setTIN(String sTIN) throws IllegalArgumentException
  {
    validateTIN(sTIN);
    m_sTIN=sTIN;
  }
  // </editor-fold>
  
  // </editor-fold>
}