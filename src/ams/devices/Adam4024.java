/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSApp;
import ams.AMSConstants;
import ams.serial.CheckSumm;
import ams.serial.TwoWaySerialComm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JComponent;
import org.apache.log4j.Logger;

/**
 * Class for Adam4024 (DAC) device
 * @author yaroslav
 */
public class Adam4024 extends AbstractDevice {
    private AMSApp theApp;
    static Logger logger = Logger.getLogger( Adam4024.class);

    HashMap m_mapChannelsValues;
    
    public double GetChannel1LastSetValue() { return ( ( double) m_mapChannelsValues.get( AMSConstants.CHANNEL1)); }
    public double GetChannel2LastSetValue() { return ( ( double) m_mapChannelsValues.get( AMSConstants.CHANNEL2)); }
    public double GetChannel3LastSetValue() { return ( ( double) m_mapChannelsValues.get( AMSConstants.CHANNEL3)); }
    public double GetChannel4LastSetValue() { return ( ( double) m_mapChannelsValues.get( AMSConstants.CHANNEL4)); }
    
    public double GetLastSetValue( int nChannel) {//throws Exception{
        /*if( nChannel != AMSConstants.CHANNEL1 &&
                nChannel != AMSConstants.CHANNEL2 &&
                nChannel != AMSConstants.CHANNEL3 &&
                nChannel != AMSConstants.CHANNEL4) {
            throw new Exception( "Странный номер канала '" + nChannel + "'");
        }*/
        return ( ( double) m_mapChannelsValues.get( nChannel));
    }
    
    public void SetChannel1LastSetValue( double dblNewVal) {
        if( dblNewVal < AMSConstants.DAC_MIN_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП меньше минимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL1, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        }
        else if ( dblNewVal > AMSConstants.DAC_MAX_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП больше максимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL1, AMSConstants.DAC_MAX_OUTPUT_VOLTAGE);
        }
        else
            m_mapChannelsValues.put( AMSConstants.CHANNEL1, dblNewVal);
    }
    
    public void SetChannel2LastSetValue( double dblNewVal) {
        if( dblNewVal < AMSConstants.DAC_MIN_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП меньше минимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL2, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        }
        else if ( dblNewVal > AMSConstants.DAC_MAX_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП больше максимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL2, AMSConstants.DAC_MAX_OUTPUT_VOLTAGE);
        }
        else
            m_mapChannelsValues.put( AMSConstants.CHANNEL2, dblNewVal);
    }
    
    public void SetChannel3LastSetValue( double dblNewVal) {
        if( dblNewVal < AMSConstants.DAC_MIN_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП меньше минимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL3, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        }
        else if ( dblNewVal > AMSConstants.DAC_MAX_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП больше максимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL3, AMSConstants.DAC_MAX_OUTPUT_VOLTAGE);
        }
        else
            m_mapChannelsValues.put( AMSConstants.CHANNEL3, dblNewVal);
    }
    
    public void SetChannel4LastSetValue( double dblNewVal) {
        if( dblNewVal < AMSConstants.DAC_MIN_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП меньше минимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL4, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        }
        else if ( dblNewVal > AMSConstants.DAC_MAX_OUTPUT_VOLTAGE) {
            logger.warn( "Попытка выставить на ЦАП больше максимально возможного значения!");
            m_mapChannelsValues.put( AMSConstants.CHANNEL4, AMSConstants.DAC_MAX_OUTPUT_VOLTAGE);
        }
        else
            m_mapChannelsValues.put( AMSConstants.CHANNEL4, dblNewVal);
    }    
    /**
     * Method for setting output voltage for current channel of DAC
     * @param nChannel - zero-based channel number [0-3]
     * @param dblNewVal - value to set. Before applying will be range-checked.
     */
    public void SetLastSetValue( int nChannel, double dblNewVal) throws Exception {
        switch( nChannel) {
            case AMSConstants.CHANNEL1: SetChannel1LastSetValue( dblNewVal); break;
            case AMSConstants.CHANNEL2: SetChannel2LastSetValue( dblNewVal); break;
            case AMSConstants.CHANNEL3: SetChannel3LastSetValue( dblNewVal); break;
            case AMSConstants.CHANNEL4: SetChannel4LastSetValue( dblNewVal); break;
            default:
                logger.warn( "Странный номер канала '" + nChannel + "'");
                throw new Exception( "Странный номер канала '" + nChannel + "'");
        }
    }
    
    public Adam4024( AMSApp theApp, int nDeviceDescriptor) throws Exception {
        this.theApp = theApp;
        //logger.setLevel( AMSApp.LOG_LEVEL);
        
        if( nDeviceDescriptor != AMSConstants.DAC1 &&
                nDeviceDescriptor != AMSConstants.DAC2 &&
                nDeviceDescriptor != AMSConstants.DAC3 &&
                nDeviceDescriptor != AMSConstants.DAC4) {
            throw new Exception( "Некорректный DeviceDescriptor!");
        }
        SetDeviceDescriptor( nDeviceDescriptor);
        
        m_mapChannelsValues = new HashMap( 4);
        m_mapChannelsValues.put( AMSConstants.CHANNEL1, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        m_mapChannelsValues.put( AMSConstants.CHANNEL2, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        m_mapChannelsValues.put( AMSConstants.CHANNEL3, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
        m_mapChannelsValues.put( AMSConstants.CHANNEL4, AMSConstants.DAC_MIN_OUTPUT_VOLTAGE);
    }
    
    @Override
    public void ProcessResponse( String strCmd, String strResponse) {
        logger.debug( "ProcessResponse(): Stub for the moment. TODO");
    }

    @Override
    public String GetRequestDataCommand() {
        throw new UnsupportedOperationException("GetRequestDataCommand(): Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return "";
    }

    @Override
    void AddVisualComponent(int nDescription, JComponent comp) {
        throw new UnsupportedOperationException("AddVisualComponent(): Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ProcessTimeOut(String strCmd) {
        logger.warn( "ProcessTimeOut() for command: " + strCmd);
        
        String strFirstSymb = strCmd.substring( 0, 1);
        switch( strFirstSymb) {
            case "$":
                logger.warn( "ProcessTimeOut(): Это была инициализационная команда выставки диапазона... WHAT TODO? Not implemented!");
            break;

            case "#":
                logger.warn( "ProcessTimeOut(): Это была команда выставки значения в канал... WHAT TODO? Not implemented!");
            break;

            case "%":
                logger.warn( "ProcessTimeOut(): Это была команда инициализации... WHAT TODO? Not implemented!");
            break;
                
            default:
                logger.warn( "ProcessTimeOut(): Это ещё что за команда?... WHAT TODO? Not implemented!");
            break;
        }    
    }
        
    @Override
    public ArrayList initialize( String strAddress, boolean bEnableCS) {
        ArrayList lstToReturn = new ArrayList();
        
        SetCSEnabled( bEnableCS);
        SetAddress( strAddress);
        
        //baudrate initialization
        String strInitBaudRate = null;
        switch( theApp.GetSettings().GetCOMPortSettings().GetBaudRate()) {
            case 57600:  strInitBaudRate = "09"; break;
            case 115200: strInitBaudRate = "0A"; break;
        }
                
        if( strInitBaudRate == null) {
            logger.fatal( "В настройках программы указан baudrate=" + theApp.GetSettings().GetCOMPortSettings().GetBaudRate());
            logger.fatal( "Однако этот baudrate не поддерживается классом работы с блоком ADAM-4024");
            return lstToReturn;
        }
        
        //CS initialisation
        String strCSpart;
        if( GetCSEnabled())
            strCSpart = "40";
        else
            strCSpart = "00";
        
        String strCmd = "%" + strAddress + strAddress + "00" + strInitBaudRate + strCSpart;
        
        if( GetCSEnabled())
            strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        
        //CHANNEL 0
        //set range (-10.0V;+10.0V)
        strCmd = "$" + strAddress + "7" + "C0R32";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        //set startup channel value=3.0V
        strCmd = "#" + strAddress + "S" + "C0" + "+03.000";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        logger.info( "TODO: initialize 4024. Set default value chan0?");
        
        //CHANNEL 1
        //set range (-10.0V;+10.0V)
        strCmd = "$" + strAddress + "7" + "C1R32";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        //set startup channel value=3.0V
        strCmd = "#" + strAddress + "S" + "C1" + "+03.000";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        logger.info( "TODO: initialize 4024. Set default value chan1?");
        
        //CHANNEL 2
        //set range (-10.0V;+10.0V) for channel 2
        strCmd = "$" + strAddress + "7" + "C2R32";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        //set startup channel value=3.0V
        strCmd = "#" + strAddress + "S" + "C2" + "+03.000";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        logger.info( "TODO: initialize 4024. Set default value chan2?");
        
        //CHANNEL 3
        //set range (-10.0V;+10.0V) for channel 3
        strCmd = "$" + strAddress + "7" + "C3R32";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        //set startup channel value=3.0V
        strCmd = "#" + strAddress + "S" + "C3" + "+03.000";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstToReturn.add( strCmd);
        logger.info( "TODO: initialize 4024. Set default value chan3?");
        
        return lstToReturn;
    }
    
    /**
     * Функция создания команды
     * @param nChannel  канал (0-based)
     * @param dblVal    значение (0.0, 3.0, etc.)
     * @return 
     */
    public String GetSetChannelOutputValueCommand( int nChannel, double dblVal) {
        if( nChannel != AMSConstants.CHANNEL1 &&
                nChannel != AMSConstants.CHANNEL2 &&
                nChannel != AMSConstants.CHANNEL3 &&
                nChannel != AMSConstants.CHANNEL4) {
            logger.warn( "QueueSetChannelOutputValueCommand(): канал не в диапазоне [0;4]");
            return null;
        }
    
        String strCmd = null;
        try {
            strCmd = SetChannelOutputValueCommand( nChannel, dblVal);
            SetLastSetValue( nChannel, dblVal);
        } catch( Exception e) {
            logger.error( "Отловлен Exception", e);
        }
        
        return strCmd;
    }
    
    public void QueueSetChannelOutputValueCommand( int nChannel, double dblVal) {
        String strCmd = GetSetChannelOutputValueCommand( nChannel, dblVal);
        if( strCmd != null) {
            theApp.GetRxTx().AddCommandToQueue( strCmd, this);
        }
        else {
            logger.error( "QueueSetChannelOutputValueCommand(): GetSetChannelOutputValueCommand has returned NULL!");
        }
    }
    
    private String SetChannelOutputValueCommand( int nChannel, double dblValue) {
        double dblVal = dblValue;
        
        if( dblValue < AMSConstants.DAC_MIN_OUTPUT_VOLTAGE) {
            dblVal = AMSConstants.DAC_MIN_OUTPUT_VOLTAGE;
            logger.warn( "SetChannelOutputValueCommand(): Попытка выставить на ЦАП меньше минимально возможного значения!");
        }
        
        if( dblValue > AMSConstants.DAC_MAX_OUTPUT_VOLTAGE) {
            dblVal = AMSConstants.DAC_MAX_OUTPUT_VOLTAGE;
            logger.warn( "SetChannelOutputValueCommand(): Попытка выставить на ЦАП больше максимально возможного значения!");
        }
        
        String strValue;
        Locale local = new Locale( "Locale.US");
        strValue = String.format( local, "%+07.3f", dblVal);
        
        String strCmd = "#" + GetAddress() + "C" + nChannel + strValue;
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        
        logger.debug( "SetChannelOutputValueCommand(): resultCmd='" + strCmd + "'");
        return strCmd;
    }
}