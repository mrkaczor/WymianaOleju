/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.v;

import clients.v.ClientsWindow;
import services.v.ServicesWindow;

/**
 *
 * @author mrkaczor
 */
public class MainWindow extends javax.swing.JFrame {

  // <editor-fold defaultstate="collapsed" desc="Creating object">
  // <editor-fold defaultstate="collapsed" desc="Singleton">
  public static MainWindow getInstance()
  {
    return InstanceHolder.p_instance;
  }

  private static final class InstanceHolder
  {
    private static final MainWindow p_instance = new MainWindow();
  }
  // </editor-fold>

  private MainWindow()
  {
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

        jpMainPanel = new javax.swing.JPanel();
        bCustomers = new javax.swing.JButton();
        bHistory = new javax.swing.JButton();
        bNewService = new javax.swing.JButton();
        bExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WymianaOleju 1.0");
        setMinimumSize(new java.awt.Dimension(250, 300));
        setPreferredSize(new java.awt.Dimension(250, 300));
        setResizable(false);

        bCustomers.setText("Klienci");
        bCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCustomersActionPerformed(evt);
            }
        });

        bHistory.setText("Historia usług");
        bHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHistoryActionPerformed(evt);
            }
        });

        bNewService.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        bNewService.setText("Nowa wymiana");
        bNewService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewServiceActionPerformed(evt);
            }
        });

        bExit.setText("Zakończ");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpMainPanelLayout = new javax.swing.GroupLayout(jpMainPanel);
        jpMainPanel.setLayout(jpMainPanelLayout);
        jpMainPanelLayout.setHorizontalGroup(
            jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMainPanelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bNewService, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        jpMainPanelLayout.setVerticalGroup(
            jpMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bNewService, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCustomersActionPerformed
      ClientsWindow.getInstance().setVisible(true);
    }//GEN-LAST:event_bCustomersActionPerformed

    private void bNewServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bNewServiceActionPerformed

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
      System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHistoryActionPerformed
      ServicesWindow.getInstance().setVisible(true);
    }//GEN-LAST:event_bHistoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCustomers;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bHistory;
    private javax.swing.JButton bNewService;
    private javax.swing.JPanel jpMainPanel;
    // End of variables declaration//GEN-END:variables
}