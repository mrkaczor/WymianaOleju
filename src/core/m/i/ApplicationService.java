package core.m.i;

import core.c.CoreService;
import core.m.exceptions.UnexpectedDataException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mrkaczor
 */
public abstract class ApplicationService {

    private boolean isPersistent;
    private Date lastPersistancyLost;

    public ApplicationService() {
        isPersistent = true;
    }

    protected final void setPersistencyLost() {
        isPersistent = false;
        lastPersistancyLost = Calendar.getInstance().getTime();
    }

    public final Date getLastPersistencyLost() {
        return lastPersistancyLost;
    }
    
    public abstract String getServiceName();

    public abstract String getServiceUID();

    public abstract Map<String, List> getDataToPersist();

    public final boolean isModulePersistent() {
        return isPersistent;
    }

    public abstract void loadData(String uid, List data) throws UnexpectedDataException;

    public final void updateModulePersistenceState() {
        isPersistent = CoreService.getInstance().getModulePersistencyState(this.getServiceUID());
    }
}