/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.poller.adapter;


import ams.AMSApp;
import ams.AMSConstants;
import ams.AMSDeviceManager;
import ams.devices.Adam4017plus;
import ams.devices.Adam4024;
import ams.devices.Adam4068;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import org.apache.log4j.Logger;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import javax.swing.JTextField;

/**
 *
 * @author control
 */
public class PollerAdapter implements Runnable {
    static Logger logger = Logger.getLogger( PollerAdapter.class);
    
    private boolean m_bConnected;
    
    private final AMSApp theApp;
            
    private boolean m_bContinue;
    public Date dtmLastIncomingCommand;
    
    public void StopThread() {
        if( m_thread.isAlive()) {
            m_bContinue = false;
            try {
                do {
                    m_thread.join( 200);
                    logger.debug( "Пытаемся остановить поток адаптера Poller'a");
                } while( m_thread.isAlive());
            } catch (InterruptedException ex) {
                logger.debug( "InterruptedException при попытке остановить поток адаптера Poller'a", ex);
            }
        }
    }
    
    private Thread m_thread;
    public boolean GetAliveStatus() {
        if( m_thread == null) return false;
        if( !m_thread.isAlive()) return false;
        return m_bConnected;
    }
    
    public PollerAdapter( AMSApp app) {
        m_bContinue = false;
        theApp = app;        
    }
    
    public void start() {
        m_thread = new Thread( this);
        m_thread.start();
    }
    
    public void restart() {
        if( m_thread.isAlive())
            return;
        
        m_thread = new Thread( this);
        m_thread.start();
    }
    
    @Override
    public void run() {
        m_bContinue = true;
        
        ServerSocket serverSocket;
        Socket socket = null;
    
        try {
            serverSocket = new ServerSocket( theApp.GetSettings().GetPortPoller());
            serverSocket.setSoTimeout( 10001);
            
            m_bConnected = false;
            ObjectInputStream is = null;
            ObjectOutputStream os = null;
            
            do {
                if( m_bConnected) {
                    //System.err.print( ".");
                    
                    if( is != null && os != null) {
                        
                        try {
                            String strReq = ( String) is.readObject();
                            //System.err.print( ",");
                        
                            if( "REQ".equals( strReq)) {
                                //System.err.print( ":");
                                String strParam = ( String) is.readObject();
                                //System.err.print( ";\n");
                                if( strParam != null) {
                                    switch( strParam) {
                                        case "MSW.01":
                                            int nSwitch = theApp.GetMainSwitcher() ? 1 : 0;
                                            logger.trace( "'MAINSWITCH' requested. Responding with " + nSwitch);
                                            os.writeObject( "RESP");
                                            os.writeObject( "MSW.01");
                                            os.writeObject( nSwitch);
                                            os.flush();
                                        break;
                                        case "REG.01": {
                                            int nRegime = theApp.GetRegime();
                                            logger.trace( "'REGIME' requested. Responding with " + nRegime);
                                            os.writeObject( "RESP");
                                            os.writeObject( "REG.01");
                                            os.writeObject( nRegime);
                                            os.flush();
                                        }
                                        break;
                                        case "PRE.01": {
                                            int nPreset = theApp.GetOuterCurrent();
                                            logger.trace( "'PRESET' requested. Responding with " + nPreset);
                                            os.writeObject( "RESP");
                                            os.writeObject( "PRE.01");
                                            os.writeObject( nPreset);
                                            os.flush();
                                        }
                                        break;
                                        
                                        
                                        //Состояния каналов (группа параметров с 01 на конце)
                                        case "L1A.01":
                                        case "L1T.01":
                                        case "L2A.01":
                                        case "L2T.01":
                                        case "L3A.01":
                                        case "L3T.01":
                                        case "L4A.01":
                                        case "L4T.01":
                                        case "L5A.01":
                                        case "L5T.01":
                                        case "L6A.01":
                                        case "L6T.01":
                                        case "L7A.01":
                                        case "L7T.01":
                                        case "L8A.01":
                                        case "L8T.01": {
                                            AMSDeviceManager mngr = null;

                                            switch( strParam) {
                                                case "L1A.01": mngr = theApp.GetDevManager_0_a(); break;
                                                case "L1T.01": mngr = theApp.GetDevManager_0_t(); break;
                                                case "L2A.01": mngr = theApp.GetDevManager_1_a(); break;
                                                case "L2T.01": mngr = theApp.GetDevManager_1_t(); break;
                                                case "L3A.01": mngr = theApp.GetDevManager_2_a(); break;
                                                case "L3T.01": mngr = theApp.GetDevManager_2_t(); break;
                                                case "L4A.01": mngr = theApp.GetDevManager_3_a(); break;
                                                case "L4T.01": mngr = theApp.GetDevManager_3_t(); break;
                                                case "L5A.01": mngr = theApp.GetDevManager_4_a(); break;
                                                case "L5T.01": mngr = theApp.GetDevManager_4_t(); break;
                                                case "L6A.01": mngr = theApp.GetDevManager_5_a(); break;
                                                case "L6T.01": mngr = theApp.GetDevManager_5_t(); break;
                                                case "L7A.01": mngr = theApp.GetDevManager_6_a(); break;
                                                case "L7T.01": mngr = theApp.GetDevManager_6_t(); break;
                                                case "L8A.01": mngr = theApp.GetDevManager_7_a(); break;
                                                case "L8T.01": mngr = theApp.GetDevManager_7_t(); break;
                                            }

                                            if( mngr != null) {
                                                Adam4068 rel = mngr.GetRelay(); 
                                                int nRelay = rel.GetRelayBitState( mngr.GetRelayChannel());
                                                logger.trace( "'" + strParam + "' requested. Responding with " + nRelay);
                                                os.writeObject( "RESP");
                                                os.writeObject( strParam);
                                                os.writeObject( nRelay);
                                                os.flush();
                                            }
                                            else {
                                                logger.error( "Не найден менеджер канала для '" + strParam + "'");
                                            }
                                        }
                                        break;
                                    
                                        
                                        //Токи каналов (группа параметров с 02 на конце)
                                        case "L1A.02":
                                        case "L1T.02":
                                        case "L2A.02":
                                        case "L2T.02":
                                        case "L3A.02":
                                        case "L3T.02":
                                        case "L4A.02":
                                        case "L4T.02":
                                        case "L5A.02":
                                        case "L5T.02":
                                        case "L6A.02":
                                        case "L6T.02":
                                        case "L7A.02":
                                        case "L7T.02":
                                        case "L8A.02":
                                        case "L8T.02": {
                                            AMSDeviceManager mngr = null;

                                            switch( strParam) {
                                                case "L1A.02": mngr = theApp.GetDevManager_0_a(); break;
                                                case "L1T.02": mngr = theApp.GetDevManager_0_t(); break;
                                                case "L2A.02": mngr = theApp.GetDevManager_1_a(); break;
                                                case "L2T.02": mngr = theApp.GetDevManager_1_t(); break;
                                                case "L3A.02": mngr = theApp.GetDevManager_2_a(); break;
                                                case "L3T.02": mngr = theApp.GetDevManager_2_t(); break;
                                                case "L4A.02": mngr = theApp.GetDevManager_3_a(); break;
                                                case "L4T.02": mngr = theApp.GetDevManager_3_t(); break;
                                                case "L5A.02": mngr = theApp.GetDevManager_4_a(); break;
                                                case "L5T.02": mngr = theApp.GetDevManager_4_t(); break;
                                                case "L6A.02": mngr = theApp.GetDevManager_5_a(); break;
                                                case "L6T.02": mngr = theApp.GetDevManager_5_t(); break;
                                                case "L7A.02": mngr = theApp.GetDevManager_6_a(); break;
                                                case "L7T.02": mngr = theApp.GetDevManager_6_t(); break;
                                                case "L8A.02": mngr = theApp.GetDevManager_7_a(); break;
                                                case "L8T.02": mngr = theApp.GetDevManager_7_t(); break;
                                            }

                                            if( mngr != null) {
                                                Adam4017plus adcC = mngr.GetADCCurrent();
                                                int nAdcCchan = mngr.GetAdcCurrentChannel();
                                                JTextField jtf = ( JTextField) adcC.m_vctVisualComponents.get( nAdcCchan);
                                                if( jtf != null) {
                                                    String strCurrentValue = jtf.getText();

                                                    int nCurr = -1;
                                                    try {
                                                        nCurr = Integer.parseInt( strCurrentValue);
                                                    }
                                                    catch( NumberFormatException ex) {
                                                        logger.error( "'" + strParam + "' requested. But it's value from screen is not a number and it is ='"
                                                                + strCurrentValue + "'");
                                                        break;
                                                    }

                                                    logger.trace( "'" + strParam + "' requested. Responding with " + nCurr);
                                                    os.writeObject( "RESP");
                                                    os.writeObject( strParam);
                                                    os.writeObject( nCurr);
                                                    os.flush();
                                                }
                                                else {
                                                    //В начальные моменты времени программы мы ещё не имеем привязанных окошек вывода
                                                }
                                            }
                                            else {
                                                logger.error( "Не найден менеджер канала для '" + strParam + "'");
                                            }
                                        }
                                        break;

                                        case "VIB.01":
                                            logger.trace( "'" + strParam + "' requested. Responding with " + theApp.m_nVibration);
                                            os.writeObject( "RESP");
                                            os.writeObject( strParam);
                                            os.writeObject( theApp.m_nVibration);
                                            os.flush();
                                        break;
                                            
                                        case "LAA.01":
                                        case "LAT.01":
                                            logger.trace( "'" + strParam + "' requested. Responding with 0");
                                            os.writeObject( "RESP");
                                            os.writeObject( strParam);
                                            os.writeObject( 0);
                                            os.flush();
                                        break;
                                            
                                        //Напряжения каналов (группа параметров с 03 на конце)
                                        case "L1A.03":
                                        case "L1T.03":
                                        case "L2A.03":
                                        case "L2T.03":
                                        case "L3A.03":
                                        case "L3T.03":
                                        case "L4A.03":
                                        case "L4T.03":
                                        case "L5A.03":
                                        case "L5T.03":
                                        case "L6A.03":
                                        case "L6T.03":
                                        case "L7A.03":
                                        case "L7T.03":
                                        case "L8A.03":
                                        case "L8T.03": {
                                            AMSDeviceManager mngr = null;

                                            switch( strParam) {
                                                case "L1A.03": mngr = theApp.GetDevManager_0_a(); break;
                                                case "L1T.03": mngr = theApp.GetDevManager_0_t(); break;
                                                case "L2A.03": mngr = theApp.GetDevManager_1_a(); break;
                                                case "L2T.03": mngr = theApp.GetDevManager_1_t(); break;
                                                case "L3A.03": mngr = theApp.GetDevManager_2_a(); break;
                                                case "L3T.03": mngr = theApp.GetDevManager_2_t(); break;
                                                case "L4A.03": mngr = theApp.GetDevManager_3_a(); break;
                                                case "L4T.03": mngr = theApp.GetDevManager_3_t(); break;
                                                case "L5A.03": mngr = theApp.GetDevManager_4_a(); break;
                                                case "L5T.03": mngr = theApp.GetDevManager_4_t(); break;
                                                case "L6A.03": mngr = theApp.GetDevManager_5_a(); break;
                                                case "L6T.03": mngr = theApp.GetDevManager_5_t(); break;
                                                case "L7A.03": mngr = theApp.GetDevManager_6_a(); break;
                                                case "L7T.03": mngr = theApp.GetDevManager_6_t(); break;
                                                case "L8A.03": mngr = theApp.GetDevManager_7_a(); break;
                                                case "L8T.03": mngr = theApp.GetDevManager_7_t(); break;
                                            }

                                            if( mngr != null) {
                                                Adam4017plus adcV = mngr.GetADCVoltage();
                                                int nAdcVchan = mngr.GetAdcVoltageChannel();
                                                JTextField jtf = ( JTextField) adcV.m_vctVisualComponents.get( nAdcVchan);
                                                if( jtf != null) {
                                                    String strVoltageValue = jtf.getText();

                                                    int nVolt = -1;
                                                    try {
                                                        nVolt = Integer.parseInt( strVoltageValue);
                                                    }
                                                    catch( NumberFormatException ex) {
                                                        logger.error( "'" + strParam + "' requested. But it's value from screen is not a number and it is ='"
                                                                + strVoltageValue + "'");
                                                        break;
                                                    }

                                                    logger.trace( "'" + strParam + "' requested. Responding with " + nVolt);
                                                    os.writeObject( "RESP");
                                                    os.writeObject( strParam);
                                                    os.writeObject( nVolt);
                                                    os.flush();
                                                }
                                                else {
                                                    //В начальные моменты времени программы мы ещё не имеем привязанных окошек вывода
                                                }

                                            }
                                            else {
                                                logger.error( "Не найден менеджер канала для '" + strParam + "'");
                                            }
                                        }
                                        break;
                                    
                                        case "DAC1.1":
                                        case "DAC1.2":
                                        case "DAC1.3":
                                        case "DAC1.4": {
                                            Adam4024 dev = theApp.GetDevicesSet().GetDAC( AMSConstants.DAC1);
                                            double dblLastSetVal = 0.;
                                            switch( strParam) {
                                                case "DAC1.1": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL1); break;
                                                case "DAC1.2": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL2); break;
                                                case "DAC1.3": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL3); break;
                                                case "DAC1.4": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL4); break;
                                            }
                                            logger.trace( "'" + strParam + "' requested. Responding with " + dblLastSetVal);
                                            
                                            os.writeObject( "RESP");
                                            os.writeObject( strParam);
                                            os.writeObject( dblLastSetVal);
                                            os.flush();
                                            
                                        }
                                        break;
                                            
                                        case "DAC2.1":
                                        case "DAC2.2":
                                        case "DAC2.3":
                                        case "DAC2.4": {
                                            Adam4024 dev = theApp.GetDevicesSet().GetDAC( AMSConstants.DAC2);
                                            double dblLastSetVal = 0.;
                                            switch( strParam) {
                                                case "DAC2.1": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL1); break;
                                                case "DAC2.2": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL2); break;
                                                case "DAC2.3": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL3); break;
                                                case "DAC2.4": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL4); break;
                                            }
                                            logger.trace( "'" + strParam + "' requested. Responding with " + dblLastSetVal);
                                            
                                            os.writeObject( "RESP");
                                            os.writeObject( strParam);
                                            os.writeObject( dblLastSetVal);
                                            os.flush();
                                            
                                        }
                                        break;
                                            
                                        case "DAC3.1":
                                        case "DAC3.2":
                                        case "DAC3.3":
                                        case "DAC3.4": {
                                            Adam4024 dev = theApp.GetDevicesSet().GetDAC( AMSConstants.DAC3);
                                            double dblLastSetVal = 0.;
                                            switch( strParam) {
                                                case "DAC3.1": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL1); break;
                                                case "DAC3.2": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL2); break;
                                                case "DAC3.3": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL3); break;
                                                case "DAC3.4": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL4); break;
                                            }
                                            logger.trace( "'" + strParam + "' requested. Responding with " + dblLastSetVal);
                                            
                                            os.writeObject( "RESP");
                                            os.writeObject( strParam);
                                            os.writeObject( dblLastSetVal);
                                            os.flush();
                                            
                                        }
                                        break;
                                            
                                        case "DAC4.1":
                                        case "DAC4.2":
                                        case "DAC4.3":
                                        case "DAC4.4": {
                                            Adam4024 dev = theApp.GetDevicesSet().GetDAC( AMSConstants.DAC4);
                                            double dblLastSetVal = 0.;
                                            switch( strParam) {
                                                case "DAC4.1": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL1); break;
                                                case "DAC4.2": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL2); break;
                                                case "DAC4.3": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL3); break;
                                                case "DAC4.4": dblLastSetVal = dev.GetLastSetValue( AMSConstants.CHANNEL4); break;
                                            }
                                            logger.trace( "'" + strParam + "' requested. Responding with " + dblLastSetVal);
                                            
                                            os.writeObject( "RESP");
                                            os.writeObject( strParam);
                                            os.writeObject( dblLastSetVal);
                                            os.flush();
                                            
                                        }
                                        break;
                                            
                                        default:
                                            logger.warn( "Strange requested object: '" + strParam + "'");
                                        break;
                                    }
                                }
                                else {
                                    logger.warn( "Param (2) string is null!");
                                }
                            }
                            else {
                                logger.warn( "First (1) string is not 'REQ'!");
                            }
                        } catch( IOException ex) {
                            logger.error( "IOException caught!", ex);
                            if( is != null) { logger.info( "dp1?"); /*is.close(); is = null; */ logger.info( "dp1!");}
                            if( os != null) { logger.info( "dp2?"); /*is.close(); is = null; */ logger.info( "dp2!");}
                            if( socket != null) { logger.info( "dp3?"); socket.close(); socket = null; logger.info( "dp3!");}
                            m_bConnected = false;
                        }
                    }
                    else {
                        logger.error("One or both of streams is (are) null... Reseting connecton!");
                        if( is != null) { is.close(); is = null; }
                        if( os != null) { os.close(); os = null; }
                        if( socket!= null) { socket.close(); socket = null; }
                        m_bConnected = false;
                    }
                }
                else {
                    logger.info( "Waiting for a connection on " + theApp.GetSettings().GetPortPoller());
                    try {
                        socket = serverSocket.accept();
                    
                        logger.info( "Connection accepted! Creating streams");
                        os = new ObjectOutputStream( socket.getOutputStream());
                        is = new ObjectInputStream( socket.getInputStream());
            
                        socket.setKeepAlive( true);
                        socket.setSoLinger( true, 5);
                        
                        m_bConnected = true;
                    }
                    catch( SocketTimeoutException ex) {
                        logger.warn( "Server connection timeout! Restarting!");
                    }
                    catch( Exception ex) {
                        logger.warn( "Exception caught!", ex);
                    }
                }
            } while( true);
        
            /*
            logger.info( "Closing streams");
            is.close();
            os.close();
        
            System.out.println( "Closing socket");
            socket.close();
            */
        }
        catch( Exception ex) {
            logger.fatal( "Exception caught!", ex);
        }
        
        
    }
    
}
