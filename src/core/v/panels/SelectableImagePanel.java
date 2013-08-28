package core.v.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SelectableImagePanel extends ImagePanel
{
  // <editor-fold defaultstate="collapsed" desc="Object variables">
  BufferedImage m_SelectionImage;
  private boolean m_bSelectionState;
  private int m_iSelectionIncrease;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  public SelectableImagePanel(Dimension size, BufferedImage defaultImage)
  {
    super(size, defaultImage);
    m_iSelectionIncrease=0;
    m_SelectionImage=null;
    m_bSelectionState=false;
  }
  
  public SelectableImagePanel(Dimension size, BufferedImage defaultImage, int iSelectionIncrease)
  {
    super(size, defaultImage);
    m_iSelectionIncrease=iSelectionIncrease;
    m_SelectionImage=null;
    m_bSelectionState=false;
  }
  
  public SelectableImagePanel(Dimension size, BufferedImage defaultImage, BufferedImage selectionImage)
  {
    super(size, defaultImage);
    m_iSelectionIncrease=0;
    m_SelectionImage=selectionImage;
    m_bSelectionState=false;
  }
  
  public SelectableImagePanel(Dimension size, BufferedImage defaultImage, int iSelectionIncrease, BufferedImage selectionImage)
  {
    super(size, defaultImage);
    m_iSelectionIncrease=iSelectionIncrease;
    m_SelectionImage=selectionImage;
    m_bSelectionState=false;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PRIVATE methods">
  
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object PUBLIC methods">
  @Override
  public void paint(Graphics g)
  {
    if(m_shownImage!=null && m_bSelectionState)
      g.drawImage(m_shownImage, 0, 0, this);
    else if(m_shownImage!=null)
      g.drawImage(m_shownImage, m_iSelectionIncrease/2, m_iSelectionIncrease/2, this);
  }
  
  /**
   * Metoda umożliwia ustawienie obrazu wyświetlanego w trybie zaznaczenia panelu
   * w miejscu obrazu głównego.
   * @param selectionImage Obraz wyświetlany w trybie zaznaczenia panelu
   */
  public void setSelectionImage(BufferedImage selectionImage)
  {
    m_SelectionImage=selectionImage;
  }
  
  /**
   * Metoda umożliwia ustawienie wartości przyrostu rozmiaru obrazka w trybie zaznaczenia
   * panelu (w rzeczywistości obraz główny zostanie zmiejszony o zadaną wielkość w trybie
   * podstawowym panelu, tj. gdy panel nie jest zaznaczony).
   * @param iSelectionIncrease Wartość liniowego przyrostu rozmiaru obrazka w każdej z osi (px)
   */
  public void setSelectionIncrease(int iSelectionIncrease)
  {
    m_iSelectionIncrease=iSelectionIncrease;
  }
  
  @Override
  public void showImage()
  {
    if(m_bSelectionState && m_SelectionImage!=null)
      m_shownImage=fitImage(m_SelectionImage);
    else if(m_Image!=null)
      m_shownImage=fitImage(m_Image);
    else if(m_DefaultImage!=null)
      m_shownImage=fitImage(m_DefaultImage);
    else
      m_shownImage=null;
    repaint();
  }
  
  /**
   * Metoda przełącza widok panelu do trybu zaznaczenia.
   */
  public void selectPanel()
  {
    m_bSelectionState=true;
    Dimension newSize=new Dimension((int)(this.getPreferredSize().getWidth())+m_iSelectionIncrease,(int)(this.getPreferredSize().getHeight())+m_iSelectionIncrease);
    this.setPreferredSize(newSize);
    showImage();
    newSize=new Dimension((int)(this.getPreferredSize().getWidth())-m_iSelectionIncrease,(int)(this.getPreferredSize().getHeight())-m_iSelectionIncrease);
    this.setPreferredSize(newSize);
  }
  
  /**
   * Metoda wyłącza tryb zaznaczenia panelu (powrót do domyślnego widoku).
   */
  public void unselectPanel()
  {
    m_bSelectionState=false;
    showImage();
  }
  // </editor-fold>
}
