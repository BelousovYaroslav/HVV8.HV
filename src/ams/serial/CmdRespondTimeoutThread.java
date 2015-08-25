/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.serial;

import ams.AMSApp;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class CmdRespondTimeoutThread extends Thread {//implements Runnable {
    static Logger logger = Logger.getLogger( CmdRespondTimeoutThread.class);
    TwoWaySerialComm m_rxtx;
    
    private boolean m_bInProgress;
    public boolean GetInProgress() { return m_bInProgress;}
    
    private int m_nTimeOut;
    public int GetTimeout() { return m_nTimeOut; }
    public void SetTimeout( int nNewTimeOut) {
        m_nTimeOut = nNewTimeOut;
    }
    
    /*
    private boolean m_bCancel;
    public void CancelTimeout() { m_bCancel = true;}
    */
    
    public CmdRespondTimeoutThread( TwoWaySerialComm rxtx) {
        m_nTimeOut = 1000;
        m_rxtx = rxtx;
        //logger.setLevel( AMSApp.LOG_LEVEL);
    }
    
    @Override
    public void run() {
        m_bInProgress = true;
        logger.trace( "Timeout thread starts!");
        
        try {
            //m_bCancel = false;
            
            Thread.sleep( m_nTimeOut);
            
            logger.debug( "Timeout happens!");
            if( m_rxtx.currentCommandInAction != null) {
                m_rxtx.currentCommandInAction.ProcessTimeOut();
                m_rxtx.currentCommandInAction = null;
            }
            else {
                logger.warn( "Произошло события таймаута, однако текущая обрабатываемая команда null!");
            }
            
        }
        catch ( InterruptedException ex) {
            logger.trace( "Timeout thread interrupted!");
        }
        
        m_bInProgress = false;
    }
    
}
