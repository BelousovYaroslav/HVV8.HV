/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import static java.lang.Thread.sleep;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class SpinnerThread implements Runnable{
    AMSApp theApp;
    MainFrame3 pParent;

    static Logger logger = org.apache.log4j.Logger.getLogger( SpinnerThread.class);
    
    private boolean m_bContinue;
    private int m_nSign;
    public void SetSign( int nNewSign) { m_nSign = nNewSign; }

    private int m_nPause;
    public void DecPause() {
        if( m_nPause > 100) m_nPause -= 100;
    }
        
    public SpinnerThread( AMSApp theApp, int sign, MainFrame3 parent) {
        //logger.setLevel( AMSApp.LOG_LEVEL);
        this.theApp = theApp;
        m_nSign = sign;
        pParent = parent;
    }
        
    public void stop() {
        m_bContinue = false;
    }
        
    @Override
    public void run() {
        m_bContinue = true;
        m_nPause = 500;
        try {
            
            do {
                theApp.SetOuterCurrent( theApp.GetOuterCurrent() + theApp.CURRENT_DISCRETE * m_nSign);
                pParent.refreshControlsState();
        
                //System.out.print( m_nPause);
                sleep( m_nPause);
            } while( m_bContinue);
            
        } catch (InterruptedException ex) {
            logger.warn( "Caught Interrupted Exception!", ex);
        }
    }
}
