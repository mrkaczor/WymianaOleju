package core.v.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ListPanel extends JPanel
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  private final int DEFAULT_MARGIN_VALUE = 10;
  private final int DEFAULT_ARCSIZE_VALUE = 60;
  private final int MINIMUM_ITEM_HEIGHT = 20;
  private final int MINIMUM_ITEM_SPACING = 2;
  private final Color DEFAULT_BACKGROUND_COLOR = Color.white;
  
  private int m_iMargin;
  private int m_iCornerArcSize;
  private Color m_iBackgroundColor;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  public ListPanel()
  {
    super();
    m_iMargin=DEFAULT_MARGIN_VALUE;
    m_iCornerArcSize=DEFAULT_ARCSIZE_VALUE;
    m_iBackgroundColor=DEFAULT_BACKGROUND_COLOR;
    this.setMinimumSize(new Dimension(2*m_iMargin+m_iCornerArcSize+120, 2*m_iMargin+m_iCornerArcSize+120));
  }
  
  public ListPanel(int iMarginSize, int iCornerArcSize)
  {
    super();
    if(iMarginSize<0)
      m_iMargin=0;
    else
      m_iMargin=iMarginSize;
    if(iCornerArcSize<0)
      m_iCornerArcSize=0;
    else
      m_iCornerArcSize=iCornerArcSize;
    m_iBackgroundColor=DEFAULT_BACKGROUND_COLOR;
    this.setMinimumSize(new Dimension(2*m_iMargin+m_iCornerArcSize+120, 2*m_iMargin+m_iCornerArcSize+120));
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  private int calculateMaxItemsNum() {
	return contentVerticalSpace()/(MINIMUM_ITEM_HEIGHT+MINIMUM_ITEM_SPACING);
  }
  
  private int contentHorizontalSpace() {
	return this.getWidth()-2*m_iMargin;
  }
  
  private int contentVerticalSpace() {
	return this.getHeight()-2*m_iMargin;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  public void setBackgroundColor(Color newColor) {
      m_iBackgroundColor = newColor;
  }
  
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
  // </editor-fold>
}
