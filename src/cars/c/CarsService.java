package cars.c;

import cars.c.validators.CarValidator;
import cars.m.Car;
import cars.m.Generation;
import cars.m.Mark;
import cars.m.Model;
import core.m.exceptions.UnexpectedDataException;
import core.m.exceptions.ValidationException;
import core.m.i.ApplicationService;
import core.m.i.EntityValidator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mrkaczor
 */
public class CarsService extends ApplicationService {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private final static String SERVICE_NAME = "Cars Service";
    private final static String SERVICE_UID = "carsrv";
    private final static String CLIENTS_CAR_DATA_UID = "ccars";
    private final static String CAR_DATABASE_DATA_UID = "dcars";

    private List<Car> m_ContractorsCars;
    private List<Mark> m_CarsDatabase;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    // <editor-fold defaultstate="collapsed" desc="Singleton">
    public static CarsService getInstance() {
        return CarsService.InstanceHolder.p_instance;
    }

    private static final class InstanceHolder {
        private static final CarsService p_instance = new CarsService();
    }
    // </editor-fold>

    private CarsService() {
        m_ContractorsCars = new ArrayList<>();
        m_CarsDatabase = new LinkedList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    public List<Mark> getCarsDatabase() {
        return m_CarsDatabase;
    }

    public List<Car> getClientsCars() {
        return m_ContractorsCars;
    }

    @Override
    public Map<String, List> getDataToPersist() {
        Map<String, List> data = new HashMap<>();
        data.put(CAR_DATABASE_DATA_UID, m_CarsDatabase);
        data.put(CLIENTS_CAR_DATA_UID, m_ContractorsCars);
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
    
    public void addCar(Car car) throws ValidationException {
        EntityValidator validator = new CarValidator();
        validator.validate(car);
        
        m_ContractorsCars.add(car);
    }

    public Generation getModelGenerationForProductionDate(Model carModel, Date carProductionDate) {
        for (Generation g : carModel.getGenerations()) {
            //TODO warunki brzegowe dat - after działa jak '>' czy '>=' ??
            if (g.getProductionStartDate().before(carProductionDate) && (g.getProductionEndDate() == null || g.getProductionEndDate().after(carProductionDate))) {
                return g;
            }
        }
        return null;
    }

    @Override
    public void loadData(String uid, List data) throws UnexpectedDataException {
        switch (uid) {
            case CAR_DATABASE_DATA_UID:
                m_CarsDatabase = (List<Mark>) data;
                break;

            case CLIENTS_CAR_DATA_UID:
                m_ContractorsCars = (List<Car>) data;
                break;

            default:
                throw new UnexpectedDataException("Unexpected data with UID=" + uid + " in " + SERVICE_NAME);
        }
    }
    // </editor-fold>

}