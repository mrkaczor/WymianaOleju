package core.v.panels;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ObjectsPanel extends JPanel
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private GroupLayout m_Layout;
  private Component[] m_Objects;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  public ObjectsPanel(Component[] objects, Dimension objectSize) throws IllegalArgumentException
  {
    if(objects.length>0)
    {
      m_Objects = objects;
    }
    else
      throw new IllegalArgumentException("Ilość komponentów musi być liczbą całkowitą dodatnią!");
    m_Layout=new GroupLayout(this);    
    this.setLayout(m_Layout);
    initLayout();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  /**
   * Metoda umożliwia wczytanie layoutu widoku menu opcji.
   */
  private void initLayout()
  {    
    m_Layout.setAutoCreateContainerGaps(false);
    m_Layout.setAutoCreateGaps(true);
    SequentialGroup tmpHorizontalGroup=m_Layout.createSequentialGroup();
    for(int i=0;i<m_Objects.length;i++)
    {
      tmpHorizontalGroup.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
      tmpHorizontalGroup.addComponent(m_Objects[i]);
    }
    tmpHorizontalGroup.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
    m_Layout.setHorizontalGroup(
      m_Layout.createParallelGroup()
        .addGroup(tmpHorizontalGroup));
    
    ParallelGroup tmpVerticalGroup=m_Layout.createParallelGroup();
    for(int i=0;i<m_Objects.length;i++)
    {
      tmpVerticalGroup.addComponent(m_Objects[i]);
    }
    m_Layout.setVerticalGroup(
      m_Layout.createSequentialGroup()
        .addGroup(tmpVerticalGroup)
      );
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  /**
   * Returns object from this panel with given id.
   * @param objectId Id of the desired object
   * @return Object item from this panel
   */
  public Component getObject(int objectId) {
    return (objectId>=0 && objectId<m_Objects.length)?m_Objects[objectId]:null;
  }
  // </editor-fold>
}
