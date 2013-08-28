package cars.m;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private static final int NAME_MAX_LENGTH = 20;
  
  /**
   * The name of this car model.
   */
  private String m_sName;
  /**
   * List of generetions available for this mark.
   */
  private List<Generation> m_Generations;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  /**
   * Creates new car model with given name.
   * @param sName name of created car model
   * @throws IllegalArgumentException when name is not valid
   */
  public Model(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
    m_Generations=new ArrayList<>();
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
   * Returns the list of generations available for this model.
   * @return The list of generations available for this model
   */
  public List<Generation> getGenerations()
  {
    return m_Generations;
  }
  
  /**
   * Returns the name of this model.
   * @return the name of this model
   */
  public String getName()
  {
    return m_sName;
  }
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Sets new name for this model.
   * @param sName name of this model
   * @throws IllegalArgumentException when given name is too long or null
   */
  public void setName(String sName) throws IllegalArgumentException
  {
    validateName(sName);
    m_sName=sName;
  }
  // </editor-fold>
  
  /**
   * Adds new generation to this model.
   * @param generation new generation available for this mark
   * @throws IllegalArgumentException when generation is null
   */
  public void addGeneration(Generation generation) throws IllegalArgumentException
  {
    if(generation==null)
      throw new IllegalArgumentException("Generacja pojazdu nie może być null'em!");
    m_Generations.add(generation);
  }
  // </editor-fold>
}