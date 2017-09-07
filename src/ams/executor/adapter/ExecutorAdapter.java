/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.executor.adapter;


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
public class ExecutorAdapter implements Runnable {
    static Logger logger = Logger.getLogger( ExecutorAdapter.class);
    
    private boolean m_bConnected;
    
    private final AMSApp theApp;
            
    private boolean m_bContinue;
    public Date dtmLastIncomingCommand;
    
    public void StopThread() {
        if( m_thread.isAlive()) {
            m_bContinue = false;
            try {
                do {
                    logger.debug( "Пытаемся остановить поток адаптера Executor'a");
                    m_thread.join( 200);
                } while( m_thread.isAlive());
            } catch (InterruptedException ex) {
                logger.debug( "InterruptedException при попытке остановить поток адаптера Poller'a", ex);
            }
            logger.debug( "Поток адаптера Executor'a остановлен");
        }
    }
    
    private Thread m_thread;
    public boolean GetAliveStatus() {
        if( m_thread == null) return false;
        if( !m_thread.isAlive()) return false;
        return m_bConnected;
    }
    
    public ExecutorAdapter( AMSApp app) {
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
            serverSocket = new ServerSocket( theApp.GetSettings().GetPortExecutor());
            serverSocket.setSoTimeout( 10001);
            
            m_bConnected = false;
            ObjectInputStream is = null;
            ObjectOutputStream os = null;
            
            do {
                if( m_bConnected) {
                    //System.err.print( ".");
                    
                    if( is != null && os != null) {
                        
                        try {
                            int nRetCode = 0;
                            String strReqId = ( String) is.readObject();
                            logger.debug( "Incoming Request ID:" + strReqId);
                            if( strReqId != null) {
                                String strCmd = ( String) is.readObject();
                                if( strCmd != null) {
                                    switch( strCmd) {
                                        case "PING":
                                            logger.debug( strReqId + ": PING: RetCode 0.");
                                            nRetCode = 0;
                                        break;

                                        case "SET":
                                            logger.debug( strReqId + ": SET");

                                            String strObject = ( String) is.readObject();
                                            if( strObject != null) {

                                                logger.debug( strReqId + ": SET: " + strObject);

                                                int nValue = ( int) is.readObject();
                                                logger.debug( strReqId + ": SET: " + strObject + ": " + nValue);

                                                if( strObject != null) {
                                                    switch( strObject) {
                                                        case "L1A":
                                                        case "L1T":
                                                        case "L2A":
                                                        case "L2T":
                                                        case "L3A":
                                                        case "L3T":
                                                        case "L4A":
                                                        case "L4T":
                                                        case "L5A":
                                                        case "L5T":
                                                        case "L6A":
                                                        case "L6T":
                                                        case "L7A":
                                                        case "L7T":
                                                        case "L8A":
                                                        case "L8T":
                                                            switch( nValue) {
                                                                case 1:
                                                                    logger.debug("Turning channel on!");
                                                                    switch( strObject) {
                                                                        case "L1A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE1); break;
                                                                        case "L1T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE1); break;
                                                                        case "L2A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE2); break;
                                                                        case "L2T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE2); break;
                                                                        case "L3A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE3); break;
                                                                        case "L3T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE3); break;
                                                                        case "L4A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE4); break;
                                                                        case "L4T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE4); break;
                                                                        case "L5A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE5); break;
                                                                        case "L5T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE5); break;
                                                                        case "L6A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE6); break;
                                                                        case "L6T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE6); break;
                                                                        case "L7A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE7); break;
                                                                        case "L7T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE7); break;
                                                                        case "L8A": theApp.m_pMainWnd.TurnOnAnoDevice( AMSConstants.T_DEVICE8); break;
                                                                        case "L8T": theApp.m_pMainWnd.TurnOnTubuDevice( AMSConstants.T_DEVICE8); break;
                                                                    }
                                                                break;
                                                                    
                                                                case 0:
                                                                    logger.debug("Turning channel off!");
                                                                    switch( strObject) {
                                                                        case "L1A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE1); break;
                                                                        case "L1T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE1); break;
                                                                        case "L2A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE2); break;
                                                                        case "L2T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE2); break;
                                                                        case "L3A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE3); break;
                                                                        case "L3T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE3); break;
                                                                        case "L4A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE4); break;
                                                                        case "L4T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE4); break;
                                                                        case "L5A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE5); break;
                                                                        case "L5T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE5); break;
                                                                        case "L6A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE6); break;
                                                                        case "L6T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE6); break;
                                                                        case "L7A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE7); break;
                                                                        case "L7T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE7); break;
                                                                        case "L8A": theApp.m_pMainWnd.TurnOffAnoDevice( AMSConstants.T_DEVICE8); break;
                                                                        case "L8T": theApp.m_pMainWnd.TurnOffTubuDevice( AMSConstants.T_DEVICE8); break;
                                                                    }
                                                                break;
                                                                    
                                                                default:
                                                                    logger.error("Wrong value for object channel. RetCode 10");
                                                                    nRetCode = 10;
                                                                break;
                                                            }
                                                        break;

                                                        case "MSW.01":
                                                            switch( nValue) {
                                                                case 1:
                                                                    logger.debug("Turning main switch on!");
                                                                    theApp.m_pMainWnd.fakeSwitchOn();
                                                                break;
                                                                
                                                                case 2:
                                                                    logger.debug("Turning main switch off!");
                                                                    theApp.m_pMainWnd.fakeSwitchOff();
                                                                break;
                                                                
                                                                default:
                                                                    logger.debug("Wrong value for object main switch. RetCode 9");
                                                                    nRetCode = 9;
                                                                break;
                                                            }
                                                        break;

                                                        case "PRE.01":
                                                            if( nValue >= 300 || nValue <= 2900) {
                                                                logger.debug("Setting preset current to " + nValue);
                                                                theApp.SetOuterCurrent( nValue);
                                                                theApp.CheckCurrentLimits();
                                                                theApp.m_pMainWnd.refreshControlsState();
                                                            }
                                                            else {
                                                                logger.error( "Wrong value for preset current=" + nValue + ". RetCode 8");
                                                                nRetCode = 8;
                                                            }
                                                        break;

                                                        case "VIB.01":
                                                            switch( nValue) {
                                                                case 1:
                                                                    logger.debug("Turning vibration on!");
                                                                    theApp.m_pMainWnd.TurnVibroOn();
                                                                break;
                                                                    
                                                                case 2:
                                                                    logger.debug("Turning vibration off!");
                                                                    theApp.m_pMainWnd.TurnVibroOff();
                                                                break;
                                                                    
                                                                default:
                                                                    logger.debug("Wrong value for object vibration. RetCode 7");
                                                                    nRetCode = 7;
                                                                break;
                                                            }
                                                        break;
                                                            
                                                        case "FAN.01":
                                                            switch( nValue) {
                                                                case 1:
                                                                    logger.debug("Turning fan on!");
                                                                    theApp.m_pMainWnd.TurnFanOn();
                                                                break;
                                                                    
                                                                case 2:
                                                                    logger.debug("Turning fan off!");
                                                                    theApp.m_pMainWnd.TurnFanOff();
                                                                break;
                                                                    
                                                                default:
                                                                    logger.debug("Wrong value for object vibration. RetCode 7");
                                                                    nRetCode = 7;
                                                                break;
                                                            }
                                                        break;

                                                        /*
                                                        public final static int REGIME_UIC = 1;
                                                        public final static int REGIME_I_EDGE = 2;
                                                        public final static int REGIME_PROCESSING = 3;
                                                        public final static int REGIME_MANUAL = 4;
                                                        */
                                                        case "REG.01":
                                                            switch( nValue) {
                                                                case 1: logger.debug("Switch to UIC regime"); break;
                                                                case 2: logger.debug("Switch to EDGE regime"); break;
                                                                case 3: logger.debug("Switch to PROCSSING regime"); break;
                                                                case 4: logger.debug("Switch to MANUAL regime"); break;
                                                                default:
                                                                    logger.debug("Wrong value for object regime. RetCode 6");
                                                                    nRetCode = 6;
                                                                break;
                                                            }
                                                        break;

                                                        default:
                                                            logger.error( "" + strReqId + ": SET: " + strObject + ". Wrong object! Retcode 5");
                                                            nRetCode = 5;
                                                        break;
                                                    }
                                                }
                                                else {
                                                    logger.error( "" + strReqId + ": SET: Object is NULL. RetCode 4");
                                                    nRetCode = 4;
                                                }
                                            }
                                        break;  //SET

                                        case "QUIT":
                                            logger.info( "QUIT processing");

                                            if( socket != null && !socket.isClosed()) is.close();    
                                            is = null;

                                            if( socket != null && !socket.isClosed()) os.close();
                                            os = null;

                                            if( socket!= null) { socket.close(); socket = null; }
                                            m_bConnected = false;
                                            //continue;
                                        break;

                                        default:
                                            logger.error( "" + strReqId + ": Unknown command '" + strCmd + "'. RetCode 3");
                                            nRetCode = 3;
                                    }
                                }
                                else {
                                    logger.error( "" + strReqId + ": Command is null. RetCode 2");
                                    nRetCode = 2;
                                }
                            }
                            else {
                                logger.error( "Request-ID is NULL. RetCode 1");
                                nRetCode = 1;
                            }

                            //RESPOND
                            logger.info( "Responding with: " + nRetCode);
                            os.writeObject( strReqId);
                            os.writeInt( nRetCode);
                            //os.writeByte( 0xFF);
                            os.flush();
                        }
                        catch( IOException ex) {
                            logger.error( "IOException caught", ex);

                            if( socket != null && !socket.isClosed())
                                    is.close();    
                            is = null;


                            if( socket != null && !socket.isClosed())
                                os.close();
                            os = null;

                            if( socket!= null) { socket.close(); socket = null; }
                            m_bConnected = false;
                        }
                        catch( Exception ex) {
                            logger.error("Exception caught!", ex);
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
                    logger.info( "Waiting for a connection on " + theApp.GetSettings().GetPortExecutor());
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
