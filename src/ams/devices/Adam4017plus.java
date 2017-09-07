/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSApp;
import ams.AMSAverager;
import ams.AMSConstants;
import ams.AMSDeviceManager;
import ams.serial.CheckSumm;
import ams.serial.TwoWaySerialComm;
import java.awt.Color;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 * Class for Adam4017plus (ADC) device
 * @author yaroslav
 */
public class Adam4017plus extends AbstractDevice {
    private final AMSApp theApp;
    
    private final static int MAX_FAILS_VISUALIZATION = 5;
    
    private HashMap m_dblChannelValues;
    private HashMap m_nChannelFailsCounter;
    private HashMap m_nChannelDescriptors;
    
    /**
     * Функция позволяет узнать что измеряет заданный канал этого устройства.
     * @return
     * измеряемый параметр.
     * Может иметь значения:<br>
     * ADC_UNKNOWN<br>
     * ADC_ANODE_VOLTAGE<br>
     * ADC_TUBULATION_VOLTAGE<br>
     * ADC_ANODE_CURRENT<br>
     * ADC_TUBULATION_CURRENT
     */
    public int GetChannelDescriptor( int nChannel) {
        if( nChannel < AMSConstants.CHANNEL1 || nChannel > AMSConstants.CHANNEL8)
            return AMSConstants.ADC_UNKNOWN;        
        int nDescriptor = ( int) m_nChannelDescriptors.get( nChannel);
        return nDescriptor;
    }
    
    public int GetChannel1Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL1); }
    public int GetChannel2Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL2); }
    public int GetChannel3Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL3); }
    public int GetChannel4Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL4); }
    public int GetChannel5Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL5); }
    public int GetChannel6Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL6); }
    public int GetChannel7Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL7); }
    public int GetChannel8Descriptor() { return GetChannelDescriptor( AMSConstants.CHANNEL8); }
    
    public void SetChannelDescriptor( int nChannel, int nChannelDescription) throws Exception {
        if( nChannel < AMSConstants.CHANNEL1 && nChannel > AMSConstants.CHANNEL8) {
            throw new Exception( "Неверный номер канала '" + nChannel + "' при вызове функции SetChannelDecriptor(...)");
        }
        
        switch( nChannelDescription) {
            case AMSConstants.ADC_ANODE_VOLTAGE:
            case AMSConstants.ADC_TUBULATION_VOLTAGE:
            case AMSConstants.ADC_ANODE_CURRENT:
            case AMSConstants.ADC_TUBULATION_CURRENT:
            break;
            
            default:
                throw new Exception( "Неверный дескриптор канала '" + nChannelDescription + "' при вызове функции SetChannelDecriptor(...)");
    
        }
        
        m_nChannelDescriptors.put( nChannel, nChannelDescription);
        
    }
    
    public void SetChannel1Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL1, nChannelDescription); }
    public void SetChannel2Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL2, nChannelDescription); }
    public void SetChannel3Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL3, nChannelDescription); }
    public void SetChannel4Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL4, nChannelDescription); }
    public void SetChannel5Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL5, nChannelDescription); }
    public void SetChannel6Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL6, nChannelDescription); }
    public void SetChannel7Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL7, nChannelDescription); }
    public void SetChannel8Descriptor( int nChannelDescription) throws Exception { SetChannelDescriptor( AMSConstants.CHANNEL8, nChannelDescription); }
    
    /**
     * Check if this ADC instance controls voltages
     * @param nChannel
     * @return 
     * true  - controls voltages<br>
     * false - does not controls voltages<br>
     */
    public boolean IsVoltageChannel( int nChannel) {
        int nChannelDescriptor = GetChannelDescriptor( nChannel);
        return ( nChannelDescriptor == AMSConstants.ADC_ANODE_VOLTAGE ||
                    nChannelDescriptor == AMSConstants.ADC_TUBULATION_VOLTAGE);
    }
    
    /**
     * Check if this ADC instance controls currents
     * @return 
     * true  - controls currents<br>
     * false - does not controls currents<br>
     */
    public boolean IsCurrentChannel( int nChannel) {
        int nChannelDescriptor = GetChannelDescriptor( nChannel);
        return ( nChannelDescriptor == AMSConstants.ADC_ANODE_CURRENT ||
                    nChannelDescriptor == AMSConstants.ADC_TUBULATION_CURRENT);
    }

    /**
     * Элементы для измерения частоты опроса АЦП
     */
    private Date m_dtm1;
    private final AMSAverager m_avgT;
    public AMSAverager GetAveragerT() { return m_avgT;}
    
    
    
    private final HashMap m_avgChannels;
    public AMSAverager GetAveragerChannel( int nChannel) throws Exception {
        if( nChannel < AMSConstants.CHANNEL1 && nChannel > AMSConstants.CHANNEL8) {
            throw new Exception( "Неверный номер канала '" + nChannel + "' при вызове функции SetChannelDecriptor(...)");
        }
        return ( ( AMSAverager) m_avgChannels.get( nChannel));
    }
    
    
    /**
     * Calculate physical value of measured parameter
     * @param dblValInCodes
     * measured voltage
     * @param nChannel
     * channel
     * @return
     * calibrated physical value of measured parameter
     */
    public double PhysValueCalculation( double dblValInCodes, int nChannel) {
        double m_dblA = theApp.GetCalibration().GetCalibUnit( GetDeviceDescriptor()).GetSlope( nChannel);
        double m_dblB = theApp.GetCalibration().GetCalibUnit( GetDeviceDescriptor()).GetShift( nChannel);
        
        /*
        double dblResult = dblValInCodes * m_dblPhysValueCoefficient[nChannel] + m_dblPhysValuePiedestal[nChannel];
        logger.info( "nChannel=" + nChannel);
        logger.info( "m_dblPhysValueCoefficient=" + m_dblPhysValueCoefficient[nChannel]);
        logger.info( "m_dblPhysValuePiedestal=" + m_dblPhysValuePiedestal[nChannel]);
        logger.info( "dblResult=" + dblResult);
        */
        
        double dblResult = dblValInCodes * m_dblA + m_dblB;
        logger.trace( "nChannel=" + nChannel);
        logger.trace( "m_dblPhysValueCoefficient=" + m_dblA);
        logger.trace( "m_dblPhysValuePiedestal=" + m_dblB);
        logger.trace( "dblResult=" + dblResult);
        
        return dblResult;
    }
    
    private String m_strVisualizationFormat;
    /**
     * Set physics value format for edit boxes visualization
     * @param strNewFormat 
     * format as in println <br>
     * for example "%.3f" will show three digits of value after decimal point
     */
    public void  SetVisualizationFormat( String strNewFormat) {
        m_strVisualizationFormat = strNewFormat;
    }
    
    static Logger logger = Logger.getLogger( Adam4017plus.class);

    /**
     * Constructor of Adam4017plus device instance
     * @param app
     * the application
     * @throws java.lang.Exception
     */
    public Adam4017plus( AMSApp app, int nDeviceDescriptor) throws Exception {
        theApp = app;
        //logger.setLevel( AMSApp.LOG_LEVEL);
    
        if( nDeviceDescriptor != AMSConstants.ADC1 &&
                nDeviceDescriptor != AMSConstants.ADC2 &&
                nDeviceDescriptor != AMSConstants.ADC3 &&
                nDeviceDescriptor != AMSConstants.ADC4) {
            throw new Exception( "Некорректный DeviceDescriptor!");
        }
        
        SetDeviceDescriptor( nDeviceDescriptor);
        
        m_strVisualizationFormat = "%.3f";
        
        m_nChannelDescriptors = new HashMap();
        m_dblChannelValues = new HashMap();
        m_nChannelFailsCounter = new HashMap();
        m_avgChannels = new HashMap();
        
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
            m_nChannelDescriptors.put( i, AMSConstants.ADC_UNKNOWN);
            m_dblChannelValues.put( i, 0.);
            m_nChannelFailsCounter.put( i, 0);
            m_avgChannels.put( i, new AMSAverager( 1));
        }
        
    
        m_avgT = new AMSAverager( 10);
    }
    
    /**
     * Set statistic amount for averager
     * @param nNewStatistic 
     */
    public void SetManagingStatistic( int nNewStatistic) {
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
            AMSAverager avg = ( AMSAverager) m_avgChannels.get( i);
            avg.SetStatistic( nNewStatistic);
        }
    }
    
    /**
     * Process device response for get channels values command
     * @param strCmd
     * outgoing get channels values command
     * @param strResponse 
     * incoming response
     */
    public void ProcessResponseGetCommand( String strCmd, String strResponse) {
        if( strResponse.length() < 10) {
            logger.error( "Ответ на команду запроса [" + strResponse + "] меньше чем 10 символов.");
            return;
        }
        
        if( strResponse.equals( strCmd)) {
            logger.error( "Ответ совпадает с командой запроса!");
            return;
        }
    
        
        if( m_dtm1 == null) {
            m_dtm1 = new Date();
        }
        else {
            Date dtm2 = new Date();
            long delay = dtm2.getTime() - m_dtm1.getTime();
            m_avgT.AddValue( 1000. / ( double) delay);
            
            m_dtm1 = dtm2;
        }
                
        //отбросим чексумму
        strResponse = strResponse.substring( 0, strResponse.length() - 2);
        
        int nLeft = 1;
        int nRightPl, nRightMi, nRight;
        for( int i = 0; i<8; i++) {
            nRightPl = strResponse.indexOf( '+', nLeft + 1);
            nRightMi = strResponse.indexOf( '-', nLeft + 1);
            
            if( ( nRightPl * nRightMi) < 0)
                nRight = max( nRightPl, nRightMi);
            else if( nRightPl < 0)
                nRight = strResponse.length();
            else
                nRight = min( nRightPl, nRightMi);
            
            
            String strMid = strResponse.substring( nLeft, nRight);
            
            //к нам приходит число с четырьмя знаками после запятой, отбросим последний
            strMid = strResponse.substring( nLeft, nRight - 1);
            
            double dblVal = Double.parseDouble( strMid);
            
            
            int nChannel = -1;
            switch( i) {
                case 0: nChannel = AMSConstants.CHANNEL1; break;
                case 1: nChannel = AMSConstants.CHANNEL2; break;
                case 2: nChannel = AMSConstants.CHANNEL3; break;
                case 3: nChannel = AMSConstants.CHANNEL4; break;
                case 4: nChannel = AMSConstants.CHANNEL5; break;
                case 5: nChannel = AMSConstants.CHANNEL6; break;
                case 6: nChannel = AMSConstants.CHANNEL7; break;
                case 7: nChannel = AMSConstants.CHANNEL8; break;
            }
            
            if( nChannel != -1) {
                double dblPhysValue = PhysValueCalculation( dblVal, nChannel);
                m_dblChannelValues.put( nChannel, dblVal);
                try {
                    GetAveragerChannel( nChannel).AddValue( dblPhysValue);
                } catch (Exception ex) {
                    logger.error( "При получении осреднителя значения для канала произошёл Exception", ex);
                }
            }
            
            
            nLeft = nRight;
        }
        
        //ОБРАБОТАЕМ ПОЛУЧЕННЫЕ ЗНАЧЕНИЯ
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
            theApp.ManageAdcChannel( this, i);
        }
        
        //ОТОБРАЖЕНИЕ ЗНАЧЕНИЙ
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
            JTextField edtTextOut;
            double dblPhysValue = PhysValueCalculation( ( double) m_dblChannelValues.get(i), i);
            
            if( IsVoltageChannel( i)) {
                
                AMSDeviceManager processor = null;
                int nTDev = -1;
                        
                if( theApp.GetDevManager_0_a().GetADCVoltage()== this && theApp.GetDevManager_0_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE1;
                    processor = theApp.GetDevManager_0_a();
                }
                //if( theApp.GetDevManager_0_t().GetADCVoltage()== this && theApp.GetDevManager_0_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_0_t();
                
                if( theApp.GetDevManager_1_a().GetADCVoltage()== this && theApp.GetDevManager_1_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE2;
                    processor = theApp.GetDevManager_1_a();
                }
                //if( theApp.GetDevManager_1_t().GetADCVoltage()== this && theApp.GetDevManager_1_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_1_t();
        
                if( theApp.GetDevManager_2_a().GetADCVoltage()== this && theApp.GetDevManager_2_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE3;
                    processor = theApp.GetDevManager_2_a();
                }
                //if( theApp.GetDevManager_2_t().GetADCVoltage()== this && theApp.GetDevManager_2_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_2_t();
                
                if( theApp.GetDevManager_3_a().GetADCVoltage()== this && theApp.GetDevManager_3_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE4;
                    processor = theApp.GetDevManager_3_a();
                }
                //if( theApp.GetDevManager_3_t().GetADCVoltage()== this && theApp.GetDevManager_3_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_3_t();
                
                if( theApp.GetDevManager_4_a().GetADCVoltage()== this && theApp.GetDevManager_4_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE5;
                    processor = theApp.GetDevManager_4_a();
                }
                //if( theApp.GetDevManager_4_t().GetADCVoltage()== this && theApp.GetDevManager_4_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_4_t();
                
                if( theApp.GetDevManager_5_a().GetADCVoltage()== this && theApp.GetDevManager_5_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE6;
                    processor = theApp.GetDevManager_5_a();
                }
                //if( theApp.GetDevManager_5_t().GetADCVoltage()== this && theApp.GetDevManager_5_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_5_t();
        
                if( theApp.GetDevManager_6_a().GetADCVoltage()== this && theApp.GetDevManager_6_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE7;
                    processor = theApp.GetDevManager_6_a();
                }
                //if( theApp.GetDevManager_6_t().GetADCVoltage()== this && theApp.GetDevManager_6_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_6_t();
                
                if( theApp.GetDevManager_7_a().GetADCVoltage()== this && theApp.GetDevManager_7_a().GetAdcVoltageChannel() == i) {
                    nTDev = AMSConstants.T_DEVICE8;
                    processor = theApp.GetDevManager_7_a();
                }
                //if( theApp.GetDevManager_7_t().GetADCVoltage()== this && theApp.GetDevManager_7_t().GetAdcVoltageChannel() == i) processor = theApp.GetDevManager_7_t();

                if( processor != null) {
                    
                    //измеренный ток
                    double dblMeasuredCurrent_mcA = 0.;
                
                    try {
                        dblMeasuredCurrent_mcA = processor.GetADCCurrent().GetAveragerChannel( processor.GetAdcCurrentChannel()).GetAverage();
                    } catch (Exception ex) {
                        logger.error( "Exception при получении объектов АЦП токов и напряжений", ex);
                    }
                    
                    if( theApp.GetNewAnoCalib().GetUsage( nTDev) == true) {
                        logger.trace( "Работаем по новой калибровке U=f(u_adc, i)");
                        dblPhysValue = theApp.GetNewAnoCalib().GetResult( nTDev, dblMeasuredCurrent_mcA, ( double) m_dblChannelValues.get(i));
                    }
                    else {
                        logger.debug( "Работаем по старой калибровке U=f(u_adc)");
                        //РАБОТАЕМ ПО СТАРОЙ КАЛИБРОВКЕ - КОРРЕКТИРУЕМСЯ НА СОПРОТИВЛЕНИЕ
                        logger.debug( ">>>>>>> POI: dblPhysValue (V)=" + dblPhysValue);
                        logger.debug( ">>>>>>> POI: dblMeasuredCurrent_mcA (mcA)=" + dblMeasuredCurrent_mcA);
                    
                        dblPhysValue -= 0.395 * dblMeasuredCurrent_mcA;
                    
                        logger.debug( ">>>>>>> POI: Corrected Voltage (V)=" + dblPhysValue);
                    }
                }
                
                
                if( dblPhysValue < AMSConstants.ADC_VALIDATION_EDGE_VOLTAGE_LOW) dblPhysValue = 0.;
                if( dblPhysValue > AMSConstants.ADC_VALIDATION_EDGE_VOLTAGE_HIGH) dblPhysValue = AMSConstants.ADC_VALIDATION_EDGE_VOLTAGE_HIGH;
            }
            else if( IsCurrentChannel( i)) {
                if( dblPhysValue < AMSConstants.ADC_VALIDATION_EDGE_CURRENT_LOW) dblPhysValue = 0.;
            }
            else {
                logger.error( "Не смог определить параметр, измеряемый этим каналом АЦП. Напряжение или ток?");
            }        
        
            edtTextOut = ( ( JTextField) m_vctVisualComponents.get( i));
            if( edtTextOut != null) edtTextOut.setText( String.format( m_strVisualizationFormat, dblPhysValue));
            
            int nFail = ( int) m_nChannelFailsCounter.get(i);
            if( nFail > 0) m_nChannelFailsCounter.put( i, 0);
        }
        
        //разукрасим фон EDITBOX'ов отображающих наши значения
        paintBgVisualComponents();
    }
    
    /**
     * Paint output visual component backgrounds<br>
     * If there were more than MAX_FAILS_VISUALIZATION failures, background will be rose-red
     */
    public void paintBgVisualComponents() {
        JTextField edtTxtBoxOut;
    
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
            edtTxtBoxOut = ( ( JTextField) m_vctVisualComponents.get( i));
            int nFail = ( int) m_nChannelFailsCounter.get( i);
            if( nFail >= MAX_FAILS_VISUALIZATION)
                if( edtTxtBoxOut != null) edtTxtBoxOut.setBackground( new Color( 210, 110, 110));  //bgRed
            else            
                if( edtTxtBoxOut != null) edtTxtBoxOut.setBackground( new Color( 237, 236, 235));  //bgWhite
        }
    }
    
    /**
     * Process response for this device
     * @param strCmd
     * Outgoing command
     * @param strResponse 
     * Incoming response
     */
    @Override
    public void ProcessResponse( String strCmd, String strResponse) {
        logger.trace( "ProcessResponse(): " + "strCmd='" + strCmd + "  strResponse: " + strResponse);
        
        boolean bCSOk;
        
        if( GetCSEnabled()) {
            
            if( CheckSumm.CheckCheckSumm( strResponse)) {
                logger.trace( "ProcessResponse(): CS control is enabled and it's correct");
                bCSOk = true;
            }
            else {
                logger.warn( "ProcessResponse(): CS control is enabled and it's failed");
                bCSOk = false;
            }
        }
        else {
            logger.debug( "ProcessResponse(): CS control disabled");
            bCSOk = true;
        }
        
        
        String strFirstSymb = strCmd.substring( 0, 1);
        switch( strFirstSymb) {
            case "%":
                logger.debug( "ProcessResponse(): Response for initialization command! WHAT TODO? not implemented...");
            break;
                        
            case "#":
                logger.trace( "ProcessResponse(): Response for get command!");
                if( bCSOk)
                    ProcessResponseGetCommand( strCmd, strResponse);
                else {
                    for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
                        int nFails = ( int) m_nChannelFailsCounter.get( i);
                        m_nChannelFailsCounter.put( i, ++nFails);
                    }
                    
                    paintBgVisualComponents();
                }
                theApp.GetRxTx().AddCommandToQueue( GetRequestDataCommand(), this);
            break;
        }
    }

    /**
     * Get command (ascii) to request all channels values
     * @return 
     */
    @Override
    public String GetRequestDataCommand() {
        String strResult = "#" + GetAddress();
        if( GetCSEnabled())
            strResult += CheckSumm.calcCheckSumm( strResult);
        
        return strResult;
    }

    /**
     * Process timeout for transmitted command 
     * @param strCmd
     * transmitted command that got timeout
     */
    @Override
    public void ProcessTimeOut( String strCmd) {
        
        logger.error( "ProcessTimeOut(): Timeout happens for command '" + strCmd + "'");
        
        String strFirstSymb = strCmd.substring( 0, 1);
        switch( strFirstSymb) {
            case "%":
                logger.debug( "ProcessTimeOut(): Timeout on initialization command! WHAT TODO? not implemented...");
            break;
                        
            case "#":
                logger.debug( "ProcessTimeOut(): Timeout on get command!");
                
                //ProcessTimeOutGetCommand( strCmd, strResponse);
                for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
                    int nFails = ( int) m_nChannelFailsCounter.get( i);
                    m_nChannelFailsCounter.put( i, ++nFails);
                }
                    
                paintBgVisualComponents();
                
                TwoWaySerialComm rxtx = theApp.GetRxTx();
                if( rxtx != null)
                    rxtx.AddCommandToQueue( GetRequestDataCommand(), this);
                else
                    logger.error( "При обработке команды GETDATA при попытке её повторной постановки в очередь, оказалось что объекта общения с железом нет! ");
            break;
                
            default:
                logger.error( "Timeout on unknown command!");
            break;
        }

    }

    /**
     * Set visual component (editbox) for channel value output
     * @param nChannel
     * ADC channel
     * @param comp 
     * component that have setText("") method
     */
    @Override
    public void AddVisualComponent(int nChannel, JComponent comp) {
        m_vctVisualComponents.put( nChannel, comp);
    }
    
    public void SetChannel1Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL1, lbl); }    
    public void SetChannel2Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL2, lbl); }
    public void SetChannel3Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL3, lbl); }
    public void SetChannel4Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL4, lbl); }
    public void SetChannel5Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL5, lbl); }
    public void SetChannel6Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL6, lbl); }
    public void SetChannel7Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL7, lbl); }
    public void SetChannel8Component( JTextField lbl) { AddVisualComponent( AMSConstants.CHANNEL8, lbl); }

    /**
     * Initialize instance
     * @param strAddress
     * Device address
     * @param bEnableCS
     * Usage of checksumms
     * @return 
     * List of initialize commands to transmit
     */
    @Override
    public ArrayList initialize( String strAddress, boolean bEnableCS) {
        ArrayList lstInitCmds = new ArrayList();
        
        SetAddress( strAddress);
        SetCSEnabled( bEnableCS);
        
        //baudrate initialization
        String strInitBaudRate = null;
        switch( theApp.GetSettings().GetCOMPortSettings().GetBaudRate()) {
            case 57600:  strInitBaudRate = "09"; break;
            case 115200: strInitBaudRate = "0A"; break;
        }
                
        if( strInitBaudRate == null) {
            logger.fatal( "В настройках программы указан baudrate=" + theApp.GetSettings().GetCOMPortSettings().GetBaudRate());
            logger.fatal( "Однако этот baudrate не поддерживается классом работы с блоком ADAM-4017+");
            return lstInitCmds;
        }
        
        //CS initialisation
        String strCSpart;
        if( GetCSEnabled())
            strCSpart = "C0";
        else
            strCSpart = "80";
        
        String strCmd = "%" + strAddress + strAddress + "09" + strInitBaudRate + strCSpart;
        /*if( strAddress.equals( "11"))
            strCmd = "%" + strAddress + strAddress + "09" + strInitBaudRate + strCSpart;*/
        
        if( GetCSEnabled())
            strCmd += CheckSumm.calcCheckSumm( strCmd);
        
        lstInitCmds.add( strCmd);
        
        return lstInitCmds;
    }
}
