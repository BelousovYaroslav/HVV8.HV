/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSApp;
import ams.AMSConstants;
import static ams.devices.Adam4024.logger;
import ams.serial.CheckSumm;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import org.apache.log4j.Logger;

/**
 * Class for Adam4068 (RELAY) device
 * @author yaroslav
 */
public class Adam4068 extends AbstractDevice {
    private AMSApp theApp;
    
    private HashMap m_mapLines;
    private HashMap m_mapLinesFailsCounter;
    
    
    private final static int MAX_FAILS_VISUALIZATION = 5;
            
    static Logger logger = Logger.getLogger( Adam4068.class);

    public Adam4068( AMSApp theApp, int nDeviceDescriptor) throws Exception {
        this.theApp = theApp;
        
        //logger.setLevel( AMSApp.LOG_LEVEL);
        
        if( nDeviceDescriptor != AMSConstants.REL1 &&
                nDeviceDescriptor != AMSConstants.REL2) {
            throw new Exception( "Некорректный DeviceDescriptor!");
        }
        SetDeviceDescriptor( nDeviceDescriptor);

        m_mapLines = new HashMap( 8);
        m_mapLines.put( AMSConstants.CHANNEL1, false);
        m_mapLines.put( AMSConstants.CHANNEL2, false);
        m_mapLines.put( AMSConstants.CHANNEL3, false);
        m_mapLines.put( AMSConstants.CHANNEL4, false);
        m_mapLines.put( AMSConstants.CHANNEL5, false);
        m_mapLines.put( AMSConstants.CHANNEL6, false);
        m_mapLines.put( AMSConstants.CHANNEL7, false);
        m_mapLines.put( AMSConstants.CHANNEL8, false);
        
        m_mapLinesFailsCounter = new HashMap( 8);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL1, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL2, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL3, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL4, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL5, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL6, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL7, 0);
        m_mapLinesFailsCounter.put( AMSConstants.CHANNEL8, 0);
    }
    
    @Override
    public void ProcessResponse( String strCmd, String strResponse) {
        logger.trace( "ProcessResponse(): in with: strCmd='" + strCmd + "' strResponse='" + strResponse + "'");
                
        if( GetCSEnabled()) {
            
            if( CheckSumm.CheckCheckSumm( strResponse)) {
                logger.debug( "ProcessResponse(): CS control is enabled and it's correct");
            }
            else {
                logger.debug( "ProcessResponse(): CS control is enabled and it's failed");
            }
        }
        else
            logger.debug( "ProcessResponse(): CS control disabled");
        
        String strRespType = strResponse.substring( 0, 1);
        
        logger.trace( "ProcessResponse(): before switch(" + strRespType + ")");
        switch( strRespType) {
            case ">":
                ProcessSetCommand( strCmd, strResponse);
                break;
                
            case "!":
                if( strCmd.equals( GetRequestDataCommand()))
                    ProcessGetCommand( strCmd, strResponse);
                else
                    logger.warn( "Response '" + strResponse + "' to unknown command");
                break;
                
            default:
                logger.warn( "Unknown type of response: '" + strResponse + "'");
        }
        logger.trace( "ProcessResponse(): after switch");
        
        logger.debug( "ProcessResponse(): CB status: " + theApp.GetRxTx().crclBuffer.getStatus());
    }

    @Override
    public String GetRequestDataCommand() {
        String strCmd = "$" + GetAddress() + "6";
        strCmd += CheckSumm.calcCheckSumm( strCmd);
        strCmd = strCmd.toUpperCase();
        return( strCmd);
    }
    
    public void QueueRequestDataCommand() {
        String strCmd = GetRequestDataCommand();
        theApp.GetRxTx().AddCommandToQueue( strCmd, this);
    }
    
    public String GetSetRelayStateByteCommand( byte btStates) {
        String strCmd;
        if( btStates < 0x10)
            strCmd = "#" + GetAddress() + "000" + btStates;
        else
            strCmd = "#" + GetAddress() + "00" + btStates;
        
        if( GetCSEnabled())
            strCmd += CheckSumm.calcCheckSumm( strCmd);
        
        strCmd = strCmd.toUpperCase();
        return( strCmd);
    }
    
    public void QueueSetRelayStateByteCommand( byte btVal) {
        String strCmd = GetSetRelayStateByteCommand( btVal);
        theApp.GetRxTx().AddCommandToQueue( strCmd, this);
    }
    
    public String GetSetRelayStateBitCommand( int nChannel, boolean bValue) {
        String strCmd = "#" + GetAddress() + "1" + Integer.toHexString( nChannel);
        if( bValue)
            strCmd += "01";
        else
            strCmd += "00";

        if( GetCSEnabled())
            strCmd += CheckSumm.calcCheckSumm( strCmd);
        
        strCmd = strCmd.toUpperCase();
        return( strCmd);
    }
    
    public void QueueSetRelayStateBitCommand( int nChannel, boolean bVal) {
        String strCmd = GetSetRelayStateBitCommand( nChannel, bVal);
        theApp.GetRxTx().AddCommandToQueue( strCmd, this);
    }

    @Override
    public void ProcessTimeOut( String strCmd) {
        logger.error( "Timeout happens: '" + strCmd + "'");
        
        String strCmdType = strCmd.substring( 0, 1);
        
        if( "$".equals( strCmdType)) {
            logger.error( "Timeout on get (0,1)='$' command!");

            for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
                int nFailsCounter = ( int) m_mapLinesFailsCounter.get( i);
                nFailsCounter++;
                m_mapLinesFailsCounter.put( i, nFailsCounter);
                
                if( nFailsCounter >= MAX_FAILS_VISUALIZATION)
                    ( ( JLabel) m_vctVisualComponents.get( i)).setIcon( theApp.GetResources().getIconUnknown());
                
            }
        }
        else if( "#".equals( strCmdType)) {
            logger.error( "Timeout on set (0,1)='#' command!");
        }
        else {
            logger.error( "Timeout on unknown command 0-1=(" + strCmdType + ")");
        }
    }

    private void ProcessSetCommand( String strCmd, String strResponse) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ProcessGetCommand( String strCmd, String strResponse) {
        String strMarker = strResponse.substring( 0, 1);
        String strDigiOut = strResponse.substring( 1, 3);
        String strDigiIn = strResponse.substring( 4, 6);
        String strReserved = strResponse.substring( 7, 9);
        
        logger.debug( "ProcessGetCommand(): " + strMarker + "-" + strDigiOut + "-" + strDigiIn + "-" + strReserved);
        
        int state = Integer.parseInt( strDigiOut, 16);
        
        m_mapLines.put( AMSConstants.CHANNEL1, ( state & 0x01) > 0);
        m_mapLines.put( AMSConstants.CHANNEL2, ( state & 0x02) > 0);
        m_mapLines.put( AMSConstants.CHANNEL3, ( state & 0x04) > 0);
        m_mapLines.put( AMSConstants.CHANNEL4, ( state & 0x08) > 0);
        m_mapLines.put( AMSConstants.CHANNEL5, ( state & 0x10) > 0);
        m_mapLines.put( AMSConstants.CHANNEL6, ( state & 0x20) > 0);
        m_mapLines.put( AMSConstants.CHANNEL7, ( state & 0x40) > 0);
        m_mapLines.put( AMSConstants.CHANNEL8, ( state & 0x80) > 0);
        
        /*
        String strOut = "Lines: ";
        strOut += ( m_bLine0?"1":"0");
        strOut += ( m_bLine1?"1":"0");
        strOut += ( m_bLine2?"1":"0");
        strOut += ( m_bLine3?"1":"0");
        strOut += ( m_bLine4?"1":"0");
        strOut += ( m_bLine5?"1":"0");
        strOut += ( m_bLine6?"1":"0");
        strOut += ( m_bLine7?"1":"0");
        logger.debug( strOut);
        */
        
        JLabel lbl;
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++) {
            lbl = ( ( JLabel) m_vctVisualComponents.get( i));
            if( lbl != null) {
                boolean bValue = ( boolean) m_mapLines.get( i);
                if( bValue == true)
                    lbl.setIcon( theApp.GetResources().getIconOn());
                else
                    lbl.setIcon( theApp.GetResources().getIconOff());
            }
        }
        
        for( int i = AMSConstants.CHANNEL1; i <= AMSConstants.CHANNEL8; i++)
            m_mapLinesFailsCounter.put( i, 0);
        
    }

    
    @Override
    public void AddVisualComponent(int nDescription, JComponent comp) {
        m_vctVisualComponents.put( nDescription, comp);
    }
    
    public void SetChannel1Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL1, lbl); }    
    public void SetChannel2Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL2, lbl); }
    public void SetChannel3Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL3, lbl); }
    public void SetChannel4Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL4, lbl); }
    public void SetChannel5Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL5, lbl); }
    public void SetChannel6Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL6, lbl); }
    public void SetChannel7Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL7, lbl); }
    public void SetChannel8Component( JLabel lbl) { AddVisualComponent( AMSConstants.CHANNEL8, lbl); }
    
    @Override
    public ArrayList initialize( String strAddress, boolean bEnableCS) {
        ArrayList lstInitCommands = new ArrayList();
        
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
            logger.fatal( "Однако этот baudrate не поддерживается классом работы с блоком ADAM-4068");
            return lstInitCommands;
        }
        
        //CS initialisation
        String strCSpart;
        if( GetCSEnabled())
            strCSpart = "40";
        else
            strCSpart = "00";
        
        //initialization command construction
        String strCmd = "%" + strAddress + strAddress + "40" + strInitBaudRate + strCSpart;
        
        if( GetCSEnabled())
            strCmd += CheckSumm.calcCheckSumm( strCmd);
        lstInitCommands.add( strCmd);
        
        return lstInitCommands;
    }
}
