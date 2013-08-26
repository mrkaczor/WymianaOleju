/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.c;

import clients.ClientsService;
import clients.m.Client;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrkaczor
 */
public class Initializer {
  
  // <editor-fold defaultstate="collapsed" desc="Creating object">
  // <editor-fold defaultstate="collapsed" desc="Singleton">
  public static Initializer getInstance()
  {
    return Initializer.InstanceHolder.p_instance;
  }

  private static final class InstanceHolder
  {
    private static final Initializer p_instance = new Initializer();
  }
  // </editor-fold>

  private Initializer()
  {
    
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  private List<Client> loadClientsData()
  {
    FileInputStream fis;
    ObjectInputStream in;
    List<Client> result = new ArrayList<>();
      try {
        fis = new FileInputStream("data\\clients.ser");
        in = new ObjectInputStream(fis);
        result = (ArrayList<Client>) in.readObject();
        
      } catch (FileNotFoundException ex) {
        
      } catch (IOException | ClassNotFoundException ex) {
        
      }
    return result;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  public void runApplication()
  {
    ClientsService.getInstance().setClients(loadClientsData());
  }
  // </editor-fold>

}
