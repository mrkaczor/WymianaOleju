package core.m.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Damian Kaczybura
 */
public class ExceptionDictionary {
    
    // <editor-fold defaultstate="collapsed" desc="Object variables">
    private List<String> m_Messages;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Creating object">
    public ExceptionDictionary() {
        m_Messages = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">

    // </editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
    public void addMessage(String sMessage) {
        if(!m_Messages.contains(sMessage)) {
            m_Messages.add(sMessage);
        }
    }
    
    public void addMessages(List<String> messages) {
        for (String sMsg : messages) {
            if (!m_Messages.contains(sMsg)) {
                m_Messages.add(sMsg);
            }
        }
    }
    
    public List<String> getMessages() {
        return m_Messages;
    }
    
    public String getMergeMessages() {
        String sMessages = "";
        for(String sMsg : m_Messages) {
            sMessages += sMsg + ", ";
        }
        return sMessages.substring(0, sMessages.length()-2);
    }
    
    public boolean isEmpty() {
        return m_Messages.isEmpty();
    }
    // </editor-fold>

}
