package core;

import cars.c.CarsService;
import contractors.c.ContractorsService;
import core.c.Initializer;
import core.m.exceptions.InitializationException;
import core.m.exceptions.UnexpectedDataException;

/**
 *
 * @author mrkaczor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Initializer.getInstance().runApplication();
        } catch (InitializationException | UnexpectedDataException ex) {
            System.err.println(ex);
            ex.getCause().printStackTrace();
        }
        System.out.println("Clients:\n"+ContractorsService.getInstance().getContractors().toString());
        System.out.println("Cars:\n"+CarsService.getInstance().getClientsCars().toString());
        //Client client = new Client("Kaczy", "Damian", "Kaczybura");
        //ClientsService.getInstance().addClient(client);
    }
}
