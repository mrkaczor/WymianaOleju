package core.m;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Damian Kaczybura
 */
public class DataFileMetaInf implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private String m_sServiceUID;
    private String m_sDataUID;
    private String m_sDataFilePath;
    private Date m_ArchivisationDate;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    public DataFileMetaInf(String serviceUID, String dataUID, String dataFilePath, Date archivisationDate) {
        m_sServiceUID = serviceUID;
        m_sDataUID = dataUID;
        m_sDataFilePath = dataFilePath;
        m_ArchivisationDate = archivisationDate;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public Date getArchivisationDate() {
        return m_ArchivisationDate;
    }

    public String getDataFilePath() {
        return m_sDataFilePath;
    }

    public String getDataUID() {
        return m_sDataUID;
    }

    public String getServiceUID() {
        return m_sServiceUID;
    }
    // </editor-fold>
    
}
