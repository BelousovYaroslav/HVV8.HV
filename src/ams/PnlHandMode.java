/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import ams.devices.Adam4017plus;
import ams.devices.Adam4024;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class PnlHandMode extends javax.swing.JPanel implements ActionListener {

    static Logger logger = Logger.getLogger(PnlHandMode.class);
    AMSApp theApp;

    AMSAverager avr = new AMSAverager( 10);
    
    Timer tmrRefresh;
    /**
     * Creates new form PnlHandMode
     * @param app - the APP
     */
    public PnlHandMode( AMSApp app) {
        initComponents();
        theApp = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Adam4024 dac = theApp.GetDevManager_0_a().GetDAC();
        int nDacChannel = 0;
        
        Adam4017plus adc = theApp.GetDevManager_0_a().GetADCCurrent();
        int nAdcChannel = 0;
        
        JLabel lblDac = lblDev1AnoDac;
        JLabel lblLed = lblDev1AnoLed;
        JLabel lblMsg = lblDev1Message;
        
        AMSDeviceManager mngr = theApp.GetDevManager_0_a();
        
        for( int i=0; i < 16; i++) {
            switch( i) {
                case  0: mngr = theApp.GetDevManager_0_a(); lblDac = lblDev1AnoDac; lblLed = lblDev1AnoLed; lblMsg = lblDev1Message; break;
                case  1: mngr = theApp.GetDevManager_0_t(); lblDac = lblDev1TubDac; lblLed = lblDev1TubLed; lblMsg = lblDev1Message; break;
                    
                case  2: mngr = theApp.GetDevManager_1_a(); lblDac = lblDev2AnoDac; lblLed = lblDev2AnoLed; lblMsg = lblDev2Message; break;
                case  3: mngr = theApp.GetDevManager_1_t(); lblDac = lblDev2TubDac; lblLed = lblDev2TubLed; lblMsg = lblDev2Message; break;
                    
                case  4: mngr = theApp.GetDevManager_2_a(); lblDac = lblDev3AnoDac; lblLed = lblDev3AnoLed; lblMsg = lblDev3Message; break;
                case  5: mngr = theApp.GetDevManager_2_t(); lblDac = lblDev3TubDac; lblLed = lblDev3TubLed; lblMsg = lblDev3Message; break;
                    
                case  6: mngr = theApp.GetDevManager_3_a(); lblDac = lblDev4AnoDac; lblLed = lblDev4AnoLed; lblMsg = lblDev4Message; break;
                case  7: mngr = theApp.GetDevManager_3_t(); lblDac = lblDev4TubDac; lblLed = lblDev4TubLed; lblMsg = lblDev4Message; break;
                    
                case  8: mngr = theApp.GetDevManager_4_a(); lblDac = lblDev5AnoDac; lblLed = lblDev5AnoLed; lblMsg = lblDev5Message; break;
                case  9: mngr = theApp.GetDevManager_4_t(); lblDac = lblDev5TubDac; lblLed = lblDev5TubLed; lblMsg = lblDev5Message; break;
                    
                case 10: mngr = theApp.GetDevManager_5_a(); lblDac = lblDev6AnoDac; lblLed = lblDev6AnoLed; lblMsg = lblDev6Message; break;
                case 11: mngr = theApp.GetDevManager_5_t(); lblDac = lblDev6TubDac; lblLed = lblDev6TubLed; lblMsg = lblDev6Message; break;
                    
                case 12: mngr = theApp.GetDevManager_6_a(); lblDac = lblDev7AnoDac; lblLed = lblDev7AnoLed; lblMsg = lblDev7Message; break;
                case 13: mngr = theApp.GetDevManager_6_t(); lblDac = lblDev7TubDac; lblLed = lblDev7TubLed; lblMsg = lblDev7Message; break;
                    
                case 14: mngr = theApp.GetDevManager_7_a(); lblDac = lblDev8AnoDac; lblLed = lblDev8AnoLed; lblMsg = lblDev8Message; break;
                case 15: mngr = theApp.GetDevManager_7_t(); lblDac = lblDev8TubDac; lblLed = lblDev8TubLed; lblMsg = lblDev8Message; break;
            }
        
            if( mngr != null) {
                dac = mngr.GetDAC();
                nDacChannel = mngr.GetDacChannel();
                double dblLastSetDacValue = dac.GetLastSetValue( nDacChannel);
                lblDac.setText("" + String.format( "%.3f", dblLastSetDacValue) + "V");
                
                
                if( dblLastSetDacValue >= AMSConstants.DAC_MAX_OUTPUT_VOLTAGE_WARN)
                    lblDac.setForeground( Color.red);
                else if( dblLastSetDacValue <= AMSConstants.DAC_MIN_OUTPUT_VOLTAGE_WARN)
                    lblDac.setForeground( Color.red);
                else
                    lblDac.setForeground( Color.black);
                
                if( dblLastSetDacValue >= AMSConstants.DAC_MAX_OUTPUT_VOLTAGE) {
                    Font oldFont = lblDac.getFont();
                    if( !oldFont.isBold()) {
                        Font newFont = new Font( oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize());
                        lblDac.setFont( newFont);
                    }
                }
                else if( dblLastSetDacValue <= AMSConstants.DAC_MIN_OUTPUT_VOLTAGE_WARN) {
                    Font oldFont = lblDac.getFont();
                    if( !oldFont.isBold()) {
                        Font newFont = new Font( oldFont.getName(), oldFont.getStyle() + Font.BOLD, oldFont.getSize());
                        lblDac.setFont( newFont);
                    }
                }
                else {
                    Font oldFont = lblDac.getFont();
                    if( oldFont.isBold()) {
                        Font newFont = new Font( oldFont.getName(), oldFont.getStyle() - Font.BOLD, oldFont.getSize());
                        lblDac.setFont( newFont);
                    }
                }
                
                adc = mngr.GetADCCurrent();
                nAdcChannel = mngr.GetAdcCurrentChannel();
                double dblMeasuredCurrent = adc.GetAveragerChannel( nAdcChannel).GetAverage();            
                
                
                if( !Double.isNaN( dblMeasuredCurrent))
                    lblLed.setText( ( dblMeasuredCurrent < 300) ? "не горит" : "горит");
                else
                    lblLed.setText( "-");
                
                
                String strMsg = "";
                if( theApp.GetDevSerNums().GetDeviceSerialNumber( 1 + ( i / 2)).isEmpty())
                    strMsg = "Не задан номер для прибора!";
                
                lblMsg.setText( strMsg);
            }
            /*
            else
                logger.warn( "mngr = null");
            */
        }
        
        
        
        
        adc = theApp.GetDevManager_0_a().GetADCCurrent();
        lblADCrate.setText( "" + String.format( "%03f", adc.GetAveragerT().GetAverage()));
        
        tmrRefresh.restart();
        
    }
    
    public void startRefreshTimer() {
        tmrRefresh = new Timer( 1000, this);
        tmrRefresh.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDev1AnoDac = new javax.swing.JLabel();
        lblDev1AnoLed = new javax.swing.JLabel();
        lblDev1TubDac = new javax.swing.JLabel();
        lblDev1TubLed = new javax.swing.JLabel();
        lblDev2AnoDac = new javax.swing.JLabel();
        lblDev2AnoLed = new javax.swing.JLabel();
        lblDev2TubDac = new javax.swing.JLabel();
        lblDev2TubLed = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDev3AnoDac = new javax.swing.JLabel();
        lblDev3AnoLed = new javax.swing.JLabel();
        lblDev3TubDac = new javax.swing.JLabel();
        lblDev3TubLed = new javax.swing.JLabel();
        lblDev4AnoDac = new javax.swing.JLabel();
        lblDev4AnoLed = new javax.swing.JLabel();
        lblDev4TubDac = new javax.swing.JLabel();
        lblDev4TubLed = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblADCreadings = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblADCtime = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblADCrate = new javax.swing.JLabel();
        lblDev4Message = new javax.swing.JLabel();
        lblDev1Message = new javax.swing.JLabel();
        lblDev2Message = new javax.swing.JLabel();
        lblDev3Message = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblDev5AnoDac = new javax.swing.JLabel();
        lblDev5AnoLed = new javax.swing.JLabel();
        lblDev5TubDac = new javax.swing.JLabel();
        lblDev5TubLed = new javax.swing.JLabel();
        lblDev6AnoDac = new javax.swing.JLabel();
        lblDev6AnoLed = new javax.swing.JLabel();
        lblDev6TubDac = new javax.swing.JLabel();
        lblDev6TubLed = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblDev7AnoDac = new javax.swing.JLabel();
        lblDev7AnoLed = new javax.swing.JLabel();
        lblDev7TubDac = new javax.swing.JLabel();
        lblDev7TubLed = new javax.swing.JLabel();
        lblDev8AnoDac = new javax.swing.JLabel();
        lblDev8AnoLed = new javax.swing.JLabel();
        lblDev8TubDac = new javax.swing.JLabel();
        lblDev8TubLed = new javax.swing.JLabel();
        lblDev8Message = new javax.swing.JLabel();
        lblDev5Message = new javax.swing.JLabel();
        lblDev6Message = new javax.swing.JLabel();
        lblDev7Message = new javax.swing.JLabel();

        setLayout(null);

        jLabel1.setText("Прибор 1.Штенгель");
        add(jLabel1);
        jLabel1.setBounds(10, 30, 160, 20);
        jLabel1.getAccessibleContext().setAccessibleName("Резонатор. 1.Штенгель");

        jLabel2.setText("Прибор 1.Анод");
        add(jLabel2);
        jLabel2.setBounds(10, 10, 160, 20);

        jLabel3.setText("Прибор 2.Штенгель");
        add(jLabel3);
        jLabel3.setBounds(10, 100, 160, 20);

        jLabel4.setText("Прибор 2.Анод");
        add(jLabel4);
        jLabel4.setBounds(10, 80, 160, 20);

        lblDev1AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev1AnoDac.setText("-");
        add(lblDev1AnoDac);
        lblDev1AnoDac.setBounds(290, 10, 70, 20);

        lblDev1AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev1AnoLed.setText("-");
        add(lblDev1AnoLed);
        lblDev1AnoLed.setBounds(190, 10, 70, 20);

        lblDev1TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev1TubDac.setText("-");
        add(lblDev1TubDac);
        lblDev1TubDac.setBounds(290, 30, 70, 20);

        lblDev1TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev1TubLed.setText("-");
        add(lblDev1TubLed);
        lblDev1TubLed.setBounds(190, 30, 70, 20);

        lblDev2AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev2AnoDac.setText("-");
        add(lblDev2AnoDac);
        lblDev2AnoDac.setBounds(290, 80, 70, 20);

        lblDev2AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev2AnoLed.setText("-");
        add(lblDev2AnoLed);
        lblDev2AnoLed.setBounds(190, 80, 70, 20);

        lblDev2TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev2TubDac.setText("-");
        add(lblDev2TubDac);
        lblDev2TubDac.setBounds(290, 100, 70, 20);

        lblDev2TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev2TubLed.setText("-");
        add(lblDev2TubLed);
        lblDev2TubLed.setBounds(190, 100, 70, 20);

        jLabel13.setText("Прибор 3.Штенгель");
        add(jLabel13);
        jLabel13.setBounds(10, 170, 160, 20);

        jLabel14.setText("Прибор 3.Анод");
        add(jLabel14);
        jLabel14.setBounds(10, 150, 160, 20);

        jLabel15.setText("Прибор 4.Штенгель");
        add(jLabel15);
        jLabel15.setBounds(10, 240, 160, 20);

        jLabel16.setText("Прибор 4.Анод");
        add(jLabel16);
        jLabel16.setBounds(10, 220, 160, 20);

        lblDev3AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev3AnoDac.setText("-");
        add(lblDev3AnoDac);
        lblDev3AnoDac.setBounds(290, 150, 70, 20);

        lblDev3AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev3AnoLed.setText("-");
        add(lblDev3AnoLed);
        lblDev3AnoLed.setBounds(190, 150, 70, 20);

        lblDev3TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev3TubDac.setText("-");
        add(lblDev3TubDac);
        lblDev3TubDac.setBounds(290, 170, 70, 20);

        lblDev3TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev3TubLed.setText("-");
        add(lblDev3TubLed);
        lblDev3TubLed.setBounds(190, 170, 70, 20);

        lblDev4AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev4AnoDac.setText("-");
        add(lblDev4AnoDac);
        lblDev4AnoDac.setBounds(290, 220, 70, 20);

        lblDev4AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev4AnoLed.setText("-");
        add(lblDev4AnoLed);
        lblDev4AnoLed.setBounds(190, 220, 70, 20);

        lblDev4TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev4TubDac.setText("-");
        add(lblDev4TubDac);
        lblDev4TubDac.setBounds(290, 240, 70, 20);

        lblDev4TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev4TubLed.setText("-");
        add(lblDev4TubLed);
        lblDev4TubLed.setBounds(190, 240, 70, 20);

        jLabel5.setText("Число выполненных команд чтения АЦП токов");
        add(jLabel5);
        jLabel5.setBounds(10, 680, 320, 20);

        lblADCreadings.setText("jLabel6");
        add(lblADCreadings);
        lblADCreadings.setBounds(330, 680, 47, 20);

        jLabel7.setText("Число секунд после первой команды чтения");
        add(jLabel7);
        jLabel7.setBounds(10, 700, 320, 20);

        lblADCtime.setText("jLabel6");
        add(lblADCtime);
        lblADCtime.setBounds(330, 700, 47, 20);

        jLabel8.setText("Частота чтения АЦП токов:");
        add(jLabel8);
        jLabel8.setBounds(10, 720, 320, 20);

        lblADCrate.setText("jLabel6");
        add(lblADCrate);
        lblADCrate.setBounds(330, 720, 47, 20);

        lblDev4Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev4Message.setText("...");
        add(lblDev4Message);
        lblDev4Message.setBounds(10, 260, 340, 20);

        lblDev1Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev1Message.setText("...");
        add(lblDev1Message);
        lblDev1Message.setBounds(10, 50, 260, 20);

        lblDev2Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev2Message.setText("...");
        add(lblDev2Message);
        lblDev2Message.setBounds(10, 120, 340, 20);

        lblDev3Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev3Message.setText("...");
        add(lblDev3Message);
        lblDev3Message.setBounds(10, 190, 340, 20);

        jLabel12.setText("Прибор 5.Штенгель");
        add(jLabel12);
        jLabel12.setBounds(10, 310, 160, 20);

        jLabel17.setText("Прибор 5.Анод");
        add(jLabel17);
        jLabel17.setBounds(10, 290, 160, 20);

        jLabel18.setText("Прибор 6.Штенгель");
        add(jLabel18);
        jLabel18.setBounds(10, 380, 160, 20);

        jLabel19.setText("Прибор 6.Анод");
        add(jLabel19);
        jLabel19.setBounds(10, 360, 160, 20);

        lblDev5AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev5AnoDac.setText("-");
        add(lblDev5AnoDac);
        lblDev5AnoDac.setBounds(290, 290, 70, 20);

        lblDev5AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev5AnoLed.setText("-");
        add(lblDev5AnoLed);
        lblDev5AnoLed.setBounds(190, 290, 70, 20);

        lblDev5TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev5TubDac.setText("-");
        add(lblDev5TubDac);
        lblDev5TubDac.setBounds(290, 310, 70, 20);

        lblDev5TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev5TubLed.setText("-");
        add(lblDev5TubLed);
        lblDev5TubLed.setBounds(190, 310, 70, 20);

        lblDev6AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev6AnoDac.setText("-");
        add(lblDev6AnoDac);
        lblDev6AnoDac.setBounds(290, 360, 70, 20);

        lblDev6AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev6AnoLed.setText("-");
        add(lblDev6AnoLed);
        lblDev6AnoLed.setBounds(190, 360, 70, 20);

        lblDev6TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev6TubDac.setText("-");
        add(lblDev6TubDac);
        lblDev6TubDac.setBounds(290, 380, 70, 20);

        lblDev6TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev6TubLed.setText("-");
        add(lblDev6TubLed);
        lblDev6TubLed.setBounds(190, 380, 70, 20);

        jLabel20.setText("Прибор 7.Штенгель");
        add(jLabel20);
        jLabel20.setBounds(10, 450, 160, 20);

        jLabel21.setText("Прибор 7.Анод");
        add(jLabel21);
        jLabel21.setBounds(10, 430, 160, 20);

        jLabel22.setText("Прибор 8.Штенгель");
        add(jLabel22);
        jLabel22.setBounds(10, 520, 160, 20);

        jLabel23.setText("Прибор 8.Анод");
        add(jLabel23);
        jLabel23.setBounds(10, 500, 160, 20);

        lblDev7AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev7AnoDac.setText("-");
        add(lblDev7AnoDac);
        lblDev7AnoDac.setBounds(290, 430, 70, 20);

        lblDev7AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev7AnoLed.setText("-");
        add(lblDev7AnoLed);
        lblDev7AnoLed.setBounds(190, 430, 70, 20);

        lblDev7TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev7TubDac.setText("-");
        add(lblDev7TubDac);
        lblDev7TubDac.setBounds(290, 450, 70, 20);

        lblDev7TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev7TubLed.setText("-");
        add(lblDev7TubLed);
        lblDev7TubLed.setBounds(190, 450, 70, 20);

        lblDev8AnoDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev8AnoDac.setText("-");
        add(lblDev8AnoDac);
        lblDev8AnoDac.setBounds(290, 500, 70, 20);

        lblDev8AnoLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev8AnoLed.setText("-");
        add(lblDev8AnoLed);
        lblDev8AnoLed.setBounds(190, 500, 70, 20);

        lblDev8TubDac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev8TubDac.setText("-");
        add(lblDev8TubDac);
        lblDev8TubDac.setBounds(290, 520, 70, 20);

        lblDev8TubLed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev8TubLed.setText("-");
        add(lblDev8TubLed);
        lblDev8TubLed.setBounds(190, 520, 70, 20);

        lblDev8Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev8Message.setText("...");
        add(lblDev8Message);
        lblDev8Message.setBounds(10, 540, 340, 20);

        lblDev5Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev5Message.setText("...");
        add(lblDev5Message);
        lblDev5Message.setBounds(10, 330, 340, 20);

        lblDev6Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev6Message.setText("...");
        add(lblDev6Message);
        lblDev6Message.setBounds(10, 400, 340, 20);

        lblDev7Message.setForeground(new java.awt.Color(215, 30, 30));
        lblDev7Message.setText("...");
        add(lblDev7Message);
        lblDev7Message.setBounds(10, 470, 340, 20);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblADCrate;
    private javax.swing.JLabel lblADCreadings;
    private javax.swing.JLabel lblADCtime;
    private javax.swing.JLabel lblDev1AnoDac;
    private javax.swing.JLabel lblDev1AnoLed;
    private javax.swing.JLabel lblDev1Message;
    private javax.swing.JLabel lblDev1TubDac;
    private javax.swing.JLabel lblDev1TubLed;
    private javax.swing.JLabel lblDev2AnoDac;
    private javax.swing.JLabel lblDev2AnoLed;
    private javax.swing.JLabel lblDev2Message;
    private javax.swing.JLabel lblDev2TubDac;
    private javax.swing.JLabel lblDev2TubLed;
    private javax.swing.JLabel lblDev3AnoDac;
    private javax.swing.JLabel lblDev3AnoLed;
    private javax.swing.JLabel lblDev3Message;
    private javax.swing.JLabel lblDev3TubDac;
    private javax.swing.JLabel lblDev3TubLed;
    private javax.swing.JLabel lblDev4AnoDac;
    private javax.swing.JLabel lblDev4AnoLed;
    private javax.swing.JLabel lblDev4Message;
    private javax.swing.JLabel lblDev4TubDac;
    private javax.swing.JLabel lblDev4TubLed;
    private javax.swing.JLabel lblDev5AnoDac;
    private javax.swing.JLabel lblDev5AnoLed;
    private javax.swing.JLabel lblDev5Message;
    private javax.swing.JLabel lblDev5TubDac;
    private javax.swing.JLabel lblDev5TubLed;
    private javax.swing.JLabel lblDev6AnoDac;
    private javax.swing.JLabel lblDev6AnoLed;
    private javax.swing.JLabel lblDev6Message;
    private javax.swing.JLabel lblDev6TubDac;
    private javax.swing.JLabel lblDev6TubLed;
    private javax.swing.JLabel lblDev7AnoDac;
    private javax.swing.JLabel lblDev7AnoLed;
    private javax.swing.JLabel lblDev7Message;
    private javax.swing.JLabel lblDev7TubDac;
    private javax.swing.JLabel lblDev7TubLed;
    private javax.swing.JLabel lblDev8AnoDac;
    private javax.swing.JLabel lblDev8AnoLed;
    private javax.swing.JLabel lblDev8Message;
    private javax.swing.JLabel lblDev8TubDac;
    private javax.swing.JLabel lblDev8TubLed;
    // End of variables declaration//GEN-END:variables
}
