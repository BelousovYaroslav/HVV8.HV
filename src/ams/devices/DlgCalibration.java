/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSApp;
import ams.AMSConstants;

/**
 *
 * @author yaroslav
 */
public class DlgCalibration extends javax.swing.JDialog {
    private AMSApp theApp;
    
    private double m_dblAdc1_0_A;
    private double m_dblAdc1_0_B;
    private double m_dblAdc1_1_A;
    private double m_dblAdc1_1_B;
    private double m_dblAdc1_2_A;
    private double m_dblAdc1_2_B;
    private double m_dblAdc1_3_A;
    private double m_dblAdc1_3_B;
    private double m_dblAdc1_4_A;
    private double m_dblAdc1_4_B;
    private double m_dblAdc1_5_A;
    private double m_dblAdc1_5_B;
    private double m_dblAdc1_6_A;
    private double m_dblAdc1_6_B;
    private double m_dblAdc1_7_A;
    private double m_dblAdc1_7_B;
    
    private double m_dblAdc2_0_A;
    private double m_dblAdc2_0_B;
    private double m_dblAdc2_1_A;
    private double m_dblAdc2_1_B;
    private double m_dblAdc2_2_A;
    private double m_dblAdc2_2_B;
    private double m_dblAdc2_3_A;
    private double m_dblAdc2_3_B;
    private double m_dblAdc2_4_A;
    private double m_dblAdc2_4_B;
    private double m_dblAdc2_5_A;
    private double m_dblAdc2_5_B;
    private double m_dblAdc2_6_A;
    private double m_dblAdc2_6_B;
    private double m_dblAdc2_7_A;
    private double m_dblAdc2_7_B;
    
    private double m_dblAdc3_0_A;
    private double m_dblAdc3_0_B;
    private double m_dblAdc3_1_A;
    private double m_dblAdc3_1_B;
    private double m_dblAdc3_2_A;
    private double m_dblAdc3_2_B;
    private double m_dblAdc3_3_A;
    private double m_dblAdc3_3_B;
    private double m_dblAdc3_4_A;
    private double m_dblAdc3_4_B;
    private double m_dblAdc3_5_A;
    private double m_dblAdc3_5_B;
    private double m_dblAdc3_6_A;
    private double m_dblAdc3_6_B;
    private double m_dblAdc3_7_A;
    private double m_dblAdc3_7_B;
    
    private double m_dblAdc4_0_A;
    private double m_dblAdc4_0_B;
    private double m_dblAdc4_1_A;
    private double m_dblAdc4_1_B;
    private double m_dblAdc4_2_A;
    private double m_dblAdc4_2_B;
    private double m_dblAdc4_3_A;
    private double m_dblAdc4_3_B;
    private double m_dblAdc4_4_A;
    private double m_dblAdc4_4_B;
    private double m_dblAdc4_5_A;
    private double m_dblAdc4_5_B;
    private double m_dblAdc4_6_A;
    private double m_dblAdc4_6_B;
    private double m_dblAdc4_7_A;
    private double m_dblAdc4_7_B;
    
    
    /**
     * Creates new form DlgCalibration
     * @param parent
     * @param modal
     * @param app
     */
    public DlgCalibration(java.awt.Frame parent, boolean modal, AMSApp app) {
        super( parent, modal);
        initComponents();
        theApp = app;
        LoadData();
        btnSave.setEnabled( false);
    }

    private void LoadData() {
        m_dblAdc1_0_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL1);
        edtAdc1_0_A.setText("" + String.format("%.3f", m_dblAdc1_0_A));       
        m_dblAdc1_0_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL1);
        edtAdc1_0_B.setText("" + String.format("%.3f", m_dblAdc1_0_B));
        
        m_dblAdc1_1_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL2);
        edtAdc1_1_A.setText( "" + String.format( "%.3f", m_dblAdc1_1_A));       
        m_dblAdc1_1_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL2);
        edtAdc1_1_B.setText( "" + String.format( "%.3f", m_dblAdc1_1_B));
        
        m_dblAdc1_2_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL3);
        edtAdc1_2_A.setText( "" + String.format( "%.3f", m_dblAdc1_2_A));       
        m_dblAdc1_2_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL3);
        edtAdc1_2_B.setText( "" + String.format( "%.3f", m_dblAdc1_2_B));
        
        m_dblAdc1_3_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL4);
        edtAdc1_3_A.setText( "" + String.format( "%.3f", m_dblAdc1_3_A));       
        m_dblAdc1_3_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL4);
        edtAdc1_3_B.setText( "" + String.format( "%.3f", m_dblAdc1_3_B));
        
        m_dblAdc1_4_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL5);
        edtAdc1_4_A.setText( "" + String.format( "%.3f", m_dblAdc1_4_A));       
        m_dblAdc1_4_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL5);
        edtAdc1_4_B.setText( "" + String.format( "%.3f", m_dblAdc1_4_B));
        
        m_dblAdc1_5_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL6);
        edtAdc1_5_A.setText( "" + String.format( "%.3f", m_dblAdc1_5_A));       
        m_dblAdc1_5_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL6);
        edtAdc1_5_B.setText( "" + String.format( "%.3f", m_dblAdc1_5_B));
        
        m_dblAdc1_6_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL7);
        edtAdc1_6_A.setText( "" + String.format( "%.3f", m_dblAdc1_6_A));       
        m_dblAdc1_6_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL7);
        edtAdc1_6_B.setText( "" + String.format( "%.3f", m_dblAdc1_6_B));
        
        m_dblAdc1_7_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetSlope( AMSConstants.CHANNEL8);
        edtAdc1_7_A.setText( "" + String.format( "%.3f", m_dblAdc1_7_A));       
        m_dblAdc1_7_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).GetShift( AMSConstants.CHANNEL8);
        edtAdc1_7_B.setText( "" + String.format( "%.3f", m_dblAdc1_7_B));
        
        
        m_dblAdc2_0_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL1);
        edtAdc2_0_A.setText( "" + String.format( "%.3f", m_dblAdc2_0_A));       
        m_dblAdc2_0_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL1);
        edtAdc2_0_B.setText( "" + String.format( "%.3f", m_dblAdc2_0_B));
        
        m_dblAdc2_1_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL2);
        edtAdc2_1_A.setText( "" + String.format( "%.3f", m_dblAdc2_1_A));       
        m_dblAdc2_1_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL2);
        edtAdc2_1_B.setText( "" + String.format( "%.3f", m_dblAdc2_1_B));
        
        m_dblAdc2_2_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL3);
        edtAdc2_2_A.setText( "" + String.format( "%.3f", m_dblAdc2_2_A));       
        m_dblAdc2_2_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL3);
        edtAdc2_2_B.setText( "" + String.format( "%.3f", m_dblAdc2_2_B));
        
        m_dblAdc2_3_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL4);
        edtAdc2_3_A.setText( "" + String.format( "%.3f", m_dblAdc2_3_A));       
        m_dblAdc2_3_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL4);
        edtAdc2_3_B.setText( "" + String.format( "%.3f", m_dblAdc2_3_B));
        
        m_dblAdc2_4_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL5);
        edtAdc2_4_A.setText( "" + String.format( "%.3f", m_dblAdc2_4_A));       
        m_dblAdc2_4_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL5);
        edtAdc2_4_B.setText( "" + String.format( "%.3f", m_dblAdc2_4_B));
        
        m_dblAdc2_5_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL6);
        edtAdc2_5_A.setText( "" + String.format( "%.3f", m_dblAdc2_5_A));       
        m_dblAdc2_5_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL6);
        edtAdc2_5_B.setText( "" + String.format( "%.3f", m_dblAdc2_5_B));
        
        m_dblAdc2_6_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL7);
        edtAdc2_6_A.setText( "" + String.format( "%.3f", m_dblAdc2_6_A));       
        m_dblAdc2_6_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL7);
        edtAdc2_6_B.setText( "" + String.format( "%.3f", m_dblAdc2_6_B));
        
        m_dblAdc2_7_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetSlope( AMSConstants.CHANNEL8);
        edtAdc2_7_A.setText( "" + String.format( "%.3f", m_dblAdc2_7_A));       
        m_dblAdc2_7_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).GetShift( AMSConstants.CHANNEL8);
        edtAdc2_7_B.setText( "" + String.format( "%.3f", m_dblAdc2_7_B));
        
        
        m_dblAdc3_0_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL1);
        edtAdc3_0_A.setText( "" + String.format( "%.3f", m_dblAdc3_0_A));       
        m_dblAdc3_0_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL1);
        edtAdc3_0_B.setText( "" + String.format( "%.3f", m_dblAdc3_0_B));
        
        m_dblAdc3_1_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL2);
        edtAdc3_1_A.setText( "" + String.format( "%.3f", m_dblAdc3_1_A));       
        m_dblAdc3_1_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL2);
        edtAdc3_1_B.setText( "" + String.format( "%.3f", m_dblAdc3_1_B));
        
        m_dblAdc3_2_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL3);
        edtAdc3_2_A.setText( "" + String.format( "%.3f", m_dblAdc3_2_A));       
        m_dblAdc3_2_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL3);
        edtAdc3_2_B.setText( "" + String.format( "%.3f", m_dblAdc3_2_B));
        
        m_dblAdc3_3_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL4);
        edtAdc3_3_A.setText( "" + String.format( "%.3f", m_dblAdc3_3_A));       
        m_dblAdc3_3_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL4);
        edtAdc3_3_B.setText( "" + String.format( "%.3f", m_dblAdc3_3_B));
        
        m_dblAdc3_4_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL5);
        edtAdc3_4_A.setText( "" + String.format( "%.3f", m_dblAdc3_4_A));       
        m_dblAdc3_4_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL5);
        edtAdc3_4_B.setText( "" + String.format( "%.3f", m_dblAdc3_4_B));
        
        m_dblAdc3_5_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL6);
        edtAdc3_5_A.setText( "" + String.format( "%.3f", m_dblAdc3_5_A));       
        m_dblAdc3_5_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL6);
        edtAdc3_5_B.setText( "" + String.format( "%.3f", m_dblAdc3_5_B));
        
        m_dblAdc3_6_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL7);
        edtAdc3_6_A.setText( "" + String.format( "%.3f", m_dblAdc3_6_A));       
        m_dblAdc3_6_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL7);
        edtAdc3_6_B.setText( "" + String.format( "%.3f", m_dblAdc3_6_B));
        
        m_dblAdc3_7_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetSlope( AMSConstants.CHANNEL8);
        edtAdc3_7_A.setText( "" + String.format( "%.3f", m_dblAdc3_7_A));       
        m_dblAdc3_7_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).GetShift( AMSConstants.CHANNEL8);
        edtAdc3_7_B.setText( "" + String.format( "%.3f", m_dblAdc3_7_B));
        
        
        m_dblAdc4_0_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL1);
        edtAdc4_0_A.setText( "" + String.format( "%.3f", m_dblAdc4_0_A));       
        m_dblAdc4_0_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL1);
        edtAdc4_0_B.setText( "" + String.format( "%.3f", m_dblAdc4_0_B));
        
        m_dblAdc4_1_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL2);
        edtAdc4_1_A.setText( "" + String.format( "%.3f", m_dblAdc4_1_A));       
        m_dblAdc4_1_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL2);
        edtAdc4_1_B.setText( "" + String.format( "%.3f", m_dblAdc4_1_B));
        
        m_dblAdc4_2_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL3);
        edtAdc4_2_A.setText( "" + String.format( "%.3f", m_dblAdc4_2_A));       
        m_dblAdc4_2_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL3);
        edtAdc4_2_B.setText( "" + String.format( "%.3f", m_dblAdc4_2_B));
        
        m_dblAdc4_3_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL4);
        edtAdc4_3_A.setText( "" + String.format( "%.3f", m_dblAdc4_3_A));       
        m_dblAdc4_3_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL4);
        edtAdc4_3_B.setText( "" + String.format( "%.3f", m_dblAdc4_3_B));
        
        m_dblAdc4_4_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL5);
        edtAdc4_4_A.setText( "" + String.format( "%.3f", m_dblAdc4_4_A));       
        m_dblAdc4_4_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL5);
        edtAdc4_4_B.setText( "" + String.format( "%.3f", m_dblAdc4_4_B));
        
        m_dblAdc4_5_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL6);
        edtAdc4_5_A.setText( "" + String.format( "%.3f", m_dblAdc4_5_A));       
        m_dblAdc4_5_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL6);
        edtAdc4_5_B.setText( "" + String.format( "%.3f", m_dblAdc4_5_B));
        
        m_dblAdc4_6_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL7);
        edtAdc4_6_A.setText( "" + String.format( "%.3f", m_dblAdc4_6_A));       
        m_dblAdc4_6_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL7);
        edtAdc4_6_B.setText( "" + String.format( "%.3f", m_dblAdc4_6_B));
        
        m_dblAdc4_7_A = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetSlope( AMSConstants.CHANNEL8);
        edtAdc4_7_A.setText( "" + String.format( "%.3f", m_dblAdc4_7_A));       
        m_dblAdc4_7_B = theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).GetShift( AMSConstants.CHANNEL8);
        edtAdc4_7_B.setText( "" + String.format( "%.3f", m_dblAdc4_7_B));
    }
    
    public void SaveData() {
        ApplyEdtAdc2s();
        ApplyEdtAdc1s();
        ApplyEdtAdc4s();
        ApplyEdtAdc3s();
        
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL1, m_dblAdc1_0_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL1, m_dblAdc1_0_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL2, m_dblAdc1_1_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL2, m_dblAdc1_1_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL3, m_dblAdc1_2_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL3, m_dblAdc1_2_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL4, m_dblAdc1_3_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL4, m_dblAdc1_3_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL5, m_dblAdc1_4_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL5, m_dblAdc1_4_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL6, m_dblAdc1_5_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL6, m_dblAdc1_5_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL7, m_dblAdc1_6_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL7, m_dblAdc1_6_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetSlope( AMSConstants.CHANNEL8, m_dblAdc1_7_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC1).SetShift( AMSConstants.CHANNEL8, m_dblAdc1_7_B);
        
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL1, m_dblAdc2_0_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL1, m_dblAdc2_0_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL2, m_dblAdc2_1_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL2, m_dblAdc2_1_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL3, m_dblAdc2_2_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL3, m_dblAdc2_2_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL4, m_dblAdc2_3_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL4, m_dblAdc2_3_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL5, m_dblAdc2_4_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL5, m_dblAdc2_4_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL6, m_dblAdc2_5_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL6, m_dblAdc2_5_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL7, m_dblAdc2_6_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL7, m_dblAdc2_6_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetSlope( AMSConstants.CHANNEL8, m_dblAdc2_7_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC2).SetShift( AMSConstants.CHANNEL8, m_dblAdc2_7_B);
        
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL1, m_dblAdc3_0_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL1, m_dblAdc3_0_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL2, m_dblAdc3_1_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL2, m_dblAdc3_1_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL3, m_dblAdc3_2_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL3, m_dblAdc3_2_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL4, m_dblAdc3_3_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL4, m_dblAdc3_3_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL5, m_dblAdc3_4_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL5, m_dblAdc3_4_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL6, m_dblAdc3_5_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL6, m_dblAdc3_5_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL7, m_dblAdc3_6_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL7, m_dblAdc3_6_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetSlope( AMSConstants.CHANNEL8, m_dblAdc3_7_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC3).SetShift( AMSConstants.CHANNEL8, m_dblAdc3_7_B);
        
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL1, m_dblAdc4_0_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL1, m_dblAdc4_0_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL2, m_dblAdc4_1_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL2, m_dblAdc4_1_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL3, m_dblAdc4_2_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL3, m_dblAdc4_2_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL4, m_dblAdc4_3_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL4, m_dblAdc4_3_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL5, m_dblAdc4_4_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL5, m_dblAdc4_4_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL6, m_dblAdc4_5_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL6, m_dblAdc4_5_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL7, m_dblAdc4_6_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL7, m_dblAdc4_6_B);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetSlope( AMSConstants.CHANNEL8, m_dblAdc4_7_A);
        theApp.GetCalibration().GetCalibUnit( AMSConstants.ADC4).SetShift( AMSConstants.CHANNEL8, m_dblAdc4_7_B);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtAdc1_2_B = new javax.swing.JTextField();
        edtAdc1_2_A = new javax.swing.JTextField();
        edtAdc1_1_B = new javax.swing.JTextField();
        edtAdc1_1_A = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edtAdc1_0_B = new javax.swing.JTextField();
        edtAdc1_0_A = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtAdc1_3_B = new javax.swing.JTextField();
        edtAdc1_3_A = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        edtAdc1_4_A = new javax.swing.JTextField();
        edtAdc1_4_B = new javax.swing.JTextField();
        edtAdc1_5_B = new javax.swing.JTextField();
        edtAdc1_5_A = new javax.swing.JTextField();
        edtAdc1_6_A = new javax.swing.JTextField();
        edtAdc1_7_A = new javax.swing.JTextField();
        edtAdc1_7_B = new javax.swing.JTextField();
        edtAdc1_6_B = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        edtAdc3_2_B = new javax.swing.JTextField();
        edtAdc3_2_A = new javax.swing.JTextField();
        edtAdc3_1_B = new javax.swing.JTextField();
        edtAdc3_1_A = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        edtAdc3_0_B = new javax.swing.JTextField();
        edtAdc3_0_A = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        edtAdc3_3_B = new javax.swing.JTextField();
        edtAdc3_3_A = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        edtAdc3_4_A = new javax.swing.JTextField();
        edtAdc3_4_B = new javax.swing.JTextField();
        edtAdc3_5_B = new javax.swing.JTextField();
        edtAdc3_5_A = new javax.swing.JTextField();
        edtAdc3_6_A = new javax.swing.JTextField();
        edtAdc3_7_A = new javax.swing.JTextField();
        edtAdc3_7_B = new javax.swing.JTextField();
        edtAdc3_6_B = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        edtAdc4_2_B = new javax.swing.JTextField();
        edtAdc4_2_A = new javax.swing.JTextField();
        edtAdc4_1_B = new javax.swing.JTextField();
        edtAdc4_1_A = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        edtAdc4_0_B = new javax.swing.JTextField();
        edtAdc4_0_A = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        edtAdc4_3_B = new javax.swing.JTextField();
        edtAdc4_3_A = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        edtAdc4_4_A = new javax.swing.JTextField();
        edtAdc4_4_B = new javax.swing.JTextField();
        edtAdc4_5_B = new javax.swing.JTextField();
        edtAdc4_5_A = new javax.swing.JTextField();
        edtAdc4_6_A = new javax.swing.JTextField();
        edtAdc4_7_A = new javax.swing.JTextField();
        edtAdc4_7_B = new javax.swing.JTextField();
        edtAdc4_6_B = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        edtAdc2_2_B = new javax.swing.JTextField();
        edtAdc2_2_A = new javax.swing.JTextField();
        edtAdc2_1_B = new javax.swing.JTextField();
        edtAdc2_1_A = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        edtAdc2_0_B = new javax.swing.JTextField();
        edtAdc2_0_A = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        edtAdc2_3_B = new javax.swing.JTextField();
        edtAdc2_3_A = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        edtAdc2_4_A = new javax.swing.JTextField();
        edtAdc2_4_B = new javax.swing.JTextField();
        edtAdc2_5_B = new javax.swing.JTextField();
        edtAdc2_5_A = new javax.swing.JTextField();
        edtAdc2_6_A = new javax.swing.JTextField();
        edtAdc2_7_A = new javax.swing.JTextField();
        edtAdc2_7_B = new javax.swing.JTextField();
        edtAdc2_6_B = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Калибровка АЦП");
        setMaximumSize(new java.awt.Dimension(600, 680));
        setMinimumSize(new java.awt.Dimension(600, 680));
        setPreferredSize(new java.awt.Dimension(600, 680));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("АЦП1 (0x01)"));
        jPanel8.setLayout(null);

        jLabel1.setText("Канал 2:");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel1);
        jLabel1.setBounds(20, 100, 70, 25);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Пьедестал");
        jPanel8.add(jLabel2);
        jLabel2.setBounds(180, 20, 80, 16);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Наклон");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel3);
        jLabel3.setBounds(90, 20, 80, 16);

        edtAdc1_2_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_2_B.setText("-");
        edtAdc1_2_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_2_B);
        edtAdc1_2_B.setBounds(180, 100, 80, 25);

        edtAdc1_2_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_2_A.setText("-");
        edtAdc1_2_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_2_A);
        edtAdc1_2_A.setBounds(90, 100, 80, 25);

        edtAdc1_1_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_1_B.setText("-");
        edtAdc1_1_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_1_B);
        edtAdc1_1_B.setBounds(180, 70, 80, 25);

        edtAdc1_1_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_1_A.setText("-");
        edtAdc1_1_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_1_A);
        edtAdc1_1_A.setBounds(90, 70, 80, 25);

        jLabel4.setText("Канал 1:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel4);
        jLabel4.setBounds(20, 70, 70, 25);

        edtAdc1_0_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_0_B.setText("-");
        edtAdc1_0_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_0_B);
        edtAdc1_0_B.setBounds(180, 40, 80, 25);

        edtAdc1_0_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_0_A.setText("-");
        edtAdc1_0_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_0_A);
        edtAdc1_0_A.setBounds(90, 40, 80, 25);

        jLabel5.setText("Канал 0:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel5);
        jLabel5.setBounds(20, 40, 70, 25);

        edtAdc1_3_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_3_B.setText("-");
        edtAdc1_3_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_3_B);
        edtAdc1_3_B.setBounds(180, 130, 80, 25);

        edtAdc1_3_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_3_A.setText("-");
        edtAdc1_3_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_3_A);
        edtAdc1_3_A.setBounds(90, 130, 80, 25);

        jLabel6.setText("Канал 3:");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel6);
        jLabel6.setBounds(20, 130, 70, 25);

        jLabel7.setText("Канал 7:");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel7);
        jLabel7.setBounds(20, 250, 70, 25);

        jLabel26.setText("Канал 6:");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel26);
        jLabel26.setBounds(20, 220, 70, 25);

        jLabel27.setText("Канал 5:");
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel27);
        jLabel27.setBounds(20, 190, 70, 25);

        jLabel28.setText("Канал 4:");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel28);
        jLabel28.setBounds(20, 160, 70, 25);

        edtAdc1_4_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_4_A.setText("-");
        edtAdc1_4_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_4_A);
        edtAdc1_4_A.setBounds(90, 160, 80, 25);

        edtAdc1_4_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_4_B.setText("-");
        edtAdc1_4_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_4_B);
        edtAdc1_4_B.setBounds(180, 160, 80, 25);

        edtAdc1_5_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_5_B.setText("-");
        edtAdc1_5_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_5_B);
        edtAdc1_5_B.setBounds(180, 190, 80, 25);

        edtAdc1_5_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_5_A.setText("-");
        edtAdc1_5_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_5_A);
        edtAdc1_5_A.setBounds(90, 190, 80, 25);

        edtAdc1_6_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_6_A.setText("-");
        edtAdc1_6_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_6_A);
        edtAdc1_6_A.setBounds(90, 220, 80, 25);

        edtAdc1_7_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_7_A.setText("-");
        edtAdc1_7_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_7_A);
        edtAdc1_7_A.setBounds(90, 250, 80, 25);

        edtAdc1_7_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_7_B.setText("-");
        edtAdc1_7_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_7_B);
        edtAdc1_7_B.setBounds(180, 250, 80, 25);

        edtAdc1_6_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc1_6_B.setText("-");
        edtAdc1_6_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel8.add(edtAdc1_6_B);
        edtAdc1_6_B.setBounds(180, 220, 80, 25);

        getContentPane().add(jPanel8);
        jPanel8.setBounds(10, 10, 280, 290);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("АЦП3 (0x11)"));
        jPanel10.setLayout(null);

        jLabel29.setText("Канал 2:");
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel29);
        jLabel29.setBounds(20, 100, 70, 25);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Пьедестал");
        jPanel10.add(jLabel30);
        jLabel30.setBounds(180, 20, 80, 16);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Наклон");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel31);
        jLabel31.setBounds(90, 20, 80, 16);

        edtAdc3_2_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_2_B.setText("-");
        edtAdc3_2_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_2_B);
        edtAdc3_2_B.setBounds(180, 100, 80, 25);

        edtAdc3_2_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_2_A.setText("-");
        edtAdc3_2_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_2_A);
        edtAdc3_2_A.setBounds(90, 100, 80, 25);

        edtAdc3_1_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_1_B.setText("-");
        edtAdc3_1_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_1_B);
        edtAdc3_1_B.setBounds(180, 70, 80, 25);

        edtAdc3_1_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_1_A.setText("-");
        edtAdc3_1_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_1_A);
        edtAdc3_1_A.setBounds(90, 70, 80, 25);

        jLabel32.setText("Канал 1:");
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel32);
        jLabel32.setBounds(20, 70, 70, 25);

        edtAdc3_0_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_0_B.setText("-");
        edtAdc3_0_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_0_B);
        edtAdc3_0_B.setBounds(180, 40, 80, 25);

        edtAdc3_0_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_0_A.setText("-");
        edtAdc3_0_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_0_A);
        edtAdc3_0_A.setBounds(90, 40, 80, 25);

        jLabel33.setText("Канал 0:");
        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel33);
        jLabel33.setBounds(20, 40, 70, 25);

        edtAdc3_3_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_3_B.setText("-");
        edtAdc3_3_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_3_B);
        edtAdc3_3_B.setBounds(180, 130, 80, 25);

        edtAdc3_3_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_3_A.setText("-");
        edtAdc3_3_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_3_A);
        edtAdc3_3_A.setBounds(90, 130, 80, 25);

        jLabel34.setText("Канал 3:");
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel34);
        jLabel34.setBounds(20, 130, 70, 25);

        jLabel35.setText("Канал 7:");
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel35);
        jLabel35.setBounds(20, 250, 70, 25);

        jLabel36.setText("Канал 6:");
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel36);
        jLabel36.setBounds(20, 220, 70, 25);

        jLabel37.setText("Канал 5:");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel37);
        jLabel37.setBounds(20, 190, 70, 25);

        jLabel38.setText("Канал 4:");
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel10.add(jLabel38);
        jLabel38.setBounds(20, 160, 70, 25);

        edtAdc3_4_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_4_A.setText("-");
        edtAdc3_4_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_4_A);
        edtAdc3_4_A.setBounds(90, 160, 80, 25);

        edtAdc3_4_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_4_B.setText("-");
        edtAdc3_4_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_4_B);
        edtAdc3_4_B.setBounds(180, 160, 80, 25);

        edtAdc3_5_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_5_B.setText("-");
        edtAdc3_5_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_5_B);
        edtAdc3_5_B.setBounds(180, 190, 80, 25);

        edtAdc3_5_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_5_A.setText("-");
        edtAdc3_5_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_5_A);
        edtAdc3_5_A.setBounds(90, 190, 80, 25);

        edtAdc3_6_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_6_A.setText("-");
        edtAdc3_6_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_6_A);
        edtAdc3_6_A.setBounds(90, 220, 80, 25);

        edtAdc3_7_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_7_A.setText("-");
        edtAdc3_7_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_7_A);
        edtAdc3_7_A.setBounds(90, 250, 80, 25);

        edtAdc3_7_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_7_B.setText("-");
        edtAdc3_7_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_7_B);
        edtAdc3_7_B.setBounds(180, 250, 80, 25);

        edtAdc3_6_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc3_6_B.setText("-");
        edtAdc3_6_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel10.add(edtAdc3_6_B);
        edtAdc3_6_B.setBounds(180, 220, 80, 25);

        getContentPane().add(jPanel10);
        jPanel10.setBounds(300, 10, 280, 290);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("АЦП4 (0x12)"));
        jPanel11.setLayout(null);

        jLabel49.setText("Канал 2:");
        jLabel49.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel49);
        jLabel49.setBounds(20, 100, 70, 25);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Пьедестал");
        jPanel11.add(jLabel50);
        jLabel50.setBounds(180, 20, 80, 16);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Наклон");
        jLabel51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel51);
        jLabel51.setBounds(90, 20, 80, 16);

        edtAdc4_2_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_2_B.setText("-");
        edtAdc4_2_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_2_B);
        edtAdc4_2_B.setBounds(180, 100, 80, 25);

        edtAdc4_2_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_2_A.setText("-");
        edtAdc4_2_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_2_A);
        edtAdc4_2_A.setBounds(90, 100, 80, 25);

        edtAdc4_1_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_1_B.setText("-");
        edtAdc4_1_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_1_B);
        edtAdc4_1_B.setBounds(180, 70, 80, 25);

        edtAdc4_1_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_1_A.setText("-");
        edtAdc4_1_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_1_A);
        edtAdc4_1_A.setBounds(90, 70, 80, 25);

        jLabel52.setText("Канал 1:");
        jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel52);
        jLabel52.setBounds(20, 70, 70, 25);

        edtAdc4_0_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_0_B.setText("-");
        edtAdc4_0_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_0_B);
        edtAdc4_0_B.setBounds(180, 40, 80, 25);

        edtAdc4_0_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_0_A.setText("-");
        edtAdc4_0_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_0_A);
        edtAdc4_0_A.setBounds(90, 40, 80, 25);

        jLabel53.setText("Канал 0:");
        jLabel53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel53);
        jLabel53.setBounds(20, 40, 70, 25);

        edtAdc4_3_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_3_B.setText("-");
        edtAdc4_3_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_3_B);
        edtAdc4_3_B.setBounds(180, 130, 80, 25);

        edtAdc4_3_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_3_A.setText("-");
        edtAdc4_3_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_3_A);
        edtAdc4_3_A.setBounds(90, 130, 80, 25);

        jLabel54.setText("Канал 3:");
        jLabel54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel54);
        jLabel54.setBounds(20, 130, 70, 25);

        jLabel55.setText("Канал 7:");
        jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel55);
        jLabel55.setBounds(20, 250, 70, 25);

        jLabel56.setText("Канал 6:");
        jLabel56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel56);
        jLabel56.setBounds(20, 220, 70, 25);

        jLabel57.setText("Канал 5:");
        jLabel57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel57);
        jLabel57.setBounds(20, 190, 70, 25);

        jLabel58.setText("Канал 4:");
        jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel11.add(jLabel58);
        jLabel58.setBounds(20, 160, 70, 25);

        edtAdc4_4_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_4_A.setText("-");
        edtAdc4_4_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_4_A);
        edtAdc4_4_A.setBounds(90, 160, 80, 25);

        edtAdc4_4_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_4_B.setText("-");
        edtAdc4_4_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_4_B);
        edtAdc4_4_B.setBounds(180, 160, 80, 25);

        edtAdc4_5_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_5_B.setText("-");
        edtAdc4_5_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_5_B);
        edtAdc4_5_B.setBounds(180, 190, 80, 25);

        edtAdc4_5_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_5_A.setText("-");
        edtAdc4_5_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_5_A);
        edtAdc4_5_A.setBounds(90, 190, 80, 25);

        edtAdc4_6_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_6_A.setText("-");
        edtAdc4_6_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_6_A);
        edtAdc4_6_A.setBounds(90, 220, 80, 25);

        edtAdc4_7_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_7_A.setText("-");
        edtAdc4_7_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_7_A);
        edtAdc4_7_A.setBounds(90, 250, 80, 25);

        edtAdc4_7_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_7_B.setText("-");
        edtAdc4_7_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_7_B);
        edtAdc4_7_B.setBounds(180, 250, 80, 25);

        edtAdc4_6_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc4_6_B.setText("-");
        edtAdc4_6_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel11.add(edtAdc4_6_B);
        edtAdc4_6_B.setBounds(180, 220, 80, 25);

        getContentPane().add(jPanel11);
        jPanel11.setBounds(300, 300, 280, 290);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("АЦП2 (0x02)"));
        jPanel9.setLayout(null);

        jLabel39.setText("Канал 2:");
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel39);
        jLabel39.setBounds(20, 100, 70, 25);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Пьедестал");
        jPanel9.add(jLabel40);
        jLabel40.setBounds(180, 20, 80, 16);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Наклон");
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel41);
        jLabel41.setBounds(90, 20, 80, 16);

        edtAdc2_2_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_2_B.setText("-");
        edtAdc2_2_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_2_B);
        edtAdc2_2_B.setBounds(180, 100, 80, 25);

        edtAdc2_2_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_2_A.setText("-");
        edtAdc2_2_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_2_A);
        edtAdc2_2_A.setBounds(90, 100, 80, 25);

        edtAdc2_1_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_1_B.setText("-");
        edtAdc2_1_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_1_B);
        edtAdc2_1_B.setBounds(180, 70, 80, 25);

        edtAdc2_1_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_1_A.setText("-");
        edtAdc2_1_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_1_A);
        edtAdc2_1_A.setBounds(90, 70, 80, 25);

        jLabel42.setText("Канал 1:");
        jLabel42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel42);
        jLabel42.setBounds(20, 70, 70, 25);

        edtAdc2_0_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_0_B.setText("-");
        edtAdc2_0_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_0_B);
        edtAdc2_0_B.setBounds(180, 40, 80, 25);

        edtAdc2_0_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_0_A.setText("-");
        edtAdc2_0_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_0_A);
        edtAdc2_0_A.setBounds(90, 40, 80, 25);

        jLabel43.setText("Канал 0:");
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel43);
        jLabel43.setBounds(20, 40, 70, 25);

        edtAdc2_3_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_3_B.setText("-");
        edtAdc2_3_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_3_B);
        edtAdc2_3_B.setBounds(180, 130, 80, 25);

        edtAdc2_3_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_3_A.setText("-");
        edtAdc2_3_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_3_A);
        edtAdc2_3_A.setBounds(90, 130, 80, 25);

        jLabel44.setText("Канал 3:");
        jLabel44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel44);
        jLabel44.setBounds(20, 130, 70, 25);

        jLabel45.setText("Канал 7:");
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel45);
        jLabel45.setBounds(20, 250, 70, 25);

        jLabel46.setText("Канал 6:");
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel46);
        jLabel46.setBounds(20, 220, 70, 25);

        jLabel47.setText("Канал 5:");
        jLabel47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel47);
        jLabel47.setBounds(20, 190, 70, 25);

        jLabel48.setText("Канал 4:");
        jLabel48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(jLabel48);
        jLabel48.setBounds(20, 160, 70, 25);

        edtAdc2_4_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_4_A.setText("-");
        edtAdc2_4_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_4_A);
        edtAdc2_4_A.setBounds(90, 160, 80, 25);

        edtAdc2_4_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_4_B.setText("-");
        edtAdc2_4_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_4_B);
        edtAdc2_4_B.setBounds(180, 160, 80, 25);

        edtAdc2_5_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_5_B.setText("-");
        edtAdc2_5_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_5_B);
        edtAdc2_5_B.setBounds(180, 190, 80, 25);

        edtAdc2_5_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_5_A.setText("-");
        edtAdc2_5_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_5_A);
        edtAdc2_5_A.setBounds(90, 190, 80, 25);

        edtAdc2_6_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_6_A.setText("-");
        edtAdc2_6_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_6_A);
        edtAdc2_6_A.setBounds(90, 220, 80, 25);

        edtAdc2_7_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_7_A.setText("-");
        edtAdc2_7_A.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_7_A);
        edtAdc2_7_A.setBounds(90, 250, 80, 25);

        edtAdc2_7_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_7_B.setText("-");
        edtAdc2_7_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_7_B);
        edtAdc2_7_B.setBounds(180, 250, 80, 25);

        edtAdc2_6_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtAdc2_6_B.setText("-");
        edtAdc2_6_B.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EdtFocusLost(evt);
            }
        });
        jPanel9.add(edtAdc2_6_B);
        edtAdc2_6_B.setBounds(180, 220, 80, 25);

        getContentPane().add(jPanel9);
        jPanel9.setBounds(10, 300, 280, 290);

        btnExit.setText("Выход");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(450, 600, 130, 28);

        btnSave.setText("Сохранить");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(310, 600, 130, 28);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean ApplyEdtAdc2s() {
        boolean bChanged = false;
        Double fromEdt;
        try {
            fromEdt = new Double( edtAdc2_0_A.getText());
            if( fromEdt != m_dblAdc2_0_A) {
                m_dblAdc2_0_A = fromEdt; edtAdc2_0_A.setText( String.format( "%.3f", m_dblAdc2_0_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_0_A.setText( String.format( "%.3f", m_dblAdc2_0_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_0_B.getText());
            if( fromEdt != m_dblAdc2_0_B) {
                m_dblAdc2_0_B = fromEdt; edtAdc2_0_B.setText( String.format( "%.3f", m_dblAdc2_0_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_0_B.setText( String.format( "%.3f", m_dblAdc2_0_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_1_A.getText());
            if( fromEdt != m_dblAdc2_1_A) {
                m_dblAdc2_1_A = fromEdt; edtAdc2_1_A.setText( String.format( "%.3f", m_dblAdc2_1_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_1_A.setText( String.format( "%.3f", m_dblAdc2_1_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_1_B.getText());
            if( fromEdt != m_dblAdc2_1_B) {
                m_dblAdc2_1_B = fromEdt; edtAdc2_1_B.setText( String.format( "%.3f", m_dblAdc2_1_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_1_B.setText( String.format( "%.3f", m_dblAdc2_1_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_2_A.getText());
            if( fromEdt != m_dblAdc2_2_A) {
                m_dblAdc2_2_A = fromEdt; edtAdc2_2_A.setText( String.format( "%.3f", m_dblAdc2_2_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_2_A.setText( String.format( "%.3f", m_dblAdc2_2_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_2_B.getText());
            if( fromEdt != m_dblAdc2_2_B) {
                m_dblAdc2_2_B = fromEdt; edtAdc2_2_B.setText( String.format( "%.3f", m_dblAdc2_2_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_2_B.setText( String.format( "%.3f", m_dblAdc2_2_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_3_A.getText());
            if( fromEdt != m_dblAdc2_3_A) {
                m_dblAdc2_3_A = fromEdt; edtAdc2_3_A.setText( String.format( "%.3f", m_dblAdc2_3_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_3_A.setText( String.format( "%.3f", m_dblAdc2_3_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_3_B.getText());
            if( fromEdt != m_dblAdc2_3_B) {
                m_dblAdc2_3_B = fromEdt; edtAdc2_3_B.setText( String.format( "%.3f", m_dblAdc2_3_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_3_B.setText( String.format( "%.3f", m_dblAdc2_3_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_4_A.getText());
            if( fromEdt != m_dblAdc2_4_A) {
                m_dblAdc2_4_A = fromEdt; edtAdc2_4_A.setText( String.format( "%.3f", m_dblAdc2_4_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_4_A.setText( String.format( "%.3f", m_dblAdc2_4_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_4_B.getText());
            if( fromEdt != m_dblAdc2_4_B) {
                m_dblAdc2_4_B = fromEdt; edtAdc2_4_B.setText( String.format( "%.3f", m_dblAdc2_4_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_4_B.setText( String.format( "%.3f", m_dblAdc2_4_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_5_A.getText());
            if( fromEdt != m_dblAdc2_5_A) {
                m_dblAdc2_5_A = fromEdt; edtAdc2_5_A.setText( String.format( "%.3f", m_dblAdc2_5_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_5_A.setText( String.format( "%.3f", m_dblAdc2_5_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_5_B.getText());
            if( fromEdt != m_dblAdc2_5_B) {
                m_dblAdc2_5_B = fromEdt; edtAdc2_5_B.setText( String.format( "%.3f", m_dblAdc2_5_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_5_B.setText( String.format( "%.3f", m_dblAdc2_5_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_6_A.getText());
            if( fromEdt != m_dblAdc2_6_A) {
                m_dblAdc2_6_A = fromEdt; edtAdc2_6_A.setText( String.format( "%.3f", m_dblAdc2_6_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_6_A.setText( String.format( "%.3f", m_dblAdc2_6_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_6_B.getText());
            if( fromEdt != m_dblAdc2_6_B) {
                m_dblAdc2_6_B = fromEdt; edtAdc2_6_B.setText( String.format( "%.3f", m_dblAdc2_6_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_6_B.setText( String.format( "%.3f", m_dblAdc2_6_B));
        }
        
        try {
            fromEdt = new Double( edtAdc2_7_A.getText());
            if( fromEdt != m_dblAdc2_7_A) {
                m_dblAdc2_7_A = fromEdt; edtAdc2_7_A.setText( String.format( "%.3f", m_dblAdc2_7_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_7_A.setText( String.format( "%.3f", m_dblAdc2_7_A));
        }
        
        try {
            fromEdt = new Double( edtAdc2_7_B.getText());
            if( fromEdt != m_dblAdc2_7_B) {
                m_dblAdc2_7_B = fromEdt; edtAdc2_7_B.setText( String.format( "%.3f", m_dblAdc2_7_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc2_7_B.setText( String.format( "%.3f", m_dblAdc2_7_B));
        }
        
        return bChanged;
    }
    
    private boolean ApplyEdtAdc1s() {
        boolean bChanged = false;
        Double fromEdt;
        try {
            fromEdt = new Double( edtAdc1_0_A.getText());
            if( fromEdt != m_dblAdc1_0_A) {
                m_dblAdc1_0_A = fromEdt; edtAdc1_0_A.setText(String.format("%.3f", m_dblAdc1_0_A));  bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_0_A.setText(String.format("%.3f", m_dblAdc1_0_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_0_B.getText());
            if( fromEdt != m_dblAdc1_0_B) {
                m_dblAdc1_0_B = fromEdt; edtAdc1_0_B.setText(String.format("%.3f", m_dblAdc1_0_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_0_B.setText(String.format("%.3f", m_dblAdc1_0_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_1_A.getText());
            if( fromEdt != m_dblAdc1_1_A) {
                m_dblAdc1_1_A = fromEdt; edtAdc1_1_A.setText( String.format( "%.3f", m_dblAdc1_1_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_1_A.setText( String.format( "%.3f", m_dblAdc1_1_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_1_B.getText());
            if( fromEdt != m_dblAdc1_1_B) {
                m_dblAdc1_1_B = fromEdt; edtAdc1_1_B.setText( String.format( "%.3f", m_dblAdc1_1_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_1_B.setText( String.format( "%.3f", m_dblAdc1_1_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_2_A.getText());
            if( fromEdt != m_dblAdc1_2_A) {
                m_dblAdc1_2_A = fromEdt; edtAdc1_2_A.setText( String.format( "%.3f", m_dblAdc1_2_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_2_A.setText( String.format( "%.3f", m_dblAdc1_2_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_2_B.getText());
            if( fromEdt != m_dblAdc1_2_B) {
                m_dblAdc1_2_B = fromEdt; edtAdc1_2_B.setText( String.format( "%.3f", m_dblAdc1_2_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_2_B.setText( String.format( "%.3f", m_dblAdc1_2_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_3_A.getText());
            if( fromEdt != m_dblAdc1_3_A) {
                m_dblAdc1_3_A = fromEdt; edtAdc1_3_A.setText( String.format( "%.3f", m_dblAdc1_3_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_3_A.setText( String.format( "%.3f", m_dblAdc1_3_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_3_B.getText());
            if( fromEdt != m_dblAdc1_3_B) {
                m_dblAdc1_3_B = fromEdt; edtAdc1_3_B.setText( String.format( "%.3f", m_dblAdc1_3_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_3_B.setText( String.format( "%.3f", m_dblAdc1_3_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_4_A.getText());
            if( fromEdt != m_dblAdc1_4_A) {
                m_dblAdc1_4_A = fromEdt; edtAdc1_4_A.setText( String.format( "%.3f", m_dblAdc1_4_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_4_A.setText( String.format( "%.3f", m_dblAdc1_4_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_4_B.getText());
            if( fromEdt != m_dblAdc1_4_B) {
                m_dblAdc1_4_B = fromEdt; edtAdc1_4_B.setText( String.format( "%.3f", m_dblAdc1_4_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_4_B.setText( String.format( "%.3f", m_dblAdc1_4_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_5_A.getText());
            if( fromEdt != m_dblAdc1_5_A) {
                m_dblAdc1_5_A = fromEdt; edtAdc1_5_A.setText( String.format( "%.3f", m_dblAdc1_5_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_5_A.setText( String.format( "%.3f", m_dblAdc1_5_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_5_B.getText());
            if( fromEdt != m_dblAdc1_5_B) {
                m_dblAdc1_5_B = fromEdt; edtAdc1_5_B.setText( String.format( "%.3f", m_dblAdc1_5_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_5_B.setText( String.format( "%.3f", m_dblAdc1_5_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_6_A.getText());
            if( fromEdt != m_dblAdc1_6_A) {
                m_dblAdc1_6_A = fromEdt; edtAdc1_6_A.setText( String.format( "%.3f", m_dblAdc1_6_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_6_A.setText( String.format( "%.3f", m_dblAdc1_6_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_6_B.getText());
            if( fromEdt != m_dblAdc1_6_B) {
                m_dblAdc1_6_B = fromEdt; edtAdc1_6_B.setText( String.format( "%.3f", m_dblAdc1_6_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_6_B.setText( String.format( "%.3f", m_dblAdc1_6_B));
        }
        
        try {
            fromEdt = new Double( edtAdc1_7_A.getText());
            if( fromEdt != m_dblAdc1_7_A) {
                m_dblAdc1_7_A = fromEdt; edtAdc1_7_A.setText( String.format( "%.3f", m_dblAdc1_7_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_7_A.setText( String.format( "%.3f", m_dblAdc1_7_A));
        }
        
        try {
            fromEdt = new Double( edtAdc1_7_B.getText());
            if( fromEdt != m_dblAdc1_7_B) {
                m_dblAdc1_7_B = fromEdt; edtAdc1_7_B.setText( String.format( "%.3f", m_dblAdc1_7_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc1_7_B.setText( String.format( "%.3f", m_dblAdc1_7_B));
        }
        
        return bChanged;
    }
    
    private boolean ApplyEdtAdc4s() {
        boolean bChanged = false;
        Double fromEdt;
        try {
            fromEdt = new Double( edtAdc4_0_A.getText());
            if( fromEdt != m_dblAdc4_0_A) {
                m_dblAdc4_0_A = fromEdt; edtAdc4_0_A.setText( String.format( "%.3f", m_dblAdc4_0_A));  bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_0_A.setText( String.format( "%.3f", m_dblAdc4_0_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_0_B.getText());
            if( fromEdt != m_dblAdc4_0_B) {
                m_dblAdc4_0_B = fromEdt; edtAdc4_0_B.setText( String.format( "%.3f", m_dblAdc4_0_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_0_B.setText( String.format( "%.3f", m_dblAdc4_0_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_1_A.getText());
            if( fromEdt != m_dblAdc4_1_A) {
                m_dblAdc4_1_A = fromEdt; edtAdc4_1_A.setText( String.format( "%.3f", m_dblAdc4_1_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_1_A.setText( String.format( "%.3f", m_dblAdc4_1_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_1_B.getText());
            if( fromEdt != m_dblAdc4_1_B) {
                m_dblAdc4_1_B = fromEdt; edtAdc4_1_B.setText( String.format( "%.3f", m_dblAdc4_1_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_1_B.setText( String.format( "%.3f", m_dblAdc4_1_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_2_A.getText());
            if( fromEdt != m_dblAdc4_2_A) {
                m_dblAdc4_2_A = fromEdt; edtAdc4_2_A.setText( String.format( "%.3f", m_dblAdc4_2_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_2_A.setText( String.format( "%.3f", m_dblAdc4_2_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_2_B.getText());
            if( fromEdt != m_dblAdc4_2_B) {
                m_dblAdc4_2_B = fromEdt; edtAdc4_2_B.setText( String.format( "%.3f", m_dblAdc4_2_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_2_B.setText( String.format( "%.3f", m_dblAdc4_2_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_3_A.getText());
            if( fromEdt != m_dblAdc4_3_A) {
                m_dblAdc4_3_A = fromEdt; edtAdc4_3_A.setText( String.format( "%.3f", m_dblAdc4_3_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_3_A.setText( String.format( "%.3f", m_dblAdc4_3_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_3_B.getText());
            if( fromEdt != m_dblAdc4_3_B) {
                m_dblAdc4_3_B = fromEdt; edtAdc4_3_B.setText( String.format( "%.3f", m_dblAdc4_3_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_3_B.setText( String.format( "%.3f", m_dblAdc4_3_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_4_A.getText());
            if( fromEdt != m_dblAdc4_4_A) {
                m_dblAdc4_4_A = fromEdt; edtAdc4_4_A.setText( String.format( "%.3f", m_dblAdc4_4_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_4_A.setText( String.format( "%.3f", m_dblAdc4_4_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_4_B.getText());
            if( fromEdt != m_dblAdc4_4_B) {
                m_dblAdc4_4_B = fromEdt; edtAdc4_4_B.setText( String.format( "%.3f", m_dblAdc4_4_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_4_B.setText( String.format( "%.3f", m_dblAdc4_4_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_5_A.getText());
            if( fromEdt != m_dblAdc4_5_A) {
                m_dblAdc4_5_A = fromEdt; edtAdc4_5_A.setText( String.format( "%.3f", m_dblAdc4_5_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_5_A.setText( String.format( "%.3f", m_dblAdc4_5_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_5_B.getText());
            if( fromEdt != m_dblAdc4_5_B) {
                m_dblAdc4_5_B = fromEdt; edtAdc4_5_B.setText( String.format( "%.3f", m_dblAdc4_5_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_5_B.setText( String.format( "%.3f", m_dblAdc4_5_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_6_A.getText());
            if( fromEdt != m_dblAdc4_6_A) {
                m_dblAdc4_6_A = fromEdt; edtAdc4_6_A.setText( String.format( "%.3f", m_dblAdc4_6_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_6_A.setText( String.format( "%.3f", m_dblAdc4_6_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_6_B.getText());
            if( fromEdt != m_dblAdc4_6_B) {
                m_dblAdc4_6_B = fromEdt; edtAdc4_6_B.setText( String.format( "%.3f", m_dblAdc4_6_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_6_B.setText( String.format( "%.3f", m_dblAdc4_6_B));
        }
        
        try {
            fromEdt = new Double( edtAdc4_7_A.getText());
            if( fromEdt != m_dblAdc4_7_A) {
                m_dblAdc4_7_A = fromEdt; edtAdc4_7_A.setText( String.format( "%.3f", m_dblAdc4_7_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_7_A.setText( String.format( "%.3f", m_dblAdc4_7_A));
        }
        
        try {
            fromEdt = new Double( edtAdc4_7_B.getText());
            if( fromEdt != m_dblAdc4_7_B) {
                m_dblAdc4_7_B = fromEdt; edtAdc4_7_B.setText( String.format( "%.3f", m_dblAdc4_7_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc4_7_B.setText( String.format( "%.3f", m_dblAdc4_7_B));
        }
        
        return bChanged;
    }
    
    private boolean ApplyEdtAdc3s() {
        boolean bChanged = false;
        Double fromEdt;
        try {
            fromEdt = new Double( edtAdc3_0_A.getText());
            if( fromEdt != m_dblAdc3_0_A) {
                m_dblAdc3_0_A = fromEdt; edtAdc3_0_A.setText( String.format( "%.3f", m_dblAdc3_0_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_0_A.setText( String.format( "%.3f", m_dblAdc3_0_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_0_B.getText());
            if( fromEdt != m_dblAdc3_0_B) {
                m_dblAdc3_0_B = fromEdt; edtAdc3_0_B.setText( String.format( "%.3f", m_dblAdc3_0_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_0_B.setText( String.format( "%.3f", m_dblAdc3_0_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_1_A.getText());
            if( fromEdt != m_dblAdc3_1_A) {
                m_dblAdc3_1_A = fromEdt; edtAdc3_1_A.setText( String.format( "%.3f", m_dblAdc3_1_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_1_A.setText( String.format( "%.3f", m_dblAdc3_1_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_1_B.getText());
            if( fromEdt != m_dblAdc3_1_B) {
                m_dblAdc3_1_B = fromEdt; edtAdc3_1_B.setText( String.format( "%.3f", m_dblAdc3_1_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_1_B.setText( String.format( "%.3f", m_dblAdc3_1_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_2_A.getText());
            if( fromEdt != m_dblAdc3_2_A) {
                m_dblAdc3_2_A = fromEdt; edtAdc3_2_A.setText( String.format( "%.3f", m_dblAdc3_2_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_2_A.setText( String.format( "%.3f", m_dblAdc3_2_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_2_B.getText());
            if( fromEdt != m_dblAdc3_2_B) {
                m_dblAdc3_2_B = fromEdt; edtAdc3_2_B.setText( String.format( "%.3f", m_dblAdc3_2_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_2_B.setText( String.format( "%.3f", m_dblAdc3_2_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_3_A.getText());
            if( fromEdt != m_dblAdc3_3_A) {
                m_dblAdc3_3_A = fromEdt; edtAdc3_3_A.setText( String.format( "%.3f", m_dblAdc3_3_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_3_A.setText( String.format( "%.3f", m_dblAdc3_3_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_3_B.getText());
            if( fromEdt != m_dblAdc3_3_B) {
                m_dblAdc3_3_B = fromEdt; edtAdc3_3_B.setText( String.format( "%.3f", m_dblAdc3_3_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_3_B.setText( String.format( "%.3f", m_dblAdc3_3_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_4_A.getText());
            if( fromEdt != m_dblAdc3_4_A) {
                m_dblAdc3_4_A = fromEdt; edtAdc3_4_A.setText( String.format( "%.3f", m_dblAdc3_4_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_4_A.setText( String.format( "%.3f", m_dblAdc3_4_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_4_B.getText());
            if( fromEdt != m_dblAdc3_4_B) {
                m_dblAdc3_4_B = fromEdt; edtAdc3_4_B.setText( String.format( "%.3f", m_dblAdc3_4_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_4_B.setText( String.format( "%.3f", m_dblAdc3_4_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_5_A.getText());
            if( fromEdt != m_dblAdc3_5_A) {
                m_dblAdc3_5_A = fromEdt; edtAdc3_5_A.setText( String.format( "%.3f", m_dblAdc3_5_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_5_A.setText( String.format( "%.3f", m_dblAdc3_5_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_5_B.getText());
            if( fromEdt != m_dblAdc3_5_B) {
                m_dblAdc3_5_B = fromEdt; edtAdc3_5_B.setText( String.format( "%.3f", m_dblAdc3_5_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_5_B.setText( String.format( "%.3f", m_dblAdc3_5_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_6_A.getText());
            if( fromEdt != m_dblAdc3_6_A) {
                m_dblAdc3_6_A = fromEdt; edtAdc3_6_A.setText( String.format( "%.3f", m_dblAdc3_6_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_6_A.setText( String.format( "%.3f", m_dblAdc3_6_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_6_B.getText());
            if( fromEdt != m_dblAdc3_6_B) {
                m_dblAdc3_6_B = fromEdt; edtAdc3_6_B.setText( String.format( "%.3f", m_dblAdc3_6_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_6_B.setText( String.format( "%.3f", m_dblAdc3_6_B));
        }
        
        try {
            fromEdt = new Double( edtAdc3_7_A.getText());
            if( fromEdt != m_dblAdc3_7_A) {
                m_dblAdc3_7_A = fromEdt; edtAdc3_7_A.setText( String.format( "%.3f", m_dblAdc3_7_A)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_7_A.setText( String.format( "%.3f", m_dblAdc3_7_A));
        }
        
        try {
            fromEdt = new Double( edtAdc3_7_B.getText());
            if( fromEdt != m_dblAdc3_7_B) {
                m_dblAdc3_7_B = fromEdt; edtAdc3_7_B.setText( String.format( "%.3f", m_dblAdc3_7_B)); bChanged = true;
            }
        }
        catch( java.lang.NumberFormatException ex) {
            edtAdc3_7_B.setText( String.format( "%.3f", m_dblAdc3_7_B));
        }
        
        return bChanged;
    }
    
    private void EdtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EdtFocusLost
        boolean bChanged = false;
        
        if( ApplyEdtAdc2s()) bChanged = true;
        if( ApplyEdtAdc1s()) bChanged = true;
        if( ApplyEdtAdc4s()) bChanged = true;
        if( ApplyEdtAdc3s()) bChanged = true;
        
        if( !btnSave.isEnabled()) {
            if( bChanged)
                btnSave.setEnabled( true);
        }
    }//GEN-LAST:event_EdtFocusLost

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        /*
        if( btnSave.isEnabled()) {
            
        }
        */
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        SaveData();
        theApp.GetCalibration().SaveCalibration();
        btnSave.setEnabled( false);
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(DlgCalibration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgCalibration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgCalibration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgCalibration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgCalibration dialog = new DlgCalibration(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField edtAdc1_0_A;
    private javax.swing.JTextField edtAdc1_0_B;
    private javax.swing.JTextField edtAdc1_1_A;
    private javax.swing.JTextField edtAdc1_1_B;
    private javax.swing.JTextField edtAdc1_2_A;
    private javax.swing.JTextField edtAdc1_2_B;
    private javax.swing.JTextField edtAdc1_3_A;
    private javax.swing.JTextField edtAdc1_3_B;
    private javax.swing.JTextField edtAdc1_4_A;
    private javax.swing.JTextField edtAdc1_4_B;
    private javax.swing.JTextField edtAdc1_5_A;
    private javax.swing.JTextField edtAdc1_5_B;
    private javax.swing.JTextField edtAdc1_6_A;
    private javax.swing.JTextField edtAdc1_6_B;
    private javax.swing.JTextField edtAdc1_7_A;
    private javax.swing.JTextField edtAdc1_7_B;
    private javax.swing.JTextField edtAdc2_0_A;
    private javax.swing.JTextField edtAdc2_0_B;
    private javax.swing.JTextField edtAdc2_1_A;
    private javax.swing.JTextField edtAdc2_1_B;
    private javax.swing.JTextField edtAdc2_2_A;
    private javax.swing.JTextField edtAdc2_2_B;
    private javax.swing.JTextField edtAdc2_3_A;
    private javax.swing.JTextField edtAdc2_3_B;
    private javax.swing.JTextField edtAdc2_4_A;
    private javax.swing.JTextField edtAdc2_4_B;
    private javax.swing.JTextField edtAdc2_5_A;
    private javax.swing.JTextField edtAdc2_5_B;
    private javax.swing.JTextField edtAdc2_6_A;
    private javax.swing.JTextField edtAdc2_6_B;
    private javax.swing.JTextField edtAdc2_7_A;
    private javax.swing.JTextField edtAdc2_7_B;
    private javax.swing.JTextField edtAdc3_0_A;
    private javax.swing.JTextField edtAdc3_0_B;
    private javax.swing.JTextField edtAdc3_1_A;
    private javax.swing.JTextField edtAdc3_1_B;
    private javax.swing.JTextField edtAdc3_2_A;
    private javax.swing.JTextField edtAdc3_2_B;
    private javax.swing.JTextField edtAdc3_3_A;
    private javax.swing.JTextField edtAdc3_3_B;
    private javax.swing.JTextField edtAdc3_4_A;
    private javax.swing.JTextField edtAdc3_4_B;
    private javax.swing.JTextField edtAdc3_5_A;
    private javax.swing.JTextField edtAdc3_5_B;
    private javax.swing.JTextField edtAdc3_6_A;
    private javax.swing.JTextField edtAdc3_6_B;
    private javax.swing.JTextField edtAdc3_7_A;
    private javax.swing.JTextField edtAdc3_7_B;
    private javax.swing.JTextField edtAdc4_0_A;
    private javax.swing.JTextField edtAdc4_0_B;
    private javax.swing.JTextField edtAdc4_1_A;
    private javax.swing.JTextField edtAdc4_1_B;
    private javax.swing.JTextField edtAdc4_2_A;
    private javax.swing.JTextField edtAdc4_2_B;
    private javax.swing.JTextField edtAdc4_3_A;
    private javax.swing.JTextField edtAdc4_3_B;
    private javax.swing.JTextField edtAdc4_4_A;
    private javax.swing.JTextField edtAdc4_4_B;
    private javax.swing.JTextField edtAdc4_5_A;
    private javax.swing.JTextField edtAdc4_5_B;
    private javax.swing.JTextField edtAdc4_6_A;
    private javax.swing.JTextField edtAdc4_6_B;
    private javax.swing.JTextField edtAdc4_7_A;
    private javax.swing.JTextField edtAdc4_7_B;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
