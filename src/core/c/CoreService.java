package core.c;

import core.m.DataFileMetaInf;
import core.m.i.ApplicationService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrkaczor
 */
public class CoreService {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    public final static String APPLICATION_DATA_PATH = "data\\";
    private List<ApplicationService> m_registeredServices;
    private Map<String, Boolean> m_modulesPersistencyFlags;
    private Map<String, Date> m_modulesPersistencyDates;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static CoreService getInstance() {
        return CoreService.InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {
        private static final CoreService p_instance = new CoreService();
    }
    // </editor-fold>

    private CoreService() {
        m_registeredServices = new ArrayList<>();
        m_modulesPersistencyFlags = new HashMap<>();
        m_modulesPersistencyDates = new HashMap<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private File createDataFile(String uid) {
        return new File(APPLICATION_DATA_PATH+uid+".d");
    }
    
    private File createMetaFile(String uid) {
        return new File(APPLICATION_DATA_PATH+uid+".m");
    }
    
    private void saveObjectToFile(File output, Object obj) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(output);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        System.out.println("Object saved to file ["+output.getPath()+"]: "+obj.toString());
    }
    
    private void updateModulesPersistencyFlag(String uid) {
        ApplicationService as = getService(uid);
        Boolean flag = m_modulesPersistencyFlags.get(uid);
        if(!flag.equals(as.isModulePersistent())) {
            flag = m_modulesPersistencyDates.get(uid).after(as.getLastPersistencyLost());
            //TODO sprawdzić czy by się zaktualizowało w mapie przez referencję (bez poniższego wywołania)
            m_modulesPersistencyFlags.put(uid, flag);
        }
    }
    
    private void updateModulesPersistencyFlags() {
        ApplicationService as;
        for(Entry<String, Boolean> entry : m_modulesPersistencyFlags.entrySet()) {
            as = getService(entry.getKey());
            if(!entry.getValue().equals(as.isModulePersistent())) {
                boolean state = m_modulesPersistencyDates.get(entry.getKey()).after(as.getLastPersistencyLost());
                m_modulesPersistencyFlags.put(entry.getKey(), state);
            }
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public void exitApplication() {
        try {
            saveApplicationState();
        } catch (IOException ex) {
            //TODO perform some action ;O
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        System.exit(0);
    }
    
    public boolean getModulePersistencyState(String uid) throws IllegalArgumentException {
        if(!m_modulesPersistencyFlags.containsKey(uid))
            throw new IllegalArgumentException("No service registered wit UID="+uid);
        updateModulesPersistencyFlag(uid);
        return m_modulesPersistencyFlags.get(uid);
    }
    
    public ApplicationService getService(String uid) {
        for (ApplicationService as : m_registeredServices) {
            if (as.getServiceUID().equals(uid)) {
                return as;
            }
        }
        return null;
    }

    public boolean registerService(ApplicationService service) {
        if (m_registeredServices.contains(service)) {
            return false;
        }
        m_registeredServices.add(service);
        m_modulesPersistencyFlags.put(service.getServiceUID(), Boolean.TRUE);
        m_modulesPersistencyDates.put(service.getServiceUID(), Calendar.getInstance().getTime());
        return true;
    }

    public void saveApplicationState() throws FileNotFoundException, IOException {
        for (ApplicationService appsrv : m_registeredServices) {
            if (!appsrv.isModulePersistent()) {
                Map<String, List> moduleData = appsrv.getDataToPersist();
                for(Entry<String, List> entry : moduleData.entrySet()) {
                    File metaFile = createMetaFile(entry.getKey());
                    File dataFile = createDataFile(entry.getKey());
                    System.out.println("Created data files paths:\n[META]\t"+metaFile.getAbsolutePath()+"\n[DATA]\t"+dataFile.getAbsolutePath());
                    DataFileMetaInf mi = new DataFileMetaInf(appsrv.getServiceUID(), entry.getKey(), dataFile.getAbsolutePath(), Calendar.getInstance().getTime());
                    saveObjectToFile(metaFile, mi);
                    saveObjectToFile(dataFile, entry.getValue());
                }
                m_modulesPersistencyFlags.put(appsrv.getServiceUID(), Boolean.TRUE);
                m_modulesPersistencyDates.put(appsrv.getServiceUID(), Calendar.getInstance().getTime());
                appsrv.updateModulePersistenceState();
            }
        }
    }
    // </editor-fold>
    
}