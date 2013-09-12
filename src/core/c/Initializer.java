package core.c;

import cars.c.CarsService;
import contractors.c.ContractorsService;
import core.m.DataFileMetaInf;
import core.m.exceptions.InitializationException;
import core.m.exceptions.UnexpectedDataException;
import core.m.i.ApplicationService;
import core.v.MainWindow;
import java.io.File;
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
    public static Initializer getInstance() {
        return Initializer.InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {
        private static final Initializer p_instance = new Initializer();
    }
    // </editor-fold>

    private Initializer() {
        
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void initApplicationServices() {
        CoreService.getInstance().registerService(CarsService.getInstance());
        CoreService.getInstance().registerService(ContractorsService.getInstance());
    }

    private void loadApplicationData() throws FileNotFoundException, IOException, ClassNotFoundException, UnexpectedDataException {
        ApplicationService as;
        List data;
        List<DataFileMetaInf> metaFiles = loadMetaFiles();
        for (DataFileMetaInf mi : metaFiles) {
            as = CoreService.getInstance().getService(mi.getServiceUID());
            data = loadDataFromFile(mi.getDataFilePath());
            as.loadData(mi.getDataUID(), data);
        }
    }

    private List loadDataFromFile(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream in = new ObjectInputStream(fis);
        return (List) in.readObject();
    }

    private List<DataFileMetaInf> loadMetaFiles() throws FileNotFoundException, IOException, ClassNotFoundException {
        File[] dataFiles = new File(CoreService.APPLICATION_DATA_PATH).listFiles();
        List<DataFileMetaInf> metaFiles = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream in;
        for (File df : dataFiles) {
            if (df.getName().endsWith(".m")) {
                fis = new FileInputStream(df.getAbsolutePath());
                in = new ObjectInputStream(fis);
                metaFiles.add((DataFileMetaInf) in.readObject());
            }
        }
        return metaFiles;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public void runApplication() throws InitializationException, UnexpectedDataException {
        initApplicationServices();
        try {
            loadApplicationData();
        } catch (IOException | ClassNotFoundException ex) {
            throw new InitializationException(ex);
        }
        MainWindow.getInstance().setVisible(true);
    }
    // </editor-fold>
    
}
