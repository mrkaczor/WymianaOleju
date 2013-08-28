package cars.m;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mark implements Serializable
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private static final int NAME_MAX_LENGTH = 20;
  
  /**
   * The name of this car mark.
   */
  private String m_sName;
  /**
   * Models of cars available for this mark.
   */
  private List<Model> m_Models;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  /**
   * Creates new car mark with given name.
   * @param sName Name of created car mark
   * @throws IllegalArgumentException when name is not valid
   */
  public Mark(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    m_Models=new ArrayList<>();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  private void validateName(String sName) throws IllegalArgumentException
  {
    if(sName==null)
      throw new IllegalArgumentException("Pole NAZWA jest wymagane!");
    if(sName.length()>NAME_MAX_LENGTH)
      throw new IllegalArgumentException("Maksymalna liczba znaków dla tego pola wynosi: " + NAME_MAX_LENGTH);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Returns the name of this mark.
   * @return the name of this mark
   */
  public String getName()
  {
    return m_sName;
  }
  
  /**
   * Returns the list of models available for this mark.
   * @return The list of models available for this mark
   */
  public List<Model> getModels()
  {
    return m_Models;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new name for this mark.
   * @param sName Name of this mark
   * @throws IllegalArgumentException when given name is too long or null
   */
  public void setName(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
  }
  // </editor-fold>
  
  /**
   * Adds new model to this mark.
   * @param model new model available for this mark
   * @throws IllegalArgumentException when model is null
   */
  public void addModel(Model model) throws IllegalArgumentException
  {
    if(model==null)
      throw new IllegalArgumentException("Model pojazdu nie może być null'em!");
    m_Models.add(model);
  }
  // </editor-fold>
}