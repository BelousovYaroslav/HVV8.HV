/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author yaroslav
 */
public class PnlUIC extends javax.swing.JPanel implements ActionListener {
    private Timer tmrCountDown;
    private final AMSApp theApp;
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger( PnlUIC.class);
    
    /**
     * Creates new form PnlUAC
     */
    public PnlUIC( AMSApp app) {
        initComponents();
        theApp = app;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStartTimer = new javax.swing.JButton();
        btnStopTimer = new javax.swing.JButton();
        edtTimer = new javax.swing.JTextField();

        btnStartTimer.setText("Старт");
        btnStartTimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartTimerActionPerformed(evt);
            }
        });

        btnStopTimer.setText("Стоп");
        btnStopTimer.setEnabled(false);

        edtTimer.setFont(new java.awt.Font("Cantarell", 0, 70)); // NOI18N
        edtTimer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtTimer.setText("15");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(edtTimer)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStartTimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                        .addComponent(btnStopTimer)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartTimer)
                    .addComponent(btnStopTimer))
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartTimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartTimerActionPerformed
        try {
            Integer test = new Integer( edtTimer.getText());
            if( test > 0 && test < 120) {
                tmrCountDown = new Timer( 1000, this);
                tmrCountDown.start();
                edtTimer.setEnabled( false);
                btnStartTimer.setEnabled( false);
                btnStopTimer.setEnabled( true);
            }
        }
        catch( NumberFormatException e) {
        }
    }//GEN-LAST:event_btnStartTimerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartTimer;
    private javax.swing.JButton btnStopTimer;
    private javax.swing.JTextField edtTimer;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer test = new Integer( edtTimer.getText());
        test--;
        edtTimer.setText( test.toString());
        if( test == 0) {
            tmrCountDown.stop();
            
            theApp.m_pMainWnd.fakeSwitchOff();
            edtTimer.setEnabled( true);
            edtTimer.setText( "15");
            btnStartTimer.setEnabled(true);
        }
    }
}
