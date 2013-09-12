package contractors.c;

import contractors.c.validators.ContractorValidator;
import contractors.m.Contractor;
import core.m.exceptions.UnexpectedDataException;
import core.m.exceptions.ValidationException;
import core.m.i.ApplicationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mrkaczor
 */
public class ContractorsService extends ApplicationService {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private final static String SERVICE_NAME = "Contractors Service";
    private final static String SERVICE_UID = "ctrsrv";
    private final static String CLIENTS_DATA_UID = "ctrsd";
    
    private List<Contractor> m_Clients;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static ContractorsService getInstance() {
        return ContractorsService.InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {
        private static final ContractorsService p_instance = new ContractorsService();
    }
    // </editor-fold>

    private ContractorsService() {
        m_Clients = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    // </editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    public List<Contractor> getContractors() {
        return m_Clients;
    }
    
    @Override
    public Map<String, List> getDataToPersist() {
        Map<String, List> data = new HashMap<>();
        data.put(CLIENTS_DATA_UID, m_Clients);
        return data;
    }

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public String getServiceUID() {
        return SERVICE_UID;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters">
    
    // </editor-fold>
    
    public void addContractor(Contractor contractor) throws ValidationException {
        ContractorValidator validator = new ContractorValidator();
        validator.validate(contractor);
        
        m_Clients.add(contractor);
        this.setPersistencyLost();
    }

    @Override
    public void loadData(String uid, List data) throws UnexpectedDataException {
        switch (uid) {
          case CLIENTS_DATA_UID:
              m_Clients = (List<Contractor>) data;
              break;

          default:
            throw new UnexpectedDataException("Unexpected data with UID="+uid+" in "+SERVICE_NAME);
      }
    }
    // </editor-fold>
    
}
