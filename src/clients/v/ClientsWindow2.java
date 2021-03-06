/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clients.v;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

/**
 *
 * @author mrkaczor
 */
public class ClientsWindow2 extends JDialog {
  
  // <editor-fold defaultstate="collapsed" desc="Creating object">
  // <editor-fold defaultstate="collapsed" desc="Singleton">
  public static ClientsWindow2 getInstance()
  {
    return InstanceHolder.p_instance;
  }

  private static final class InstanceHolder
  {
    private static final ClientsWindow2 p_instance = new ClientsWindow2();
  }
  // </editor-fold>

  private ClientsWindow2()
  {
    Dimension initSize = new Dimension(800, 600);
    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize(initSize);
    this.setMinimumSize(initSize);
    WindowAdapter wa=new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent we)
      {
        setVisible(false);
        dispose();
      }
    };
    this.addWindowListener(wa);
    this.setLocation((int)((screenSize.width-this.getSize().width)/2), (int)(screenSize.height-this.getSize().height)/2);
    
    initComponents();
  }
  // </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jbAdd = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        lSearch = new javax.swing.JLabel();
        listPanel1 = new core.v.panels.ListPanel(0, 40);
        listPanel1.setBackgroundColor(new Color(249, 249, 249));
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Klienci");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jbAdd.setText("Dodaj");

        lSearch.setText("Szukaj:");

        javax.swing.GroupLayout listPanel1Layout = new javax.swing.GroupLayout(listPanel1);
        listPanel1.setLayout(listPanel1Layout);
        listPanel1Layout.setHorizontalGroup(
            listPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listPanel1Layout.setVerticalGroup(
            listPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                        .addComponent(lSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAdd)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lSearch))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbAdd;
    private javax.swing.JLabel lSearch;
    private core.v.panels.ListPanel listPanel1;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
