/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.v.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author mrkaczor
 */
public class ListItem extends JPanel {
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private final Color DEFAULT_BACKGROUND_COLOR = Color.blue;
  
  private int m_iMargin;
  private int m_iCornerArcSize;
  private Color m_iBackgroundColor;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  public ListItem() {
      m_iBackgroundColor=DEFAULT_BACKGROUND_COLOR;
  }
  // </editor-fold>
  
  @Override
  public void paint(Graphics g)
  {
    g.setColor(m_iBackgroundColor);
    g.fillArc(m_iMargin, m_iMargin, m_iCornerArcSize, m_iCornerArcSize, 90, 90);
    g.fillArc(this.getWidth()-m_iCornerArcSize-m_iMargin, m_iMargin, m_iCornerArcSize, m_iCornerArcSize, 0, 90);
    g.fillArc(m_iMargin, this.getHeight()-m_iCornerArcSize-m_iMargin, m_iCornerArcSize, m_iCornerArcSize, 180, 90);
    g.fillArc(this.getWidth()-m_iCornerArcSize-m_iMargin, this.getHeight()-m_iCornerArcSize-m_iMargin, m_iCornerArcSize, m_iCornerArcSize, 270, 90);
    g.fillRect(m_iMargin+m_iCornerArcSize/2, m_iMargin, this.getWidth()-m_iCornerArcSize-2*m_iMargin, m_iCornerArcSize/2);
    g.fillRect(m_iMargin, m_iMargin+m_iCornerArcSize/2, this.getWidth()-2*m_iMargin, this.getHeight()-m_iCornerArcSize-2*m_iMargin);
    g.fillRect(m_iMargin+m_iCornerArcSize/2, this.getHeight()-m_iCornerArcSize/2-m_iMargin, this.getWidth()-m_iCornerArcSize-2*m_iMargin, m_iCornerArcSize/2);
  }
}
