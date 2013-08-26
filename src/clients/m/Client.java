package clients.m;

import java.io.Serializable;

/**
 * Class representing a client, which extends contractor.
 * @author MRKACZOR
 */
public class Client extends Contractor implements Serializable
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private static final int ADDRESS_MAX_LENGTH = 100;
  private static final int PHONE_NUMBER_MAX_LENGTH = 12;
  private static final int PHONE_NUMBER_MIN_LENGTH = 9;
  private static final int SURNAME_MAX_LENGTH = 20;
  private static final int TIN_LENGTH = 10;
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
   * Creates new client with given short-cut name, vorname and surname.
   * @param sName short-cut name of this client
   * @param sVorname created client's vorname
   * @param sSurname created client's surname
   * @throws IllegalArgumentException when any of parameters is not valid
   */
  public Client(String sName, String sVorname, String sSurname) throws IllegalArgumentException
  {
    super(sName);
    validateVorname(sVorname);
    m_sVorname=sVorname;
    validateSurname(sSurname);
    m_sSurname=sSurname;
    m_sAddress=null;
    m_sPhoneNumber=null;
    m_sTIN=null;
  }
  
  /**
   * Creates new client with given short-cut name, vorname, surname and TIN.
   * @param sName short-cut name of this client
   * @param sVorname created client's vorname
   * @param sSurname created client's surname
   * @param sTIN created client's TIN
   * @throws IllegalArgumentException when any of parameters is not valid
   */
  public Client(String sName, String sVorname, String sSurname, String sTIN) throws IllegalArgumentException
  {
    super(sName);
    validateVorname(sVorname);
    m_sVorname=sVorname;
    validateSurname(sSurname);
    m_sSurname=sSurname;
    validateTIN(sTIN);
    m_sTIN=sTIN;
    m_sAddress=null;
    m_sPhoneNumber=null;
  }
  
  /**
   * Creates new client with given short-cut name, vorname, surname and company.
   * @param sName short-cut name of this client
   * @param sVorname created client's vorname
   * @param sSurname created client's surname
   * @param company created client's company
   * @throws IllegalArgumentException when any of parameters is not valid
   */
  public Client(String sName, String sVorname, String sSurname, Company company) throws IllegalArgumentException
  {
    super(sName, company);
    validateVorname(sVorname);
    m_sVorname=sVorname;
    validateSurname(sSurname);
    m_sSurname=sSurname;
    m_sAddress=null;
    m_sPhoneNumber=null;
    m_sTIN=null;
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
  
  private void validatePhoneNumber(String sPhoneNumber) throws IllegalArgumentException
  {
    if(sPhoneNumber!=null)
    {
      if(sPhoneNumber.length()<PHONE_NUMBER_MIN_LENGTH || sPhoneNumber.length()>PHONE_NUMBER_MAX_LENGTH)
        throw new IllegalArgumentException("Numer telefonu jest niepoprawny!");
      if(sPhoneNumber.charAt(0)!='+' && (sPhoneNumber.charAt(0)<48 || sPhoneNumber.charAt(0)>57))
          throw new IllegalArgumentException("Numer telefonu jest niepoprawny!");
      for(int i=1;i<sPhoneNumber.length();i++)
        if(sPhoneNumber.charAt(i)<48 || sPhoneNumber.charAt(i)>57)
          throw new IllegalArgumentException("Numer NIP jest niepoprawny!");
    }
  }
  
  private void validateSurname(String sSurname) throws IllegalArgumentException
  {
    if(sSurname==null || sSurname.isEmpty())
      throw new IllegalArgumentException("Pole NAZWISKO jest wymagane!");
    if(sSurname.length()>SURNAME_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + SURNAME_MAX_LENGTH);
  }
  
  private void validateTIN(String sTIN) throws IllegalArgumentException
  {
    if(sTIN!=null)
    {
      if(sTIN.length()!=TIN_LENGTH)
        throw new IllegalArgumentException("Numer NIP jest niepoprawny!");
      for(int i=0;i<sTIN.length();i++)
        if(sTIN.charAt(i)<48 || sTIN.charAt(i)>57)
          throw new IllegalArgumentException("Numer NIP jest niepoprawny!");
    }
  }
  
  private void validateVorname(String sVorname) throws IllegalArgumentException
  {
    if(sVorname==null || sVorname.isEmpty())
      throw new IllegalArgumentException("Pole IMIĘ jest wymagane!");
    if(sVorname.length()>VORNAME_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + VORNAME_MAX_LENGTH);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the address of this client.
   * @return Address of this client or null
   */
  public String getAddress()
  {
    return m_sAddress;
  }
  
  /**
   * Returns the phone number of this client.
   * @return Phone number of this client
   */
  public String getPhoneNumber()
  {
    return m_sPhoneNumber;
  }
  
  /**
   * Returns the surname of this client.
   * @return Surname of this client
   */
  public String getSurname()
  {
    return m_sSurname;
  }
  
  /**
   * Returns the tax identification number of this client.
   * @return Tax identification number of this client or null
   */
  public String getTIN()
  {
    return m_sTIN;
  }
  
  /**
   * Returns the vorname of this client.
   * @return Vorname of this client
   */
  public String getVorname()
  {
    return m_sVorname;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new address for this client.
   * @param sAddress address of this client
   * @throws IllegalArgumentException when given address is too long
   */
  public void setAddress(String sAddress) throws IllegalArgumentException
  {
    validateAddress(sAddress);
    m_sAddress=sAddress;
  }
  
  /**
   * Sets new phone number for this client.
   * @param sPhoneNumber phone number of this client
   * @throws IllegalArgumentException when given phone number is not valid
   */
  public void setPhoneNumber(String sPhoneNumber) throws IllegalArgumentException
  {
    validatePhoneNumber(sPhoneNumber);
    m_sPhoneNumber=sPhoneNumber;
  }
  
  /**
   * Sets new surname for this client.
   * @param sSurname surname of this client
   * @throws IllegalArgumentException when given surname is too long or null
   */
  public void setSurname(String sSurname) throws IllegalArgumentException
  {
    validateSurname(sSurname);
    m_sSurname=sSurname;
  }
  
  /**
   * Sets new tax identification number for this client.
   * @param sTIN tax identification number of this client
   * @throws IllegalArgumentException when given TIN is not valid
   */
  public void setTIN(String sTIN) throws IllegalArgumentException
  {
    validateTIN(sTIN);
    m_sTIN=sTIN;
  }
  
  /**
   * Sets new vorname for this client.
   * @param sVorname vorname of this client
   * @throws IllegalArgumentException when given vorname is too long or null
   */
  public void setVorname(String sVorname) throws IllegalArgumentException
  {
    validateVorname(sVorname);
    m_sVorname=sVorname;
  }
  // </editor-fold>
  
  // </editor-fold>
}