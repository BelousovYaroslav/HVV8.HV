/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import ams.devices.AbstractDevice;
import ams.serial.TwoWaySerialComm;
import java.util.Iterator;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class PollingThread implements Runnable {

    static Logger logger = Logger.getLogger( PollingThread.class);
    
    private boolean m_bContinue;
    private Vector vctDevices;
    private TwoWaySerialComm m_RxTx;
            
    public PollingThread( TwoWaySerialComm serial) {
        //logger.setLevel( AMSApp.LOG_LEVEL);
        vctDevices = new Vector();
        this.m_RxTx = serial;
    }
    
    public void AddPollingDevice( AbstractDevice client) {
        vctDevices.add( client);
    }
    
    public void stopThread() { m_bContinue = false; }
    
    @Override
    public void run() {
        m_bContinue = true;
        logger.info( "Started.");
        
        try {
            while( m_bContinue) {
            
                if( m_RxTx.GetCommandQueueLen() < 50) {
                    Iterator it = vctDevices.iterator();
                    while( it.hasNext()) {
                        AbstractDevice item = (AbstractDevice) it.next();
                        logger.debug( "device: " + item.GetAddress());
                    
                        String strCmd = item.GetRequestDataCommand();
                        logger.debug( "getDataRequest: " + strCmd);
                    
                        m_RxTx.AddCommandToQueue( strCmd, item);
                        Thread.sleep(100);
                    }
            
                    Thread.sleep(100);
                }
                else {
                    logger.warn( "Command queue exceeds 50!");
                }
            }
            
        }
        catch( InterruptedException ex) {
            logger.error( "Caught InterruptedException", ex);
        }
        
        logger.info( "Stopped.");
    }
    
}
