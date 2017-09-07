/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import ams.devices.Adam4017plus;
import ams.devices.Adam4024;
import ams.devices.Adam4068;
import ams.serial.TwoWaySerialComm;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;

/**
 *
 * @author yaroslav
 */
public class MainFrame3 extends javax.swing.JFrame {
    AMSApp theApp;
    static Logger logger = Logger.getLogger(MainFrame3.class);
    
    public JLabel GetLblDev1a() { return lblDev1_anode; }
    public JLabel GetLblDev1t() { return lblDev1_tubu; }
    public JLabel GetLblDev2a() { return lblDev2_anode; }
    public JLabel GetLblDev2t() { return lblDev2_tubu; }
    public JLabel GetLblDev3a() { return lblDev3_anode; }
    public JLabel GetLblDev3t() { return lblDev3_tubu; }
    public JLabel GetLblDev4a() { return lblDev4_anode; }
    public JLabel GetLblDev4t() { return lblDev4_tubu; }
    public JLabel GetLblDev5a() { return lblDev5_anode; }
    public JLabel GetLblDev5t() { return lblDev5_tubu; }
    public JLabel GetLblDev6a() { return lblDev6_anode; }
    public JLabel GetLblDev6t() { return lblDev6_tubu; }
    public JLabel GetLblDev7a() { return lblDev7_anode; }
    public JLabel GetLblDev7t() { return lblDev7_tubu; }
    public JLabel GetLblDev8a() { return lblDev8_anode; }
    public JLabel GetLblDev8t() { return lblDev8_tubu; }
    
    private final SpinnerThread spinning;
    private final SpinFasterThread spinnerAccelerator;
    
    //private JDialog dlgAdditional;    
    //private JFrame frmAdditionalUAC;
    
    private final PnlHandMode pnlRegimeManual;
    private final PnlUIC pnlRegimeUIC;
    private final PnlProcessing pnlRegimeProcessing;
    private final PnlEdge pnlRegimeEdge;
    
    Timer tmrRefreshIOThreadsState;
    
    
    
    /**
     * Creates new form MainFrame2
     * @param app - ссылка на приложение
     * 
     */
    public MainFrame3( final AMSApp app) {
        theApp = app;
        
        initComponents();
        
        spinning = new SpinnerThread( theApp, 1, this);
        spinnerAccelerator = new SpinFasterThread( spinning);
        
        pnlRegimeManual = new PnlHandMode( app);
        pnlRegimeManual.setVisible( true);
        pnlRegimeManual.startRefreshTimer();
        pnlAdditionalPanel.add(pnlRegimeManual);
        pnlRegimeManual.setBounds( 2, 2, 376, 936);
        
        pnlRegimeProcessing = new PnlProcessing(app);
        pnlRegimeProcessing.setVisible( false);
        pnlRegimeProcessing.startRefreshTimer();
        pnlAdditionalPanel.add( pnlRegimeProcessing);
        pnlRegimeProcessing.setBounds( 2, 2, 376, 936);
        
        pnlRegimeUIC = new PnlUIC(app);
        pnlRegimeUIC.setVisible( false);
        pnlAdditionalPanel.add( pnlRegimeUIC);
        pnlRegimeUIC.setBounds( 2, 2, 376, 936);
        
        pnlRegimeEdge = new PnlEdge(app);
        pnlRegimeEdge.setVisible( false);
        pnlAdditionalPanel.add( pnlRegimeEdge);
        pnlRegimeEdge.setBounds( 2, 2, 376, 936);
        
        tmrRefreshIOThreadsState = new Timer( 1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TwoWaySerialComm conn = app.GetRxTx();
                if( conn != null) {
                    if( app.GetRxTx().IsInputThreadAlive())
                        lblThreadRState.setIcon( app.GetResources().getLittleBrightGreenLed());
                    else
                        lblThreadRState.setIcon( app.GetResources().getLittleBrightRedLed());
                
                    if( app.GetRxTx().IsOutputThreadAlive())
                        lblThreadWState.setIcon( app.GetResources().getLittleBrightGreenLed());
                    else
                        lblThreadWState.setIcon( app.GetResources().getLittleBrightRedLed());
                    
                    
                }
                else {
                    lblThreadRState.setIcon( app.GetResources().getLittleBrightRedLed());
                    lblThreadWState.setIcon( app.GetResources().getLittleBrightRedLed());
                }
                
                if( app.GetPollerAdapter().GetAliveStatus() == true) {
                    lblThreadPoller.setIcon( app.GetResources().getLittleBrightGreenLed());
                    //TODO: blue state (connection established, but inactive)
                }
                else
                    lblThreadPoller.setIcon( app.GetResources().getLittleBrightRedLed());
                
                if( app.GetExecutorAdapter().GetAliveStatus() == true) {
                    lblThreadExecutor.setIcon( app.GetResources().getLittleBrightGreenLed());
                    //TODO: blue state (connection established, but inactive)
                }
                else
                    lblThreadExecutor.setIcon( app.GetResources().getLittleBrightRedLed());
            }
        });
        tmrRefreshIOThreadsState.start();
        //***** ***** ***** ***** ***** ***** ***** ***** ***** *****
        
        Adam4068 relay;
        Adam4017plus adc;
        Adam4024 dac;
        
        //TO_REMOVE
        theApp.GetDevManager_0_a().SetActiveLed( lblDev1_anode);
        theApp.GetDevManager_0_t().SetActiveLed( lblDev1_tubu);
        
        HashMap mapTestedDevicesAnodeVoltages = new HashMap(8);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE1, edtDev1_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE2, edtDev2_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE3, edtDev3_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE4, edtDev4_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE5, edtDev5_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE6, edtDev6_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE7, edtDev7_Ano_Voltage);
        mapTestedDevicesAnodeVoltages.put(AMSConstants.T_DEVICE8, edtDev8_Ano_Voltage);
        
        HashMap mapTestedDevicesAnodeCurrents = new HashMap(8);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE1, edtDev1_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE2, edtDev2_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE3, edtDev3_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE4, edtDev4_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE5, edtDev5_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE6, edtDev6_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE7, edtDev7_Ano_Current);
        mapTestedDevicesAnodeCurrents.put(AMSConstants.T_DEVICE8, edtDev8_Ano_Current);
        
        HashMap mapTestedDevicesAnodeRelays = new HashMap(8);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE1, lbl_Dev1_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE2, lbl_Dev2_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE3, lbl_Dev3_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE4, lbl_Dev4_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE5, lbl_Dev5_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE6, lbl_Dev6_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE7, lbl_Dev7_Anode_RelState);
        mapTestedDevicesAnodeRelays.put(AMSConstants.T_DEVICE8, lbl_Dev8_Anode_RelState);
        
        HashMap mapTestedDevicesTubulationVoltages = new HashMap(8);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE1, edtDev1_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE2, edtDev2_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE3, edtDev3_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE4, edtDev4_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE5, edtDev5_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE6, edtDev6_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE7, edtDev7_Tub_Voltage);
        mapTestedDevicesTubulationVoltages.put(AMSConstants.T_DEVICE8, edtDev8_Tub_Voltage);
        
        HashMap mapTestedDevicesTubulationCurrents = new HashMap(8);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE1, edtDev1_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE2, edtDev2_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE3, edtDev3_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE4, edtDev4_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE5, edtDev5_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE6, edtDev6_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE7, edtDev7_Tub_Current);
        mapTestedDevicesTubulationCurrents.put(AMSConstants.T_DEVICE8, edtDev8_Tub_Current);
        
        HashMap mapTestedDevicesTubulationRelays = new HashMap(8);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE1, lbl_Dev1_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE2, lbl_Dev2_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE3, lbl_Dev3_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE4, lbl_Dev4_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE5, lbl_Dev5_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE6, lbl_Dev6_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE7, lbl_Dev7_Tubu_RelState);
        mapTestedDevicesTubulationRelays.put(AMSConstants.T_DEVICE8, lbl_Dev8_Tubu_RelState);
        
        Iterator it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();
            AMSSettingsTDev devSett;
            try {
                devSett = theApp.GetSettings().GetDev( nDevice);
            }
            catch( Exception e) {
                logger.error( "При получении настроек испытуемого устройства, произошла исключительная ситуация!", e);
                return;
            }
            
            //ANODE VOLTAGE
            int device = devSett.GetAnoAdcVoltDev();
            int channel = devSett.GetAnoAdcVoltChan();
            try {
                theApp.m_devicesSet.GetADC( device).AddVisualComponent( channel, ( JTextField) mapTestedDevicesAnodeVoltages.get( nDevice));
                theApp.m_devicesSet.GetADC( device).SetVisualizationFormat( "%.0f");
            }
            catch( Exception e) {
                logger.error( "При получении объекта АЦП анодного напряжения испытуемого устройства, произошла исключительная ситуация!", e);
                return; 
            }
            
            //ANODE CURRENT
            device = devSett.GetAnoAdcCurrDev();
            channel = devSett.GetAnoAdcCurrChan();
            try {
                theApp.m_devicesSet.GetADC( device).AddVisualComponent( channel, ( JTextField) mapTestedDevicesAnodeCurrents.get( nDevice));
                theApp.m_devicesSet.GetADC( device).SetVisualizationFormat( "%.0f");
            }
            catch( Exception e) {
                logger.error( "При получении объекта АЦП анодного тока испытуемого устройства, произошла исключительная ситуация!", e);
                return; 
            }
            
            //ANODE RELAY
            device = devSett.GetAnoRelDev();
            channel = devSett.GetAnoRelChan();
            try {
                theApp.m_devicesSet.GetRelay( device).AddVisualComponent( channel, ( JLabel) mapTestedDevicesAnodeRelays.get( nDevice));
            }
            catch( Exception e) {
                logger.error( "При получении объекта реле анода испытуемого устройства, произошла исключительная ситуация!", e);
                return; 
            }
            
            //TUBU VOLTAGE
            device = devSett.GetTubAdcVoltDev();
            channel = devSett.GetTubAdcVoltChan();
            try {
                theApp.m_devicesSet.GetADC( device).AddVisualComponent( channel, ( JTextField) mapTestedDevicesTubulationVoltages.get( nDevice));
            }
            catch( Exception e) {
                logger.error( "При получении объекта АЦП штенгельного напряжения испытуемого устройства, произошла исключительная ситуация!", e);
                return; 
            }
            
            //TUBU CURRENT
            device = devSett.GetTubAdcCurrDev();
            channel = devSett.GetTubAdcCurrChan();
            try {
                theApp.m_devicesSet.GetADC( device).AddVisualComponent( channel, ( JTextField) mapTestedDevicesTubulationCurrents.get( nDevice));
            }
            catch( Exception e) {
                logger.error( "При получении объекта АЦП штенгельного тока испытуемого устройства, произошла исключительная ситуация!", e);
                return; 
            }
            
            //TUBU RELAY
            device = devSett.GetTubRelDev();
            channel = devSett.GetTubRelChan();
            try {
                theApp.m_devicesSet.GetRelay( device).AddVisualComponent( channel, ( JLabel) mapTestedDevicesTubulationRelays.get( nDevice));
            }
            catch( Exception e) {
                logger.error( "При получении объекта реле штенгеля испытуемого устройства, произошла исключительная ситуация!", e);
                return; 
            }
        }
                    

        //сигнальная лампочка вибрации
        try {
            Adam4068 dev = theApp.GetDevicesSet().GetRelay( AMSConstants.REL3);
            dev.AddVisualComponent( AMSConstants.CHANNEL1, lblFan);
            dev.AddVisualComponent( AMSConstants.CHANNEL5, lblVibration);
            
        }
        catch( Exception ex) {
            logger.error( "При инициализации модуля реле3 произошла исключительная ситуация", ex);
        }
        //***** ***** ***** ***** ***** ***** ***** ***** ***** *****
        //Сигнальные лампочки для менеджеров
        theApp.GetDevManager_0_a().SetActiveLed( lblDev1_anode);
        theApp.GetDevManager_0_t().SetActiveLed( lblDev1_tubu);
        theApp.GetDevManager_1_a().SetActiveLed( lblDev2_anode);
        theApp.GetDevManager_1_t().SetActiveLed( lblDev2_tubu);
        theApp.GetDevManager_2_a().SetActiveLed( lblDev3_anode);
        theApp.GetDevManager_2_t().SetActiveLed( lblDev3_tubu);
        theApp.GetDevManager_3_a().SetActiveLed( lblDev4_anode);
        theApp.GetDevManager_3_t().SetActiveLed( lblDev4_tubu);
        theApp.GetDevManager_4_a().SetActiveLed( lblDev5_anode);
        theApp.GetDevManager_4_t().SetActiveLed( lblDev5_tubu);
        theApp.GetDevManager_5_a().SetActiveLed( lblDev6_anode);
        theApp.GetDevManager_5_t().SetActiveLed( lblDev6_tubu);
        theApp.GetDevManager_6_a().SetActiveLed( lblDev7_anode);
        theApp.GetDevManager_6_t().SetActiveLed( lblDev7_tubu);
        theApp.GetDevManager_7_a().SetActiveLed( lblDev8_anode);
        theApp.GetDevManager_7_t().SetActiveLed( lblDev8_tubu);
        
        SetHyroNumbers();
        tmrRefreshIOThreadsState = new Timer( 1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if( theApp.GetRxTx() != null) {
                    
                    if( theApp.GetRxTx().IsInputThreadAlive())
                        lblThreadRState.setIcon( theApp.GetResources().getLittleBrightGreenLed());
                    else
                        lblThreadRState.setIcon( theApp.GetResources().getLittleBrightRedLed());
                    
                    if( theApp.GetRxTx().IsOutputThreadAlive())
                        lblThreadWState.setIcon( theApp.GetResources().getLittleBrightGreenLed());
                    else
                        lblThreadWState.setIcon( theApp.GetResources().getLittleBrightRedLed());
                        
                    if( !theApp.GetRxTx().IsActive()) {
                        edtDev1_Ano_Current.setBackground( new Color( 190, 110, 110));  //bgRed
                        edtDev2_Ano_Current.setBackground( new Color( 190, 110, 110));
                        edtDev3_Ano_Current.setBackground( new Color( 190, 110, 110));
                        edtDev4_Ano_Current.setBackground( new Color( 190, 110, 110));
                        edtDev5_Ano_Current.setBackground( new Color( 190, 110, 110));
                        edtDev6_Ano_Current.setBackground( new Color( 190, 110, 110));
                        edtDev7_Ano_Current.setBackground( new Color( 190, 110, 110));
                        edtDev8_Ano_Current.setBackground( new Color( 190, 110, 110));
                    
                        edtDev1_Ano_Voltage.setBackground( new Color( 190, 110, 110));  //bgRed
                        edtDev2_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev3_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev4_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev5_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev6_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev7_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev8_Ano_Voltage.setBackground( new Color( 190, 110, 110));
                    
                        edtDev1_Tub_Current.setBackground( new Color( 190, 110, 110));  //bgRed
                        edtDev2_Tub_Current.setBackground( new Color( 190, 110, 110));
                        edtDev3_Tub_Current.setBackground( new Color( 190, 110, 110));
                        edtDev4_Tub_Current.setBackground( new Color( 190, 110, 110));
                        edtDev5_Tub_Current.setBackground( new Color( 190, 110, 110));
                        edtDev6_Tub_Current.setBackground( new Color( 190, 110, 110));
                        edtDev7_Tub_Current.setBackground( new Color( 190, 110, 110));
                        edtDev8_Tub_Current.setBackground( new Color( 190, 110, 110));
                    
                        edtDev1_Tub_Voltage.setBackground( new Color( 190, 110, 110));  //bgRed
                        edtDev2_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev3_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev4_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev5_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev6_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev7_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                        edtDev8_Tub_Voltage.setBackground( new Color( 190, 110, 110));
                    }
                    else {
                        edtDev1_Ano_Current.setBackground( new Color( 237, 236, 235));  //bgWhite
                        edtDev2_Ano_Current.setBackground( new Color( 237, 236, 235));
                        edtDev3_Ano_Current.setBackground( new Color( 237, 236, 235));
                        edtDev4_Ano_Current.setBackground( new Color( 237, 236, 235));
                        edtDev5_Ano_Current.setBackground( new Color( 237, 236, 235));
                        edtDev6_Ano_Current.setBackground( new Color( 237, 236, 235));
                        edtDev7_Ano_Current.setBackground( new Color( 237, 236, 235));
                        edtDev8_Ano_Current.setBackground( new Color( 237, 236, 235));
                        
                        edtDev1_Ano_Voltage.setBackground( new Color( 237, 236, 235));  //bgWhite
                        edtDev2_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev3_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev4_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev5_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev6_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev7_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev8_Ano_Voltage.setBackground( new Color( 237, 236, 235));
                        
                        edtDev1_Tub_Current.setBackground( new Color( 237, 236, 235));  //bgWhite
                        edtDev2_Tub_Current.setBackground( new Color( 237, 236, 235));
                        edtDev3_Tub_Current.setBackground( new Color( 237, 236, 235));
                        edtDev4_Tub_Current.setBackground( new Color( 237, 236, 235));
                        edtDev5_Tub_Current.setBackground( new Color( 237, 236, 235));
                        edtDev6_Tub_Current.setBackground( new Color( 237, 236, 235));
                        edtDev7_Tub_Current.setBackground( new Color( 237, 236, 235));
                        edtDev8_Tub_Current.setBackground( new Color( 237, 236, 235));
                        
                        edtDev1_Tub_Voltage.setBackground( new Color( 237, 236, 235));  //bgWhite
                        edtDev2_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev3_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev4_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev5_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev6_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev7_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                        edtDev8_Tub_Voltage.setBackground( new Color( 237, 236, 235));
                    }
                    
                }
                tmrRefreshIOThreadsState.restart();
                
            }
        });
        tmrRefreshIOThreadsState.start();
        
        btnSetDevSerNums.setVisible( false);
        edtHyroNum1.setVisible( false);
        edtHyroNum2.setVisible( false);
        edtHyroNum3.setVisible( false);
        edtHyroNum4.setVisible( false);
        edtHyroNum5.setVisible( false);
        edtHyroNum6.setVisible( false);
        edtHyroNum7.setVisible( false);
        edtHyroNum8.setVisible( false);
        
        
        btnLogTrace.setSelected( logger.getLevel() == Level.TRACE);
        btnLogDebug.setSelected( logger.getLevel() == Level.DEBUG);
        btnLogInfo.setSelected(  logger.getLevel() == Level.INFO);
        btnLogWarn.setSelected(  logger.getLevel() == Level.WARN);
        btnLogError.setSelected( logger.getLevel() == Level.ERROR);
        btnLogFatal.setSelected( logger.getLevel() == Level.FATAL);
    }

    /**
     * Установить номера гироскопов
     */
    public void SetHyroNumbers() {
        edtHyroNum1.setText( theApp.GetDevSerNums().GetDev1SerialNumber());
        edtHyroNum2.setText( theApp.GetDevSerNums().GetDev2SerialNumber());
        edtHyroNum3.setText( theApp.GetDevSerNums().GetDev3SerialNumber());
        edtHyroNum4.setText( theApp.GetDevSerNums().GetDev4SerialNumber());
        edtHyroNum5.setText( theApp.GetDevSerNums().GetDev5SerialNumber());
        edtHyroNum6.setText( theApp.GetDevSerNums().GetDev6SerialNumber());
        edtHyroNum7.setText( theApp.GetDevSerNums().GetDev7SerialNumber());
        edtHyroNum8.setText( theApp.GetDevSerNums().GetDev8SerialNumber());
    }
    
    /**
     * Выставка слайдера и окошка-значения тока уставки
     */
    public void refreshControlsState() {
        sldOuterCurrent.setValue( theApp.GetOuterCurrent());
        edtOuterCurrent.setText( "" + theApp.GetOuterCurrent());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupLogLevel = new javax.swing.ButtonGroup();
        btnRegimeManual = new javax.swing.JButton();
        btnRegimeUIC = new javax.swing.JButton();
        btnRegimeEdge = new javax.swing.JButton();
        btnRegimeProcessing = new javax.swing.JButton();
        sldOuterCurrent = new javax.swing.JSlider();
        edtOuterCurrent = new javax.swing.JTextField();
        btnOuterCurrentSpinUp = new javax.swing.JButton();
        btnOuterCurrentSpinDown = new javax.swing.JButton();
        btn05mA = new javax.swing.JButton();
        btn10mA = new javax.swing.JButton();
        btn15mA = new javax.swing.JButton();
        btn20mA = new javax.swing.JButton();
        btn25mA = new javax.swing.JButton();
        btn30mA = new javax.swing.JButton();
        btnSwitchOff = new javax.swing.JButton();
        btnSwitchOn = new javax.swing.JButton();
        edtHyroNum1 = new javax.swing.JTextField();
        edtHyroNum2 = new javax.swing.JTextField();
        edtHyroNum3 = new javax.swing.JTextField();
        edtHyroNum4 = new javax.swing.JTextField();
        edtHyroNum5 = new javax.swing.JTextField();
        edtHyroNum6 = new javax.swing.JTextField();
        edtHyroNum8 = new javax.swing.JTextField();
        edtHyroNum7 = new javax.swing.JTextField();
        lblManagementTitle = new javax.swing.JLabel();
        lblDev1_anode = new javax.swing.JLabel();
        btnDev1_Anode_Off = new javax.swing.JButton();
        lbl_Dev1_Anode_RelState = new javax.swing.JLabel();
        btnDev1_Anode_On = new javax.swing.JButton();
        lblDev2_anode = new javax.swing.JLabel();
        btnDev2_Anode_Off = new javax.swing.JButton();
        lbl_Dev2_Anode_RelState = new javax.swing.JLabel();
        btnDev2_Anode_On = new javax.swing.JButton();
        lblDev3_anode = new javax.swing.JLabel();
        btnDev3_Anode_Off = new javax.swing.JButton();
        lbl_Dev3_Anode_RelState = new javax.swing.JLabel();
        btnDev3_Anode_On = new javax.swing.JButton();
        lblDev4_anode = new javax.swing.JLabel();
        btnDev4_Anode_Off = new javax.swing.JButton();
        lbl_Dev4_Anode_RelState = new javax.swing.JLabel();
        btnDev4_Anode_On = new javax.swing.JButton();
        lblDev5_anode = new javax.swing.JLabel();
        btnDev5_Anode_Off = new javax.swing.JButton();
        lbl_Dev5_Anode_RelState = new javax.swing.JLabel();
        btnDev5_Anode_On = new javax.swing.JButton();
        lblDev6_anode = new javax.swing.JLabel();
        btnDev6_Anode_Off = new javax.swing.JButton();
        lbl_Dev6_Anode_RelState = new javax.swing.JLabel();
        btnDev6_Anode_On = new javax.swing.JButton();
        lblDev7_anode = new javax.swing.JLabel();
        btnDev7_Anode_Off = new javax.swing.JButton();
        lbl_Dev7_Anode_RelState = new javax.swing.JLabel();
        btnDev7_Anode_On = new javax.swing.JButton();
        lblDev8_anode = new javax.swing.JLabel();
        btnDev8_Anode_Off = new javax.swing.JButton();
        lbl_Dev8_Anode_RelState = new javax.swing.JLabel();
        btnDev8_Anode_On = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        edtDev1_Ano_Current = new javax.swing.JTextField();
        edtDev2_Ano_Current = new javax.swing.JTextField();
        edtDev3_Ano_Current = new javax.swing.JTextField();
        edtDev4_Ano_Current = new javax.swing.JTextField();
        edtDev5_Ano_Current = new javax.swing.JTextField();
        edtDev6_Ano_Current = new javax.swing.JTextField();
        edtDev8_Ano_Current = new javax.swing.JTextField();
        edtDev7_Ano_Current = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        edtDev1_Ano_Voltage = new javax.swing.JTextField();
        edtDev2_Ano_Voltage = new javax.swing.JTextField();
        edtDev3_Ano_Voltage = new javax.swing.JTextField();
        edtDev4_Ano_Voltage = new javax.swing.JTextField();
        edtDev5_Ano_Voltage = new javax.swing.JTextField();
        edtDev6_Ano_Voltage = new javax.swing.JTextField();
        edtDev8_Ano_Voltage = new javax.swing.JTextField();
        edtDev7_Ano_Voltage = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblThreadPoller = new javax.swing.JLabel();
        btnVibroOff = new javax.swing.JButton();
        lbl_Dev1_Tubu_RelState = new javax.swing.JLabel();
        btnVibroOn = new javax.swing.JButton();
        lblDev2_tubu = new javax.swing.JLabel();
        btnDev2_Tubu_Off = new javax.swing.JButton();
        lbl_Dev2_Tubu_RelState = new javax.swing.JLabel();
        btnDev2_Tubu_On = new javax.swing.JButton();
        lblDev3_tubu = new javax.swing.JLabel();
        btnDev3_Tubu_Off = new javax.swing.JButton();
        lbl_Dev3_Tubu_RelState = new javax.swing.JLabel();
        btnDev3_Tubu_On = new javax.swing.JButton();
        lblDev4_tubu = new javax.swing.JLabel();
        btnDev4_Tubu_Off = new javax.swing.JButton();
        lbl_Dev4_Tubu_RelState = new javax.swing.JLabel();
        btnDev4_Tubu_On = new javax.swing.JButton();
        lblDev5_tubu = new javax.swing.JLabel();
        btnDev5_Tubu_Off = new javax.swing.JButton();
        lbl_Dev5_Tubu_RelState = new javax.swing.JLabel();
        btnDev5_Tubu_On = new javax.swing.JButton();
        lblDev6_tubu = new javax.swing.JLabel();
        btnDev6_Tubu_Off = new javax.swing.JButton();
        lbl_Dev6_Tubu_RelState = new javax.swing.JLabel();
        btnDev6_Tubu_On = new javax.swing.JButton();
        lblDev7_tubu = new javax.swing.JLabel();
        btnDev7_Tubu_Off = new javax.swing.JButton();
        lbl_Dev7_Tubu_RelState = new javax.swing.JLabel();
        btnDev7_Tubu_On = new javax.swing.JButton();
        lblDev8_tubu = new javax.swing.JLabel();
        btnDev8_Tubu_Off = new javax.swing.JButton();
        lbl_Dev8_Tubu_RelState = new javax.swing.JLabel();
        btnDev8_Tubu_On = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        edtDev1_Tub_Current = new javax.swing.JTextField();
        edtDev2_Tub_Current = new javax.swing.JTextField();
        edtDev3_Tub_Current = new javax.swing.JTextField();
        edtDev4_Tub_Current = new javax.swing.JTextField();
        edtDev5_Tub_Current = new javax.swing.JTextField();
        edtDev6_Tub_Current = new javax.swing.JTextField();
        edtDev8_Tub_Current = new javax.swing.JTextField();
        edtDev7_Tub_Current = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        edtDev1_Tub_Voltage = new javax.swing.JTextField();
        edtDev2_Tub_Voltage = new javax.swing.JTextField();
        edtDev3_Tub_Voltage = new javax.swing.JTextField();
        edtDev4_Tub_Voltage = new javax.swing.JTextField();
        edtDev5_Tub_Voltage = new javax.swing.JTextField();
        edtDev6_Tub_Voltage = new javax.swing.JTextField();
        edtDev8_Tub_Voltage = new javax.swing.JTextField();
        edtDev7_Tub_Voltage = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lblVibration = new javax.swing.JLabel();
        btnDev1_Tubu_On = new javax.swing.JButton();
        btnDev1_Tubu_Off = new javax.swing.JButton();
        lblMainSwitch = new javax.swing.JLabel();
        btnToggleBlockScreen = new javax.swing.JToggleButton();
        btnSettings = new javax.swing.JButton();
        btnAllTubusOn = new javax.swing.JButton();
        btnAllTubusOff = new javax.swing.JButton();
        btnAllAnodesOn = new javax.swing.JButton();
        btnAllAnodesOff = new javax.swing.JButton();
        pnlAdditionalPanel = new javax.swing.JPanel();
        btnSetDevSerNums = new javax.swing.JButton();
        btnCalibration = new javax.swing.JButton();
        lblDev1_tubu = new javax.swing.JLabel();
        lblThreadRState = new javax.swing.JLabel();
        lblThreadWState = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        btnFanOn = new javax.swing.JButton();
        lblFan = new javax.swing.JLabel();
        btnFanOff = new javax.swing.JButton();
        lblThreadExecutor = new javax.swing.JLabel();
        btn04mA = new javax.swing.JButton();
        btnLogDebug = new javax.swing.JToggleButton();
        btnLogTrace = new javax.swing.JToggleButton();
        btnLogWarn = new javax.swing.JToggleButton();
        btnLogInfo = new javax.swing.JToggleButton();
        btnLogFatal = new javax.swing.JToggleButton();
        btnLogError = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Система управления и поддержания разряда кольцевого лазерного резонатора.   1.00 (2016.07.19 12:58)   НПО Алькор-Лаборатории 2016");
        setMinimumSize(new java.awt.Dimension(1630, 1020));
        setResizable(false);
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        getContentPane().setLayout(null);

        btnRegimeManual.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnRegimeManual.setText("РУЧНОЙ РЕЖИМ");
        btnRegimeManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegimeManualActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegimeManual);
        btnRegimeManual.setBounds(10, 10, 210, 80);

        btnRegimeUIC.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        btnRegimeUIC.setText("ИЗМЕРЕНИЕ ВАХ");
        btnRegimeUIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegimeUICActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegimeUIC);
        btnRegimeUIC.setBounds(10, 90, 210, 80);

        btnRegimeEdge.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        btnRegimeEdge.setText("<html><center>ИЗМЕРЕНИЕ<br>ПОРОГОВОГО<br>ТОКА</center></html>");
        btnRegimeEdge.setToolTipText("");
        btnRegimeEdge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegimeEdgeActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegimeEdge);
        btnRegimeEdge.setBounds(10, 250, 210, 80);

        btnRegimeProcessing.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        btnRegimeProcessing.setText("ОБРАБОТКА");
        btnRegimeProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegimeProcessingActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegimeProcessing);
        btnRegimeProcessing.setBounds(10, 170, 210, 80);

        sldOuterCurrent.setMajorTickSpacing(500);
        sldOuterCurrent.setMaximum(3100);
        sldOuterCurrent.setMinorTickSpacing(100);
        sldOuterCurrent.setPaintLabels(true);
        sldOuterCurrent.setPaintTicks(true);
        sldOuterCurrent.setValue(1000);
        sldOuterCurrent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sldOuterCurrentMouseReleased(evt);
            }
        });
        sldOuterCurrent.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sldOuterCurrentMouseDragged(evt);
            }
        });
        getContentPane().add(sldOuterCurrent);
        sldOuterCurrent.setBounds(240, 20, 700, 70);

        edtOuterCurrent.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        edtOuterCurrent.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtOuterCurrent.setText("1000");
        edtOuterCurrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtOuterCurrentActionPerformed(evt);
            }
        });
        getContentPane().add(edtOuterCurrent);
        edtOuterCurrent.setBounds(950, 20, 180, 100);

        btnOuterCurrentSpinUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/up.gif"))); // NOI18N
        btnOuterCurrentSpinUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOuterCurrentSpinUpMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnOuterCurrentSpinUpMouseReleased(evt);
            }
        });
        getContentPane().add(btnOuterCurrentSpinUp);
        btnOuterCurrentSpinUp.setBounds(1140, 20, 60, 50);

        btnOuterCurrentSpinDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/down.gif"))); // NOI18N
        btnOuterCurrentSpinDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOuterCurrentSpinDownMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnOuterCurrentSpinDownMouseReleased(evt);
            }
        });
        getContentPane().add(btnOuterCurrentSpinDown);
        btnOuterCurrentSpinDown.setBounds(1140, 70, 60, 50);

        btn05mA.setText("500 мкА");
        btn05mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn05mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn05mA);
        btn05mA.setBounds(330, 90, 90, 28);

        btn10mA.setText("1000 мкА");
        btn10mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn10mA);
        btn10mA.setBounds(425, 90, 90, 28);

        btn15mA.setText("1500 мкА");
        btn15mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn15mA);
        btn15mA.setBounds(535, 90, 90, 28);

        btn20mA.setText("2000 мкА");
        btn20mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn20mA);
        btn20mA.setBounds(645, 90, 90, 28);

        btn25mA.setText("2500 мкА");
        btn25mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn25mA);
        btn25mA.setBounds(750, 90, 90, 28);

        btn30mA.setText("3000 мкА");
        btn30mA.setToolTipText("");
        btn30mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn30mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn30mA);
        btn30mA.setBounds(850, 90, 90, 28);

        btnSwitchOff.setBackground(new java.awt.Color(150, 220, 150));
        btnSwitchOff.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        btnSwitchOff.setText("СНЯТЬ");
        btnSwitchOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwitchOffActionPerformed(evt);
            }
        });
        getContentPane().add(btnSwitchOff);
        btnSwitchOff.setBounds(10, 450, 210, 60);

        btnSwitchOn.setBackground(new java.awt.Color(210, 110, 110));
        btnSwitchOn.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        btnSwitchOn.setText("ПОДАТЬ");
        btnSwitchOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwitchOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnSwitchOn);
        btnSwitchOn.setBounds(10, 340, 210, 60);

        edtHyroNum1.setEditable(false);
        edtHyroNum1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum1);
        edtHyroNum1.setBounds(1090, 130, 110, 30);

        edtHyroNum2.setEditable(false);
        edtHyroNum2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum2);
        edtHyroNum2.setBounds(1090, 130, 110, 30);

        edtHyroNum3.setEditable(false);
        edtHyroNum3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum3);
        edtHyroNum3.setBounds(1090, 130, 110, 30);

        edtHyroNum4.setEditable(false);
        edtHyroNum4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum4);
        edtHyroNum4.setBounds(1090, 130, 110, 30);

        edtHyroNum5.setEditable(false);
        edtHyroNum5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum5);
        edtHyroNum5.setBounds(1090, 130, 110, 30);

        edtHyroNum6.setEditable(false);
        edtHyroNum6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum6);
        edtHyroNum6.setBounds(1090, 130, 110, 30);

        edtHyroNum8.setEditable(false);
        edtHyroNum8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum8);
        edtHyroNum8.setBounds(1090, 130, 110, 30);

        edtHyroNum7.setEditable(false);
        edtHyroNum7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(edtHyroNum7);
        edtHyroNum7.setBounds(1090, 130, 110, 30);

        lblManagementTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManagementTitle.setText("Управление");
        lblManagementTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblManagementTitle);
        lblManagementTitle.setBounds(240, 660, 950, 30);

        lblDev1_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev1_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev1_anode.setText("1");
        getContentPane().add(lblDev1_anode);
        lblDev1_anode.setBounds(240, 270, 40, 30);

        btnDev1_Anode_Off.setText("ВЫКЛ");
        btnDev1_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev1_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev1_Anode_Off);
        btnDev1_Anode_Off.setBounds(240, 310, 110, 30);

        lbl_Dev1_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev1_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev1_Anode_RelState);
        lbl_Dev1_Anode_RelState.setBounds(280, 270, 70, 30);

        btnDev1_Anode_On.setText("ВКЛ");
        btnDev1_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev1_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev1_Anode_On);
        btnDev1_Anode_On.setBounds(240, 230, 110, 30);

        lblDev2_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev2_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev2_anode.setText("3");
        getContentPane().add(lblDev2_anode);
        lblDev2_anode.setBounds(360, 270, 40, 30);

        btnDev2_Anode_Off.setText("ВЫКЛ");
        btnDev2_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev2_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev2_Anode_Off);
        btnDev2_Anode_Off.setBounds(360, 310, 110, 30);

        lbl_Dev2_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev2_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev2_Anode_RelState);
        lbl_Dev2_Anode_RelState.setBounds(400, 270, 70, 30);

        btnDev2_Anode_On.setText("ВКЛ");
        btnDev2_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev2_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev2_Anode_On);
        btnDev2_Anode_On.setBounds(360, 230, 110, 30);

        lblDev3_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev3_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev3_anode.setText("5");
        getContentPane().add(lblDev3_anode);
        lblDev3_anode.setBounds(480, 270, 40, 30);

        btnDev3_Anode_Off.setText("ВЫКЛ");
        btnDev3_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev3_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev3_Anode_Off);
        btnDev3_Anode_Off.setBounds(480, 310, 110, 30);

        lbl_Dev3_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev3_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev3_Anode_RelState);
        lbl_Dev3_Anode_RelState.setBounds(520, 270, 70, 30);

        btnDev3_Anode_On.setText("ВКЛ");
        btnDev3_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev3_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev3_Anode_On);
        btnDev3_Anode_On.setBounds(480, 230, 110, 30);

        lblDev4_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev4_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev4_anode.setText("7");
        getContentPane().add(lblDev4_anode);
        lblDev4_anode.setBounds(600, 270, 40, 30);

        btnDev4_Anode_Off.setText("ВЫКЛ");
        btnDev4_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev4_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev4_Anode_Off);
        btnDev4_Anode_Off.setBounds(600, 310, 110, 30);

        lbl_Dev4_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev4_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev4_Anode_RelState);
        lbl_Dev4_Anode_RelState.setBounds(640, 270, 70, 30);

        btnDev4_Anode_On.setText("ВКЛ");
        btnDev4_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev4_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev4_Anode_On);
        btnDev4_Anode_On.setBounds(600, 230, 110, 30);

        lblDev5_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev5_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev5_anode.setText("9");
        getContentPane().add(lblDev5_anode);
        lblDev5_anode.setBounds(720, 270, 40, 30);

        btnDev5_Anode_Off.setText("ВЫКЛ");
        btnDev5_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev5_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev5_Anode_Off);
        btnDev5_Anode_Off.setBounds(720, 310, 110, 30);

        lbl_Dev5_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev5_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev5_Anode_RelState);
        lbl_Dev5_Anode_RelState.setBounds(760, 270, 70, 30);

        btnDev5_Anode_On.setText("ВКЛ");
        btnDev5_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev5_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev5_Anode_On);
        btnDev5_Anode_On.setBounds(720, 230, 110, 30);

        lblDev6_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev6_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev6_anode.setText("11");
        getContentPane().add(lblDev6_anode);
        lblDev6_anode.setBounds(840, 270, 40, 30);

        btnDev6_Anode_Off.setText("ВЫКЛ");
        btnDev6_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev6_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev6_Anode_Off);
        btnDev6_Anode_Off.setBounds(840, 310, 110, 30);

        lbl_Dev6_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev6_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev6_Anode_RelState);
        lbl_Dev6_Anode_RelState.setBounds(880, 270, 70, 30);

        btnDev6_Anode_On.setText("ВКЛ");
        btnDev6_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev6_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev6_Anode_On);
        btnDev6_Anode_On.setBounds(840, 230, 110, 30);

        lblDev7_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev7_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev7_anode.setText("13");
        getContentPane().add(lblDev7_anode);
        lblDev7_anode.setBounds(960, 270, 40, 30);

        btnDev7_Anode_Off.setText("ВЫКЛ");
        btnDev7_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev7_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev7_Anode_Off);
        btnDev7_Anode_Off.setBounds(960, 310, 110, 30);

        lbl_Dev7_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev7_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev7_Anode_RelState);
        lbl_Dev7_Anode_RelState.setBounds(1000, 270, 70, 30);

        btnDev7_Anode_On.setText("ВКЛ");
        btnDev7_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev7_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev7_Anode_On);
        btnDev7_Anode_On.setBounds(960, 230, 110, 30);

        lblDev8_anode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev8_anode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev8_anode.setText("15");
        getContentPane().add(lblDev8_anode);
        lblDev8_anode.setBounds(1080, 270, 40, 30);

        btnDev8_Anode_Off.setText("ВЫКЛ");
        btnDev8_Anode_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev8_Anode_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev8_Anode_Off);
        btnDev8_Anode_Off.setBounds(1080, 310, 110, 30);

        lbl_Dev8_Anode_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev8_Anode_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev8_Anode_RelState);
        lbl_Dev8_Anode_RelState.setBounds(1120, 270, 70, 30);

        btnDev8_Anode_On.setText("ВКЛ");
        btnDev8_Anode_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev8_Anode_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev8_Anode_On);
        btnDev8_Anode_On.setBounds(1080, 230, 110, 30);

        jLabel19.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("АНОДЫ");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(600, 150, 230, 30);

        edtDev1_Ano_Current.setEditable(false);
        edtDev1_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev1_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev1_Ano_Current.setText("-");
        getContentPane().add(edtDev1_Ano_Current);
        edtDev1_Ano_Current.setBounds(240, 380, 110, 40);

        edtDev2_Ano_Current.setEditable(false);
        edtDev2_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev2_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev2_Ano_Current.setText("-");
        edtDev2_Ano_Current.setToolTipText("");
        getContentPane().add(edtDev2_Ano_Current);
        edtDev2_Ano_Current.setBounds(360, 380, 110, 40);

        edtDev3_Ano_Current.setEditable(false);
        edtDev3_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev3_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev3_Ano_Current.setText("-");
        getContentPane().add(edtDev3_Ano_Current);
        edtDev3_Ano_Current.setBounds(480, 380, 110, 40);

        edtDev4_Ano_Current.setEditable(false);
        edtDev4_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev4_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev4_Ano_Current.setText("-");
        getContentPane().add(edtDev4_Ano_Current);
        edtDev4_Ano_Current.setBounds(600, 380, 110, 40);

        edtDev5_Ano_Current.setEditable(false);
        edtDev5_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev5_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev5_Ano_Current.setText("-");
        getContentPane().add(edtDev5_Ano_Current);
        edtDev5_Ano_Current.setBounds(720, 380, 110, 40);

        edtDev6_Ano_Current.setEditable(false);
        edtDev6_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev6_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev6_Ano_Current.setText("-");
        getContentPane().add(edtDev6_Ano_Current);
        edtDev6_Ano_Current.setBounds(840, 380, 110, 40);

        edtDev8_Ano_Current.setEditable(false);
        edtDev8_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev8_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev8_Ano_Current.setText("-");
        getContentPane().add(edtDev8_Ano_Current);
        edtDev8_Ano_Current.setBounds(1080, 380, 110, 40);

        edtDev7_Ano_Current.setEditable(false);
        edtDev7_Ano_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev7_Ano_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev7_Ano_Current.setText("-");
        getContentPane().add(edtDev7_Ano_Current);
        edtDev7_Ano_Current.setBounds(960, 380, 110, 40);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Ток, мкА");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(240, 800, 950, 40);

        edtDev1_Ano_Voltage.setEditable(false);
        edtDev1_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev1_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev1_Ano_Voltage.setText("-");
        getContentPane().add(edtDev1_Ano_Voltage);
        edtDev1_Ano_Voltage.setBounds(240, 460, 110, 40);

        edtDev2_Ano_Voltage.setEditable(false);
        edtDev2_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev2_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev2_Ano_Voltage.setText("-");
        getContentPane().add(edtDev2_Ano_Voltage);
        edtDev2_Ano_Voltage.setBounds(360, 460, 110, 40);

        edtDev3_Ano_Voltage.setEditable(false);
        edtDev3_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev3_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev3_Ano_Voltage.setText("-");
        getContentPane().add(edtDev3_Ano_Voltage);
        edtDev3_Ano_Voltage.setBounds(480, 460, 110, 40);

        edtDev4_Ano_Voltage.setEditable(false);
        edtDev4_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev4_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev4_Ano_Voltage.setText("-");
        getContentPane().add(edtDev4_Ano_Voltage);
        edtDev4_Ano_Voltage.setBounds(600, 460, 110, 40);

        edtDev5_Ano_Voltage.setEditable(false);
        edtDev5_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev5_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev5_Ano_Voltage.setText("-");
        getContentPane().add(edtDev5_Ano_Voltage);
        edtDev5_Ano_Voltage.setBounds(720, 460, 110, 40);

        edtDev6_Ano_Voltage.setEditable(false);
        edtDev6_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev6_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev6_Ano_Voltage.setText("-");
        getContentPane().add(edtDev6_Ano_Voltage);
        edtDev6_Ano_Voltage.setBounds(840, 460, 110, 40);

        edtDev8_Ano_Voltage.setEditable(false);
        edtDev8_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev8_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev8_Ano_Voltage.setText("-");
        getContentPane().add(edtDev8_Ano_Voltage);
        edtDev8_Ano_Voltage.setBounds(1080, 460, 110, 40);

        edtDev7_Ano_Voltage.setEditable(false);
        edtDev7_Ano_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev7_Ano_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev7_Ano_Voltage.setText("-");
        getContentPane().add(edtDev7_Ano_Voltage);
        edtDev7_Ano_Voltage.setBounds(960, 460, 110, 40);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Напряжение, В");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(240, 880, 950, 40);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Управление");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel22);
        jLabel22.setBounds(600, 200, 230, 30);

        lblThreadPoller.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThreadPoller.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/red_little_dark.gif"))); // NOI18N
        lblThreadPoller.setText("P");
        getContentPane().add(lblThreadPoller);
        lblThreadPoller.setBounds(90, 950, 40, 30);

        btnVibroOff.setText("ВЫКЛ");
        btnVibroOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVibroOffActionPerformed(evt);
            }
        });
        getContentPane().add(btnVibroOff);
        btnVibroOff.setBounds(50, 610, 130, 40);

        lbl_Dev1_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev1_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev1_Tubu_RelState);
        lbl_Dev1_Tubu_RelState.setBounds(280, 730, 70, 30);

        btnVibroOn.setText("ВКЛ");
        btnVibroOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVibroOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnVibroOn);
        btnVibroOn.setBounds(50, 540, 130, 40);

        lblDev2_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev2_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev2_tubu.setText("4");
        getContentPane().add(lblDev2_tubu);
        lblDev2_tubu.setBounds(360, 730, 40, 30);

        btnDev2_Tubu_Off.setText("ВЫКЛ");
        btnDev2_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev2_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev2_Tubu_Off);
        btnDev2_Tubu_Off.setBounds(360, 770, 110, 30);

        lbl_Dev2_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev2_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev2_Tubu_RelState);
        lbl_Dev2_Tubu_RelState.setBounds(400, 730, 70, 30);

        btnDev2_Tubu_On.setText("ВКЛ");
        btnDev2_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev2_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev2_Tubu_On);
        btnDev2_Tubu_On.setBounds(360, 690, 110, 30);

        lblDev3_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev3_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev3_tubu.setText("6");
        getContentPane().add(lblDev3_tubu);
        lblDev3_tubu.setBounds(480, 730, 40, 30);

        btnDev3_Tubu_Off.setText("ВЫКЛ");
        btnDev3_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev3_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev3_Tubu_Off);
        btnDev3_Tubu_Off.setBounds(480, 770, 110, 30);

        lbl_Dev3_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev3_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev3_Tubu_RelState);
        lbl_Dev3_Tubu_RelState.setBounds(520, 730, 70, 30);

        btnDev3_Tubu_On.setText("ВКЛ");
        btnDev3_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev3_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev3_Tubu_On);
        btnDev3_Tubu_On.setBounds(480, 690, 110, 30);

        lblDev4_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev4_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev4_tubu.setText("8");
        getContentPane().add(lblDev4_tubu);
        lblDev4_tubu.setBounds(600, 730, 40, 30);

        btnDev4_Tubu_Off.setText("ВЫКЛ");
        btnDev4_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev4_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev4_Tubu_Off);
        btnDev4_Tubu_Off.setBounds(600, 770, 110, 30);

        lbl_Dev4_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev4_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev4_Tubu_RelState);
        lbl_Dev4_Tubu_RelState.setBounds(640, 730, 70, 30);

        btnDev4_Tubu_On.setText("ВКЛ");
        btnDev4_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev4_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev4_Tubu_On);
        btnDev4_Tubu_On.setBounds(600, 690, 110, 30);

        lblDev5_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev5_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev5_tubu.setText("10");
        getContentPane().add(lblDev5_tubu);
        lblDev5_tubu.setBounds(720, 730, 40, 30);

        btnDev5_Tubu_Off.setText("ВЫКЛ");
        btnDev5_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev5_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev5_Tubu_Off);
        btnDev5_Tubu_Off.setBounds(720, 770, 110, 30);

        lbl_Dev5_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev5_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev5_Tubu_RelState);
        lbl_Dev5_Tubu_RelState.setBounds(760, 730, 70, 30);

        btnDev5_Tubu_On.setText("ВКЛ");
        btnDev5_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev5_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev5_Tubu_On);
        btnDev5_Tubu_On.setBounds(720, 690, 110, 30);

        lblDev6_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev6_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev6_tubu.setText("12");
        getContentPane().add(lblDev6_tubu);
        lblDev6_tubu.setBounds(840, 730, 40, 30);

        btnDev6_Tubu_Off.setText("ВЫКЛ");
        btnDev6_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev6_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev6_Tubu_Off);
        btnDev6_Tubu_Off.setBounds(840, 770, 110, 30);

        lbl_Dev6_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev6_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev6_Tubu_RelState);
        lbl_Dev6_Tubu_RelState.setBounds(880, 730, 70, 30);

        btnDev6_Tubu_On.setText("ВКЛ");
        btnDev6_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev6_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev6_Tubu_On);
        btnDev6_Tubu_On.setBounds(840, 690, 110, 30);

        lblDev7_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev7_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev7_tubu.setText("14");
        getContentPane().add(lblDev7_tubu);
        lblDev7_tubu.setBounds(960, 730, 40, 30);

        btnDev7_Tubu_Off.setText("ВЫКЛ");
        btnDev7_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev7_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev7_Tubu_Off);
        btnDev7_Tubu_Off.setBounds(960, 770, 110, 30);

        lbl_Dev7_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev7_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev7_Tubu_RelState);
        lbl_Dev7_Tubu_RelState.setBounds(1000, 730, 70, 30);

        btnDev7_Tubu_On.setText("ВКЛ");
        btnDev7_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev7_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev7_Tubu_On);
        btnDev7_Tubu_On.setBounds(960, 690, 110, 30);

        lblDev8_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev8_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev8_tubu.setText("16");
        getContentPane().add(lblDev8_tubu);
        lblDev8_tubu.setBounds(1080, 730, 40, 30);

        btnDev8_Tubu_Off.setText("ВЫКЛ");
        btnDev8_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev8_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev8_Tubu_Off);
        btnDev8_Tubu_Off.setBounds(1080, 770, 110, 30);

        lbl_Dev8_Tubu_RelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Dev8_Tubu_RelState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lbl_Dev8_Tubu_RelState);
        lbl_Dev8_Tubu_RelState.setBounds(1120, 730, 70, 30);

        btnDev8_Tubu_On.setText("ВКЛ");
        btnDev8_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev8_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev8_Tubu_On);
        btnDev8_Tubu_On.setBounds(1080, 690, 110, 30);

        jLabel39.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("ШТЕНГЕЛИ");
        getContentPane().add(jLabel39);
        jLabel39.setBounds(600, 610, 230, 40);

        edtDev1_Tub_Current.setEditable(false);
        edtDev1_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev1_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev1_Tub_Current.setText("-");
        getContentPane().add(edtDev1_Tub_Current);
        edtDev1_Tub_Current.setBounds(240, 840, 110, 40);

        edtDev2_Tub_Current.setEditable(false);
        edtDev2_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev2_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev2_Tub_Current.setText("-");
        getContentPane().add(edtDev2_Tub_Current);
        edtDev2_Tub_Current.setBounds(360, 840, 110, 40);

        edtDev3_Tub_Current.setEditable(false);
        edtDev3_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev3_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev3_Tub_Current.setText("-");
        getContentPane().add(edtDev3_Tub_Current);
        edtDev3_Tub_Current.setBounds(480, 840, 110, 40);

        edtDev4_Tub_Current.setEditable(false);
        edtDev4_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev4_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev4_Tub_Current.setText("-");
        getContentPane().add(edtDev4_Tub_Current);
        edtDev4_Tub_Current.setBounds(600, 840, 110, 40);

        edtDev5_Tub_Current.setEditable(false);
        edtDev5_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev5_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev5_Tub_Current.setText("-");
        getContentPane().add(edtDev5_Tub_Current);
        edtDev5_Tub_Current.setBounds(720, 840, 110, 40);

        edtDev6_Tub_Current.setEditable(false);
        edtDev6_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev6_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev6_Tub_Current.setText("-");
        getContentPane().add(edtDev6_Tub_Current);
        edtDev6_Tub_Current.setBounds(840, 840, 110, 40);

        edtDev8_Tub_Current.setEditable(false);
        edtDev8_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev8_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev8_Tub_Current.setText("-");
        getContentPane().add(edtDev8_Tub_Current);
        edtDev8_Tub_Current.setBounds(1080, 840, 110, 40);

        edtDev7_Tub_Current.setEditable(false);
        edtDev7_Tub_Current.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev7_Tub_Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev7_Tub_Current.setText("-");
        getContentPane().add(edtDev7_Tub_Current);
        edtDev7_Tub_Current.setBounds(960, 840, 110, 40);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Ток, мкА");
        getContentPane().add(jLabel40);
        jLabel40.setBounds(240, 340, 950, 40);

        edtDev1_Tub_Voltage.setEditable(false);
        edtDev1_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev1_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev1_Tub_Voltage.setText("-");
        getContentPane().add(edtDev1_Tub_Voltage);
        edtDev1_Tub_Voltage.setBounds(240, 920, 110, 40);

        edtDev2_Tub_Voltage.setEditable(false);
        edtDev2_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev2_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev2_Tub_Voltage.setText("-");
        getContentPane().add(edtDev2_Tub_Voltage);
        edtDev2_Tub_Voltage.setBounds(360, 920, 110, 40);

        edtDev3_Tub_Voltage.setEditable(false);
        edtDev3_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev3_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev3_Tub_Voltage.setText("-");
        getContentPane().add(edtDev3_Tub_Voltage);
        edtDev3_Tub_Voltage.setBounds(480, 920, 110, 40);

        edtDev4_Tub_Voltage.setEditable(false);
        edtDev4_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev4_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev4_Tub_Voltage.setText("-");
        getContentPane().add(edtDev4_Tub_Voltage);
        edtDev4_Tub_Voltage.setBounds(600, 920, 110, 40);

        edtDev5_Tub_Voltage.setEditable(false);
        edtDev5_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev5_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev5_Tub_Voltage.setText("-");
        getContentPane().add(edtDev5_Tub_Voltage);
        edtDev5_Tub_Voltage.setBounds(720, 920, 110, 40);

        edtDev6_Tub_Voltage.setEditable(false);
        edtDev6_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev6_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev6_Tub_Voltage.setText("-");
        getContentPane().add(edtDev6_Tub_Voltage);
        edtDev6_Tub_Voltage.setBounds(840, 920, 110, 40);

        edtDev8_Tub_Voltage.setEditable(false);
        edtDev8_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev8_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev8_Tub_Voltage.setText("-");
        edtDev8_Tub_Voltage.setToolTipText("");
        getContentPane().add(edtDev8_Tub_Voltage);
        edtDev8_Tub_Voltage.setBounds(1080, 920, 110, 40);

        edtDev7_Tub_Voltage.setEditable(false);
        edtDev7_Tub_Voltage.setFont(new java.awt.Font("Cantarell", 0, 28)); // NOI18N
        edtDev7_Tub_Voltage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDev7_Tub_Voltage.setText("-");
        getContentPane().add(edtDev7_Tub_Voltage);
        edtDev7_Tub_Voltage.setBounds(960, 920, 110, 40);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Напряжение, В");
        getContentPane().add(jLabel41);
        jLabel41.setBounds(240, 420, 950, 40);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Вибрация");
        getContentPane().add(jLabel42);
        jLabel42.setBounds(10, 510, 210, 30);

        lblVibration.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVibration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lblVibration);
        lblVibration.setBounds(20, 580, 190, 30);

        btnDev1_Tubu_On.setText("ВКЛ");
        btnDev1_Tubu_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev1_Tubu_OnActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev1_Tubu_On);
        btnDev1_Tubu_On.setBounds(240, 690, 110, 30);

        btnDev1_Tubu_Off.setText("ВЫКЛ");
        btnDev1_Tubu_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDev1_Tubu_OffActionPerformed(evt);
            }
        });
        getContentPane().add(btnDev1_Tubu_Off);
        btnDev1_Tubu_Off.setBounds(240, 770, 110, 30);

        lblMainSwitch.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblMainSwitch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMainSwitch.setText("СНЯТО");
        getContentPane().add(lblMainSwitch);
        lblMainSwitch.setBounds(20, 400, 190, 50);

        btnToggleBlockScreen.setText("Заблокировать  экран");
        btnToggleBlockScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnToggleBlockScreenMouseClicked(evt);
            }
        });
        getContentPane().add(btnToggleBlockScreen);
        btnToggleBlockScreen.setBounds(10, 910, 210, 40);

        btnSettings.setText("Настройки программы");
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        getContentPane().add(btnSettings);
        btnSettings.setBounds(10, 870, 210, 40);

        btnAllTubusOn.setText("включить все каналы штенгелей");
        btnAllTubusOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllTubusOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnAllTubusOn);
        btnAllTubusOn.setBounds(242, 620, 350, 28);

        btnAllTubusOff.setText("выключить все каналы штенгелей");
        btnAllTubusOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllTubusOffActionPerformed(evt);
            }
        });
        getContentPane().add(btnAllTubusOff);
        btnAllTubusOff.setBounds(840, 620, 350, 28);

        btnAllAnodesOn.setText("включить все каналы анодов");
        btnAllAnodesOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllAnodesOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnAllAnodesOn);
        btnAllAnodesOn.setBounds(240, 170, 350, 28);

        btnAllAnodesOff.setText("выключить все каналы анодов");
        btnAllAnodesOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllAnodesOffActionPerformed(evt);
            }
        });
        getContentPane().add(btnAllAnodesOff);
        btnAllAnodesOff.setBounds(840, 170, 350, 28);

        pnlAdditionalPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlAdditionalPanel.setLayout(null);
        getContentPane().add(pnlAdditionalPanel);
        pnlAdditionalPanel.setBounds(1210, 20, 380, 940);

        btnSetDevSerNums.setText("Задать номера резонаторов");
        btnSetDevSerNums.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDevSerNumsActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetDevSerNums);
        btnSetDevSerNums.setBounds(970, 130, 230, 28);

        btnCalibration.setText("Калибровка");
        btnCalibration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalibrationActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalibration);
        btnCalibration.setBounds(10, 830, 210, 40);

        lblDev1_tubu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDev1_tubu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/green_little_bright.gif"))); // NOI18N
        lblDev1_tubu.setText("2");
        getContentPane().add(lblDev1_tubu);
        lblDev1_tubu.setBounds(240, 730, 40, 30);

        lblThreadRState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThreadRState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/red_little_dark.gif"))); // NOI18N
        lblThreadRState.setText("R");
        getContentPane().add(lblThreadRState);
        lblThreadRState.setBounds(10, 950, 40, 30);

        lblThreadWState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThreadWState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/red_little_dark.gif"))); // NOI18N
        lblThreadWState.setText("W");
        getContentPane().add(lblThreadWState);
        lblThreadWState.setBounds(50, 950, 40, 30);

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("8");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1080, 510, 110, 80);

        jLabel2.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("1");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(240, 510, 110, 80);

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("2");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(360, 510, 110, 80);

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("3");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(480, 510, 110, 80);

        jLabel5.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("4");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(600, 510, 110, 80);

        jLabel6.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("5");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(720, 510, 110, 80);

        jLabel7.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("6");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(840, 510, 110, 80);

        jLabel8.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("7");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(960, 510, 110, 80);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Вентилятор");
        getContentPane().add(jLabel43);
        jLabel43.setBounds(10, 650, 210, 30);

        btnFanOn.setText("ВКЛ");
        btnFanOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFanOnActionPerformed(evt);
            }
        });
        getContentPane().add(btnFanOn);
        btnFanOn.setBounds(50, 680, 130, 40);

        lblFan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/blackLED.gif"))); // NOI18N
        getContentPane().add(lblFan);
        lblFan.setBounds(20, 720, 190, 30);

        btnFanOff.setText("ВЫКЛ");
        btnFanOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFanOffActionPerformed(evt);
            }
        });
        getContentPane().add(btnFanOff);
        btnFanOff.setBounds(50, 750, 130, 40);

        lblThreadExecutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThreadExecutor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ams/images/red_little_dark.gif"))); // NOI18N
        lblThreadExecutor.setText("E");
        getContentPane().add(lblThreadExecutor);
        lblThreadExecutor.setBounds(130, 950, 40, 30);

        btn04mA.setText("400 мкА");
        btn04mA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn04mAActionPerformed(evt);
            }
        });
        getContentPane().add(btn04mA);
        btn04mA.setBounds(240, 90, 80, 28);

        btnGroupLogLevel.add(btnLogDebug);
        btnLogDebug.setFont(new java.awt.Font("Courier 10 Pitch", 0, 10)); // NOI18N
        btnLogDebug.setText("D");
        btnLogDebug.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogDebug.setMaximumSize(new java.awt.Dimension(35, 35));
        btnLogDebug.setMinimumSize(new java.awt.Dimension(35, 35));
        btnLogDebug.setPreferredSize(new java.awt.Dimension(35, 35));
        btnLogDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogDebugActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogDebug);
        btnLogDebug.setBounds(40, 795, 35, 35);

        btnGroupLogLevel.add(btnLogTrace);
        btnLogTrace.setFont(new java.awt.Font("Courier 10 Pitch", 0, 10)); // NOI18N
        btnLogTrace.setText("T");
        btnLogTrace.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogTrace.setMaximumSize(new java.awt.Dimension(35, 35));
        btnLogTrace.setMinimumSize(new java.awt.Dimension(35, 35));
        btnLogTrace.setPreferredSize(new java.awt.Dimension(35, 35));
        btnLogTrace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogTraceActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogTrace);
        btnLogTrace.setBounds(0, 795, 35, 35);

        btnGroupLogLevel.add(btnLogWarn);
        btnLogWarn.setFont(new java.awt.Font("Courier 10 Pitch", 0, 10)); // NOI18N
        btnLogWarn.setText("W");
        btnLogWarn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogWarn.setMaximumSize(new java.awt.Dimension(35, 35));
        btnLogWarn.setMinimumSize(new java.awt.Dimension(35, 35));
        btnLogWarn.setPreferredSize(new java.awt.Dimension(35, 35));
        btnLogWarn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogWarnActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogWarn);
        btnLogWarn.setBounds(120, 795, 35, 35);

        btnGroupLogLevel.add(btnLogInfo);
        btnLogInfo.setFont(new java.awt.Font("Courier 10 Pitch", 0, 10)); // NOI18N
        btnLogInfo.setText("I");
        btnLogInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogInfo.setMaximumSize(new java.awt.Dimension(35, 35));
        btnLogInfo.setMinimumSize(new java.awt.Dimension(35, 35));
        btnLogInfo.setPreferredSize(new java.awt.Dimension(35, 35));
        btnLogInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogInfo);
        btnLogInfo.setBounds(80, 795, 35, 35);

        btnGroupLogLevel.add(btnLogFatal);
        btnLogFatal.setFont(new java.awt.Font("Courier 10 Pitch", 0, 10)); // NOI18N
        btnLogFatal.setText("F");
        btnLogFatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogFatal.setMaximumSize(new java.awt.Dimension(35, 35));
        btnLogFatal.setMinimumSize(new java.awt.Dimension(35, 35));
        btnLogFatal.setPreferredSize(new java.awt.Dimension(35, 35));
        btnLogFatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogFatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogFatal);
        btnLogFatal.setBounds(200, 795, 35, 35);

        btnGroupLogLevel.add(btnLogError);
        btnLogError.setFont(new java.awt.Font("Courier 10 Pitch", 0, 10)); // NOI18N
        btnLogError.setText("E");
        btnLogError.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogError.setMaximumSize(new java.awt.Dimension(35, 35));
        btnLogError.setMinimumSize(new java.awt.Dimension(35, 35));
        btnLogError.setPreferredSize(new java.awt.Dimension(35, 35));
        btnLogError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogErrorActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogError);
        btnLogError.setBounds(160, 795, 35, 35);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Обработчик колеса мыши
     * @param evt 
     */
    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        logger.trace( "onMouseWheel in");
        
        if( btnToggleBlockScreen.isSelected())
            return;
        
        int nCurrent = theApp.IncDecOuterCurrent( ( int) Math.floor( evt.getPreciseWheelRotation() * 10.));
        refreshControlsState();
    }//GEN-LAST:event_formMouseWheelMoved

    /**
     * Нажатие кнопки "500мкА"
     * @param evt 
     */
    private void btn05mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn05mAActionPerformed
        theApp.SetOuterCurrent( 500);
        refreshControlsState();
    }//GEN-LAST:event_btn05mAActionPerformed

    /**
     * Нажатие кнопки "1000мкА"
     * @param evt 
     */
    private void btn10mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10mAActionPerformed
        theApp.SetOuterCurrent( 1000);
        refreshControlsState();
    }//GEN-LAST:event_btn10mAActionPerformed

    /**
     * Нажатие кнопки "1500мкА"
     * @param evt 
     */
    private void btn15mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15mAActionPerformed
        theApp.SetOuterCurrent( 1500);
        refreshControlsState();
    }//GEN-LAST:event_btn15mAActionPerformed

    /**
     * Нажатие кнопки "2000мкА"
     * @param evt 
     */
    private void btn20mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20mAActionPerformed
        theApp.SetOuterCurrent( 2000);
        refreshControlsState();
    }//GEN-LAST:event_btn20mAActionPerformed

    /**
     * Нажатие кнопки "2500мкА"
     * @param evt 
     */
    private void btn25mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25mAActionPerformed
        theApp.SetOuterCurrent( 2500);
        refreshControlsState();
    }//GEN-LAST:event_btn25mAActionPerformed

    /**
     * Нажатие кнопки "3000мкА"
     * @param evt 
     */
    private void btn30mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn30mAActionPerformed
        theApp.SetOuterCurrent( 3000);
        refreshControlsState();
    }//GEN-LAST:event_btn30mAActionPerformed

    private void btnOuterCurrentSpinUpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOuterCurrentSpinUpMousePressed
        spinning.SetSign( 1);
        Thread thrSpnDn = new Thread( spinning);
        thrSpnDn.start();
        
        Thread thrSpnAcc = new Thread( spinnerAccelerator);
        thrSpnAcc.start();
    }//GEN-LAST:event_btnOuterCurrentSpinUpMousePressed

    private void btnOuterCurrentSpinUpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOuterCurrentSpinUpMouseReleased
        spinning.stop();
        spinnerAccelerator.stop();
    }//GEN-LAST:event_btnOuterCurrentSpinUpMouseReleased

    private void btnOuterCurrentSpinDownMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOuterCurrentSpinDownMousePressed
        spinning.SetSign( -1);
        Thread thrSpnDn = new Thread(spinning);
        thrSpnDn.start();
        
        Thread thrSpnAcc = new Thread( spinnerAccelerator);
        thrSpnAcc.start();
    }//GEN-LAST:event_btnOuterCurrentSpinDownMousePressed

    private void btnOuterCurrentSpinDownMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOuterCurrentSpinDownMouseReleased
        spinning.stop();
        spinnerAccelerator.stop();
    }//GEN-LAST:event_btnOuterCurrentSpinDownMouseReleased

    private void edtOuterCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtOuterCurrentActionPerformed
        String strEdt = edtOuterCurrent.getText();
        try {
            int value = Integer.parseInt(strEdt);
            theApp.SetOuterCurrent( value);
            theApp.CheckCurrentLimits();
            refreshControlsState();
            
        } catch (NumberFormatException nfe) {
            refreshControlsState();
        }
    }//GEN-LAST:event_edtOuterCurrentActionPerformed

    private void sldOuterCurrentMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sldOuterCurrentMouseDragged
        int value = sldOuterCurrent.getValue();
        edtOuterCurrent.setText( "" + value);
    }//GEN-LAST:event_sldOuterCurrentMouseDragged

    private void sldOuterCurrentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sldOuterCurrentMouseReleased
        int value = sldOuterCurrent.getValue();
        theApp.SetOuterCurrent( value);
        theApp.CheckCurrentLimits();
        refreshControlsState();
    }//GEN-LAST:event_sldOuterCurrentMouseReleased

    /**
     * Включение канала анода для 1 устройства
     */
    private void btnDev1_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev1_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE1);
    }//GEN-LAST:event_btnDev1_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 1 устройства
     * @param evt 
     */
    private void btnDev1_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev1_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE1);
        /*
        logger.debug( "Device1.Anode current control turned off!");
        if( theApp.GetRegime() == AMSApp.REGIME_PROCESSING) {
            //РЕЖИМ КАЛИБРОВКИ.
            //если рубильник включён - размыкаем наше конкретное реле
            if( theApp.GetMainSwitcher() == true) {
                String str_Dev1_Ano_REL_channel = theApp.GetSettings().GetDev1().GetAnoREL_chan();
                theApp.m_Relay_anodes.QueueSetRelayStateBitCommand( 6, false); //Integer.parseInt( str_Dev1_Ano_REL_channel), false);
                theApp.m_Relay_anodes.QueueRequestDataCommand();
            }
            return;
        }
        
        //снимаем напряжение на выходе DACa
        String str_Dev1_Ano_DAC_channel = theApp.GetSettings().GetDev1().GetAnoDAC_chan();
        theApp.m_DAC_anodes_01.QueueSetChannelOutputValueCommand( Integer.parseInt( str_Dev1_Ano_DAC_channel), Adam4024.CHANEL_REG_OFF_DAC_VOLT);
        
        //если рубильник включён - размыкаем наше конкретное реле
        if( theApp.GetMainSwitcher() == true) {
            String str_Dev1_Ano_REL_channel = theApp.GetSettings().GetDev1().GetAnoREL_chan();
            theApp.m_Relay_anodes.QueueSetRelayStateBitCommand( 6, false); //Integer.parseInt( str_Dev1_Ano_REL_channel), false);
            theApp.m_Relay_anodes.QueueRequestDataCommand();
        }
        
        //выключаем менеджера канала
        lblDev1_anode.setIcon( theApp.GetResources().getLittleIconOff());
        theApp.GetDevManager_0_a().SetEnabled( false);
        */
    }//GEN-LAST:event_btnDev1_Anode_OffActionPerformed

    /**
     * Включение канала анода для 2 устройства
     * @param evt 
     */
    private void btnDev2_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev2_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE2);
    }//GEN-LAST:event_btnDev2_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 2 устройства
     * @param evt 
     */
    private void btnDev2_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev2_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE2);
    }//GEN-LAST:event_btnDev2_Anode_OffActionPerformed
      
    /**
     * Включение канала анода для 3 устройства
     * @param evt 
     */
    private void btnDev3_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev3_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE3);
    }//GEN-LAST:event_btnDev3_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 3 устройства
     * @param evt 
     */
    private void btnDev3_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev3_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE3);
    }//GEN-LAST:event_btnDev3_Anode_OffActionPerformed
    
    /**
     * Включение канала анода для 4 устройства
     * @param evt 
     */
    private void btnDev4_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev4_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE4);
    }//GEN-LAST:event_btnDev4_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 4 устройства
     * @param evt 
     */
    private void btnDev4_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev4_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE4);
    }//GEN-LAST:event_btnDev4_Anode_OffActionPerformed
    
    /**
     * Включение канала анода для 5 устройства
     * @param evt 
     */
    private void btnDev5_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev5_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE5);
    }//GEN-LAST:event_btnDev5_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 5 устройства
     * @param evt 
     */
    private void btnDev5_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev5_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE5);
    }//GEN-LAST:event_btnDev5_Anode_OffActionPerformed

    /**
     * Включение канала анода для 6 устройства
     * @param evt 
     */
    private void btnDev6_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev6_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE6);
    }//GEN-LAST:event_btnDev6_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 6 устройства
     * @param evt 
     */
    private void btnDev6_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev6_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE6);
    }//GEN-LAST:event_btnDev6_Anode_OffActionPerformed

    /**
     * Включение канала анода для 7 устройства
     * @param evt 
     */
    private void btnDev7_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev7_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE7);
    }//GEN-LAST:event_btnDev7_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 7 устройства
     * @param evt 
     */
    private void btnDev7_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev7_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE7);
    }//GEN-LAST:event_btnDev7_Anode_OffActionPerformed

    /**
     * Включение канала анода для 8 устройства
     * @param evt 
     */
    private void btnDev8_Anode_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev8_Anode_OnActionPerformed
        TurnOnAnoDevice( AMSConstants.T_DEVICE8);
    }//GEN-LAST:event_btnDev8_Anode_OnActionPerformed

    /**
     * Выключение канала анода для 8 устройства
     * @param evt 
     */
    private void btnDev8_Anode_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev8_Anode_OffActionPerformed
        TurnOffAnoDevice( AMSConstants.T_DEVICE8);
    }//GEN-LAST:event_btnDev8_Anode_OffActionPerformed

    /**
     * Включение канала штенгеля для устройства nDevice
     * @param nDevice 
     */
    public void TurnOnAnoDevice( int nDevice) {
        if( !theApp.GetRxTx().IsActive()) return;
        
        
        //DEVICE.MANAGER
        AMSDeviceManager mngr = theApp.GetDevManager_a( nDevice);
        
        //РЕЛЕ
        Adam4068 relay;
        int nDev_Ano_REL_channel;
        relay = mngr.GetRelay();
        nDev_Ano_REL_channel = mngr.GetRelayChannel();
        
        //ЦАП
        Adam4024 dac;
        int nDev_Ano_DAC_channel;
        dac = mngr.GetDAC();
        nDev_Ano_DAC_channel = mngr.GetDacChannel();
        
        //Включаем менеджера, и зажигаем его лампочку
        mngr.SetEnabled( true);
        mngr.GetActiveLed().setIcon( theApp.GetResources().getLittleIconOn());
        
        if( theApp.GetMainSwitcher() == false) {
            logger.info( "Включение канала анода заблокировано рубильником.");
            return;
        }
        
        //Ставим на соответствующий канал DAC 6.0 вольт
        dac.QueueSetChannelOutputValueCommand(
                nDev_Ano_DAC_channel, AMSConstants.DAC_CHANNEL_REG_ON_DAC_VOLT);
        
        //Включаем соответствующий канал реле
        relay.QueueSetRelayStateBitCommand(
                nDev_Ano_REL_channel, true);
        
        //посылаем запрос на состояния реле
        relay.QueueRequestDataCommand();
        
        //заблокируем управление соседнего канала
        theApp.GetDevManager_a(nDevice).BlockNeighbour();
    }
    
    /**
     * Выключение канала анода для устройства nDevice
     * @param nDevice 
     */
    public void TurnOffAnoDevice( int nDevice) {
        if( !theApp.GetRxTx().IsActive()) return;
        
        //DEVICE.MANAGER
        AMSDeviceManager mngr = theApp.GetDevManager_a( nDevice);
        
        //РЕЛЕ
        Adam4068 relay;
        int nDev_Ano_REL_channel;
        relay = mngr.GetRelay();
        nDev_Ano_REL_channel = mngr.GetRelayChannel();
                
        //ЦАП
        Adam4024 dac;
        int nDev_Ano_DAC_channel;
        dac = mngr.GetDAC();
        nDev_Ano_DAC_channel = mngr.GetDacChannel();
        
        //Выключаем соответствующий канал реле
        relay.QueueSetRelayStateBitCommand(
                nDev_Ano_REL_channel, false);
        
        //Ставим на соответствующий канал DAC 3.0 вольт
        dac.QueueSetChannelOutputValueCommand(
                nDev_Ano_DAC_channel, AMSConstants.DAC_CHANNEL_REG_OFF_DAC_VOLT);
        
        //Выключаем менеджера, и гасим его лампочку
        mngr.SetEnabled( false);
        mngr.GetActiveLed().setIcon( theApp.GetResources().getLittleIconOff());
        
        //посылаем запрос на состояния реле
        relay.QueueRequestDataCommand();
        
        //разблокируем управление соседнего канала
        theApp.GetDevManager_a(nDevice).UnBlockNeighbour();
    }
    
    /**
     * Переключение в режим "Измерение ВАХ"
     * @param evt 
     */
    private void btnRegimeUICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegimeUICActionPerformed
        Font fnt1 = null, fnt2 = btnRegimeUIC.getFont();
        switch( theApp.GetRegime()) {
            case AMSApp.REGIME_I_EDGE:
                fnt1 = btnRegimeEdge.getFont();
                btnRegimeEdge.setFont( fnt2);
                pnlRegimeEdge.setVisible( false);
            break;
            case AMSApp.REGIME_PROCESSING:
                fnt1 = btnRegimeProcessing.getFont();
                btnRegimeProcessing.setFont( fnt2);
                pnlRegimeProcessing.setVisible( false);
            break;
            case AMSApp.REGIME_MANUAL:
                fnt1 = btnRegimeManual.getFont();
                btnRegimeManual.setFont( fnt2);
                pnlRegimeManual.setVisible( false);
            break;
        }
        
        if( fnt1 != null)
            btnRegimeUIC.setFont( fnt1);
                
        try {
            theApp.SetRegime( AMSApp.REGIME_UIC);
        }
        catch( Exception ex) {
            logger.error( "Exception caught while changing app regime", ex);
        }
        
        pnlRegimeUIC.setVisible( true);
        
        theApp.SetOuterCurrent( 1200);
        refreshControlsState();
    }//GEN-LAST:event_btnRegimeUICActionPerformed

    /**
     * Переключение в режим "Измерение порогового тока"
     * @param evt 
     */
    private void btnRegimeEdgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegimeEdgeActionPerformed
        Font fnt1 = null, fnt2 = btnRegimeEdge.getFont();
        switch( theApp.GetRegime()) {
            case AMSApp.REGIME_UIC:
                fnt1 = btnRegimeUIC.getFont();
                btnRegimeUIC.setFont( fnt2);
                pnlRegimeUIC.setVisible( false);
            break;
            case AMSApp.REGIME_PROCESSING:
                fnt1 = btnRegimeProcessing.getFont();
                btnRegimeProcessing.setFont( fnt2);
                pnlRegimeProcessing.setVisible( false);
            break;
            case AMSApp.REGIME_MANUAL:
                fnt1 = btnRegimeManual.getFont();
                btnRegimeManual.setFont( fnt2);
                pnlRegimeManual.setVisible( false);
            break;
        }
        
        if( fnt1 != null)
            btnRegimeEdge.setFont( fnt1);
                
        pnlRegimeEdge.setVisible( true);

        try {
            theApp.SetRegime( AMSApp.REGIME_I_EDGE);
        }
        catch( Exception ex) {
            logger.error( "Exception caught while changing app regime", ex);
        }
    }//GEN-LAST:event_btnRegimeEdgeActionPerformed

    /**
     * Переключение в режим "Обработка"
     * @param evt 
     */
    private void btnRegimeProcessingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegimeProcessingActionPerformed
        Font fnt1 = null, fnt2 = btnRegimeProcessing.getFont();
        switch( theApp.GetRegime()) {
            case AMSApp.REGIME_UIC:
                fnt1 = btnRegimeUIC.getFont();
                btnRegimeUIC.setFont( fnt2);
                pnlRegimeUIC.setVisible( false);
            break;
            case AMSApp.REGIME_I_EDGE:
                fnt1 = btnRegimeEdge.getFont();
                btnRegimeEdge.setFont( fnt2);
                pnlRegimeEdge.setVisible( false);
            break;
            case AMSApp.REGIME_MANUAL:
                fnt1 = btnRegimeManual.getFont();
                btnRegimeManual.setFont( fnt2);
                pnlRegimeManual.setVisible( false);
            break;
        }
        
        if( fnt1 != null)
            btnRegimeProcessing.setFont( fnt1);

        pnlRegimeProcessing.setVisible( true);
        
        
        try {
            theApp.SetRegime( AMSApp.REGIME_PROCESSING);
        }
        catch( Exception ex) {
            logger.error( "Exception caught while changing app regime", ex);
        }
    }//GEN-LAST:event_btnRegimeProcessingActionPerformed

    /**
     * Переключение в режим "Ручной режим"
     * @param evt 
     */
    private void btnRegimeManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegimeManualActionPerformed
        Font fnt1 = null, fnt2 = btnRegimeManual.getFont();
        switch( theApp.GetRegime()) {
            case AMSApp.REGIME_UIC:
                fnt1 = btnRegimeUIC.getFont();
                btnRegimeUIC.setFont( fnt2);
                pnlRegimeUIC.setVisible( false);
            break;
            case AMSApp.REGIME_I_EDGE:
                fnt1 = btnRegimeEdge.getFont();
                btnRegimeEdge.setFont( fnt2);
                pnlRegimeEdge.setVisible( false);
            break;
            case AMSApp.REGIME_PROCESSING:
                fnt1 = btnRegimeProcessing.getFont();
                btnRegimeProcessing.setFont( fnt2);
                pnlRegimeProcessing.setVisible( false);
            break;
        }
        
        if( fnt1 != null)
            btnRegimeManual.setFont( fnt1);
        
        pnlRegimeManual.setVisible( true);
        
        try {
            theApp.SetRegime( AMSApp.REGIME_MANUAL);
        }
        catch( Exception ex) {
            logger.error( "Exception caught while changing app regime", ex);
        }
    }//GEN-LAST:event_btnRegimeManualActionPerformed

    /**
     * Нажатие-отжатие кнопки "Блокировка экрана"
     * @param evt 
     */
    private void btnToggleBlockScreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnToggleBlockScreenMouseClicked
        if( btnToggleBlockScreen.isSelected()) {
            btnToggleBlockScreen.setText( "Разблокировать экран");
        }
        else {
            btnToggleBlockScreen.setText( "Заблокировать  экран");
        }

        if( theApp.GetMainSwitcher() == false) {
            btnRegimeEdge.setEnabled( !btnToggleBlockScreen.isSelected());
            btnRegimeManual.setEnabled( !btnToggleBlockScreen.isSelected());
            btnRegimeProcessing.setEnabled( !btnToggleBlockScreen.isSelected());
            btnRegimeUIC.setEnabled( !btnToggleBlockScreen.isSelected());
        }
        
        //btnSwitchOff.setEnabled( !btnToggleBlockScreen.isSelected());
        //btnSwitchOn.setEnabled( !btnToggleBlockScreen.isSelected());

        btnVibroOff.setEnabled( !btnToggleBlockScreen.isSelected());
        btnVibroOn.setEnabled( !btnToggleBlockScreen.isSelected());

        sldOuterCurrent.setEnabled( !btnToggleBlockScreen.isSelected());
        edtOuterCurrent.setEnabled( !btnToggleBlockScreen.isSelected());
        btnOuterCurrentSpinDown.setEnabled( !btnToggleBlockScreen.isSelected());
        btnOuterCurrentSpinUp.setEnabled( !btnToggleBlockScreen.isSelected());

        btn05mA.setEnabled( !btnToggleBlockScreen.isSelected());
        btn10mA.setEnabled( !btnToggleBlockScreen.isSelected());
        btn15mA.setEnabled( !btnToggleBlockScreen.isSelected());
        btn20mA.setEnabled( !btnToggleBlockScreen.isSelected());
        btn25mA.setEnabled( !btnToggleBlockScreen.isSelected());
        btn30mA.setEnabled( !btnToggleBlockScreen.isSelected());

        edtHyroNum1.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum2.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum3.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum4.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum5.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum6.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum7.setEnabled( !btnToggleBlockScreen.isSelected());
        edtHyroNum8.setEnabled( !btnToggleBlockScreen.isSelected());

        btnDev1_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev1_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev2_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev2_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev3_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev3_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev4_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev4_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev5_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev5_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev6_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev6_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev7_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev7_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev8_Anode_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev8_Anode_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnAllAnodesOff.setEnabled( !btnToggleBlockScreen.isSelected());
        btnAllAnodesOn.setEnabled( !btnToggleBlockScreen.isSelected());

        btnDev1_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev1_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev2_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev2_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev3_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev3_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev4_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev4_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev5_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev5_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev6_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev6_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev7_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev7_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev8_Tubu_Off.setEnabled( !btnToggleBlockScreen.isSelected());
        btnDev8_Tubu_On.setEnabled( !btnToggleBlockScreen.isSelected());
        btnAllTubusOff.setEnabled( !btnToggleBlockScreen.isSelected());
        btnAllTubusOn.setEnabled( !btnToggleBlockScreen.isSelected());
        
        btnCalibration.setEnabled( !btnToggleBlockScreen.isSelected());
        btnSettings.setEnabled( !btnToggleBlockScreen.isSelected());
        btnSetDevSerNums.setEnabled( !btnToggleBlockScreen.isSelected());
    }//GEN-LAST:event_btnToggleBlockScreenMouseClicked

    /**
     * public-обёртка для выключения рубильника
     */ 
    public void fakeSwitchOff() {
        btnSwitchOffActionPerformed( null);
    }
    
    /**
     * Выключение рубильника
     * @param evt 
     */
    private void btnSwitchOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitchOffActionPerformed
        theApp.TurnMainSwitcherOff();
        
        /*
        //гасим иконки..... НЕ НАДО?
        GetLblDev1a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev2a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev3a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev4a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev5a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev6a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev7a().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev8a().setIcon( theApp.GetResources().getLittleIconOff());
        
        GetLblDev1t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev2t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev3t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev4t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev5t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev6t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev7t().setIcon( theApp.GetResources().getLittleIconOff());
        GetLblDev8t().setIcon( theApp.GetResources().getLittleIconOff());
        */
        
        lblMainSwitch.setText("СНЯТО");
        lblMainSwitch.setForeground( new Color(0, 0, 0));
        
        if( !btnToggleBlockScreen.isSelected()) SetRegimeButtonsState( true);
        
        /*
        //Выключаем канал реле для вентиллятора шкафа
        try {
            Adam4068 relayFan = theApp.m_devicesSet.GetRelay( AMSConstants.REL3);
            int nFanChan = theApp.GetSettings().GetREL3_Chan_Fan();
            relayFan.QueueSetRelayStateBitCommand( nFanChan, false);
        }
        catch( Exception ex) {
            logger.error( "При попытке выключить реле вентиллятора шкафа произошла исключительная ситуация.", ex);
        }
        */
    }//GEN-LAST:event_btnSwitchOffActionPerformed

    /**
     * Нажатие кнопки "Настройки программы"
     * @param evt 
     */
    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        theApp.GetSettings().ShowSettingsDialog();
    }//GEN-LAST:event_btnSettingsActionPerformed

    /**
     * public-обёртка для включения рубильника
     */ 
    public void fakeSwitchOn() {
        btnSwitchOnActionPerformed( null);
    }
    
    /**
     * Включение рубильника
     * @param evt 
     */
    private void btnSwitchOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitchOnActionPerformed
        SetRegimeButtonsState( false);
        
        theApp.TurnMainSwitcherOn();
        
        lblMainSwitch.setText("ПОДАНО");
        lblMainSwitch.setForeground( new Color( 200, 0, 0));
        
        /*
        //включаем канал реле для вентиллятора шкафа
        try {
            Adam4068 relayFan = theApp.m_devicesSet.GetRelay( AMSConstants.REL3);
            int nFanChan = theApp.GetSettings().GetREL3_Chan_Fan();
            relayFan.QueueSetRelayStateBitCommand( nFanChan, true);
        }
        catch( Exception ex) {
            logger.error( "При попытке включить реле вентиллятора шкафа произошла исключительная ситуация.", ex);
        }
        */
    }//GEN-LAST:event_btnSwitchOnActionPerformed

    /**
     * Заблокировать-разблокировать кнопки режимов работы
     * @param bState 
     */
    private void SetRegimeButtonsState( boolean bState) {
        btnRegimeEdge.setEnabled( bState);
        btnRegimeManual.setEnabled( bState);
        btnRegimeProcessing.setEnabled( bState);
        btnRegimeUIC.setEnabled( bState);
    }
    
    /**
     * Включение канала штенгеля 1 устройства
     * @param evt 
     */
    private void btnDev1_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev1_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE1);
    }//GEN-LAST:event_btnDev1_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 1 устройства
     * @param evt 
     */
    private void btnDev1_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev1_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE1);
    }//GEN-LAST:event_btnDev1_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 2 устройства
     * @param evt 
     */
    private void btnDev2_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev2_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE2);
    }//GEN-LAST:event_btnDev2_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 2 устройства
     * @param evt 
     */
    private void btnDev2_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev2_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE2);
    }//GEN-LAST:event_btnDev2_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 3 устройства
     * @param evt 
     */
    private void btnDev3_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev3_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE3);
    }//GEN-LAST:event_btnDev3_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 3 устройства
     * @param evt 
     */
    private void btnDev3_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev3_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE3);
    }//GEN-LAST:event_btnDev3_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 4 устройства
     * @param evt 
     */
    private void btnDev4_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev4_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE4);
    }//GEN-LAST:event_btnDev4_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 4 устройства
     * @param evt 
     */    
    private void btnDev4_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev4_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE4);
    }//GEN-LAST:event_btnDev4_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 5 устройства
     * @param evt 
     */
    private void btnDev5_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev5_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE5);
    }//GEN-LAST:event_btnDev5_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 5 устройства
     * @param evt 
     */
    private void btnDev5_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev5_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE5);
    }//GEN-LAST:event_btnDev5_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 6 устройства
     * @param evt 
     */
    private void btnDev6_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev6_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE6);
    }//GEN-LAST:event_btnDev6_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 6 устройства
     * @param evt 
     */
    private void btnDev6_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev6_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE6);
    }//GEN-LAST:event_btnDev6_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 7 устройства
     * @param evt 
     */    
    private void btnDev7_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev7_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE7);
    }//GEN-LAST:event_btnDev7_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 7 устройства
     * @param evt 
     */
    private void btnDev7_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev7_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE7);
    }//GEN-LAST:event_btnDev7_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля 8 устройства
     * @param evt 
     */
    private void btnDev8_Tubu_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev8_Tubu_OnActionPerformed
        TurnOnTubuDevice( AMSConstants.T_DEVICE8);
    }//GEN-LAST:event_btnDev8_Tubu_OnActionPerformed

    /**
     * Выключение канала штенгеля 8 устройства
     * @param evt 
     */
    private void btnDev8_Tubu_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDev8_Tubu_OffActionPerformed
        TurnOffTubuDevice( AMSConstants.T_DEVICE8);
    }//GEN-LAST:event_btnDev8_Tubu_OffActionPerformed

    /**
     * Включение канала штенгеля для устройства nDevice
     * @param nDevice 
     */
    public void TurnOnTubuDevice( int nDevice) {
        if( !theApp.GetRxTx().IsActive()) return;
        
        //DEVICE.MANAGER
        AMSDeviceManager mngr = theApp.GetDevManager_t( nDevice);
        
        //ЦАП
        Adam4024 dac;
        int nDev_Tub_DAC_channel;
        dac = mngr.GetDAC();
        nDev_Tub_DAC_channel = mngr.GetDacChannel();
        
        //РЕЛЕ
        Adam4068 relay;
        int nDev_Tub_REL_channel;
        relay = mngr.GetRelay();
        nDev_Tub_REL_channel = mngr.GetRelayChannel();
        
        //Включаем менеджера, и зажигаем его лампочку
        mngr.SetEnabled( true);
        mngr.GetActiveLed().setIcon( theApp.GetResources().getLittleIconOn());
        
        if( theApp.GetMainSwitcher() == false) {
            logger.info( "Включение канала штенгеля заблокировано рубильником.");
            return;
        }
        
        //Ставим на соответствующий канал DAC 6.0 вольт
        dac.QueueSetChannelOutputValueCommand(
                nDev_Tub_DAC_channel, AMSConstants.DAC_CHANNEL_REG_ON_DAC_VOLT);
        
        //Включаем соответствующий канал реле
        relay.QueueSetRelayStateBitCommand(
                nDev_Tub_REL_channel, true);
        
        //посылаем запрос на состояния реле
        relay.QueueRequestDataCommand();
        
        //заблокируем управление соседнего канала
        theApp.GetDevManager_t(nDevice).BlockNeighbour();
    }
    
    /**
     * Выключение канала штенгеля для устройства nDevice
     * @param nDevice 
     */
    public void TurnOffTubuDevice( int nDevice) {
        if( !theApp.GetRxTx().IsActive()) return;
        
        //DEVICE.MANAGER
        AMSDeviceManager mngr = theApp.GetDevManager_t( nDevice);
        
        Adam4024 dac;
        int nDev_Tub_DAC_channel;
        dac = mngr.GetDAC();
        nDev_Tub_DAC_channel = mngr.GetDacChannel();
        
        //РЕЛЕ
        Adam4068 relay;
        int nDev_Tub_REL_channel;
        relay = mngr.GetRelay();
        nDev_Tub_REL_channel = mngr.GetRelayChannel();        
        
        
        //Выключаем соответствующий канал реле
        relay.QueueSetRelayStateBitCommand(
                nDev_Tub_REL_channel, false);
        
        //Ставим на соответствующий канал DAC 3.0 вольт
        dac.QueueSetChannelOutputValueCommand(
                nDev_Tub_DAC_channel, AMSConstants.DAC_CHANNEL_REG_OFF_DAC_VOLT);
        
        //Выключаем менеджера, и зажигаем его лампочку
        mngr.SetEnabled( false);
        mngr.GetActiveLed().setIcon( theApp.GetResources().getLittleIconOff());
        
        //посылаем запрос на состояния реле
        relay.QueueRequestDataCommand();
        
        //разблокируем управление соседнего канала
        theApp.GetDevManager_t(nDevice).UnBlockNeighbour();
    }
    
    /**
     * Включение каналов анодов всех устройств
     * @param evt 
     */
    private void btnAllAnodesOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllAnodesOnActionPerformed
        if( !theApp.GetRxTx().IsActive()) return;
        
        //for( int nDevice = AMSConstants.T_DEVICE1; nDevice < AMSConstants.T_DEVICE8; nDevice++) {
        Iterator it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();
            
            //DEVICE.MANAGER
            AMSDeviceManager mngr = theApp.GetDevManager_a( nDevice);
        
            //РЕЛЕ
            Adam4068 relay;
            int nDev_Ano_REL_channel;
            relay = mngr.GetRelay();
            nDev_Ano_REL_channel = mngr.GetRelayChannel();
        
            //ЦАП
            Adam4024 dac;
            int nDev_Ano_DAC_channel;
            dac = mngr.GetDAC();
            nDev_Ano_DAC_channel = mngr.GetDacChannel();
        
            //Включаем менеджера, и зажигаем его лампочку
            mngr.SetEnabled( true);
            mngr.GetActiveLed().setIcon( theApp.GetResources().getLittleIconOn());
        
            //Ставим на соответствующий канал DAC 6.0 вольт
            dac.QueueSetChannelOutputValueCommand(
                nDev_Ano_DAC_channel, AMSConstants.DAC_CHANNEL_REG_ON_DAC_VOLT);
            
            if( theApp.GetMainSwitcher() == true) {
                //Включаем соответствующий канал реле
                relay.QueueSetRelayStateBitCommand(
                nDev_Ano_REL_channel, true);
        
                //посылаем запрос на состояния реле
                relay.QueueRequestDataCommand();
        
                //заблокируем управление соседнего канала
                theApp.GetDevManager_a(nDevice).BlockNeighbour();
            }
            else {
                logger.info( "Включение канала анода заблокировано рубильником.");
                continue;
            }            
        }
    }//GEN-LAST:event_btnAllAnodesOnActionPerformed

    /**
     * Выключение каналов анодов всех устройств
     * @param evt 
     */
    private void btnAllAnodesOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllAnodesOffActionPerformed
        if( !theApp.GetRxTx().IsActive()) return;
        
        //for( int nDevice = AMSConstants.T_DEVICE1; nDevice < AMSConstants.T_DEVICE8; nDevice++) {
        Iterator it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();    
            //Switching relays
            try {
                int nRelDev = theApp.GetSettings().GetDev(nDevice).GetAnoRelDev();
                int nRelChannel = theApp.GetSettings().GetDev(nDevice).GetAnoRelChan();
                Adam4068 relay = theApp.m_devicesSet.GetRelay( nRelDev);
                relay.QueueSetRelayStateBitCommand( nRelChannel, false);
            } catch( Exception e) {
                logger.fatal( "При поочередном выключении всех каналов анодов, при работе с соответствующим реле, произошла исключительная ситуация!", e);
                return;
            }
        }
        
        
        //for( int nDevice = AMSConstants.T_DEVICE1; nDevice < AMSConstants.T_DEVICE8; nDevice++) {
        it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();    
            //Setting DAC values
            Adam4024 dac;
            try {
                //посылаем запрос на состояния реле
                int nRelDev = theApp.GetSettings().GetDev(nDevice).GetAnoRelDev();
                int nRelChannel = theApp.GetSettings().GetDev(nDevice).GetAnoRelChan();
                Adam4068 relay = theApp.m_devicesSet.GetRelay( nRelDev);
                relay.QueueRequestDataCommand();
            
                int nDacDev = theApp.GetSettings().GetDev(nDevice).GetAnoDacDev();
                int nDacChannel = theApp.GetSettings().GetDev(nDevice).GetAnoDacChan();
                dac = theApp.m_devicesSet.GetDAC( nDacDev);
                dac.QueueSetChannelOutputValueCommand( nDacChannel, AMSConstants.DAC_CHANNEL_REG_OFF_DAC_VOLT);
            }
            catch( Exception e) {
                logger.fatal( "При поочередном выключении всех каналов анодов, при работе с соответствующим ЦАП, произошла исключительная ситуация!", e);
                return;
            }
            
            
                
            //UnBlock neighbour device control
            theApp.GetDevManager_a(nDevice).UnBlockNeighbour();
            
            //выключаем менеджера и гасим его лампочку
            theApp.GetDevManager_a(nDevice).SetEnabled( false);
            theApp.GetDevManager_a(nDevice).GetActiveLed().setIcon( theApp.GetResources().getLittleIconOff());
        }
        
        
    }//GEN-LAST:event_btnAllAnodesOffActionPerformed

    /**
     * Включение каналов штенгелей всех устройств
     * @param evt 
     */
    private void btnAllTubusOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllTubusOnActionPerformed
        if( !theApp.GetRxTx().IsActive()) return;

        //for( int nDevice = AMSConstants.T_DEVICE1; nDevice < AMSConstants.T_DEVICE8; nDevice++) {
        Iterator it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();    
            
            //DEVICE.MANAGER
            AMSDeviceManager mngr = theApp.GetDevManager_t( nDevice);
        
            //ЦАП
            Adam4024 dac;
            int nDev_Tub_DAC_channel;
            dac = mngr.GetDAC();
            nDev_Tub_DAC_channel = mngr.GetDacChannel();
        
            //РЕЛЕ
            Adam4068 relay;
            int nDev_Tub_REL_channel;
            relay = mngr.GetRelay();
            nDev_Tub_REL_channel = mngr.GetRelayChannel();
        
            //Включаем менеджера, и зажигаем его лампочку
            mngr.SetEnabled( true);
            mngr.GetActiveLed().setIcon( theApp.GetResources().getLittleIconOn());
        
            if( theApp.GetMainSwitcher() == true) {
                //Ставим на соответствующий канал DAC 6.0 вольт
                dac.QueueSetChannelOutputValueCommand(
                        nDev_Tub_DAC_channel, AMSConstants.DAC_CHANNEL_REG_ON_DAC_VOLT);

                //Включаем соответствующий канал реле
                relay.QueueSetRelayStateBitCommand(
                        nDev_Tub_REL_channel, true);

                //посылаем запрос на состояния реле
                relay.QueueRequestDataCommand();

                //заблокируем управление соседнего канала
                theApp.GetDevManager_t(nDevice).BlockNeighbour();
            }
            else {
                logger.info( "Включение канала штенгеля заблокировано рубильником.");
                return;
            }
        }
    }//GEN-LAST:event_btnAllTubusOnActionPerformed

    /**
     * Выключение каналов штенгелей всех устройств
     * @param evt 
     */
    private void btnAllTubusOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllTubusOffActionPerformed
        if( !theApp.GetRxTx().IsActive()) return;
        
        //for( int nDevice = AMSConstants.T_DEVICE1; nDevice < AMSConstants.T_DEVICE8; nDevice++) {
        Iterator it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();    
            //Switching relays
            try {
                int nRelDev = theApp.GetSettings().GetDev(nDevice).GetTubRelDev();
                int nRelChannel = theApp.GetSettings().GetDev(nDevice).GetTubRelChan();
                Adam4068 relay = theApp.m_devicesSet.GetRelay( nRelDev);
                relay.QueueSetRelayStateBitCommand( nRelChannel, false);
            } catch( Exception e) {
                logger.fatal( "При поочередном выключении всех каналов штенгелей, при работе с соответствующим реле, произошла исключительная ситуация!", e);
                return;
            }
        }
        
        //for( int nDevice = AMSConstants.T_DEVICE1; nDevice < AMSConstants.T_DEVICE8; nDevice++) {
        it = AMSConstants.getInstance().T_DEVICES.iterator();
        while( it.hasNext()) {
            int nDevice = ( int) it.next();    
            //Setting DAC values
            Adam4024 dac;
            try {
                int nDacDev = theApp.GetSettings().GetDev(nDevice).GetTubDacDev();
                int nDacChannel = theApp.GetSettings().GetDev(nDevice).GetTubDacChan();
                dac = theApp.m_devicesSet.GetDAC( nDacDev);
                dac.QueueSetChannelOutputValueCommand( nDacChannel, AMSConstants.DAC_CHANNEL_REG_OFF_DAC_VOLT);
                
                //посылаем запрос на состояния реле
                Adam4068 relay;
                int nDev_Tub_REL_channel;
                relay = theApp.GetDevManager_t(nDevice).GetRelay();
                nDev_Tub_REL_channel = theApp.GetDevManager_t(nDevice).GetRelayChannel(); 
                relay.QueueRequestDataCommand();
            }
            catch( Exception e) {
                logger.fatal( "При поочередном выключении всех каналов штенгелей, при работе с соответствующим ЦАП, произошла исключительная ситуация!", e);
                return;
            }
            
            //UnBlock neighbour device control
            theApp.GetDevManager_t(nDevice).UnBlockNeighbour();
            
            //выключаем менеджера и гасим его лампочку
            theApp.GetDevManager_t(nDevice).SetEnabled( false);
            theApp.GetDevManager_t(nDevice).GetActiveLed().setIcon( theApp.GetResources().getLittleIconOff());
        }
    }//GEN-LAST:event_btnAllTubusOffActionPerformed

    /**
     * Нажатие кнопки "Номера испытуемых приборов"
     * @param evt 
     */
    private void btnSetDevSerNumsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDevSerNumsActionPerformed
        DlgDevicesSerialNumbers dlg = new DlgDevicesSerialNumbers( theApp.m_pMainWnd, true, theApp);
        dlg.setVisible( true);
    }//GEN-LAST:event_btnSetDevSerNumsActionPerformed

    /**
     * Нажатие кнопки "Калибровка"
     * @param evt 
     */
    private void btnCalibrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalibrationActionPerformed
        theApp.GetCalibration().CallCalibrationDialog();
    }//GEN-LAST:event_btnCalibrationActionPerformed

    private void btnVibroOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVibroOnActionPerformed
        TurnVibroOn();
    }//GEN-LAST:event_btnVibroOnActionPerformed

    public void TurnVibroOn() {
        //включаем канал реле для вибрации
        try {
            Adam4068 relayVibro = theApp.m_devicesSet.GetRelay( AMSConstants.REL3);
            int nVibroChan = theApp.GetSettings().GetREL3_Chan_Vib();
            relayVibro.QueueSetRelayStateBitCommand( nVibroChan, true);
            theApp.m_nVibration = 1;
            
            //посылаем запрос на состояния реле
            relayVibro.QueueRequestDataCommand();
        }
        catch( Exception ex) {
            logger.error( "При попытке включить реле вибрации произошла исключительная ситуация.", ex);
        }
    }
    
    private void btnVibroOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVibroOffActionPerformed
        TurnVibroOff();
    }//GEN-LAST:event_btnVibroOffActionPerformed

    public void TurnVibroOff() {
        //выключаем канал реле для вибрации
        try {
            Adam4068 relayVibro = theApp.m_devicesSet.GetRelay( AMSConstants.REL3);
            int nVibroChan = theApp.GetSettings().GetREL3_Chan_Vib();
            relayVibro.QueueSetRelayStateBitCommand( nVibroChan, false);
            theApp.m_nVibration = 0;
            
            //посылаем запрос на состояния реле
            relayVibro.QueueRequestDataCommand();
        }
        catch( Exception ex) {
            logger.error( "При попытке выключить реле вибрации произошла исключительная ситуация.", ex);
        }
    }
    
    private void btnFanOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFanOnActionPerformed
        TurnFanOn();
    }//GEN-LAST:event_btnFanOnActionPerformed

    public void TurnFanOn() {
        //включаем канал реле для вентилятора
        try {
            Adam4068 relayFan = theApp.m_devicesSet.GetRelay( AMSConstants.REL3);
            int nFanChan = theApp.GetSettings().GetREL3_Chan_Fan();
            relayFan.QueueSetRelayStateBitCommand( nFanChan, true);
            
            //посылаем запрос на состояния реле
            relayFan.QueueRequestDataCommand();
        }
        catch( Exception ex) {
            logger.error( "При попытке включить реле вентилятора произошла исключительная ситуация.", ex);
        }
    }
    
    private void btnFanOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFanOffActionPerformed
        TurnFanOff();
    }//GEN-LAST:event_btnFanOffActionPerformed

    private void btn04mAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn04mAActionPerformed
        theApp.SetOuterCurrent( 400);
        refreshControlsState();
    }//GEN-LAST:event_btn04mAActionPerformed

    private void btnLogTraceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogTraceActionPerformed
        logger.fatal( "SWITCHING LOGGING TO TRACE");
        Logger.getRootLogger().setLevel( Level.TRACE);
    }//GEN-LAST:event_btnLogTraceActionPerformed

    private void btnLogDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogDebugActionPerformed
        logger.fatal( "SWITCHING LOGGING TO DEBUG");
        Logger.getRootLogger().setLevel( Level.DEBUG);
    }//GEN-LAST:event_btnLogDebugActionPerformed

    private void btnLogInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInfoActionPerformed
        logger.fatal( "SWITCHING LOGGING TO INFO");
        Logger.getRootLogger().setLevel( Level.INFO);
    }//GEN-LAST:event_btnLogInfoActionPerformed

    private void btnLogWarnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogWarnActionPerformed
        logger.fatal( "SWITCHING LOGGING TO WARN");
        Logger.getRootLogger().setLevel( Level.WARN);
    }//GEN-LAST:event_btnLogWarnActionPerformed

    private void btnLogErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogErrorActionPerformed
        logger.fatal( "SWITCHING LOGGING TO ERROR");
        Logger.getRootLogger().setLevel( Level.ERROR);
    }//GEN-LAST:event_btnLogErrorActionPerformed

    private void btnLogFatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogFatalActionPerformed
        logger.fatal("SWITCHING LOGGING TO ERROR");
        Logger.getRootLogger().setLevel( Level.FATAL);
    }//GEN-LAST:event_btnLogFatalActionPerformed

    public void TurnFanOff() {
        //выключаем канал реле для вентилятора
        try {
            Adam4068 relayFan = theApp.m_devicesSet.GetRelay( AMSConstants.REL3);
            int nFanChan = theApp.GetSettings().GetREL3_Chan_Fan();
            relayFan.QueueSetRelayStateBitCommand( nFanChan, false);
            
            //посылаем запрос на состояния реле
            relayFan.QueueRequestDataCommand();
        }
        catch( Exception ex) {
            logger.error( "При попытке выключить реле вентилятора произошла исключительная ситуация.", ex);
        }
    }
    
    /*
    public static void main(String args[]) {
        BasicConfigurator.configure();
        logger.setLevel( AMSApp.LOG_LEVEL);
        
        logger.trace( "trace");
        logger.debug( "debug");
        logger.info( "info");
        logger.warn( "warn");
        logger.error( "error");
        logger.fatal( "fatal");
        
        // Set the Nimbus look and feel
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        // If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        // For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
        //
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame3().setVisible(true);
            }
        });
    }
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn04mA;
    private javax.swing.JButton btn05mA;
    private javax.swing.JButton btn10mA;
    private javax.swing.JButton btn15mA;
    private javax.swing.JButton btn20mA;
    private javax.swing.JButton btn25mA;
    private javax.swing.JButton btn30mA;
    private javax.swing.JButton btnAllAnodesOff;
    private javax.swing.JButton btnAllAnodesOn;
    private javax.swing.JButton btnAllTubusOff;
    private javax.swing.JButton btnAllTubusOn;
    private javax.swing.JButton btnCalibration;
    private javax.swing.JButton btnDev1_Anode_Off;
    private javax.swing.JButton btnDev1_Anode_On;
    private javax.swing.JButton btnDev1_Tubu_Off;
    private javax.swing.JButton btnDev1_Tubu_On;
    private javax.swing.JButton btnDev2_Anode_Off;
    private javax.swing.JButton btnDev2_Anode_On;
    private javax.swing.JButton btnDev2_Tubu_Off;
    private javax.swing.JButton btnDev2_Tubu_On;
    private javax.swing.JButton btnDev3_Anode_Off;
    private javax.swing.JButton btnDev3_Anode_On;
    private javax.swing.JButton btnDev3_Tubu_Off;
    private javax.swing.JButton btnDev3_Tubu_On;
    private javax.swing.JButton btnDev4_Anode_Off;
    private javax.swing.JButton btnDev4_Anode_On;
    private javax.swing.JButton btnDev4_Tubu_Off;
    private javax.swing.JButton btnDev4_Tubu_On;
    private javax.swing.JButton btnDev5_Anode_Off;
    private javax.swing.JButton btnDev5_Anode_On;
    private javax.swing.JButton btnDev5_Tubu_Off;
    private javax.swing.JButton btnDev5_Tubu_On;
    private javax.swing.JButton btnDev6_Anode_Off;
    private javax.swing.JButton btnDev6_Anode_On;
    private javax.swing.JButton btnDev6_Tubu_Off;
    private javax.swing.JButton btnDev6_Tubu_On;
    private javax.swing.JButton btnDev7_Anode_Off;
    private javax.swing.JButton btnDev7_Anode_On;
    private javax.swing.JButton btnDev7_Tubu_Off;
    private javax.swing.JButton btnDev7_Tubu_On;
    private javax.swing.JButton btnDev8_Anode_Off;
    private javax.swing.JButton btnDev8_Anode_On;
    private javax.swing.JButton btnDev8_Tubu_Off;
    private javax.swing.JButton btnDev8_Tubu_On;
    private javax.swing.JButton btnFanOff;
    private javax.swing.JButton btnFanOn;
    private javax.swing.ButtonGroup btnGroupLogLevel;
    private javax.swing.JToggleButton btnLogDebug;
    private javax.swing.JToggleButton btnLogError;
    private javax.swing.JToggleButton btnLogFatal;
    private javax.swing.JToggleButton btnLogInfo;
    private javax.swing.JToggleButton btnLogTrace;
    private javax.swing.JToggleButton btnLogWarn;
    private javax.swing.JButton btnOuterCurrentSpinDown;
    private javax.swing.JButton btnOuterCurrentSpinUp;
    private javax.swing.JButton btnRegimeEdge;
    private javax.swing.JButton btnRegimeManual;
    private javax.swing.JButton btnRegimeProcessing;
    private javax.swing.JButton btnRegimeUIC;
    private javax.swing.JButton btnSetDevSerNums;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton btnSwitchOff;
    private javax.swing.JButton btnSwitchOn;
    private javax.swing.JToggleButton btnToggleBlockScreen;
    private javax.swing.JButton btnVibroOff;
    private javax.swing.JButton btnVibroOn;
    private javax.swing.JTextField edtDev1_Ano_Current;
    private javax.swing.JTextField edtDev1_Ano_Voltage;
    private javax.swing.JTextField edtDev1_Tub_Current;
    private javax.swing.JTextField edtDev1_Tub_Voltage;
    private javax.swing.JTextField edtDev2_Ano_Current;
    private javax.swing.JTextField edtDev2_Ano_Voltage;
    private javax.swing.JTextField edtDev2_Tub_Current;
    private javax.swing.JTextField edtDev2_Tub_Voltage;
    private javax.swing.JTextField edtDev3_Ano_Current;
    private javax.swing.JTextField edtDev3_Ano_Voltage;
    private javax.swing.JTextField edtDev3_Tub_Current;
    private javax.swing.JTextField edtDev3_Tub_Voltage;
    private javax.swing.JTextField edtDev4_Ano_Current;
    private javax.swing.JTextField edtDev4_Ano_Voltage;
    private javax.swing.JTextField edtDev4_Tub_Current;
    private javax.swing.JTextField edtDev4_Tub_Voltage;
    private javax.swing.JTextField edtDev5_Ano_Current;
    private javax.swing.JTextField edtDev5_Ano_Voltage;
    private javax.swing.JTextField edtDev5_Tub_Current;
    private javax.swing.JTextField edtDev5_Tub_Voltage;
    private javax.swing.JTextField edtDev6_Ano_Current;
    private javax.swing.JTextField edtDev6_Ano_Voltage;
    private javax.swing.JTextField edtDev6_Tub_Current;
    private javax.swing.JTextField edtDev6_Tub_Voltage;
    private javax.swing.JTextField edtDev7_Ano_Current;
    private javax.swing.JTextField edtDev7_Ano_Voltage;
    private javax.swing.JTextField edtDev7_Tub_Current;
    private javax.swing.JTextField edtDev7_Tub_Voltage;
    private javax.swing.JTextField edtDev8_Ano_Current;
    private javax.swing.JTextField edtDev8_Ano_Voltage;
    private javax.swing.JTextField edtDev8_Tub_Current;
    private javax.swing.JTextField edtDev8_Tub_Voltage;
    private javax.swing.JTextField edtHyroNum1;
    private javax.swing.JTextField edtHyroNum2;
    private javax.swing.JTextField edtHyroNum3;
    private javax.swing.JTextField edtHyroNum4;
    private javax.swing.JTextField edtHyroNum5;
    private javax.swing.JTextField edtHyroNum6;
    private javax.swing.JTextField edtHyroNum7;
    private javax.swing.JTextField edtHyroNum8;
    private javax.swing.JTextField edtOuterCurrent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel lblDev1_anode;
    public javax.swing.JLabel lblDev1_tubu;
    public javax.swing.JLabel lblDev2_anode;
    public javax.swing.JLabel lblDev2_tubu;
    public javax.swing.JLabel lblDev3_anode;
    public javax.swing.JLabel lblDev3_tubu;
    public javax.swing.JLabel lblDev4_anode;
    public javax.swing.JLabel lblDev4_tubu;
    public javax.swing.JLabel lblDev5_anode;
    public javax.swing.JLabel lblDev5_tubu;
    public javax.swing.JLabel lblDev6_anode;
    public javax.swing.JLabel lblDev6_tubu;
    public javax.swing.JLabel lblDev7_anode;
    public javax.swing.JLabel lblDev7_tubu;
    public javax.swing.JLabel lblDev8_anode;
    public javax.swing.JLabel lblDev8_tubu;
    private javax.swing.JLabel lblFan;
    private javax.swing.JLabel lblMainSwitch;
    private javax.swing.JLabel lblManagementTitle;
    public javax.swing.JLabel lblThreadExecutor;
    public javax.swing.JLabel lblThreadPoller;
    public javax.swing.JLabel lblThreadRState;
    public javax.swing.JLabel lblThreadWState;
    private javax.swing.JLabel lblVibration;
    private javax.swing.JLabel lbl_Dev1_Anode_RelState;
    private javax.swing.JLabel lbl_Dev1_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev2_Anode_RelState;
    private javax.swing.JLabel lbl_Dev2_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev3_Anode_RelState;
    private javax.swing.JLabel lbl_Dev3_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev4_Anode_RelState;
    private javax.swing.JLabel lbl_Dev4_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev5_Anode_RelState;
    private javax.swing.JLabel lbl_Dev5_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev6_Anode_RelState;
    private javax.swing.JLabel lbl_Dev6_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev7_Anode_RelState;
    private javax.swing.JLabel lbl_Dev7_Tubu_RelState;
    private javax.swing.JLabel lbl_Dev8_Anode_RelState;
    private javax.swing.JLabel lbl_Dev8_Tubu_RelState;
    private javax.swing.JPanel pnlAdditionalPanel;
    private javax.swing.JSlider sldOuterCurrent;
    // End of variables declaration//GEN-END:variables
}
