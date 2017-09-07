/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import static ams.AMSSettings.logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Averager class
 * @author yaroslav
 */
public class AMSAverager {
    
    static Logger logger = Logger.getLogger( AMSAverager.class);
    
    private int m_nStatistic;
    private double m_arrData[];
    private int m_nCounter;
    private boolean m_bOverRound;
    
    /**
     * Set max amount of statistic data to accumulate
     * @param nStatistic 
     * max amount of data for average calculation
     */
    public void SetStatistic( int nStatistic) {
        
        logger.trace( "SetStatistic: in with " + nStatistic);
        
        if( nStatistic > 0 && nStatistic < 100) {
            
            int nDataWeHave;
            if( m_bOverRound)
                nDataWeHave = m_nStatistic;
            else
                nDataWeHave = m_nCounter;
            
            if( nDataWeHave > nStatistic)
                nDataWeHave = nStatistic;
            
            logger.trace( "Data we have: " + nDataWeHave);
            double [] arrNew = new double [ nStatistic];
            
            int nCnt = 0;
            for( int i = nDataWeHave-1; i >= 0; i--) {
                int nIndex = ( m_nCounter - 1 - i + m_nStatistic) % m_nStatistic;
                arrNew[ nCnt++] = m_arrData[ nIndex];
            }
            
            m_nStatistic = nStatistic;
            m_arrData = arrNew;
            
            m_nCounter = nDataWeHave;
            if( m_nCounter >= m_nStatistic) {
                m_nCounter = 0;
                m_bOverRound = true;
            }
            else
                m_bOverRound = false;
        }
    }
    
    /**
     * Get max amount of statistic data
     * @return 
     *  max amount of statistic data
     */
    public int GetStatistic() { return m_nStatistic; }
    
    
    public AMSAverager() {
        m_nStatistic = 2;
        m_arrData = new double [m_nStatistic];
        m_nCounter = 0;
        m_bOverRound = false;
    }
    
    public AMSAverager( int nStatistic) {
        m_nStatistic = nStatistic;
        m_arrData = new double [m_nStatistic];
        m_nCounter = 0;
        m_bOverRound = false;
    }
    
    /*
    public void Reset() {
        m_dblSumm = 0.;
        m_nCounter = 0;
    }
    */
    
    /**
     * Debug method... prints out accumulated data
     */
    public void dump() {
        String str = m_bOverRound ? "t[" : "f[";
        for( int i=0; i<m_nStatistic; i++) {
            if( i == m_nCounter)
                str += ">" + String.format( "%02f", m_arrData[i]) + "< ";
            else
                str += "" + String.format( "%02f", m_arrData[i]) + " ";
        }
        
        str += "]";
        logger.debug( str);
    }
    
    /**
     * Add the value to accumulated statistic<br>
     * with removing most old one (if amount is more than max)
     * @param dblVal 
     *   value to add
     */
    public void AddValue( double dblVal) {
        m_arrData[ m_nCounter++] = dblVal;
        if( m_nCounter >= m_nStatistic) {
            m_nCounter = 0;
            m_bOverRound = true;
        }
    }
    
    /**
     * Main method. Get average for accumulated statistic 
     * @return 
     * average
     */
    public double GetAverage() {
        
        if( m_nStatistic == 1)
            return m_arrData[0];
        
        double dblSumm = 0.;
        double dblN = m_bOverRound ? ( double) m_nStatistic : ( double) m_nCounter;
        for( int i = 0; i < dblN; dblSumm += m_arrData[i++]);
        double dblRetVal = dblSumm / dblN;
        
        if( Double.isNaN( dblRetVal)) {
            String strArray = "";
            for( int i = 0; i < dblN; strArray += "" + m_arrData[i++] + ", ");
        
            logger.trace( "m_arrData: " + strArray);
            logger.trace( "m_nStatistic: " + m_nStatistic);
            logger.trace( "m_bOverRound: " + m_bOverRound);
            logger.trace( "dblN: " + dblN);
            logger.trace( "dblSumm: " + dblSumm);
            logger.trace( "dblRetVal: " + dblRetVal);
        }
        
        return dblRetVal;
    }
    
    public static void main( String args[]) {
        BasicConfigurator.configure();
        logger.setLevel( Level.TRACE);
        
        logger.debug( "new object(1)");
        AMSAverager avg = new AMSAverager( 1);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "add(1)");
        avg.AddValue( 1.0);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "add(2)");
        avg.AddValue( 2.0);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "SetStatistics(2)");
        avg.SetStatistic( 2);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "add(1)");
        avg.AddValue( 1.0);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "add(2)");
        avg.AddValue( 2.0);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "SetStatistics(1)");
        avg.SetStatistic( 1);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "add(1)");
        avg.AddValue( 1.0);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "add(2)");
        avg.AddValue( 2.0);
        avg.dump(); logger.debug( "" + avg.GetAverage());
        
        logger.debug( "new object(5)");
        avg = new AMSAverager( 5);
        for( int i=0; i<100; i++) {
            double probability = Math.random() * 100;
            logger.debug( "Step: " + i + ".  Probability: " + String.format( "%02f", probability));
            if( probability > 95.) {
                logger.debug( "New averager object");
                avg = new AMSAverager( avg.GetStatistic());
            }
            else if( probability > 70.) {
                int nNewCapacity = ( int) ( 3 + Math.random() * 7.);
                logger.debug( "New averager capacity: " + nNewCapacity);
                avg.SetStatistic( nNewCapacity);
            }
            else {
                logger.debug( "New value");
                avg.AddValue( probability);
            }
            avg.dump();
            logger.debug("");
        }
    }
}
