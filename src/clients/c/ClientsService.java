/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clients.c;

import clients.m.Client;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrkaczor
 */
public class ClientsService {
  private List<Client> m_Clients;
    
  // <editor-fold defaultstate="collapsed" desc="Creating object">
  // <editor-fold defaultstate="collapsed" desc="Singleton">
  public static ClientsService getInstance()
  {
    return ClientsService.InstanceHolder.p_instance;
  }

  private static final class InstanceHolder
  {
    private static final ClientsService p_instance = new ClientsService();
  }
  // </editor-fold>

  private ClientsService()
  {
    m_Clients = new ArrayList<>();
  }
  // </editor-fold>
  
  public List<Client> getClients() {
      return m_Clients;
  }
  
  public void setClients(List<Client> clients) {
      m_Clients = clients;
  }
}
