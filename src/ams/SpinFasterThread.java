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
public class SpinFasterThread implements Runnable {
    SpinnerThread parent;
    private boolean m_bContinue;
    static Logger logger = Logger.getLogger( SpinFasterThread.class);
        
    public SpinFasterThread( SpinnerThread parent) {
        //logger.setLevel( AMSApp.LOG_LEVEL);
        this.parent = parent;
    }
        
    public void stop() {
        m_bContinue = false;
    }
    
    @Override
    public void run() {

        m_bContinue = true;
        while( m_bContinue) {
            
            try {
                
                sleep( 1000);
                parent.DecPause();
                
            } catch (InterruptedException ex) {
                logger.warn( "Caught InterruptedException!", ex);
            }
            
        }
    }
}
