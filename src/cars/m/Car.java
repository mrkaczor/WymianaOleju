package cars.m;

import cars.c.CarsService;
import contractors.m.Contractor;
import core.m.exceptions.ExceptionDictionary;
import core.m.exceptions.ValidationException;
import core.m.i.Entity;
import java.io.Serializable;
import java.util.Date;
import utils.CommonValidator;

public class Car implements Entity, Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    /**
     * Owner of this car.
     */
    private Contractor m_Owner;
    /**
     * Mark of this car.
     */
    private Mark m_Mark;
    /**
     * Model of this car.
     */
    private Model m_Model;
    /**
     * Generation of this car.
     */
    private Generation m_Generation;
    /**
     * Engine of this car.
     */
    private Engine m_Engine;
    /**
     * The production date of this car.
     */
    private Date m_ProductionDate;
    /**
     * Vechicle Identification Number for this car.
     */
    private String m_sVIN;
    /**
     * Registration identifier for this car.
     */
    private String m_sRegistrationIdentifier;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    /**
     * Creates new Car with empty fields without validation.
     */
    public Car() {
    }

    public Car(Mark mark, Model model, Engine engine, Date productionDate) throws ValidationException {
        m_Mark = mark;
        m_Model = model;
        validateModel();
        m_Generation = CarsService.getInstance().getModelGenerationForProductionDate(m_Model, productionDate);
        m_ProductionDate = productionDate;
        validateProductionDate();
        validateEngine();
        m_Engine = engine;
    }

    public Car(Mark mark, Model model, Generation generaion, Engine engine, Date productionDate) throws ValidationException {
        m_Mark = mark;
        m_Model = model;
        validateModel();
        m_Generation = generaion;
        validateGeneration();
        m_Engine = engine;
        validateEngine();
        m_ProductionDate = productionDate;
        validateProductionDate();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    private void validateEngine() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Generation == null) {
            ed.addMessage("Musisz określić generację pojazdu zanim zdefiniujesz jego silnik!");
        } else if (m_Engine == null) {
            ed.addMessage("Silnik jest wymagany");
        } else if (!m_Generation.getEngines().contains(m_Engine)) {
            ed.addMessage("Wybrana wersja silnikowa nie była dostępna w tej generacji!");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field ENGINE within CAR entity failed", ed);
        }
    }

    private void validateGeneration() throws ValidationException {
        if (m_Generation != null) {
            ExceptionDictionary ed = new ExceptionDictionary();
            if (m_Model == null) {
                ed.addMessage("Musisz określić model pojazdu zanim zdefiniujesz jego generację!");
            } else if (!m_Model.getGenerations().contains(m_Generation)) {
                ed.addMessage("Wybrana generacja nie istnieje dla podanego modelu!");
            }
            if (!ed.isEmpty()) {
                throw new ValidationException("Validation of field GENERATION within CAR entity failed", ed);
            }
        }
    }

    private void validateMark() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Mark == null) {
            ed.addMessage("Marka pojazdu jest wymagana");
        } else {
            try {
                m_Mark.selfValidate();
            } catch (ValidationException ex) {
                ed.addMessages(ex.getErrorMessages().getMessages());
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field MODEL within CAR entity failed", ed);
        }
    }

    private void validateModel() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Mark == null) {
            ed.addMessage("Musisz określić markę pojazdu zanim zdefiniujesz jego model!");
        } else if (m_Model == null) {
            ed.addMessage("Model pojazdu jest wymagany");
        } else if (!m_Mark.getModels().contains(m_Model)) {
            ed.addMessage("Wybrany model nie istnieje dla podanej marki!");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field MODEL within CAR entity failed", ed);
        }
    }

    private void validateOwner() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_Owner == null) {
            ed.addMessage("Właściciel pojazdu jest wymagany");
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field MODEL within CAR entity failed", ed);
        }
    }

    private void validateProductionDate() throws ValidationException {
        if (m_ProductionDate != null) {
            ExceptionDictionary ed = new ExceptionDictionary();
            if (m_Model == null) {
                ed.addMessage("Musisz określić model pojazdu zanim zdefiniujesz datę jego produkcji!");
            } else {
                if (m_Generation == null) {
                    if (CarsService.getInstance().getModelGenerationForProductionDate(m_Model, m_ProductionDate) == null) {
                        ed.addMessage("Wybrany model pojazdu nie był produkowany we wskazanym czasie!");
                    }
                } else {
                    if (m_Generation.getProductionStartDate().after(m_ProductionDate) || (m_Generation.getProductionEndDate() != null && m_ProductionDate.after(m_Generation.getProductionEndDate()))) {
                        ed.addMessage("Wybrana generacja pojazdu nie była produkowana we wskazanym czasie!");
                    }
                }
            }
            if (!ed.isEmpty()) {
                throw new ValidationException("Validation of field PRODUCTION_DATE within CAR entity failed", ed);
            }
        }
    }

    private void validateRegistrationIdentifier() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        if (m_sRegistrationIdentifier == null) {
            ed.addMessage("Numer rejestracyjny jest wymagany");
        } else {
            if (m_sRegistrationIdentifier.charAt(0) < 65 || m_sRegistrationIdentifier.charAt(0) > 90) {
                ed.addMessage("Numer rejestracyjny musi zaczynać się od litery");
            }
            for (int i = 0; i < m_sRegistrationIdentifier.length(); i++) {
                //Identifier should contain only digits [0-9] and letters [A-Z]
                char symbol = m_sRegistrationIdentifier.charAt(i);
                if (symbol < 48 || (symbol > 57 && symbol < 65) || symbol > 90) {
                    ed.addMessage("Numer rejestracyjny zawiera niedozwolone znaki (musi się składać wyłącznie z cyfr oraz wielkich liter)");
                    break;
                }
            }
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("Validation of field REGISTRATION_IDENTIFIER within CAR entity failed", ed);
        }
    }

    private void validateVIN() throws ValidationException {
        if (m_sVIN != null) {
            try {
                CommonValidator.validateVIN(m_sVIN);
            } catch (ValidationException ex) {
                throw new ValidationException("Validation of field VIN within CAR entity failed", ex);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    // <editor-fold defaultstate="collapsed" desc="Getters">
    public Engine getEngine() {
        return m_Engine;
    }

    public Generation getGeneration() {
        return m_Generation;
    }

    public Mark getMark() {
        return m_Mark;
    }

    public Model getModel() {
        return m_Model;
    }

    public Contractor getOwner() {
        return m_Owner;
    }

    public Date getProductionDate() {
        return m_ProductionDate;
    }

    public String getVIN() {
        return m_sVIN;
    }

    public String getRegistrationIdentifier() {
        return m_sRegistrationIdentifier;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Setters">

    public void setEngine(Engine engine) throws ValidationException {
        m_Engine = engine;
        validateEngine();
    }

    public void setGeneration(Generation generation) throws ValidationException {
        m_Generation = generation;
        validateGeneration();
    }

    public void setMark(Mark mark) throws ValidationException {
        m_Mark = mark;
        validateMark();
    }

    public void setModel(Model model) throws ValidationException {
        m_Model = model;
        validateModel();
    }

    public void setOwner(Contractor owner) throws ValidationException {
        m_Owner = owner;
        validateOwner();
    }

    public void setProductionDate(Date productionDate) throws ValidationException {
        m_ProductionDate = productionDate;
        validateProductionDate();
    }

    public void setRegistrationIdentifier(String identifier) throws ValidationException {
        m_sRegistrationIdentifier = identifier;
        validateRegistrationIdentifier();
    }

    public void setVIN(String VIN) throws ValidationException {
        m_sVIN = VIN;
        validateVIN();
    }
    // </editor-fold>

    @Override
    public void selfValidate() throws ValidationException {
        ExceptionDictionary ed = new ExceptionDictionary();
        try {
            validateOwner();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateMark();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateModel();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateGeneration();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateEngine();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateProductionDate();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateRegistrationIdentifier();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        try {
            validateVIN();
        } catch (ValidationException ex) {
            ed.addMessages(ex.getErrorMessages().getMessages());
        }
        if (!ed.isEmpty()) {
            throw new ValidationException("CAR entity is not valid", ed);
        }
    }

    @Override
    public String toString() {
        return m_Mark + " " + m_Model + " " + m_Generation.getName();
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Car) {
            Car car = (Car)o;
            return car.getRegistrationIdentifier().equals(m_sRegistrationIdentifier);
        }
        return false;
    }
    // </editor-fold>

}