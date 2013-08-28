package core.v.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  protected BufferedImage m_DefaultImage;
  protected BufferedImage m_Image;
  protected BufferedImage m_shownImage;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  public ImagePanel(Dimension panelSize, BufferedImage defaultImage)
  {
    super();
    this.setMaximumSize(panelSize);
    this.setMinimumSize(panelSize);
    this.setPreferredSize(panelSize);
    m_DefaultImage=defaultImage;
    m_Image=null;
    m_shownImage=null;
  }
  
  public ImagePanel(Dimension minimumSize, Dimension maksimumSize, Dimension prefferedSize, BufferedImage defaultImage)
  {
    super();
    this.setMaximumSize(maksimumSize);
    this.setMinimumSize(minimumSize);
    this.setPreferredSize(prefferedSize);
    m_DefaultImage=defaultImage;
    m_Image=null;
    m_shownImage=null;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  protected BufferedImage fitImage(BufferedImage image)
  {
    BufferedImage finalImage=new BufferedImage((int)(this.getPreferredSize().getWidth()), (int)(this.getPreferredSize().getHeight()), BufferedImage.TYPE_INT_ARGB);    
    Graphics2D g2d = finalImage.createGraphics();
    AffineTransform at=AffineTransform.getScaleInstance(this.getPreferredSize().getWidth()/image.getWidth(),this.getPreferredSize().getHeight()/image.getHeight());
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    g2d.drawImage(image,at,null);
    g2d.dispose();    
    return finalImage;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  // <editor-fold defaultstate="collapsed" desc="GETTERS">
  
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="SETTERS">
  /**
   * Loads an image to the panel.
   * @param image Image to be loaded
   */
  public void setImage(BufferedImage image)
  {
    m_Image=image;
  }
  // </editor-fold>  
  
  public void hideImage()
  {
    this.removeAll();    
    if(m_DefaultImage!=null)
      m_shownImage=fitImage(m_DefaultImage);
    else
      m_shownImage=null;
    repaint();
  }
  
  @Override
  public void paint(Graphics g)
  {
    if(m_shownImage!=null)
      g.drawImage(m_shownImage, 0, 0, this);
  }
  
  public void showImage()
  {
    if(m_Image!=null)
      m_shownImage=fitImage(m_Image);
    else if(m_DefaultImage!=null)
      m_shownImage=fitImage(m_DefaultImage);
    else
      m_shownImage=null;
    repaint();
  }
  // </editor-fold>
}
