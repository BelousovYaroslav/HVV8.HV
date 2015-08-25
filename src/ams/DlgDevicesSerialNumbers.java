/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import static ams.AMSSettings.logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;

/**
 *
 * @author yaroslav
 */
public class DlgDevicesSerialNumbers extends javax.swing.JDialog {

    private final AMSApp theApp;
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger( DlgDevicesSerialNumbers.class);
    
    /**
     * Creates new form DlgDevicesSerialNumbers
     */
    public DlgDevicesSerialNumbers(java.awt.Frame parent, boolean modal, AMSApp app) {
        super(parent, modal);
        initComponents();
        theApp = app;
        
        edtDev1SerialNumber.setText( theApp.GetDevSerNums().GetDev1SerialNumber());
        edtDev2SerialNumber.setText( theApp.GetDevSerNums().GetDev2SerialNumber());
        edtDev3SerialNumber.setText( theApp.GetDevSerNums().GetDev3SerialNumber());
        edtDev4SerialNumber.setText( theApp.GetDevSerNums().GetDev4SerialNumber());
        edtDev5SerialNumber.setText( theApp.GetDevSerNums().GetDev5SerialNumber());
        edtDev6SerialNumber.setText( theApp.GetDevSerNums().GetDev6SerialNumber());
        edtDev7SerialNumber.setText( theApp.GetDevSerNums().GetDev7SerialNumber());
        edtDev8SerialNumber.setText( theApp.GetDevSerNums().GetDev8SerialNumber());
        
        setTitle( "Установка номеров испытуемых резонаторов");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDev1 = new javax.swing.JLabel();
        edtDev1SerialNumber = new javax.swing.JTextField();
        lblDev2 = new javax.swing.JLabel();
        edtDev2SerialNumber = new javax.swing.JTextField();
        lblDev3 = new javax.swing.JLabel();
        edtDev3SerialNumber = new javax.swing.JTextField();
        lblDev4 = new javax.swing.JLabel();
        edtDev4SerialNumber = new javax.swing.JTextField();
        lblDev8 = new javax.swing.JLabel();
        edtDev5SerialNumber = new javax.swing.JTextField();
        lblDev5 = new javax.swing.JLabel();
        edtDev6SerialNumber = new javax.swing.JTextField();
        lblDev6 = new javax.swing.JLabel();
        edtDev7SerialNumber = new javax.swing.JTextField();
        lblDev7 = new javax.swing.JLabel();
        edtDev8SerialNumber = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblDev1.setText("Прибор 1:");

        edtDev1SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev1SerialNumberKeyPressed(evt);
            }
        });

        lblDev2.setText("Прибор 2:");

        edtDev2SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev2SerialNumberKeyPressed(evt);
            }
        });

        lblDev3.setText("Прибор 3:");

        edtDev3SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev3SerialNumberKeyPressed(evt);
            }
        });

        lblDev4.setText("Прибор 4:");

        edtDev4SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev4SerialNumberKeyPressed(evt);
            }
        });

        lblDev8.setText("Прибор 8:");

        edtDev5SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev5SerialNumberKeyPressed(evt);
            }
        });

        lblDev5.setText("Прибор 5:");

        edtDev6SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev6SerialNumberKeyPressed(evt);
            }
        });

        lblDev6.setText("Прибор 6:");

        edtDev7SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev7SerialNumberKeyPressed(evt);
            }
        });

        lblDev7.setText("Прибор 7:");

        edtDev8SerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDev8SerialNumberKeyPressed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.setEnabled(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText("Отмена");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev1)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev1SerialNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev2)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev2SerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev3)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev3SerialNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev4)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev4SerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev5)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev5SerialNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev6)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev6SerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev7)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev7SerialNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDev8)
                        .addGap(18, 18, 18)
                        .addComponent(edtDev8SerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev1)
                    .addComponent(edtDev1SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev2)
                    .addComponent(edtDev2SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev3)
                    .addComponent(edtDev3SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev4)
                    .addComponent(edtDev4SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev5)
                    .addComponent(edtDev5SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev6)
                    .addComponent(edtDev6SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev7)
                    .addComponent(edtDev7SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDev8)
                    .addComponent(edtDev8SerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtDev1SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev1SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev1SerialNumberKeyPressed

    private void edtDev2SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev2SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev2SerialNumberKeyPressed

    private void edtDev3SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev3SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev3SerialNumberKeyPressed

    private void edtDev4SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev4SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev4SerialNumberKeyPressed

    private void edtDev5SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev5SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev5SerialNumberKeyPressed

    private void edtDev6SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev6SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev6SerialNumberKeyPressed

    private void edtDev7SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev7SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev7SerialNumberKeyPressed

    private void edtDev8SerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDev8SerialNumberKeyPressed
        btnOk.setEnabled( true);
    }//GEN-LAST:event_edtDev8SerialNumberKeyPressed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        theApp.GetDevSerNums().SetDev1SerialNumber( edtDev1SerialNumber.getText());
        theApp.GetDevSerNums().SetDev2SerialNumber( edtDev2SerialNumber.getText());
        theApp.GetDevSerNums().SetDev3SerialNumber( edtDev3SerialNumber.getText());
        theApp.GetDevSerNums().SetDev4SerialNumber( edtDev4SerialNumber.getText());
        theApp.GetDevSerNums().SetDev5SerialNumber( edtDev5SerialNumber.getText());
        theApp.GetDevSerNums().SetDev6SerialNumber( edtDev6SerialNumber.getText());
        theApp.GetDevSerNums().SetDev7SerialNumber( edtDev7SerialNumber.getText());
        theApp.GetDevSerNums().SetDev8SerialNumber( edtDev8SerialNumber.getText());
        theApp.GetDevSerNums().SaveDevNumsToXML();
        
        theApp.m_pMainWnd.SetHyroNumbers();
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        BasicConfigurator.configure();
        logger.setLevel( Level.TRACE);
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgDevicesSerialNumbers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgDevicesSerialNumbers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgDevicesSerialNumbers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgDevicesSerialNumbers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

                
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgDevicesSerialNumbers dialog = new DlgDevicesSerialNumbers(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JTextField edtDev1SerialNumber;
    private javax.swing.JTextField edtDev2SerialNumber;
    private javax.swing.JTextField edtDev3SerialNumber;
    private javax.swing.JTextField edtDev4SerialNumber;
    private javax.swing.JTextField edtDev5SerialNumber;
    private javax.swing.JTextField edtDev6SerialNumber;
    private javax.swing.JTextField edtDev7SerialNumber;
    private javax.swing.JTextField edtDev8SerialNumber;
    private javax.swing.JLabel lblDev1;
    private javax.swing.JLabel lblDev2;
    private javax.swing.JLabel lblDev3;
    private javax.swing.JLabel lblDev4;
    private javax.swing.JLabel lblDev5;
    private javax.swing.JLabel lblDev6;
    private javax.swing.JLabel lblDev7;
    private javax.swing.JLabel lblDev8;
    // End of variables declaration//GEN-END:variables
}
